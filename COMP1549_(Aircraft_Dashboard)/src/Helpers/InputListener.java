/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Models.IModel;
import Views.Components.ViewComponentGauge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class InputListener implements ActionListener{      
        ViewComponentGauge viewComponent;
        IModel model;
        ConstantGauges gauge;
        boolean automatic = false;
        
        Thread thread;
        
        public InputListener(ViewComponentGauge viewComponent, IModel model, ConstantGauges gauge){
            this.viewComponent = viewComponent;
            this.viewComponent.addInputValueListener(this);
            this.model = model;
            this.gauge = gauge;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            startThread(viewComponent.getInputValue()); //Each time new data is sent new Thread is created and as long as the data will be sent to this thread it will be kept alive
        }
        
        public synchronized void startThread(double value) {         
            thread = new Thread(new Runnable() { //Create a new thread for this gauge
                @Override
                public void run() {
                    do{
                        if(automatic) model.setValue((int)((Math.random()*viewComponent.getMaxValue())+viewComponent.getMinValue())); //generate random value
                        else GaugeDatabaseModel.getInstance().updateModel(gauge, value); //Update database value
                        updateGUI(); //update the GUI
                        //System.out.println("Automatic: "+automatic+" Thread: "+Thread.currentThread().getName() + " Threads running: "+Thread.activeCount());
                        if(automatic){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                            }
                        }
                    }while(automatic);
                }
            });
            thread.start(); //start the thread
        }
        
        private void updateGUI(){
            SwingUtilities.invokeLater(new Runnable() { //Inform EDT about GUI changes
                @Override
                public void run() {
                     viewComponent.setValueAnimated(model.getValue());
                }
            });
        }

        public void toggleRandom(){
            automatic = !automatic;
            if(automatic){
                startThread(0);
            }
            SwingUtilities.invokeLater(new Runnable() { //We need to display our views before new Threads can be created in controller to avoid issues with Swing EDT
                public void run() {
                    if(!automatic){
                        reset();
                    }
                   // viewComponent.toggleInputPanel();
                }
            });
        }
        
        public ViewComponentGauge getViewComponenent(){
            return viewComponent;//Retrieve the component
        }
        
        public void reset(){
            startThread(0);//Stop thread
        }
        
}
