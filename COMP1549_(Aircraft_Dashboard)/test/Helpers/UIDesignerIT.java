/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
public class UIDesignerIT {
    
    public UIDesignerIT() {
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
     * Test of setButton method, of class UIDesigner.
     */
    @Test
    public void testSetButton() {
        System.out.println("setButton");
        JButton input = new JButton();
        JButton expResult = input;
        JButton result = UIDesigner.setButton(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setTextFields method, of class UIDesigner.
     */
    @Test
    public void testSetTextFields() {
        System.out.println("setTextFields");
        JTextField input = new JTextField();
        Color textColor =Color.BLUE;
        Color backGroundColor = Color.RED;
        JTextField expResult =input;
        JTextField result = UIDesigner.setTextFields(input, textColor, backGroundColor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hoverFields method, of class UIDesigner.
     */
    @Test
    public void testHoverFields() {
        System.out.println("hoverFields");
        JTextField input = new JTextField();
        UIDesigner.hoverFields(input);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of DesignPanel method, of class UIDesigner.
     */
    @Test
    public void testDesignPanel() {
        System.out.println("DesignPanel");
        JFrame frame = new JFrame();
        JPanel design = new JPanel();
        Color c = Color.RED;
        JPanel expResult =  UIDesigner.DesignPanel(frame, design, c);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of textBoxControl method, of class UIDesigner.
     */
    @Test
    public void testTextBoxControl() {
        System.out.println("textBoxControl");
        JTextField input = new JTextField();
        String text = "test";
        JTextField expResult =input;
        JTextField result = UIDesigner.textBoxControl(input, text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addDraggablePanels method, of class UIDesigner.
     */
    @Test
    public void testAddDraggablePanels() {
        System.out.println("addDraggablePanels");
        JPanel input = new JPanel();
        JFrame frame = new JFrame();
        Color c = Color.RED;
        JPanel expResult = input;
        JPanel result = UIDesigner.addDraggablePanels(input, frame, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
