/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.tools.BackgroundColor;
import eu.hansolo.steelseries.tools.ColorDef;
import eu.hansolo.steelseries.tools.FrameDesign;
import eu.hansolo.steelseries.tools.LcdColor;
import eu.hansolo.steelseries.tools.LedColor;
import eu.hansolo.steelseries.gauges.Radial;
/**
 * *
 * @author Kacper
 * @co-author Samuel
 */
public class RadialCircleGaugeComponent extends ViewComponentGauge{
     //Initial Settings for the component
    public RadialCircleGaugeComponent(double minValue, double maxValue, String title, String units){
        gauge = new Radial();//Component type
        initialSetup(minValue, maxValue, title, units);//Initial setup values
    }

    //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {
        ((Radial)gauge).setValueAnimated(value); //Cast to radial to allow animation
    }
     //Sets the indivual look of the component
    @Override
    public void setLookAndFeel() {
        gauge.setFrameDesign(FrameDesign.TILTED_BLACK);
        gauge.setBackgroundColor(BackgroundColor.BRUSHED_METAL);
        gauge.setLedColor(LedColor.GREEN_LED);
        ((Radial)gauge).setLcdColor(LcdColor.GREEN_LCD);
        gauge.setThreshold(600);
        ((Radial)gauge).setPointerColor(ColorDef.ORANGE);
    }
}
