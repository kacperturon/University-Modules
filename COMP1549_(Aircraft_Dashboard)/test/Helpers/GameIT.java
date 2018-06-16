/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Components.DashboardGamingPanel;
import Views.Components.RadarGaugeComponent;
import eu.hansolo.steelseries.extras.Radar;
import eu.hansolo.steelseries.gauges.AbstractGauge;
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
public class GameIT {
    RadarGaugeComponent radar = new RadarGaugeComponent(10, 100, "title", "title");
        IGameListener igame = new IGameListener() {
            @Override
            public void passValue(ConstantGauges type, Double value) {
               
            }
            @Override
            public void gameOver(){
            }
        };
        DashboardGamingPanel gaming = new DashboardGamingPanel();
    public GameIT() {
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
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");        
        Game instance = new Game(radar,igame, gaming);
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getOpponents method, of class Game.
     */
    @Test
    public void testGetOpponents() {
        System.out.println("getOpponents");        
        Game instance = new Game(radar,igame,gaming);
        GameOpponents expResult = instance.getOpponents();
        GameOpponents result = instance.getOpponents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFuel method, of class Game.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        Game instance = new Game(radar,igame,gaming);;
        double expResult = 0.0;
        double result = instance.getFuel();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of move method, of class Game.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Game instance = new Game(radar,igame,gaming);
        instance.move();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of movePoi method, of class Game.
     */
   

    /**
     * Test of getRadarGauge method, of class Game.
     */
    @Test
    public void testGetRadarGauge() {
        System.out.println("getRadarGauge");
        Game instance = new Game(radar,igame,gaming);
        AbstractGauge expResult = radar.getGauge();
        AbstractGauge result = instance.getRadarGauge();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getManager method, of class Game.
     */
    @Test
    public void testGetManager() {
        System.out.println("getManager");
        Game instance = new Game(radar,igame,gaming);
        GameOpponents expResult = null;
        GameOpponents result = instance.getManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getListener method, of class Game.
     */
    @Test
    public void testGetListener() {
        System.out.println("getListener");
        Game instance = new Game(radar,igame,gaming);
        IGameListener expResult = igame;
        IGameListener result = instance.getListener();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
