/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Components.DashboardGamingPanel;
import static Helpers.GameOpponents.Type.missle;
import static Helpers.GameOpponents.Type.rocket;
import Views.Components.RadarGaugeComponent;
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
public class GameOpponentsIT {
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
        Game game = new Game(radar,igame,gaming);
    public GameOpponentsIT() {
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
     * Test of startSpawning method, of class GameOpponents.
     */
    @Test
    public void testStartSpawning() {
        System.out.println("startSpawning");
        GameOpponents instance = new GameOpponents(game);
        instance.startSpawning();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isPlayerAlive method, of class GameOpponents.
     */
    @Test
    public void testIsPlayerAlive() {
        System.out.println("isPlayerAlive");
        GameOpponents instance = new GameOpponents(game);
        boolean expResult = true;
        boolean result = instance.isPlayerAlive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of newOpponent method, of class GameOpponents.
     */
    @Test
    public void testNewOpponent() {
         System.out.println("moveOpponent");
        GameOpponents.Type type =missle;
        double xSpeed = 0.0;
        double ySpeed = 7.0;
        GameOpponents instance = new GameOpponents(game);
        instance.startSpawning(); 
        instance.newOpponent(type);
        instance.moveOpponent(type, xSpeed, ySpeed);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of moveOpponent method, of class GameOpponents.
     */
    @Test
    public void testMoveOpponent() {
        System.out.println("moveOpponent");
        GameOpponents instance = new GameOpponents(game);
        GameOpponents.Type type = rocket;
        double xSpeed = 0.0;
        double ySpeed = 7.0;
         instance.newOpponent(type);
        instance.moveOpponent(type, xSpeed, ySpeed);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
