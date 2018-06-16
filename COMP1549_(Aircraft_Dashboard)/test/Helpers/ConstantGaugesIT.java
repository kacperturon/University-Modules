/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import static Helpers.ConstantGauges.Compass;
import static Helpers.ConstantGauges.Digital;
import static Helpers.ConstantGauges.FuelGauge;
import static Helpers.ConstantGauges.Radar;
import static Helpers.ConstantGauges.Speedometer;
import static Helpers.ConstantGauges.Temperature;
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
public class ConstantGaugesIT {
    
    public ConstantGaugesIT() {
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
     * Test of values method, of class ConstantGauges.
     */
   

    /**
     * Test of valueOf method, of class ConstantGauges.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "Speedometer";
        ConstantGauges expResult = Speedometer;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    @Test
    public void test1ValueOf() {
        System.out.println("valueOf");
        String name = "FuelGauge";
        ConstantGauges expResult = FuelGauge;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    @Test
    public void test2ValueOf() {
        System.out.println("valueOf");
        String name = "Temperature";
        ConstantGauges expResult = Temperature;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    @Test
    public void test3ValueOf() {
        System.out.println("valueOf");
        String name = "Digital";
        ConstantGauges expResult = Digital;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    @Test
    public void test4ValueOf() {
        System.out.println("valueOf");
        String name = "Compass";
        ConstantGauges expResult = Compass;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    @Test
    public void test5ValueOf() {
        System.out.println("valueOf");
        String name = "Radar";
        ConstantGauges expResult = Radar;
        ConstantGauges result = ConstantGauges.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    
    
}
