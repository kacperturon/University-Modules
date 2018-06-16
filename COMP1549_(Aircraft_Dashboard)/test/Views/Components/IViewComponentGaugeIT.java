/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.gauges.AbstractGauge;
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
public class IViewComponentGaugeIT {
    
    public IViewComponentGaugeIT() {
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
     * Test of getGauge method, of class IViewComponentGauge.
     */
    @Test
    public void testGetGauge() {
        System.out.println("getGauge");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        AbstractGauge expResult = null;
        AbstractGauge result = instance.getGauge();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addInputValueListener method, of class IViewComponentGauge.
     */
    @Test
    public void testAddInputValueListener() {
        System.out.println("addInputValueListener");
        ActionListener listenForButtonPress = null;
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        instance.addInputValueListener(listenForButtonPress);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getInputPanel method, of class IViewComponentGauge.
     */
    @Test
    public void testGetInputPanel() {
        System.out.println("getInputPanel");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        JPanel expResult = null;
        JPanel result = instance.getInputPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getInputValue method, of class IViewComponentGauge.
     */
    @Test
    public void testGetInputValue() {
        System.out.println("getInputValue");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        double expResult = 0.0;
        double result = instance.getInputValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMaxValue method, of class IViewComponentGauge.
     */
    @Test
    public void testSetMaxValue() {
        System.out.println("setMaxValue");
        double value = 0.0;
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        instance.setMaxValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMaxValue method, of class IViewComponentGauge.
     */
    @Test
    public void testGetMaxValue() {
        System.out.println("getMaxValue");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        double expResult = 0.0;
        double result = instance.getMaxValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMinValue method, of class IViewComponentGauge.
     */
    @Test
    public void testSetMinValue() {
        System.out.println("setMinValue");
        double value = 0.0;
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        instance.setMinValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMinValue method, of class IViewComponentGauge.
     */
    @Test
    public void testGetMinValue() {
        System.out.println("getMinValue");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        double expResult = 0.0;
        double result = instance.getMinValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setValueAnimated method, of class IViewComponentGauge.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 0.0;
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleInputPanel method, of class IViewComponentGauge.
     */
    @Test
    public void testToggleInputPanel() {
        System.out.println("toggleInputPanel");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        instance.toggleInputPanel();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTitle method, of class IViewComponentGauge.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        IViewComponentGauge instance = new IViewComponentGaugeImpl();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class IViewComponentGaugeImpl implements IViewComponentGauge {

        public AbstractGauge getGauge() {
            return null;
        }

        public void addInputValueListener(ActionListener listenForButtonPress) {
        }

        public JPanel getInputPanel() {
            return null;
        }

        public double getInputValue() {
            return 0.0;
        }

        public void setMaxValue(double value) {
        }

        public double getMaxValue() {
            return 0.0;
        }

        public void setMinValue(double value) {
        }

        public double getMinValue() {
            return 0.0;
        }

        public void setValueAnimated(double value) {
        }

        public void toggleInputPanel() {
        }

        public String getTitle() {
            return "";
        }
    }
    
}
