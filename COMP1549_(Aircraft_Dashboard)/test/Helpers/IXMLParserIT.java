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
public class IXMLParserIT {
    
    public IXMLParserIT() {
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
     * Test of parseXML method, of class IXMLParser.
     */
    @Test
    public void testParseXML() throws Exception {
        System.out.println("parseXML");
        IXMLValueListener listener = new IXMLValueListener() {
            @Override
            public void retriveValue(ConstantGauges type, Double value, boolean isLast) {
            }
        };
        IXMLParser instance = new IXMLParserImpl();
        instance.parseXML(listener);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class IXMLParserImpl implements IXMLParser {

        public void parseXML(IXMLValueListener listener) throws Exception {
        }
    }
    
}
