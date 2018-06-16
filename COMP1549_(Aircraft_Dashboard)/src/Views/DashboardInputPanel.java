/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Helpers.UIDesigner;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class DashboardInputPanel {
    //All extra utton components
    public enum extraButton{
        Random,
        XMLParser,
        Reset,
        Game
    }
    //All XML components
    public enum XMLAlgorithms{
        StAx,
        DOM
    }
    
    DashboardView frame;    
    JPanel panel = new JPanel();
    JPanel extras = new JPanel();
    //Extra buttons needed
    Map<extraButton, JButton> extraButtonsMap = new LinkedHashMap(){{
        put(extraButton.Random, new JButton("Random"));
        put(extraButton.XMLParser, new JButton("XML Parser"));
        put(extraButton.Reset, new JButton("Reset"));
        put(extraButton.Game, new JButton("Game"));

    }};
    //Extra radio buttons needed
    Map<XMLAlgorithms, JRadioButton> XMLRadioButtonsMap = new LinkedHashMap(){{
        put(XMLAlgorithms.StAx, new JRadioButton("StAx"));
        put(XMLAlgorithms.DOM, new JRadioButton("DOM"));
    }};
    //Setup input panel
    public DashboardInputPanel(DashboardView frame){
        this.frame = frame;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel gaugeInputs = new JPanel();
        frame.getDashboardGaugePanel().getViewComponentMap().forEach((key,value) -> gaugeInputs.add(value.getInputPanel())); //Add all input panels to JFrame

        extraButtonsMap.forEach((key,value)->
                {if(key==extraButton.XMLParser){
                    extras.add(XMLAlgorithmsRadioButtonPanel());
                }
                extras.add(UIDesigner.setButton(value));
                }); //Add extraButtons to the panel with set design
        
        panel.add(gaugeInputs);
        
        panel.add(extras);
    }
    
    private JPanel XMLAlgorithmsRadioButtonPanel(){ //Creates a panel with two radio buttons 
        XMLRadioButtonsMap.get(XMLAlgorithms.StAx).setSelected(true);
        ButtonGroup XMLAlgorithmsButtonGroup = new ButtonGroup();
        XMLRadioButtonsMap.forEach((key,value)->XMLAlgorithmsButtonGroup.add(value)); //Add all radio buttons to the radio group
        JPanel XMLAlgorithmsButtonGroupPanel = new JPanel();
        XMLAlgorithmsButtonGroupPanel.setLayout(new BoxLayout(XMLAlgorithmsButtonGroupPanel, BoxLayout.Y_AXIS));
        XMLRadioButtonsMap.forEach((key,value)->XMLAlgorithmsButtonGroupPanel.add(value)); //Add all JButtons to the Panel
        return XMLAlgorithmsButtonGroupPanel;
    }
    //Radio button selction opetion
    public boolean isXMLRadioButtonSelected(XMLAlgorithms button){
        return XMLRadioButtonsMap.get(button).isSelected();
    }
    //Adds action listeners to extra buttons
    public void addButtonListener(ActionListener buttonListener, extraButton button){
        extraButtonsMap.get(button).addActionListener(buttonListener);
    }
    //Toggles buttons on and off depending on use
    public void toggleButton(extraButton button){
        JButton btn = extraButtonsMap.get(button);
        btn.setEnabled(!btn.isEnabled());
    }
    //Toggles buttons on and off depending on use
    public void toggleAllButtons(){
        extraButtonsMap.forEach((key,value)-> {value.setEnabled(!value.isEnabled());});
        frame.getDashboardGaugePanel().getViewComponentMap().forEach((key,value)->value.toggleInputPanel());
    }
    //Toggles buttons on and off depending on use
    public void toggleAllExceptButton(extraButton button){
        extraButtonsMap.forEach((key,value)-> {
            if(key!=button){
                value.setEnabled(!value.isEnabled());
            }  
        });
       frame.getDashboardGaugePanel().getViewComponentMap().forEach((key,value)->value.toggleInputPanel());
    }

    public Map<extraButton, JButton> getExtraButtonsMap() {
        return extraButtonsMap;//Adds extra buttons (randomize, reset etc)
    }
    
    public JPanel getPanel() {
        return panel;//Returns the panel
    }
   
    
}
