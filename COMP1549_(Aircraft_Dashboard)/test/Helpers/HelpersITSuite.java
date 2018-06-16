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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Sam
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({Helpers.XMLParserDOMIT.class, Helpers.GameOpponentsIT.class, Helpers.XMLParserStAxIT.class, Helpers.IGameListenerIT.class, Helpers.GaugeDatabaseModelIT.class, Helpers.IXMLValueListenerIT.class, Helpers.EnumHelperIT.class, Helpers.InputListenerIT.class, Helpers.ConstantGaugesIT.class, Helpers.GameIT.class, Helpers.UIDesignerIT.class, Helpers.IXMLParserIT.class})
public class HelpersITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
