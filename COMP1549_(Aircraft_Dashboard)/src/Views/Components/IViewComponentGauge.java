/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Components;

import eu.hansolo.steelseries.gauges.AbstractGauge;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * @author Kacper
 * @co-author Samuel
 */
public interface IViewComponentGauge {
    //Returns the gauge type (clock,compass,radar etc..)
    public AbstractGauge getGauge();
    //Adds the input listener which contains the threads
    public void addInputValueListener(ActionListener listenForButtonPress);
    //Returns the panel which the gauge is on
    public JPanel getInputPanel();
    //Returns the value of the gauge
    public double getInputValue();
    //Changes the maximum value of the gauge
    public void setMaxValue(double value);
    //Returns the maximum value of the gauge
    public double getMaxValue();
    //Chanes the minimum value of the gauge
    public void setMinValue(double value);
    //Returns the minimum value of the gauge
    public double getMinValue();
    //Sets the animated value for the point/dial on the gauge
    public void setValueAnimated(double value);
    //Toggles buttons on and off
    public void toggleInputPanel(); //Enabled, disabled
    //Returns the title of the gauge (clock, temperature , radar etc..)
    public String getTitle();
}
