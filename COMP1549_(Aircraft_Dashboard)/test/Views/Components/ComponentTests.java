/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS-X55L
 */
public class ComponentTests {
//    List<ViewComponentGauge> view = new ArrayList<ViewComponentGauge>(){{
//        new LinearGaugeComponent(0, 10, "", "");
//        new RadialCircleGaugeComponent(0, 10, "", "");
//       new RadialSquareGaugeComponent(0, 10, "", "");
//       new CompassGaugeComponent(0, 10, "", "");
//        new RadarGaugeComponent(0, 10, "", "");
//         new AutomaticClockComponent(0, 10, "", "");
//         new RectangularDisplayComponent(0, 10, "", "");
//    }};
    public ComponentTests() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setValueAnimated method, of class AutomaticClockComponent.
     */
   
    @Test
    public void LinearComponentTest() {
        ViewComponentGauge views = new LinearGaugeComponent(0, 10, "test", "test");
        ComponentTest1(views);
        ComponentTest2(views);
        for(int i = 0; i < 10; i++) {
             ComponentTest3(views);
        }
       
    }
    @Test
    public void RadialCircleComponentTest() {
        ViewComponentGauge views = new RadialCircleGaugeComponent(0, 10, "test", "test");
        ComponentTest1(views);
        ComponentTest2(views);
        ComponentTest3(views);
    }
    public void RadialSquareComponentTest() {
        ViewComponentGauge views = new RadialSquareGaugeComponent(0, 10, "test", "test");
        ComponentTest1(views);
        ComponentTest2(views);        
        ComponentTest3(views);
    }
    public void CompassComponentTest() {
        ViewComponentGauge views = new CompassGaugeComponent(0, 10, "test", "test");
        ComponentTest1(views);
        ComponentTest2(views);        
        ComponentTest3(views);
    }
    public void RadarComponentTest() {
        ViewComponentGauge views = new RadarGaugeComponent(0, 10, "test", "test");
        ComponentTest1(views);
        ComponentTest2(views);
        ComponentTest3(views);
    }

    @Test
    public void RectangularComponentTest() {
         ViewComponentGauge views = new RectangularDisplayComponent(0, 10, "test", "test");
         ComponentTest1(views);
         ComponentTest2(views);
         ComponentTest3(views);
    }
   
    public void ComponentTest1(ViewComponentGauge views) {
        System.out.println(views);
        ViewComponentGauge expResult = views;
        assertEquals(expResult,views);
    }
    public void ComponentTest2(ViewComponentGauge views) {
        System.out.println("setValueAnimated");
        double value = 0.0;
        views.setValueAnimated(value);
        double expResult = 0.0;
        assertEquals(expResult,views.getGauge().getValue(),0);
    }
    public void ComponentTest3(ViewComponentGauge views)
    {
        double init = new Random().nextDouble();
        views.setMaxValue(init);
        double expResult = views.getMaxValue();
        System.out.println("Test: " + init + " expResult: " + expResult);
        assertEquals(expResult,init,1);
        init = new Random().nextDouble();
        views.setMinValue(init);
        expResult = views.getMinValue();
        System.out.println("Test: " + init + " expResult: " + expResult);
        assertEquals(expResult,init,1);        
    }
    
    
}
