/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Views.DashboardInputPanel.XMLAlgorithms.DOM;
import static Views.DashboardInputPanel.extraButton.Random;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
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
public class DashboardInputPanelIT {
    
    public DashboardInputPanelIT() {
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
     * Test of isXMLRadioButtonSelected method, of class DashboardInputPanel.
     */
    @Test
    public void testIsXMLRadioButtonSelected() {
        System.out.println("isXMLRadioButtonSelected");
        DashboardInputPanel.XMLAlgorithms button = DOM;
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        boolean expResult = false;
        boolean result = instance.isXMLRadioButtonSelected(button);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addButtonListener method, of class DashboardInputPanel.
     */
    @Test
    public void testAddButtonListener() {
        System.out.println("addButtonListener");
        ActionListener buttonListener = null;
        DashboardInputPanel.extraButton button = Random;
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        instance.addButtonListener(buttonListener, button);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleButton method, of class DashboardInputPanel.
     */
    @Test
    public void testToggleButton() {
        System.out.println("toggleButton");
        DashboardInputPanel.extraButton button = Random;
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        instance.toggleButton(button);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleAllButtons method, of class DashboardInputPanel.
     */
    @Test
    public void testToggleAllButtons() {
        System.out.println("toggleAllButtons");
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        instance.toggleAllButtons();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toggleAllExceptButton method, of class DashboardInputPanel.
     */
    @Test
    public void testToggleAllExceptButton() {
        System.out.println("toggleAllExceptButton");
        DashboardInputPanel.extraButton button = Random;
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        instance.toggleAllExceptButton(button);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getExtraButtonsMap method, of class DashboardInputPanel.
     */
    @Test
    public void testGetExtraButtonsMap() {
        System.out.println("getExtraButtonsMap");
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        Map<DashboardInputPanel.extraButton, JButton> expResult = instance.getExtraButtonsMap();
        Map<DashboardInputPanel.extraButton, JButton> result = instance.getExtraButtonsMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPanel method, of class DashboardInputPanel.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        DashboardInputPanel instance = new DashboardInputPanel(new DashboardView());
        JPanel expResult = instance.getPanel();
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
