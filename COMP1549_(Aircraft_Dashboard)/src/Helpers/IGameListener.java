/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 * @author Kacper
 * @co-author Samuel
 */
public interface IGameListener {
    public void passValue(ConstantGauges type, Double value);
    public void gameOver();//Ends game win / loss
}
