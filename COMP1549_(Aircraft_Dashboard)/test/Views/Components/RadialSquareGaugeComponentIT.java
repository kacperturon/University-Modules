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
public class RadialSquareGaugeComponentIT {
    
    public RadialSquareGaugeComponentIT() {
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
     * Test of setValueAnimated method, of class RadialSquareGaugeComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 43.0;
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 27.0;
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 53.0;
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 35.0;
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test4SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 23.0;
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of setLookAndFeel method, of class RadialSquareGaugeComponent.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        RadialSquareGaugeComponent instance =  new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setLookAndFeel();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
