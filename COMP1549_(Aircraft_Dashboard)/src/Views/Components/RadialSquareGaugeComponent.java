/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.gauges.Radial1Square;
import eu.hansolo.steelseries.tools.FrameDesign;
import java.awt.Color;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class RadialSquareGaugeComponent extends ViewComponentGauge{
 //Initial Settings for the component
    public RadialSquareGaugeComponent(double minValue, double maxValue, String title, String units){
        gauge = new Radial1Square();//Component type
        initialSetup(minValue, maxValue, title, units);//Initial setup values
    }
    
    //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {
        ((Radial1Square)gauge).setValueAnimated(value); //Cast back to linear to changing current value
    }
     //Sets the indivual look of the component
    @Override
    public void setLookAndFeel() {
        gauge.setTrackStart(0);
        gauge.setTrackStop(10);
        gauge.setTrackStartColor(Color.red);
        gauge.setTrackStopColor(Color.yellow);
        gauge.setLedVisible(false);
        gauge.setTrackVisible(true);
        gauge.setFrameDesign(FrameDesign.BLACK_METAL);
    }
}
