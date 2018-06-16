/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.gauges.DisplayRectangular;
import java.awt.Dimension;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class RectangularDisplayComponent extends ViewComponentGauge {
     //Initial Settings for the component
     public RectangularDisplayComponent(double minValue, double maxValue, String title, String units){  
        gauge = new DisplayRectangular();//Component type
        initialSetup(minValue, maxValue, title, units);//Initial setup values
    }
     
    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);//Returns the size of the component
    }
     //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {
       ((DisplayRectangular)gauge).setValue(value);
    }
    
}
