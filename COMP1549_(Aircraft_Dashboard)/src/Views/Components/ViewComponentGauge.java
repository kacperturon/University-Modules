/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import Helpers.UIDesigner;
import eu.hansolo.steelseries.gauges.AbstractGauge;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Kacper
 * @co-author Samuel
 */
public abstract class ViewComponentGauge extends JPanel implements IViewComponentGauge{
    AbstractGauge gauge;
    ViewComponentGaugeInput inputPanel; //JPanel with label input and button
    //Sets up the inital values of the gauge
    protected void initialSetup(double minValue, double maxValue, String title, String units){
        this.setLayout(new BorderLayout());//layout
        setMinValue(minValue);//min
        setMaxValue(maxValue);//max
        gauge.setTitle(title);//title
        gauge.setUnitString(units);
        inputPanel = new ViewComponentGaugeInput(this);
        setLookAndFeel();//gui design
        add(gauge, BorderLayout.CENTER);//add to panel
    }
    
    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);//Returns the size of the component
    }
    
    @Override
    public AbstractGauge getGauge(){
        return gauge;//Returns the type of the component
    }
    
    @Override
    public void addInputValueListener(ActionListener listenForButtonPress) {
        inputPanel.button.addActionListener(listenForButtonPress);//Adds an action listener to the button
     } 
    
    @Override
    public double getInputValue() {
        return inputPanel.getInputValue();//Returns the value of the component
    }
    
    @Override
    public JPanel getInputPanel(){
        return inputPanel;//Returns this JPanel
    }

    @Override
    public void setMaxValue(double value){
        gauge.setMaxValue(value);//Sets maximum value
    }
    
    @Override
    public double getMaxValue(){
        return gauge.getMaxValue();//Gets maximum value
    }
    
    @Override
    public void setMinValue(double value){
        gauge.setMinValue(value);//Sets minimum value
    }
    
    @Override
    public double getMinValue(){
        return gauge.getMinValue();//gets minimum value
    }
    
    @Override
    public String getTitle(){
        return gauge.getTitle();//gets component title
    }
    
    public void setLookAndFeel() {
        //Do nothing
    }
    //Sets the buttons editable depending on the response.
    @Override
    public void toggleInputPanel(){
        inputPanel.input.setText("");
        inputPanel.input.setEditable(!inputPanel.input.isEditable());
        inputPanel.button.setEnabled(!inputPanel.button.isEnabled());
    }
    
    //Button panel component
    public class ViewComponentGaugeInput extends JPanel{ //Input panel for gauges
        //Added for each component (except radar)
        JTextField input = new JTextField(5);
        JButton button;
        public ViewComponentGaugeInput(ViewComponentGauge viewComponentGauge){
            setLayout(new BorderLayout());
            String label = viewComponentGauge.getTitle();
            button = new JButton(label);   
            if(!label.equals("Radar")) {//Adds a button except for radar since radar always animates
                add("Center", input);
                add("South", UIDesigner.setButton(button));
                
            }
        }
        //Gets the input value of the compent and checks its a number
        public double getInputValue() {
            try {
                return Double.parseDouble(input.getText()); 
            } catch(NumberFormatException ne) {//Error checking
                input.setText("");
                return 0;
            }
        }
    }
}
