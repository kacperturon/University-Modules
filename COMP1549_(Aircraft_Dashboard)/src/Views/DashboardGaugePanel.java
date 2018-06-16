/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

//import Helpers.UIDesigner;
import Helpers.ConstantGauges;
import Helpers.UIDesigner;
import java.awt.FlowLayout;
import Views.Components.ViewComponentGauge;
import Views.Components.ViewComponentGaugeFactory;
import Views.Components.ViewComponentGaugeFactory.ViewComponentType;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class DashboardGaugePanel{
    
    DashboardView frame;
    JPanel panel = new JPanel();
        
    ViewComponentGaugeFactory viewComponentGaugeFactory = new ViewComponentGaugeFactory();
    //Maps all the components making them easier to use.
    Map<ConstantGauges, ViewComponentGauge> viewComponentMap = new LinkedHashMap()
    {{
        put(ConstantGauges.Compass, viewComponentGaugeFactory.getViewComponent(ViewComponentType.CompassGauge, -100,100, "Compass", "Compass"));
        put(ConstantGauges.Radar, viewComponentGaugeFactory.getViewComponent(ViewComponentType.RadarGauge, 0,1, "Radar", "Radar"));
        put(ConstantGauges.FuelGauge, viewComponentGaugeFactory.getViewComponent(ViewComponentType.RadialSquareGauge, 0,40, "Fuel", "thousand litres"));
        put(ConstantGauges.Speedometer, viewComponentGaugeFactory.getViewComponent(ViewComponentType.RadialCircleGauge,0,800, "Speedometer", "KM/H"));
        put(ConstantGauges.Temperature, viewComponentGaugeFactory.getViewComponent(ViewComponentType.LinearGauge, -140,140, "Temperature", "Celcius"));
        put(ConstantGauges.Digital, viewComponentGaugeFactory.getViewComponent(ViewComponentType.DisplayComponent, -140,140, "Digital", "Digital"));
            
    }};
    
    JPanel speedAndFuelLayout = new JPanel();
    JPanel radarAndCompassLayout = new JPanel();
    JPanel temperatureGauge = new JPanel();
    
    public DashboardGaugePanel(DashboardView frame){
        this.frame = frame;
        designFrame();
    }
    
    private void designFrame() {
        panel.setLayout(new FlowLayout());
        createViewComponents();
        
        temperatureGauge.setLayout(new BoxLayout(temperatureGauge, BoxLayout.Y_AXIS)); //Sets the layout   
        radarAndCompassLayout.setLayout(new BoxLayout(radarAndCompassLayout, BoxLayout.Y_AXIS));  //Sets the layout      
        panel.add(UIDesigner.DesignPanel(frame.getFrame(), radarAndCompassLayout , Color.black));//Designs the panels
        panel.add(UIDesigner.DesignPanel(frame.getFrame(), speedAndFuelLayout, Color.BLACK));//Designs the panels
        panel.add(UIDesigner.DesignPanel(frame.getFrame(), temperatureGauge , Color.black));//Designs the panels

    }
    //Creates the panel layout
    private void createViewComponents(){
        radarAndCompassLayout.add(viewComponentMap.get(ConstantGauges.Compass));
        radarAndCompassLayout.add(viewComponentMap.get(ConstantGauges.Radar));        
        speedAndFuelLayout.add(viewComponentMap.get(ConstantGauges.FuelGauge));
        speedAndFuelLayout.add(viewComponentMap.get(ConstantGauges.Speedometer));        
        temperatureGauge.add(viewComponentMap.get(ConstantGauges.Temperature));
        temperatureGauge.add(viewComponentMap.get(ConstantGauges.Digital));
    }

    public Map<ConstantGauges, ViewComponentGauge> getViewComponentMap() {
        return viewComponentMap;// Returns the component map
    }

    public JPanel getPanel() {
        return panel;//Returns the panel
    }
        
}
