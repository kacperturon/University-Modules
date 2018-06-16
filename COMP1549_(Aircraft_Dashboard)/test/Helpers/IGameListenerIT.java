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
public class IGameListenerIT {
    
    public IGameListenerIT() {
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
     * Test of passValue method, of class IGameListener.
     */
    @Test
    public void testPassValue() {
        System.out.println("passValue");
        ConstantGauges type = FuelGauge;
        Double value = 1.0;
        IGameListener instance = new IGameListenerImpl();
        instance.passValue(type, value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of gameOver method, of class IGameListener.
     */
    @Test
    public void testGameOver() {
        System.out.println("gameOver");
        IGameListener instance = new IGameListenerImpl();
        instance.gameOver();
        // TODO review the generated test code and remove the default call to fail.
    }

    public class IGameListenerImpl implements IGameListener {

        public void passValue(ConstantGauges type, Double value) {
        }

        public void gameOver() {
        }
    }
    
}
