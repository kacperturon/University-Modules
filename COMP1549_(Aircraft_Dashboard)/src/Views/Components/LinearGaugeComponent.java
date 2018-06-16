/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;


import eu.hansolo.steelseries.gauges.Linear;

import eu.hansolo.steelseries.tools.BackgroundColor;
import eu.hansolo.steelseries.tools.ColorDef;
import java.awt.Dimension;


/**
 * @author Kacper
 * @co-author Samuel
 */
public class LinearGaugeComponent extends ViewComponentGauge {
     //Initial Settings for the component
    public LinearGaugeComponent(double minValue, double maxValue, String title, String units){  
        gauge = new Linear();//Component type
        initialSetup(minValue, maxValue, title, units);//Initial setup values
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(150, 350);//Returns the size of the component
    }
   //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {
        ((Linear)gauge).setValueAnimated(value); //Cast back to linear to changing current value
    }  
    //Sets the indivual look of the component
    @Override
    public void setLookAndFeel() {
        gauge.setBackgroundColor(BackgroundColor.BEIGE);        
        ((Linear)gauge).setLcdVisible(false);
        ((Linear)gauge).setValueColor(ColorDef.ORANGE);
        gauge.setLedVisible(false);
    }
}
