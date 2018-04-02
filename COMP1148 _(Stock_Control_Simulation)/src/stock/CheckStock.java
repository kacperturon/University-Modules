package stock;
//Importing libaries
import java.awt.BorderLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class CheckStock extends JFrame implements ActionListener { //Initiating class CheckStock 
    //that extends JFrame (so it is an exact copy of JFrame and in this class we add additional features), also uses actionlistener to listen for button presses
    JTextField stockNo = new JTextField(7); //Initialising text field
    JTextArea information = new JTextArea(3, 30); //Initialising text area
    JButton check = new JButton("Check Stock"); //Initialising button
    DecimalFormat pounds = new DecimalFormat("£#,##0.00"); //Initialising decimal format of a pound
    public CheckStock() { //Creating basic constructor
        setLayout(new BorderLayout()); //Setting layout of our JFrame 
        setBounds(100, 100, 450, 150); //Chaning the size of JFrame
        setTitle("Check Stock"); //Setting window title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On clicking windows X this jframe will dispose/close
        JPanel top = new JPanel(); //Creating JPanel for top part of our JFrame
        top.add(new JLabel("Enter Stock Number:")); //Adding label to top jpanel
        top.add(stockNo); //Adding text field to top jpanel
        top.add(check); //Adding button to top jpanel
        check.addActionListener(this); //Adding actionlistener to check button so we can determine when it's pressed
        add("North", top); //Attaching top jpanel to 'north' which is the top part of borderlayout
        JPanel middle = new JPanel(); //Creating JPanel for middle part of our JFrame
        middle.add(information); //Adding text area to middle Jpanel
        add("Center", middle); //Attaching middle jpanel to 'center' which is the middle part of borderlayout
        setResizable(false); //Setting the window to not resizable
        setLocationRelativeTo(null); //Centering the window on monitor
        setVisible(true); //Making the window visible
    }//close constructor
    @Override
    public void actionPerformed(ActionEvent e) { //Class that interacts with user pressing buttons with attached ActionListener
        String key = stockNo.getText(); //Getting text from text area
        String name = StockData.getName(key); //Getting name of a product from StockData with using user inputted key value
        if (name == null) { //Checking if user inputted existing key code for a product
            information.setText("No such item in stock"); //If not display in text area that it doesn't exist
        } else { //If it exists
            information.setText(name); //Display the name of the product
            information.append("\nPrice: " + pounds.format(StockData.getPrice(key))); //Display the price of the procduct
            information.append("\nNumber in stock: " + StockData.getQuantity(key)); //Display the quantity of the product
        } //close else
    }//close actionperformed
}//close the class
