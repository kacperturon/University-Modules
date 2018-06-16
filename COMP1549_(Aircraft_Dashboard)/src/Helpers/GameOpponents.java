/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import eu.hansolo.steelseries.extras.Poi;
import eu.hansolo.steelseries.extras.Radar;
import java.awt.KeyboardFocusManager;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class GameOpponents {
    
    volatile Game game;
    volatile private Radar radar;
    volatile private Poi player;
    //0.9 - Lat & Lon max
    //Lat - Y Lon - X
    
    volatile boolean isPlayerAlive = true;
    
    public enum Type{
        rocket,
        missle
    }
    
    volatile IGameListener listener;

    volatile Map<Type, Poi> OpponentsMap;
    volatile Map<Type, Thread> OpponentsThreadMap;
    
    public GameOpponents(Game game){
        this.game = game;
        this.radar = game.getRadarGauge();
        this.player = radar.getMyLocation();
        this.listener = game.getListener();
        
    }
    
    public void startSpawning(){
        OpponentsThreadMap = new ConcurrentHashMap();
        OpponentsMap = new ConcurrentHashMap();

        new Thread(){
            @Override
            public void run(){
                while(isPlayerAlive&&game.getFuel()>0.0){
                   if(OpponentsMap.size()!=Type.values().length){
                       Type type = randomOpponentType();
                        
                        if(type!=null){
                             if(!OpponentsThreadMap.containsKey(type)){
                                 SwingUtilities.invokeLater(new Runnable() { //Inform EDT about GUI changes
                                    @Override
                                    public void run() {
                                        Thread thread = newOpponent(type);
                                 OpponentsThreadMap.put(type, thread);
                                 OpponentsThreadMap.get(type).start();
                                    }
                                });
                             }else{
                                 if(OpponentsThreadMap.get(type).getState()==Thread.State.TERMINATED){
                                     SwingUtilities.invokeLater(new Runnable() { //Inform EDT about GUI changes
                                    @Override
                                    public void run() {
                                       Thread thread = newOpponent(type);
                                     OpponentsThreadMap.replace(type, thread);
                                     OpponentsThreadMap.get(type).start();
                                    }
                                });
                                     
                                 }
                             }
                         }
                        try{
                            sleep(100);
                        }catch(Exception e){}
                   }
                }
                if(!isPlayerAlive){
                   losingScreen();
                }                
            }
        }.start();
    }
    


    
    private void losingScreen(){
         KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(game.getKeyEventDispatcher());
         int result = JOptionPane.showConfirmDialog(null, "You have lost.", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
         if(result == JOptionPane.OK_OPTION){
             listener.gameOver();
         }
         removeEnemies();
    }
    
    public void removeEnemies(){
        OpponentsMap.forEach((key,value)->{if(value!=null){
            SwingUtilities.invokeLater(new Runnable() { //Inform EDT about GUI changes
                @Override
                public void run() {
                    radar.removePoi(value);
                }
            });
        }});
        new Thread(){
            @Override
            public void run(){
                boolean threadsCleared = true;
                while(threadsCleared){
                    for (Map.Entry<Type, Thread> entry : OpponentsThreadMap.entrySet()) {
                        if(entry.getValue().getState()!=Thread.State.TERMINATED){
                            threadsCleared = false;
                        }
                    } 
                }
            }}.start();
    }
    
    public Thread newOpponent(Type type){
        Thread thread = new Thread(){
            @Override
            public void run(){
                Poi opponent = randomLocationOpponent(type);
                OpponentsMap.put(type, opponent);
                if(OpponentsMap.get(type)!=null){
                    radar.addPoi(OpponentsMap.get(type));
                }
                double[] XYSpeed = getXYSpeedTowardsPlayer(type);
                
                while(OpponentsMap.containsKey(type)&&isPlayerAlive&&game.getFuel()>0.0){

                    if(OpponentsMap.containsKey(type)){
                        moveOpponent(type, XYSpeed[0], XYSpeed[1]);
                        if(distanceToPlayer(type)>1) {
                            destroyOpponent(type);
                        }
                    }
                    
                    try {
                        this.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameOpponents.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        return thread;
    }
        
    private void destroyOpponent(Type type){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                radar.removePoi(OpponentsMap.get(type));
                OpponentsMap.remove(type);
            }
        });
    }
    
    public void moveOpponent(Type type, double xSpeed, double ySpeed){
        Poi opponent = OpponentsMap.get(type);
        Point2D opponentLocation = opponent.getLocation();
        double temperatureScaled = 20+(((2-distanceToPlayer(type))/2)*100);
        listener.passValue(ConstantGauges.Temperature, temperatureScaled);
        listener.passValue(ConstantGauges.Digital, temperatureScaled);

        opponentLocation.setLocation(opponentLocation.getX()+xSpeed,opponentLocation.getY()+ySpeed);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if(radar.getPoi(opponent.getName())!=null){
                    radar.updatePoi(opponent.getName(), opponentLocation);
                }
            }
        });
        if(distanceToPlayer(type)<0.1) isPlayerAlive=false;
    }
    
    private Poi randomLocationOpponent(Type type){
        Poi opponent;
        double playerY = player.getLat(), playerX = player.getLon();
        Random random = new Random();
        double cornerValue = 0.8; //one value always has to be set to either 0.8 or -0.8
        boolean isNegative = random.nextBoolean();
        boolean isLon = random.nextBoolean();
        double position = Math.random() * cornerValue + (cornerValue*-1);//Get value between 0.8 and -0.8
        
        if(isNegative) cornerValue*=-1;
        if(isLon){
           opponent = new Poi(type.toString(),playerX+position,playerY+cornerValue);
        }else{
           opponent = new Poi(type.toString(),playerX+cornerValue,playerY+position); 
        }
        
        return opponent;
    }
    
    private double[] getXYSpeedTowardsPlayer(Type type){
        Poi opponent = OpponentsMap.get(type);
        player = radar.getMyLocation();
        double distanceToPlayer = distanceToPlayer(type);
        double prefferedDistance = 0.1;
        double playerY = player.getLat(), playerX = player.getLon();
        
        double opponentY = opponent.getLat(), opponentX = opponent.getLon();
        double distanceRatio = prefferedDistance/distanceToPlayer;
        
        double newX = (((1-distanceRatio)*opponentX)+distanceRatio*playerX);
        double newY = (((1-distanceRatio)*opponentY)+distanceRatio*playerY);
        double newXSpeed = newX - opponentX;
        double newYSpeed = newY - opponentY ;

        double[] XY = {newXSpeed, newYSpeed }; 
        return XY;
    }
        
    private double distanceToPlayer(Type type){
        player = radar.getMyLocation();
        Poi opponent = OpponentsMap.get(type);
        double playerY = player.getLat(), playerX = player.getLon();

        double opponentY = opponent.getLat(), opponentX = opponent.getLon();
        double distance = Math.sqrt(Math.pow((opponentX - playerX),2)+ Math.pow((opponentY - playerY),2));
        return distance;
        
        
    }
    
    private Type randomOpponentType(){
       int random = (int)((Math.random()*(Type.values().length))+0);
        
       if(OpponentsMap.containsKey(Type.values()[random])) return null;
       else return Type.values()[random];
        
    } 
    
    public boolean isPlayerAlive(){
        return isPlayerAlive;
    }
    
    public void moveLeft(){
        OpponentsMap.forEach((key,value)->{
            if(value!=null){
                Point2D opponentLocation = value.getLocation();
                opponentLocation.setLocation(opponentLocation.getX()+0.1,opponentLocation.getY());
                 SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if(radar.getPoi(value.getName())!=null){
                            radar.updatePoi(value.getName(), opponentLocation);
                        }
                    }
                 });
            }
        });
    }
    
    public void moveRight(){
        OpponentsMap.forEach((key,value)->{
            if(value!=null){
                Point2D opponentLocation = value.getLocation();
                opponentLocation.setLocation(opponentLocation.getX()-0.1,opponentLocation.getY());
                 SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if(radar.getPoi(value.getName())!=null){
                            radar.updatePoi(value.getName(), opponentLocation);
                        }
                    }
                 });
            }
        });
    }
    
    public void moveUp(){
        OpponentsMap.forEach((key,value)->{
            if(value!=null){
                Point2D opponentLocation = value.getLocation();
                opponentLocation.setLocation(opponentLocation.getX(),opponentLocation.getY()+0.1);
                 SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if(radar.getPoi(value.getName())!=null){
                            radar.updatePoi(value.getName(), opponentLocation);
                        }
                    }
                 });
            }
        });
    }
    
    public void moveDown(){
        OpponentsMap.forEach((key,value)->{
            if(value!=null){
                Point2D opponentLocation = value.getLocation();
                opponentLocation.setLocation(opponentLocation.getX(),opponentLocation.getY()-0.1);
                 SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if(radar.getPoi(value.getName())!=null){
                            radar.updatePoi(value.getName(), opponentLocation);
                        }
                    }
                 });
            }
        });
    }
    
    
    
}
