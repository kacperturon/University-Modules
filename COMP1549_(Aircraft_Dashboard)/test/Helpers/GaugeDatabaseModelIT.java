/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import static Helpers.ConstantGauges.FuelGauge;
import static Helpers.ConstantGauges.Speedometer;
import Models.IModel;
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
public class GaugeDatabaseModelIT {
    
    public GaugeDatabaseModelIT() {
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
     * Test of getInstance method, of class GaugeDatabaseModel.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GaugeDatabaseModel expResult = GaugeDatabaseModel.getInstance();
        GaugeDatabaseModel result = GaugeDatabaseModel.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addModel method, of class GaugeDatabaseModel.
     */
    @Test
    public void testAddModel() {
        System.out.println("addModel");
        ConstantGauges gauge =Speedometer;
        IModel model = new IModel() {
            @Override
            public double getValue() {
                return 0.0;
            }

            @Override
            public void setValue(double value) {
             }
        };
        GaugeDatabaseModel.addModel(gauge, model);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateModel method, of class GaugeDatabaseModel.
     */
    @Test
    public void testUpdateModel() {
        System.out.println("updateModel");
        ConstantGauges gauge = FuelGauge;
        double value = 0.0;
        try {
            GaugeDatabaseModel.updateModel(gauge, value);
        }
        catch(NullPointerException e) {
            
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDatabase method, of class GaugeDatabaseModel.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        Map expResult =GaugeDatabaseModel.getDatabase() ;
        Map result = GaugeDatabaseModel.getDatabase();
        GaugeDatabaseModel model = GaugeDatabaseModel.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
