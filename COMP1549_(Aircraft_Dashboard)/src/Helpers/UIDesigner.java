/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class UIDesigner {
     /**
     * *************************************************************************
     * Global variables.
     * *************************************************************************
     */
    //Ints used for mouse position and slider.
    private static int positionX, positionY;

    /**
     * *************************************************************************
     * Button control methods.
     * *************************************************************************
     */
    //Sets the orignal state of a button.
    private static void setHoverButtons(JButton input) {
        //Prevent focus on buttons : removes unwanted squares
        input.setFocusPainted(false);
        //set button size
        input.setPreferredSize(new Dimension(100, 40));
        //remove borders from buttons
        input.setBorder(null);
        //set background color
        input.setBackground(Color.lightGray);
        input.setForeground(Color.black);
        //set font size
        input.setFont(new Font("Serif - BOLD", Font.BOLD, 14));
    }

    //Changes the state of a button when a mouse hovers over it.
    private static void hoverButton(final JButton input) {
        //Listen for mouse events
        input.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {//Mouse enter area
                input.setBackground(Color.blue);//Set background color
                input.setForeground(Color.white);//Set text color 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {//Mouse exit are
                input.setBackground(Color.lightGray);//Set background color
                input.setForeground(Color.black);//Set text color
            }
        });
    }

    //Single method to save space.
    public static JButton setButton(JButton input) {
        //set buttons orignal state
        setHoverButtons(input);
        //set hovering capability
        hoverButton(input);
        return input;
    }

    //Sets orignal state for text fields.
    public static JTextField setTextFields(JTextField input, Color textColor, Color backGroundColor) {
        input.setBackground(backGroundColor);//Set background color         
        input.setForeground(textColor);//Set text color
        input.setBorder(BorderFactory.createLineBorder(Color.white));//Create a line border         
        input.setEditable(false);//Remove editing        
        input.setFocusable(false); //Remove focus        
        input.setHorizontalAlignment(SwingConstants.CENTER);//Center the text
        return input;
    }

    //Sets the state of a text fields when a mouse hovers over it.
    public static void hoverFields(final JTextField input) {
        //Listen for mouse events
        input.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {//Mouse enter area
                input.setBackground(Color.blue);//Set background color
                input.setForeground(Color.white);//Set text color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {//Mouse exit area
                input.setBackground(Color.orange);//Set background color
                input.setForeground(Color.blue);//Set text color
            }
        });
    }
    public static JPanel DesignPanel(JFrame frame, JPanel design, Color c) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add("North",addDraggablePanels(new JPanel(), frame, c) );
        panel.add("South",addDraggablePanels(new JPanel(), frame, c) );
        panel.add("East",addDraggablePanels(new JPanel(), frame, c) );
        panel.add("Center", design);
        panel.add("West",addDraggablePanels(new JPanel(), frame, c) );
        return panel;
    }
    //Used for creating and removing text from a field.
    public static JTextField textBoxControl(final JTextField input, final String text) {//ADDS MOUSE CLICK TO TEXTFIELDS
        input.setText(text);//Set text of field.
        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Mouse clicked
                input.setFocusable(true);//Focus on text field
                if (input.getText().equals(text) || input.getText().isEmpty()) {//Check either parameters are true
                    input.setText("");//Set field to be empty.
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {//Mouse enters area
                input.setFocusable(true);//Focus on text field
                if (input.getText().equals(text) || input.getText().isEmpty()) {//Check either parameters are true.
                    input.setText("");//Set field to be empty.
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {//Mouse exits area
                input.setFocusable(false);//Remove focus on text field.
                if (input.getText().isEmpty() || input.getText().equals(text)) {//Check either parameters are true.                 
                    input.setText(text);//Reset field to orignal state
                }
            }
        });
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {//Area is focused
                input.setFocusable(true);//Focus on text field
                if (input.getText().isEmpty() || input.getText().equals(text)) {//Check either parameters are true. 
                    input.setText("");//Set field to be empty.
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                input.setFocusable(false);//Remove focus on text field.
                if (input.getText().isEmpty()) {//Check either parameters are true. 
                    input.setText(text);//Reset field to orignal state
                }
            }
        });
        return input;
    }

    //Adds a JPanel that can drag the frame when moved.
    public static JPanel addDraggablePanels(JPanel input, JFrame frame, Color c) {
        //Set Background color
        input.setBackground(c);
        //Listen for mouse events
        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {//Mouse pressed
                positionX = me.getX();//CHANGE POSITION X
                positionY = me.getY();//CHANGE POSITION Y
            }
        });
        //Listen for mouse motion events
        input.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(frame.getLocation().x + me.getX() - positionX, frame.getLocation().y + me.getY() - positionY);//Get new Positions
            }
        });
        return input;
    }
}
