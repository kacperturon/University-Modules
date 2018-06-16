/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.extras.Radar;
import eu.hansolo.steelseries.tools.FrameDesign;
import java.awt.Color;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class RadarGaugeComponent extends ViewComponentGauge{
    //Initial Settings for the component
    public RadarGaugeComponent(double minValue, double maxValue, String title, String units){
        gauge = new Radar();//Component type
        initialSetup(minValue, maxValue, title, units);//Initial setup values
        ((Radar)gauge).setRange(100000);
        ((Radar)gauge).setMyLocation(0, 0);
        ((Radar)gauge).animate(true); 
        
    }
    //double x =0,y=0;
    //Sets the dial/pointer value on the gauge
    @Override
    public void setValueAnimated(double value) {

       // Poi p = new Poi("a",x,0);
       //((Radar)gauge).addPoi(p);
      // System.out.println(p.getLat()+p.getLocationXY().getX()+" "+p.getLocationXY().getY()+" "+p.getLocation().getY());
      // x+=0.1; y+=0.1;
//        System.out.println(p.distanceTo(5, 5));
//        System.out.println(p.getLocation());
//        System.out.println(((Radar)gauge).getLocation());
       // gauge.setLocation(new Point(100,-800));
        
    }
     //Sets the indivual look of the component
    @Override
    public void setLookAndFeel() {
//        gauge.setTrackStart(0);
//        gauge.setTrackStop(10);
        gauge.setTrackStartColor(Color.red);
        gauge.setTrackStopColor(Color.yellow);
        gauge.setLedVisible(true);
        gauge.setTrackVisible(true);
        gauge.setFrameDesign(FrameDesign.BLACK_METAL);
    }    
}
