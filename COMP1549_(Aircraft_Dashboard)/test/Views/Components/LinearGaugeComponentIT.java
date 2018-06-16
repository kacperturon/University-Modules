/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import java.awt.Dimension;
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
public class LinearGaugeComponentIT {
    
    public LinearGaugeComponentIT() {
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
     * Test of getPreferredSize method, of class LinearGaugeComponent.
     */
    @Test
    public void testGetPreferredSize() {
        System.out.println("getPreferredSize");
        LinearGaugeComponent instance = new LinearGaugeComponent(17.0, 91.0 ,"title", "title");
        Dimension expResult = new Dimension(150, 350);
        Dimension result = instance.getPreferredSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setValueAnimated method, of class LinearGaugeComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 20.0;
        LinearGaugeComponent instance = new LinearGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 50.0;
        LinearGaugeComponent instance = new LinearGaugeComponent(28.0, 58.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 80.0;
        LinearGaugeComponent instance = new LinearGaugeComponent(20.0, 78.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 90.0;
        LinearGaugeComponent instance = new LinearGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of setLookAndFeel method, of class LinearGaugeComponent.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        LinearGaugeComponent instance = new LinearGaugeComponent(20.0, 91.0 ,"title", "title");
        instance.setLookAndFeel();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
