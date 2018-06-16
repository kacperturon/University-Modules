/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class RadarGaugeComponentIT {
    
    public RadarGaugeComponentIT() {
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
     * Test of setValueAnimated method, of class RadarGaugeComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 87.0;
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value =45.0;
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 19.0;
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 31.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 26.0;
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test4SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 65.0;
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of setLookAndFeel method, of class RadarGaugeComponent.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        RadarGaugeComponent instance = new RadarGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setLookAndFeel();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
