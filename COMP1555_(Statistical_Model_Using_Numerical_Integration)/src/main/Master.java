/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.NormalDistributionUI;

/**
 *
 * @author Kacper
 */
public class Master {

    static NormalDistributionUI ndUI; //Create the main UI reference

    public static NormalDistributionUI getNdUI() { //Get the main UI object
        return ndUI; 
    }

    public static void main(String[] args) {
        ndUI = new NormalDistributionUI(); //Create the main UI object

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ndUI.setVisible(true); //Set the UI to visible
            }
        });
    }

}
