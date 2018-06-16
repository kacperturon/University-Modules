/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import static Helpers.ConstantGauges.FuelGauge;
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
public class IXMLValueListenerIT {
    
    public IXMLValueListenerIT() {
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
     * Test of retriveValue method, of class IXMLValueListener.
     */
    @Test
    public void testRetriveValue() {
        System.out.println("retriveValue");
        ConstantGauges type = FuelGauge;
        Double value = 3.0;
        boolean isLast = false;
        IXMLValueListener instance = new IXMLValueListenerImpl();
        instance.retriveValue(type, value, isLast);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class IXMLValueListenerImpl implements IXMLValueListener {

        public void retriveValue(ConstantGauges type, Double value, boolean isLast) {
        }
    }
    
}
