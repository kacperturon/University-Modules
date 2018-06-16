/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Helpers.ConstantGauges;
import Helpers.Game;
import Helpers.IGameListener;
import Helpers.IXMLParser;
import Helpers.IXMLValueListener;
import Helpers.InputListener;
import Helpers.XMLParserDOM;
import Helpers.XMLParserStAx;
import Models.IModel;
import Views.Components.RadarGaugeComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Views.Components.ViewComponentGauge;
import Views.DashboardInputPanel;
import Views.DashboardInputPanel.extraButton;
import Views.DashboardView;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  * @author Kacper
 * @co-author Samuel
 */
public class DashboardController {
    
    DashboardView view;//Dashboard view instance
    Map<ConstantGauges, IModel> model;//New model map    
    Map<ConstantGauges, InputListener> inputListeners = new HashMap();//Input listeners map    
    IXMLParser parser;//XML control
    
    public DashboardController(DashboardView view, Map<ConstantGauges, IModel> model){       
        this.view = view;
        this.model = model;
    }
    
    public void assignListeners(){
        for(Map.Entry<ConstantGauges, ViewComponentGauge> entry:view.getDashboardGaugePanel().getViewComponentMap().entrySet()){
            inputListeners.put(entry.getKey(),new InputListener(entry.getValue(), model.get(entry.getKey()),entry.getKey()));
        }
        /* Below defined what all extra buttons do */
       assignExtraButtonListeners();

    }
    
    private void assignExtraButtonListeners(){
         view.getDashboardInputPanel().addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputListeners.forEach((key,value)->value.toggleRandom());
                view.getDashboardInputPanel().toggleAllExceptButton(extraButton.Random); 
            }
        }, extraButton.Random);
        
        view.getDashboardInputPanel().addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getDashboardInputPanel().isXMLRadioButtonSelected(DashboardInputPanel.XMLAlgorithms.StAx)) parser = new XMLParserStAx();
                else parser = new XMLParserDOM();
                view.getDashboardInputPanel().toggleAllButtons();
                new Thread() {
                    public void run() {
                        try {
                            parser.parseXML(
                                new IXMLValueListener() {
                                    @Override
                                    public void retriveValue(ConstantGauges type, Double value, boolean isLast) {
                                        inputListeners.get(type).startThread(value);
                                        try {
                                            Thread.sleep(120);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        if(isLast) view.getDashboardInputPanel().toggleAllButtons();
                                    }
                                });
                        } catch (Exception ex) {
                            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                 }.start();

            }
        }, extraButton.XMLParser);

        view.getDashboardInputPanel().addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputListeners.forEach((key,value)->value.reset());
            }
        }, extraButton.Reset);
        
        Game game = new Game((RadarGaugeComponent) inputListeners.get(ConstantGauges.Radar).getViewComponenent(),
        new IGameListener() {
            @Override
            public void passValue(ConstantGauges type, Double value) {
                inputListeners.get(type).startThread(value);
            }
            @Override
            public void gameOver(){
                inputListeners.forEach((key,value)->value.reset());
                view.switchInputPanel(DashboardView.InputPanelType.input);
            }
         },view.getDashboardGamingPanel());
    
        view.getDashboardInputPanel().addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.switchInputPanel(DashboardView.InputPanelType.gaming);
                game.startGame();
                game.getOpponents().startSpawning();
            }
        }, extraButton.Game);
    }
    
}
