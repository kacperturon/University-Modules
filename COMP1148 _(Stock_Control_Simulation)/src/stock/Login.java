/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Kacper
 */
public class Login extends JFrame implements ActionListener{
    JLabel loginScreenL = new JLabel("Stock Update");
    JLabel userNameL = new JLabel("Enter username: ");
    JTextField userName = new JTextField(8);
    JLabel passwordL = new JLabel("Enter password: ");
    JPasswordField password = new JPasswordField(8);
    JButton login = new JButton("Login");
    boolean loggedIn;
    JButton cancel = new JButton("Cancel");
    Login(){
        setLayout(new BorderLayout());
        setSize(250, 180);
        setTitle("Stock Update");
        JPanel top = new JPanel();
        top.add(loginScreenL);
        add("North", top);
        JPanel middle = new JPanel(new GridBagLayout());
        GridBagConstraints middleC = new GridBagConstraints();
        middleC.gridx=0;
        middleC.gridy=0;
        middle.add(userNameL, middleC);
        middleC.gridx=1;
        middleC.gridy=0;
        middle.add(userName, middleC);
        middleC.gridx=0;
        middleC.gridy=1;
        middle.add(passwordL, middleC);
        middleC.gridx=1;
        middleC.gridy=1;
        middle.add(password, middleC);
        add("Center", middle);
        JPanel bottom = new JPanel();
        bottom.add(login);
        login.addActionListener(this);
        bottom.add(cancel);
        cancel.addActionListener(this);
        add("South", bottom);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login){
            String user = userName.getText();
            String pass = new String(password.getPassword());
            try{
                String DB_URL="jdbc:derby://localhost:1527/" +
                new File("StoreDB").getAbsolutePath() + ";";
                Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                Statement stmt = conn.createStatement();
                String sqlStatement = "SELECT * from Administration ";
                ResultSet result = stmt.executeQuery(sqlStatement);
                while(result.next()){
                    if(result.getString("Username").equals(user)&&result.getString("Password").equals(pass)){
                        loggedIn = true;
                    }
                }
                stmt.close();
            }catch(Exception e){
                System.out.println(e);
            }
            if(loggedIn==true){
                Master.loggedIn = true;
                dispose();
                UpdateStock updateStock = new UpdateStock();
            }else{
                JOptionPane.showMessageDialog(this, "Username or Password is invalid try again.");
                userName.setText("");
                password.setText("");
            }
        }else if(ae.getSource()==cancel){
            this.dispose();
        }
    }
}
