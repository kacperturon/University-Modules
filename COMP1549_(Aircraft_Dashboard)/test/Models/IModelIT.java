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
public class IModelIT {
    
    public IModelIT() {
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
     * Test of getValue method, of class IModel.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        IModel instance = new IModelImpl();
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setValue method, of class IModel.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        double value = 0.0;
        IModel instance = new IModelImpl();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class IModelImpl implements IModel {

        public double getValue() {
            return 0.0;
        }

        public void setValue(double value) {
        }
    }
    
}
