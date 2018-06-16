/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Helpers.ConstantGauges;
import Helpers.InputListener;
import Models.IModel;
import Views.DashboardView;
import java.util.HashMap;
import java.util.Map;
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
public class DashboardControllerIT {
        DashboardView view;
    Map<ConstantGauges, IModel> model;
    
    Map<ConstantGauges, InputListener> inputListeners = new HashMap();
    public DashboardControllerIT() {
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
     * Test of assignListeners method, of class DashboardController.
     */
    @Test
    public void testAssignListeners() {
        System.out.println("assignListeners");
        try {
        DashboardController instance = new DashboardController(new DashboardView(),model);
        instance.assignListeners();
        } catch(Exception e) {
            
        }
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
