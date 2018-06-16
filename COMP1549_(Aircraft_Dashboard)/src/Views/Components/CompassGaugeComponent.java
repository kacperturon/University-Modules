/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.extras.Compass;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class CompassGaugeComponent extends ViewComponentGauge {
      //Initial Settings for the component
    public CompassGaugeComponent(double minValue, double maxValue, String title, String units){  
        gauge = new Compass();//Component type compass
        initialSetup(minValue, maxValue, title, units);//Setup the parameters
    }
    //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {
        ((Compass)gauge).setValueAnimated(value); //Cast back to linear to changing current value
        
    }
    
}
