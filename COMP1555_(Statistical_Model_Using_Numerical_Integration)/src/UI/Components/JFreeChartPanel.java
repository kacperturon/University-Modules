/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Kacper
 */
public class JFreeChartPanel extends JPanel {

    private final XYPlot plot;
    double mean = 0.0, sd = 1.0;

    XYSeries area = new XYSeries("area"); //Contains coordinates for shaded parts of the graph
    XYDifferenceRenderer areaRenderer = new XYDifferenceRenderer(); //Renderer for shading the area

    Color none = new Color(0, 0, 0, 0);
    Color teal = new Color(0, 128, 128, 255);

    XYDataset dataset = initDataset(); //Dataset for the chart
    NumberAxis domain; //Domain is constantly updated depending on standard deviation

    public JFreeChartPanel() {
        JFreeChart chart = ChartFactory.createXYLineChart( //Generate chart
                "Normal Distribution",
                "X",
                "PDF",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        plot = chart.getXYPlot();
        domain = (NumberAxis) plot.getDomainAxis();
        domain.setAutoRangeStickyZero(false); //Fixes the margin issue with 0
        domain.setTickUnit(new NumberTickUnit(sd)); //Spacing on X-axis should be standard deviation + mean   

        areaRenderer.setNegativePaint(none);//hide the area where the values for the second series are higher
        areaRenderer.setSeriesPaint(1, none);//hide the outline of the "difference area"
        areaRenderer.setPositivePaint(teal);
        plot.setRenderer(areaRenderer); //Set renderer to XYDifferenceRenderer for shading

        plot.setDomainAxis(domain);
        final ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel); //Add this chartPanel to JPanel
    }

    private XYDataset initDataset() { //Creates dataset variable with all the current values
        double minX = mean - (4 * sd), maxX = mean + (4 * sd);  //Minimum and Maximum values on X-axis (4 deviations)
        Function2D normal = new NormalDistributionFunction2D(mean, sd); //Create normal distribution function from mean and standard deviation
        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, minX, maxX, 120, "Normal"); //Create dataset
        area.clear(); //Clear the shaded area
        ((XYSeriesCollection) dataset).addSeries(area); //Add empty XYSeries to dataset
        return dataset;
    }

    public void clearArea() {
        area.clear();
    }

    public void shadeArea(double x, boolean isInfinity) { //Shades the area for above and below options
        area.clear();
        if (isInfinity) { //Above infinity
            area.add(x, 0); //From user input 
            area.add(mean + sd * 4, 0); //To 4th deviation
        } else { //Below infinity
            area.add(x, 0); //From user input
            area.add(mean - sd * 4, 0); //To -4th deviation
        }
    }

    public void shadeArea(double x1, double x2, boolean isBetween) { //Shades the area for between and outside options
        area.clear();
        if (isBetween) {
            area.add(x1, 0); //From user input
            area.add(x2, 0); //To user input
        } else {
            double PDF = dataset.getYValue(0, dataset.getItemCount(0) / 2); //Value that is rounded up to 2 decimal places for the mean PDF value
            area.add(mean - sd * 4.0, 0); //From -4th deviation
            area.add(x1, 0); //to user input
            area.add(x1, PDF); //above the graph
            area.add(x2, PDF); //to second user input (still above the graph)
            area.add(x2, 0); //second user input
            area.add(mean + sd * 4.0, 0); //to 4th deviation
        }

    }
    
    /* Getters & Setters */
    
    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
        plot.setDataset(dataset = initDataset()); //Update dataset
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
        domain.setTickUnit(new NumberTickUnit(sd)); //Update the steps between mean
        plot.setDataset(dataset = initDataset()); //Update dataset
    }
}
