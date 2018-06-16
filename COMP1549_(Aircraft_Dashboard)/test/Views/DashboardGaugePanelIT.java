/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Helpers.ConstantGauges;
import Views.Components.ViewComponentGauge;
import java.util.Map;
import javax.swing.JPanel;
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
public class DashboardGaugePanelIT {
    
    public DashboardGaugePanelIT() {
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
     * Test of getViewComponentMap method, of class DashboardGaugePanel.
     */
    @Test
    public void testGetViewComponentMap() {
        System.out.println("getViewComponentMap");
        DashboardGaugePanel instance = new DashboardGaugePanel(new DashboardView());
        Map<ConstantGauges, ViewComponentGauge> expResult = instance.getViewComponentMap();
        Map<ConstantGauges, ViewComponentGauge> result = instance.getViewComponentMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPanel method, of class DashboardGaugePanel.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        DashboardGaugePanel instance = new DashboardGaugePanel(new DashboardView());
        JPanel expResult = instance.getPanel();
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
