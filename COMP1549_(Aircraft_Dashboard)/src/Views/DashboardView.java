/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Components.DashboardGamingPanel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Kacper
 * @co-author Samuel
 */
public class DashboardView {
    //Different dashboard types (game or normal)
    public enum InputPanelType{
        input,
        gaming
    }
    
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    //CardLayout inputCards = new CardLayout();
    JPanel inputPanel = new JPanel(new CardLayout());
    //Different panel types
    DashboardGaugePanel dashboardGaugePanel = new DashboardGaugePanel(this);
    DashboardInputPanel dashboardInputPanel = new DashboardInputPanel(this);
    DashboardGamingPanel dashboardGamingPanel = new DashboardGamingPanel();    
   //Basic setup of panel, starting with normal mode
    public DashboardView(){
         frame.setTitle("Aircraft dashboard simulator");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.add(dashboardGaugePanel.getPanel());

         inputPanel.add(dashboardInputPanel.getPanel(), InputPanelType.input.toString());
         dashboardGamingPanel.setCustomDesignButton1(true);
         dashboardGamingPanel.setCustomDesignButton2(true);
         dashboardGamingPanel.setCustomDesignButton3(true);
         dashboardGamingPanel.setCustomDesignButton4(true);
         inputPanel.add(dashboardGamingPanel, InputPanelType.gaming.toString());
         panel.add(inputPanel);

         frame.setLayout(new FlowLayout());
         frame.add(panel);
         frame.pack();
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
    }
    
    public void switchInputPanel(InputPanelType type){
        ((CardLayout)inputPanel.getLayout()).show(inputPanel, type.toString());//Seitch between game mode and normal
    }
    
    public JFrame getFrame() {
        return frame;//Returns the frame
    }

    public DashboardGaugePanel getDashboardGaugePanel() {
        return dashboardGaugePanel;//Returns the component panel
    }

    public DashboardInputPanel getDashboardInputPanel() {
        return dashboardInputPanel;//Returns the dashboard button panel
    }
    
    public DashboardGamingPanel getDashboardGamingPanel(){
        return dashboardGamingPanel;//Returns the gaming panel
    }
}
