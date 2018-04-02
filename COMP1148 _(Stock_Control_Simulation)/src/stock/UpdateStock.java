/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Kacper
 */
public class UpdateStock extends JFrame implements ActionListener {
    
    JLabel stockNoLabel = new JLabel("Stock ID:");
    JTextField stockNo = new JTextField(4);
    JLabel quantityLabel = new JLabel("Quantity:");
    List<Integer> quantity = new ArrayList<Integer>();
    JComboBox quantityBox ;
    JButton addButton = new JButton("ADD");
    JScrollPane catalog = new JScrollPane(StockTable.getTable());
    StockTable stockTable = new StockTable();
    JButton addNewItemButton = new JButton("Add a new item");
    JButton editItemButton = new JButton("Edit");
    JButton deleteItemButton = new JButton("Delete");
    JButton cancel = new JButton("Cancel");
    String currentKey = null;
    
    public UpdateStock(){
        setLayout(new BorderLayout());
        setBounds(100, 100, 450, 300);
        setTitle("Update Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        add("North", top);
        JPanel middle = new JPanel();
        add("Center", middle);
        JPanel bottom = new JPanel();
        add("South", bottom);
        top.add(stockNoLabel);
        stockNo.setEditable(false);
        top.add(stockNo);
        top.add(Box.createRigidArea(new Dimension(10,0)));
        for (int i = 1; i <= 80; ++i) {
            quantity.add(i);
        }
        quantityBox = new JComboBox(quantity.toArray());
        top.add(quantityLabel);
        top.add(quantityBox);
        top.add(Box.createRigidArea(new Dimension(100,0)));
        addButton.setToolTipText("Add selected amount to the overall quantity of highlighted item.");
        top.add(addButton);
        addButton.setEnabled(false);
        addButton.addActionListener(this);
        middle.add(catalog);
        catalog.setPreferredSize(new Dimension(400, 150));
        addNewItemButton.setToolTipText("Add a completely new item to the stock database.");
        bottom.add(addNewItemButton);
        addNewItemButton.addActionListener(this);
        editItemButton.setToolTipText("Change highlighted item's specifications such as code, name, image, quantity, or price.");
        bottom.add(editItemButton);
        editItemButton.setEnabled(false);
        editItemButton.addActionListener(this);
        deleteItemButton.setToolTipText("Completely remove highlighted item from the database.");
        bottom.add(deleteItemButton);
        deleteItemButton.setEnabled(false);
        deleteItemButton.addActionListener(this);
        bottom.add(cancel);
        cancel.addActionListener(this);
        //Checking selected row
        StockTable.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                editItemButton.setEnabled(true);
                deleteItemButton.setEnabled(true);
                addButton.setEnabled(true);
                if (StockTable.getTable().getSelectedRow() > -1) {
                    for(String key : StockData.getStock().keySet())  {
                        if(StockData.getName(key).equals(StockTable.getTable().getValueAt(StockTable.getTable().getSelectedRow(), 0).toString())){
                            currentKey = key;                           
                        }
                    }
                    stockNo.setText(currentKey);
                }
            }
        });
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int quantity = (int) quantityBox.getSelectedItem();
        String code = stockNo.getText();
        if(ae.getSource() == addButton){
            if(quantity>0){
                if (StockData.getName(code)!=null) {
                    try{
                        stockTable.setCode(code);
                        stockTable.setQuantity();
                        stockTable.updateQuantity(quantity);
                        String DB_URL="jdbc:derby://localhost:1527/" +
                        new File("StoreDB").getAbsolutePath() + ";";
                        Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                        String sqlStatement = "UPDATE Stock SET QUANTITY =? WHERE STOCKID =?";
                        PreparedStatement stmt = conn.prepareStatement(sqlStatement);
                        stmt.setInt(1, StockData.getQuantity(code));
                        stmt.setString(2, code);
                        stmt.executeUpdate();
                        stmt.close();
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(this, "Something went wrong, try again later.");
                    }
                }
            }
        } else if(ae.getSource() == addNewItemButton){
            AddNewItem addNewItem = new AddNewItem();
        } else if(ae.getSource() == editItemButton){
          AddNewItem edit = new AddNewItem();  
          edit.EditItem(currentKey);
        } else if(ae.getSource() == deleteItemButton){
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Confirmation",JOptionPane.YES_NO_OPTION );
            if(response == JOptionPane.YES_OPTION){
                try{
                       String DB_URL="jdbc:derby://localhost:1527/" +
                       new File("StoreDB").getAbsolutePath() + ";";
                       Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                       Statement stmt = conn.createStatement();
                       String sqlStatement = "DELETE FROM Stock WHERE STOCKID ='"+currentKey+"'"; //Delete from DB
                       stmt.executeUpdate(sqlStatement); 
                       stmt.close();                      
                       boolean ImageUsedByOtherItem = false;
                         //Check if no other item uses the same image if it does just move the file instead of replacing
                       int b=0;
                       for(String key : StockData.getStock().keySet())  {
                            if(StockData.getFilename(key).equals(StockData.getFilename(currentKey))){                               
                                b++;
                                if(b>1){
                                    ImageUsedByOtherItem=true;
                                }
                            }
                       }
                       if(!StockData.getFilename(currentKey).equals("noImage.jpg")&&!ImageUsedByOtherItem){
                           File file = new File("images/"+StockData.getFilename(currentKey));
                           file.delete(); //Delete image
                       }
                       StockData.stock.remove(currentKey);//Delete from hashmap
                       StockTable sT = new StockTable(); //Repainting the table
                       Master.deleteImage();
                }catch(Exception es){
                    System.out.println(es);
                }
            }
        }else if(ae.getSource() == cancel){
            this.dispose();
        }
    }
}
