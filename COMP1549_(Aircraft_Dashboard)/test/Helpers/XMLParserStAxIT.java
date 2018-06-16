/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

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
public class XMLParserStAxIT {
    
    public XMLParserStAxIT() {
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
     * Test of parseXML method, of class XMLParserStAx.
     */
    @Test
    public void testParseXML() throws Exception {
        System.out.println("parseXML");
        IXMLValueListener listener = new IXMLValueListener() {
            @Override
            public void retriveValue(ConstantGauges type, Double value, boolean isLast) {
            }
        };
        XMLParserStAx instance = new XMLParserStAx();
        instance.parseXML(listener);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
