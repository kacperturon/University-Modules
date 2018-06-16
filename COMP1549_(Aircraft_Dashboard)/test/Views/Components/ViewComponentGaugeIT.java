/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.gauges.AbstractGauge;
import java.awt.Dimension;
import java.awt.event.ActionListener;
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
public class ViewComponentGaugeIT {
    
    public ViewComponentGaugeIT() {
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
     * Test of initialSetup method, of class ViewComponentGauge.
     */
    @Test
    public void testInitialSetup() {
        System.out.println("initialSetup");
        double minValue = 0.0;
        double maxValue = 20.0;
        String title = "";
        String units = "";
        ViewComponentGauge instance = new RectangularDisplayComponent(20.0, 61.0 ,"title", "title");
        instance.initialSetup(minValue, maxValue, title, units);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPreferredSize method, of class ViewComponentGauge.
     */
    @Test
    public void testGetPreferredSize() {
        System.out.println("getPreferredSize");
        ViewComponentGauge instance = new LinearGaugeComponent(20.0, 91.0 ,"title", "title");
        Dimension expResult = new Dimension(150,350);
        Dimension result = instance.getPreferredSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getGauge method, of class ViewComponentGauge.
     */
    @Test
    public void testGetGauge() {
        System.out.println("getGauge");
        ViewComponentGauge instance = new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        AbstractGauge expResult = instance.getGauge();
        AbstractGauge result = instance.getGauge();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addInputValueListener method, of class ViewComponentGauge.
     */
    
    /**
     * Test of getInputValue method, of class ViewComponentGauge.
     */


    /**
     * Test of getInputPanel method, of class ViewComponentGauge.
     */
    @Test
    public void testGetInputPanel() {
        System.out.println("getInputPanel");
        ViewComponentGauge instance = new RadarGaugeComponent(20.0, 61.0 ,"title", "title");
        JPanel expResult = instance.getInputPanel();
        JPanel result = instance.getInputPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMaxValue method, of class ViewComponentGauge.
     */
    @Test
    public void testSetMaxValue() {
        System.out.println("setMaxValue");
        double value = 0.0;
        ViewComponentGauge instance = new CompassGaugeComponent(90, 80, "title", "title");
        instance.setMaxValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMaxValue method, of class ViewComponentGauge.
     */
    @Test
    public void testGetMaxValue() {
        System.out.println("getMaxValue");
        ViewComponentGauge instance = new CompassGaugeComponent(90, 80, "title", "title");
        double expResult = 360.0;
        double result = instance.getMaxValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMinValue method, of class ViewComponentGauge.
     */
    @Test
    public void testSetMinValue() {
        System.out.println("setMinValue");
        double value = 60.0;
        ViewComponentGauge instance = new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.setMinValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMinValue method, of class ViewComponentGauge.
     */
    @Test
    public void testGetMinValue() {
        System.out.println("getMinValue");
        ViewComponentGauge instance =  new LinearGaugeComponent(20.0, 91.0 ,"title", "title");
        double expResult = 20.0;
        double result = instance.getMinValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTitle method, of class ViewComponentGauge.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        ViewComponentGauge instance = new RectangularDisplayComponent(20.0, 61.0 ,"title", "title");
        String expResult = "title";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLookAndFeel method, of class ViewComponentGauge.
     */
    @Test
    public void testSetLookAndFeel() {
        System.out.println("setLookAndFeel");
        ViewComponentGauge instance =  new CompassGaugeComponent(90, 80, "title", "title");;
        instance.setLookAndFeel();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleInputPanel method, of class ViewComponentGauge.
     */
    @Test
    public void testToggleInputPanel() {
        System.out.println("toggleInputPanel");
        ViewComponentGauge instance = new RadialSquareGaugeComponent(20.0, 61.0 ,"title", "title");
        instance.toggleInputPanel();
        // TODO review the generated test code and remove the default call to fail.
    }

    public class ViewComponentGaugeImpl extends ViewComponentGauge {

        @Override
        public void setValueAnimated(double value) {
            }
    }
    
}
