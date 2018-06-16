/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.JFrame;
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
public class DashboardViewIT {
    
    public DashboardViewIT() {
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
     * Test of getFrame method, of class DashboardView.
     */
    @Test
    public void testGetFrame() {
        System.out.println("getFrame");
        DashboardView instance = new DashboardView();
        JFrame expResult = instance.getFrame();
        JFrame result = instance.getFrame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDashboardGaugePanel method, of class DashboardView.
     */
    @Test
    public void testGetDashboardGaugePanel() {
        System.out.println("getDashboardGaugePanel");
        DashboardView instance = new DashboardView();
        DashboardGaugePanel expResult = instance.getDashboardGaugePanel();
        DashboardGaugePanel result = instance.getDashboardGaugePanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDashboardInputPanel method, of class DashboardView.
     */
    @Test
    public void testGetDashboardInputPanel() {
        System.out.println("getDashboardInputPanel");
        DashboardView instance = new DashboardView();
        DashboardInputPanel expResult = instance.getDashboardInputPanel();
        DashboardInputPanel result = instance.getDashboardInputPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
