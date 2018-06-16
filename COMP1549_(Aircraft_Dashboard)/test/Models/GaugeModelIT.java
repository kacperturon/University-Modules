/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class GaugeModelIT {
    
    public GaugeModelIT() {
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
     * Test of getValue method, of class GaugeModel.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        GaugeModel instance = new GaugeModel();
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setValue method, of class GaugeModel.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        double value = 160.0;
        GaugeModel instance = new GaugeModel();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
