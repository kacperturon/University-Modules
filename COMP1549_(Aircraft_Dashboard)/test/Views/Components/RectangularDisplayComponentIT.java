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
public class RectangularDisplayComponentIT {
    
    public RectangularDisplayComponentIT() {
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
     * Test of getPreferredSize method, of class RectangularDisplayComponent.
     */
    @Test
    public void testGetPreferredSize() {
        System.out.println("getPreferredSize");
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 61.0 ,"title", "title");
        Dimension expResult = new Dimension(200,100);
        Dimension result = instance.getPreferredSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setValueAnimated method, of class RectangularDisplayComponent.
     */
    @Test
    public void testSetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 120.0;
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 161.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test1SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 92.0;
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 161.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test2SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 67.0;
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 161.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test3SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 80.0;
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 161.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void test4SetValueAnimated() {
        System.out.println("setValueAnimated");
        double value = 108.0;
        RectangularDisplayComponent instance = new RectangularDisplayComponent(20.0, 161.0 ,"title", "title");
        instance.setValueAnimated(value);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
