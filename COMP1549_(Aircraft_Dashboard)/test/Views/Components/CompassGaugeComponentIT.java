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
public class CompassGaugeComponentIT {
    
    public CompassGaugeComponentIT() {
        
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
     * Test of setValueAnimated method, of class CompassGaugeComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        int value = 45;
        CompassGaugeComponent instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        int value = 90;
        CompassGaugeComponent instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        int value = 350;
        CompassGaugeComponent instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        int value = 180;
        CompassGaugeComponent instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test4SetValueAnimated() {
        System.out.println("setValueAnimated");
        int value = 260;
        CompassGaugeComponent instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
