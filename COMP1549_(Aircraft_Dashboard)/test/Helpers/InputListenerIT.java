/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import static Helpers.ConstantGauges.Radar;
import Models.IModel;
import Views.Components.RadarGaugeComponent;
import Views.Components.ViewComponentGauge;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
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
public class InputListenerIT {
    ViewComponentGauge viewComponent = new RadarGaugeComponent(10,100,"test","test");
        IModel model = new IModel() {
        @Override
        public double getValue() {
            return 6.0;
        }

        @Override
        public void setValue(double value) {
        }
    };
        ConstantGauges gauge = Radar;
    public InputListenerIT() {
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
     * Test of actionPerformed method, of class InputListener.
     */
    

    /**
     * Test of startThread method, of class InputListener.
     */
    @Test
    public void testStartThread() {
        System.out.println("startThread");
        double value = 0.0;
        InputListener instance = new InputListener(viewComponent,model,gauge);;
        instance.startThread(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleRandom method, of class InputListener.
     */
    @Test
    public void testToggleRandom() {
        System.out.println("toggleRandom");
        InputListener instance =new InputListener(viewComponent,model,gauge);;
        instance.toggleRandom();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getViewComponenent method, of class InputListener.
     */
    @Test
    public void testGetViewComponenent() {
        System.out.println("getViewComponenent");
        InputListener instance = new InputListener(viewComponent,model,gauge);;
        ViewComponentGauge expResult = viewComponent;
        ViewComponentGauge result = instance.getViewComponenent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reset method, of class InputListener.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        InputListener instance = new InputListener(viewComponent,model,gauge);;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
