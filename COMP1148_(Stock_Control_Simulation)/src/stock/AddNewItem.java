/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Kacper
 */
public class AddNewItem extends JFrame implements ActionListener{
    
    JPanel itemForm = new JPanel(new GridBagLayout()); 
    GridBagConstraints itemFormC = new GridBagConstraints();
    
    JLabel newItemL = new JLabel("New Item");
 
    JLabel itemNameL = new JLabel("Name: ");
    JTextField itemName = new JTextField(7);
    
    JLabel itemCodeL = new JLabel("Code: ");
    JTextField itemCode = new JTextField(7);
    
    JLabel priceL = new JLabel("Price (£): ");
    SpinnerNumberModel priceLimiter = new SpinnerNumberModel(1.00, 0.01, 10000.00, 0.50); 
    JSpinner priceS = new JSpinner(priceLimiter);
    
    JLabel quantityL = new JLabel("Quantity: ");
    SpinnerNumberModel quantityLimiter = new SpinnerNumberModel(10, 0, 10000, 5); 
    JSpinner quantityS = new JSpinner(quantityLimiter);
    
    JButton addImage = new JButton("Upload Image");
    JLabel filenameL = new JLabel("Filename [*.jpg]: ...");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg");
    JFileChooser fc = new JFileChooser();
    File file = null;
    int fcResult;
    DecimalFormat df = new DecimalFormat("#.00");
    JButton addItem = new JButton("ADD");
    JButton cancel = new JButton("Cancel");
    JButton changeItem = new JButton("CHANGE");
    String currentKey=null;
    JLabel image = null;
            
    Map<String, StockData.Item> stock = StockData.getStock();
    public AddNewItem(){
        setSize(350,300);
        itemFormC.insets = new Insets(0,0,15,0); 
        itemFormC.gridwidth = 2;
        itemFormC.gridx = 1;
        itemFormC.gridy = 0;
        itemForm.add(newItemL,itemFormC);
        
        itemFormC.insets = new Insets(0,0,0,40); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 1;
        itemForm.add(itemCodeL,itemFormC);
        itemFormC.insets = new Insets(0,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 1;
        itemFormC.gridy = 1;
        itemForm.add(itemCode,itemFormC);
        
        itemFormC.insets = new Insets(0,0,0,40); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 2;
        itemForm.add(itemNameL,itemFormC);
        itemFormC.insets = new Insets(0,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 1;
        itemFormC.gridy = 2;
        itemForm.add(itemName,itemFormC);
        
        itemFormC.insets = new Insets(3,0,0,40); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 3;
        itemForm.add(priceL,itemFormC);
        itemFormC.insets = new Insets(3,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 1;
        itemFormC.gridy = 3;
        priceS.setPreferredSize(new Dimension(60, 20));
        itemForm.add(priceS,itemFormC);
        
        itemFormC.insets = new Insets(0,0,0,40); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 4;
        itemForm.add(quantityL,itemFormC);
        itemFormC.insets = new Insets(0,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 1;
        itemFormC.gridy = 4;
        quantityS.setPreferredSize(new Dimension(60, 20));
        itemForm.add(quantityS,itemFormC);
        
        itemFormC.insets = new Insets(13,0,7,0); 
        itemFormC.gridwidth = 2;
        itemFormC.gridx = 0;
        itemFormC.gridy = 5;
        itemForm.add(filenameL,itemFormC);
        itemFormC.insets = new Insets(0,0,7,0); 
        itemFormC.gridwidth = 2;
        itemFormC.gridx = 0;
        itemFormC.gridy = 6;
        itemForm.add(addImage,itemFormC);
        addImage.addActionListener(this);
        fc.setFileFilter(filter);
        
        itemFormC.insets = new Insets(20,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 7;
        itemForm.add(addItem,itemFormC);
        addItem.addActionListener(this);
        
        itemFormC.insets = new Insets(20,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 1;
        itemFormC.gridy = 7;
        itemForm.add(cancel,itemFormC);
        cancel.addActionListener(this);
        
        add(itemForm);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
         
    }
    public void EditItem(String Key){
        currentKey = Key;
        newItemL.setText("Edit "/*+StockData.getName(Key) Optional*/);
        itemName.setText(StockData.getName(Key));
        itemCode.setText(Key);
        priceS.setValue(StockData.getPrice(Key));
        quantityS.setValue(StockData.getQuantity(Key));
        image = new JLabel(StockDropdown.setImageSize(StockData.getFilename(Key),40));
        file = new File("images/"+StockData.getFilename(Key));
        fc.setCurrentDirectory(file); 
        String Filename = null;
        if(StockData.getFilename(Key).length()>20){
            Filename =StockData.getFilename(Key).substring(0, 20)+"..." ;
        }else{
            Filename =StockData.getFilename(Key);
        }
        filenameL.setText("Filename [*.jpg]: "+Filename);
        
        itemFormC.insets = new Insets(0,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 0;
        itemForm.add(image,itemFormC);
        
        itemForm.remove(addItem);
        itemFormC.insets = new Insets(20,0,0,0); 
        itemFormC.gridwidth = 1;
        itemFormC.gridx = 0;
        itemFormC.gridy = 7;
        itemForm.add(changeItem,itemFormC);
        changeItem.addActionListener(this);
        
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == addItem){
            int response =  JOptionPane.NO_OPTION;
            boolean exists = false;
            for(String key : StockData.getStock().keySet())  {
                if(itemCode.getText().equals(key)&&!exists){
                    JOptionPane.showMessageDialog(this, "Key already exists, try again.");
                    itemCode.setText("");
                    exists = true;
                }      
            }
            if(itemName.getText().isEmpty()||itemCode.getText().isEmpty()&&!exists){
                JOptionPane.showMessageDialog(this, "Item Code and Item Name are both required fields, try again.");
                exists = true;
            }
            if(itemName.getText().length()>25||itemCode.getText().length()>25){
                JOptionPane.showMessageDialog(this, "Item Code and Item Name cannot be longer than 25 letters.");
                exists = true;
            }
            if(!exists){
                response = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item to catalog?", "Confirmation",JOptionPane.YES_NO_OPTION );
            }
            if(response == JOptionPane.YES_OPTION){
                //Add item
                try {                    
                    if(file!=null){
                        Files.copy(file.toPath(), Paths.get("images/"+file.getName()), REPLACE_EXISTING);
                        String DB_URL="jdbc:derby://localhost:1527/" +
                        new File("StoreDB").getAbsolutePath() + ";";
                        Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                        String sqlStatement = "INSERT INTO Stock (STOCKID, NAME, PRICE, QUANTITY, FILENAME) VALUES (?,?,?,?,?)";
                        PreparedStatement stmt = conn.prepareStatement(sqlStatement);
                        stmt.setString(1, itemCode.getText());
                        stmt.setString(2, itemName.getText());
                        stmt.setDouble(3, (Double) priceS.getValue());
                        stmt.setInt(4, (Integer)quantityS.getValue());
                        stmt.setString(5, file.getName());
                        stmt.executeUpdate();
                        stmt.close();
                        stock.put(itemCode.getText(), new StockData.Item(itemName.getText(),(Double) priceS.getValue(), (Integer)quantityS.getValue(), file.getName()));
                        Master.addImage(itemCode.getText());
                    }else{
                       String DB_URL="jdbc:derby://localhost:1527/" +
                       new File("StoreDB").getAbsolutePath() + ";";
                       Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                       String sqlStatement = "INSERT INTO Stock (STOCKID, NAME, PRICE, QUANTITY, FILENAME) VALUES (?,?,?,?,'noImage.jpg')";
                       PreparedStatement stmt = conn.prepareStatement(sqlStatement);
                       stmt.setString(1, itemCode.getText());
                       stmt.setString(2, itemName.getText());
                       stmt.setDouble(3, (Double) priceS.getValue());
                       stmt.setInt(4, (Integer)quantityS.getValue());
                       stmt.executeUpdate();
                       stmt.close();
                       stock.put(itemCode.getText(), new StockData.Item(itemName.getText(), (Double) priceS.getValue(), (Integer)quantityS.getValue()));
                       Master.addImage(itemCode.getText());
                    }
                    dispose();
                    StockTable sT = new StockTable(); //Repainting the table
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if(ae.getSource() == changeItem){
            int response =  JOptionPane.NO_OPTION;
            boolean exists = false;
            for(String key : StockData.getStock().keySet())  {
                if(key!=currentKey){
                    if(itemCode.getText().equals(key)&&!exists){
                        JOptionPane.showMessageDialog(this, "Key already exists, try again.");
                        itemCode.setText(currentKey);
                        exists = true;
                    }
                }   
            }
            if(itemName.getText().isEmpty()||itemCode.getText().isEmpty()&&!exists){
                JOptionPane.showMessageDialog(this, "Item Code and Item Name are both required fields, try again.");
                exists = true;
            }
            if(itemName.getText().length()>25||itemCode.getText().length()>25){
                JOptionPane.showMessageDialog(this, "Item Code and Item Name cannot be longer than 25 letters.");
                exists = true;
            }
            if(!exists){
                response = JOptionPane.showConfirmDialog(null, "Are you sure you want to make this changes?", "Confirmation",JOptionPane.YES_NO_OPTION );
            }
            if(response == JOptionPane.YES_OPTION){
                try {                    
                    Files.copy(file.toPath(), Paths.get("images/"+file.getName()), REPLACE_EXISTING);
                    String DB_URL="jdbc:derby://localhost:1527/" +
                    new File("StoreDB").getAbsolutePath() + ";";
                    Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                    String sqlStatement = "UPDATE Stock SET STOCKID=?, NAME = ?, PRICE= ?, QUANTITY =  ?, FILENAME =? WHERE STOCKID = ?";
                    PreparedStatement stmt = conn.prepareStatement(sqlStatement);
                    stmt.setString(1, itemCode.getText());
                    stmt.setString(2, itemName.getText());
                    stmt.setDouble(3, (Double) priceS.getValue());
                    stmt.setInt(4, (Integer)quantityS.getValue());
                    stmt.setString(5, file.getName());
                    stmt.setString(6, currentKey);
                    stmt.executeUpdate();
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
                    if(!file.getName().equals(StockData.getFilename(currentKey))&&!StockData.getFilename(currentKey).equals("noImage.jpg")&&!ImageUsedByOtherItem){
                        File fileTemp = new File("images/"+StockData.getFilename(currentKey));
                        fileTemp.delete(); //Delete image
                    }
                    stock.put(itemCode.getText(), new StockData.Item(itemName.getText(),(Double) priceS.getValue(), (Integer)quantityS.getValue(), file.getName()));
                    Master.deleteImage(); //Refresh
                    dispose();
                    StockTable sT = new StockTable(); //Repainting the table
                } catch (Exception e) {
                     System.out.println(e);
                }   
            }
        } else if(ae.getSource()== addImage){
            fcResult = fc.showOpenDialog(this);
            if(fcResult == JFileChooser.APPROVE_OPTION){
                file = fc.getSelectedFile();
                String filename =file.getName();
                if(file.getName().length()>20){
                    filename = filename.substring(0, 20)+"...";
                }
                filenameL.setText("Filename [*.jpg]: "+filename);
            }
            if(image!=null){
                itemForm.remove(image);
                image = new JLabel(StockDropdown.setImageSizeFullPath(file.getAbsolutePath(),40));
                itemFormC.insets = new Insets(0,0,0,0); 
                itemFormC.gridwidth = 1;
                itemFormC.gridx = 0;
                itemFormC.gridy = 0;
                itemForm.add(image,itemFormC);
                itemForm.repaint();
                itemForm.revalidate();
            }
        }
        if(ae.getSource() == cancel){
            this.dispose();
        }
    } 
}
