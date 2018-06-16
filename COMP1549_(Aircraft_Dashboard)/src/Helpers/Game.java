/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Components.DashboardGamingPanel;
import Views.Components.RadarGaugeComponent;
import eu.hansolo.steelseries.extras.Poi;
import eu.hansolo.steelseries.extras.Radar;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class Game {
    
    Poi player;
    RadarGaugeComponent radarComponent;
    Radar radarGauge;  
    GameOpponents manager;
    IGameListener listener;
    double fuel;
    double compassValue;
    KeyEventDispatcher keyEventDispatcher;

    DashboardGamingPanel gamingPanel;
    
    public Game(RadarGaugeComponent radar, IGameListener listener, DashboardGamingPanel gamingPanel) {
        this.listener = listener;
        this.gamingPanel = gamingPanel;
        this.radarComponent = radar;
        this.radarGauge = (Radar) radar.getGauge();
        
        gamingPanel.setButtonLeftListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 movePoi(1);
            }
        });
        gamingPanel.setButtonRightListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePoi(2);
            }
        });
        gamingPanel.setButtonUpListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePoi(3);
            }
        });
        gamingPanel.setButtonDownListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePoi(4);
            }
        });
        
    }
    
    public void startGame(){
        fuel = 40;
        compassValue = 0.0;
        player = radarGauge.getMyLocation();
        move();
        manager = new GameOpponents(this); //Pass radar
        
        listener.passValue(ConstantGauges.FuelGauge, fuel);
        listener.passValue(ConstantGauges.Temperature, 20.0);
        listener.passValue(ConstantGauges.Digital, 20.0);
        listener.passValue(ConstantGauges.Speedometer, 600.0);
        speedoMeterFuelThread();

    }
    
    private void speedoMeterFuelThread(){
        new Thread(){
            @Override
            public void run(){
               try {
                   sleep(800);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               while(fuel>0.0&&manager.isPlayerAlive()){
                   fuel-=0.7;
                   double speedScaled;
                   if(fuel>5){
                       speedScaled = 600-(((40-fuel)/40)*100)+((Math.random()*1)+10);
                   }else{
                       speedScaled = 600-(((40-fuel)/40)*600);
                   }

                   listener.passValue(ConstantGauges.FuelGauge, fuel);
                   listener.passValue(ConstantGauges.Speedometer, speedScaled);

                   try {
                       sleep(500);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
                if(fuel<0.1&&manager.isPlayerAlive()){
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
                    int result = JOptionPane.showConfirmDialog(null, "You have won.", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(result == JOptionPane.OK_OPTION){
                        listener.gameOver();
                    }
                    manager.removeEnemies();
                }
               
               //System.out.println("Thread: "+Thread.currentThread().getName() + " Threads running: "+Thread.activeCount());

            }
        }.start();
    }
    
    public void move() {
        Set<Integer> pressedKeys = new TreeSet<Integer>();

        keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent key) {
                switch (key.getID()) {
                 case KeyEvent.KEY_PRESSED:
                     if(!pressedKeys.contains(key.getKeyCode())){
                        pressedKeys.add(key.getKeyCode());
                        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
                            gamingPanel.doClickLeft();
                        }
                        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
                            gamingPanel.doClickRight();
                        }
                        if (key.getKeyCode() == KeyEvent.VK_UP) {
                            gamingPanel.doClickUp();
                        }
                        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
                            gamingPanel.doClickDown();
                        }
                     }
                     
                     break;
                 case KeyEvent.KEY_RELEASED:
                    pressedKeys.remove(key.getKeyCode());
                 }
                 return false;           
            }
        };
      KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }
    
    public void movePoi(int move) {
        Point2D playerLocation = player.getLocation();
        switch(move) {
            case 1: //Left  
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        manager.moveLeft();
                    }
                });
                compassValue+=4;
                listener.passValue(ConstantGauges.Compass, compassValue);
                break;
            case 2: //Right
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        manager.moveRight();

                    }
                });
                compassValue-=4;
                listener.passValue(ConstantGauges.Compass, compassValue);
                break;
            case 3: //Up
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        manager.moveUp();
                    }
                });
                break;
            case 4: //Down
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        manager.moveDown();
                    }
                });
                break;  
        }

    }

    public GameOpponents getOpponents(){
        return manager;
    }
    
    public double getFuel(){
        return fuel;
    }
    
    public Radar getRadarGauge() {
        return radarGauge;
    }

    public GameOpponents getManager() {
        return manager;
    }

    public IGameListener getListener() {
        return listener;
    }
    
    public KeyEventDispatcher getKeyEventDispatcher() {
        return keyEventDispatcher;
    }
   
}
