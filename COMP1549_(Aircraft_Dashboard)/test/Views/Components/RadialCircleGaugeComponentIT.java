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
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class RadialCircleGaugeComponentIT {
    
    public RadialCircleGaugeComponentIT() {
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
     * Test of setValueAnimated method, of class RadialCircleGaugeComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 23.0;
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 41.0;
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 28.0;
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 24.0;
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test4SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 78.0;
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLookAndFeel method, of class RadialCircleGaugeComponent.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        RadialCircleGaugeComponent instance = new RadialCircleGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setLookAndFeel();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
