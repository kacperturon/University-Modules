/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class ViewComponentGaugeFactory  {
    //This enum contains the different components types that the system has
    public enum ViewComponentType{
        LinearGauge,
        RadialCircleGauge,
        RadialSquareGauge,
        CompassGauge,
        RadarGauge,
        ClockComponent,
        DisplayComponent,
    }
    
    public ViewComponentGauge getViewComponent(ViewComponentType componentType,double minValue, double maxValue, String title, String units){      
        switch(componentType){
            case LinearGauge:
                return new LinearGaugeComponent(minValue, maxValue, title, units);
            case RadialCircleGauge:
                return new RadialCircleGaugeComponent(minValue, maxValue, title, units);
            case RadialSquareGauge:
                return new RadialSquareGaugeComponent(minValue, maxValue, title, units);
            case CompassGauge:
                return new CompassGaugeComponent(minValue, maxValue, title, units);
            case RadarGauge:
                return new RadarGaugeComponent(minValue,maxValue,title,units);
            case DisplayComponent:
                return new RectangularDisplayComponent(minValue,maxValue,title,units);
            default:
                return null;
        }
    }
}
