/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controllers.DashboardController;
import Helpers.GaugeDatabaseModel;
import Models.GaugeModel;
import Views.DashboardView;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class Main {
    //Main run class
    public static void main(String[] args) throws InterruptedException {
        //View
        DashboardView view = new DashboardView();
        //Model
        GaugeDatabaseModel databaseModel = GaugeDatabaseModel.getInstance();        
        view.getDashboardGaugePanel().getViewComponentMap().forEach((key,value)->databaseModel.addModel(key, new GaugeModel()));
        //Controller
        DashboardController dashboardController = new DashboardController(view, databaseModel.getDatabase());
        dashboardController.assignListeners();
        
    }
}
