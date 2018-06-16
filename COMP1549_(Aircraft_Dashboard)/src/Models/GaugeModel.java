/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class GaugeModel implements IModel {
    private double value;
    
    public GaugeModel(){
        this.value = 0.0f;//returns the value
    }
    
    public GaugeModel(double value){
        this.value = value;//Sets the value
    }

    @Override
    public double getValue() {
        return value;//Get the value
    }

    @Override
    public void setValue(double value) {
        this.value = value;//set the value
    }
}
