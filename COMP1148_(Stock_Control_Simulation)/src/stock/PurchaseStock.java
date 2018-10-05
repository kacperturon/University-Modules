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
import java.text.DecimalFormat;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kacper
 */
public class PurchaseStock 
                    extends JFrame
                            implements ActionListener, ChangeListener {
    JFrame frame = new JFrame();
    JPanel gui = new JPanel(new CardLayout());
    //Shop
    JPanel shop = new JPanel(new GridBagLayout());
    GridBagConstraints shopC = new GridBagConstraints();
    JPanel checkout = new JPanel();
    DecimalFormat pounds = new DecimalFormat("£###,##0.00");
    GridBagConstraints quantityC = new GridBagConstraints();
    JPanel quantityP = new JPanel(new GridBagLayout());
    JLabel quantityL = new JLabel("Quantity: ");
    static SpinnerNumberModel quantityLimiter = new SpinnerNumberModel(1, 0, 20, 1); 
    JSpinner quantitySp = new JSpinner(quantityLimiter);
    static JSlider quantitySl = new JSlider(0,20,1);
    JButton addB = new JButton("Add to basket");
    JButton checkoutB = new JButton("Checkout");
    JButton cancel2 = new JButton("Cancel");
    //Shop
    //Cart
    JPanel cart = new JPanel(new BorderLayout());
    JPanel cartMenu = new JPanel();
    static String col[] = {"Key","Quantity", "Product Name","Price"};
    static DefaultTableModel cartTModel = new DefaultTableModel(col,0){
           public boolean isCellEditable(int rowIndex, int mColIndex) {
            cartT.setFocusable(false);
            //cartT.setRowSelectionAllowed(false);
            return false;
        }
    };
    static JTable cartT = new JTable(cartTModel);
    JPanel cartTJP = new JPanel(new BorderLayout());
    JLabel totalPriceL = new JLabel("Total price: ");
    JTextField totalPriceTF = new JTextField(6);
    JButton cancel = new JButton("Cancel");
    JButton plusB = new JButton("+");
    JButton minusB = new JButton("-");
    double totalPrice = 0;
    int listCounter = 0;
    JButton purchase = new JButton("Purchase");
    JButton continueShopping = new JButton("Continue Shopping");
    //Cart
    public PurchaseStock(){
        frame.setTitle("Purchase Stock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,350));
        //QUANTITY JPANEL - part of Shop Jpanel
        quantityC.insets = new Insets(0,50,0,0); 
        quantityC.gridx = 0;
        quantityC.gridy = 0;
        quantityP.add(quantityL,quantityC);
        quantityC.insets = new Insets(0,0,0,40);
        quantityC.gridx = 1;
        quantityC.gridy = 0;
        quantityP.add(quantitySp,quantityC);
        quantitySp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                quantitySl.setValue((int) quantitySp.getValue());
            }
        });
        quantitySp.setPreferredSize(new Dimension(40, 20));
        quantityC.insets = new Insets(5,0,0,0); 
        quantityC.gridwidth = 2;
        quantityC.gridx = 0;
        quantityC.gridy = 1;
        quantityP.add(quantitySl,quantityC);
        quantitySl.setMajorTickSpacing(1);
        quantitySl.setPaintTicks(true);
        quantitySl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                quantitySp.setValue((int) quantitySl.getValue());
            }
        });
        quantityC.insets = new Insets(10,0,0,0); 
        quantityC.gridx = 0;
        quantityC.gridy = 2;
        quantityP.add(addB, quantityC);
        addB.addActionListener(this);
        //QUANTITY JPANEL - part of shop jpanel
        //checkout JPANEL - part of shop jpanel
        checkout.add(checkoutB);
        checkout.add(cancel);
        checkoutB.addActionListener(this);
        cancel.addActionListener(this);
        //checkout JPANEL - part of shop jpanel
        //shop jpanel
        shopC.gridx=0;
        shopC.gridy=0;
        shop.add(new StockDropdown(), shopC);
        shopC.gridx=1;
        shopC.gridy=0;
        shop.add(quantityP, shopC);
        shopC.insets = new Insets(30,0,0,0);
        shopC.gridwidth=2;
        shopC.gridx=0;
        shopC.gridy=1;
        shop.add(checkout, shopC);
        //shop jpanel
        //Cart jpanel
        cartTModel.setRowCount(0);
        cartT.getColumnModel().getColumn(0).setWidth(0);
        cartT.getColumnModel().getColumn(0).setMinWidth(0);
        cartT.getColumnModel().getColumn(0).setMaxWidth(0);
        cartT.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        @Override
        public void valueChanged(ListSelectionEvent lse) {
                plusB.setEnabled(true);
                minusB.setEnabled(true);
            }
        });
        JScrollPane cartTPane = new JScrollPane(cartT);
        cartTPane.setPreferredSize(new Dimension(50,50));
        cartTJP.add(cartTPane,BorderLayout.CENTER);    
        JPanel priceTFandL = new JPanel();
        JPanel plusAndMinus = new JPanel(new GridLayout(2,1));
        totalPriceTF.setEditable(false);
        priceTFandL.add(totalPriceL);
        priceTFandL.add(totalPriceTF);
        cartTJP.add(priceTFandL,BorderLayout.SOUTH); 
        plusB.setEnabled(false);
        minusB.setEnabled(false);
        plusB.setToolTipText("Add 1 more to overall quantity of highlighted product.");
        plusAndMinus.add(plusB);
        minusB.setToolTipText("Delete 1 from overall quantity of highlighted product.");
        plusAndMinus.add(minusB);
        cartTJP.add(plusAndMinus,BorderLayout.EAST);
        plusB.addActionListener(this);
        minusB.addActionListener(this);
        cart.add(cartTJP,BorderLayout.CENTER);    
        cartMenu.add(purchase);
        cartMenu.add(continueShopping);
        cartMenu.add(cancel2);
        cart.add(cartMenu, BorderLayout.SOUTH);
        purchase.addActionListener(this);
        continueShopping.addActionListener(this);
        cancel2.addActionListener(this);
        //Cart jpanel
        gui.add(shop, "shop");
        gui.add(cart, "cart");
        frame.add(gui);
        frame.pack(); //Size adequate to contents
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String key = StockDropdown.getSelectedItemKey();
        int Quantity = (int) quantitySp.getValue();
        boolean exists = false;
        if(ae.getSource() == addB){
            //Checking if item already exists in list if so updating quantity instead of creating
            // another row in list
            for (int i = 0; i<cartT.getRowCount();i++){
                if(cartT.getValueAt(i, 2).equals(StockData.getName(key))){
                    exists = true;
                    if(StockData.getQuantity(key)-(Integer)cartT.getValueAt(i, 1)>=Quantity&&Quantity!=0){
                        int quantitySum = (Integer)cartT.getValueAt(i, 1) + Quantity;
                        cartT.setValueAt(quantitySum, i,1);
                        JOptionPane.showMessageDialog(frame, "Added to cart.");
                        if(StockData.getQuantity(key)-quantitySum<51){
                            quantitySl.setMaximum(StockData.getQuantity(key)-quantitySum);
                            quantityLimiter.setMaximum(StockData.getQuantity(key)-quantitySum);
                        }else{
                            quantitySl.setMaximum(50);
                            quantityLimiter.setMaximum(50);      
                        }
                    }
                }
            }
            //If item doesn't exist add to list
            if(StockData.getQuantity(key)>=Quantity&&!exists&&Quantity!=0){
                Object[] data = {key, Quantity, StockData.getName(key), pounds.format(StockData.getPrice(key))};
                cartTModel.addRow(data);
                JOptionPane.showMessageDialog(frame, "Added to cart.");
                if(StockData.getQuantity(key)-Quantity<51){
                    quantitySl.setMaximum(StockData.getQuantity(key)-Quantity);
                    quantityLimiter.setMaximum(StockData.getQuantity(key)-Quantity);
                }else{
                    quantitySl.setMaximum(50);
                    quantityLimiter.setMaximum(50);      
                }
                listCounter++;
            }else if(StockData.getQuantity(key)<Quantity&&!exists&&Quantity!=0){
                JOptionPane.showMessageDialog(frame, "There aren't enough in stock, try less or try again later.");
            }
        }else if(ae.getSource() == checkoutB){ //Changing Jpanel from shop to cart
            totalPrice = 0;
             for (int i = 0; i<cartT.getRowCount();i++){

                 totalPrice += StockData.getPrice((String)cartT.getValueAt(i, 0))*(Integer)cartT.getValueAt(i, 1);
            }  
             totalPriceTF.setText(pounds.format(totalPrice));
            CardLayout cardLayout = (CardLayout) gui.getLayout();
            cardLayout.show(gui, "cart");
        }
        if(ae.getSource() == plusB){
                if((Integer)cartT.getValueAt(cartT.getSelectedRow(), 1)<StockData.getQuantity((String)cartT.getValueAt(cartT.getSelectedRow(), 0))){
                    int tempValue = (Integer)cartT.getValueAt(cartT.getSelectedRow(), 1) +1;
                    cartT.setValueAt(tempValue,cartT.getSelectedRow(), 1);
                    totalPrice += StockData.getPrice((String)cartT.getValueAt(cartT.getSelectedRow(),0));
                    totalPriceTF.setText(pounds.format(totalPrice));
                }else{
                    plusB.setEnabled(false);
                }
        }else if(ae.getSource() == minusB){
                if((Integer)cartT.getValueAt(cartT.getSelectedRow(), 1)<StockData.getQuantity((String)cartT.getValueAt(cartT.getSelectedRow(), 0))){
                    plusB.setEnabled(true);
                }
                int tempValue = (Integer)cartT.getValueAt(cartT.getSelectedRow(), 1) -1;
                int response = 0;
                if(tempValue==0){
                response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this item from your cart?", "Confirmation",JOptionPane.YES_NO_OPTION );}
                if(response == JOptionPane.YES_OPTION||tempValue!=0){
                cartT.setValueAt(tempValue,cartT.getSelectedRow(), 1);
                 totalPrice -= StockData.getPrice((String)cartT.getValueAt(cartT.getSelectedRow(),0));
                if(tempValue == 0){
                    cartTModel.removeRow(cartT.getSelectedRow());
                    if(cartT.getRowCount()==0){
                        minusB.setEnabled(false);
                        plusB.setEnabled(false);
                    }
                }
                totalPriceTF.setText(pounds.format(totalPrice));
                }
           }
        if(ae.getSource() == purchase){
            if(totalPrice>0){
                 for (int i = 0; i<cartT.getRowCount();i++){
                    try{
                     StockData.update((String)cartT.getValueAt(i,0), ((Integer)cartT.getValueAt(i, 1))*-1);
                      String DB_URL="jdbc:derby://localhost:1527/" +
                        new File("StoreDB").getAbsolutePath() + ";";
                        Connection conn = DriverManager.getConnection(DB_URL, "StoreAdmin","pass");
                        Statement stmt = conn.createStatement();
                        String sqlStatement = "UPDATE Stock SET QUANTITY ="+StockData.getQuantity((String)cartT.getValueAt(i, 0))+" WHERE STOCKID ='"+(String)cartT.getValueAt(i, 0)+"'";
                        stmt.executeUpdate(sqlStatement);
                        stmt.close();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                 }
                JOptionPane.showMessageDialog(frame, "Purchased.");
                StockTable sT = new StockTable(); //Refresh table
                frame.dispose(); //Close the frame after purchase
            }
            else{ //If cart is empty and user clicked purchase change jpanel back to shop
                JOptionPane.showMessageDialog(frame, "Your cart is empty.");
                CardLayout cardLayout = (CardLayout) gui.getLayout();
                cardLayout.show(gui, "shop");
            }
        }
        if(ae.getSource() == continueShopping){
            CardLayout cardLayout = (CardLayout) gui.getLayout();
            cardLayout.show(gui, "shop");
            for (int i = 0; i<PurchaseStock.cartT.getRowCount();i++){    
                if(PurchaseStock.cartT.getValueAt(i, 2).equals(StockData.getName(key))){
                    exists = true;
                    if(StockData.getQuantity(key)-(Integer)PurchaseStock.cartT.getValueAt(i, 1)<51){
                        PurchaseStock.quantitySl.setMaximum(StockData.getQuantity(key)-(Integer)PurchaseStock.cartT.getValueAt(i, 1));
                        PurchaseStock.quantityLimiter.setMaximum(StockData.getQuantity(key)-(Integer)PurchaseStock.cartT.getValueAt(i, 1));
                    }else{
                        PurchaseStock.quantitySl.setMaximum(50);
                        PurchaseStock.quantityLimiter.setMaximum(50);
                    }
                }
            }
            if(PurchaseStock.cartT.getRowCount()==0||!exists){
                if(StockData.getQuantity(key)<51){
                    PurchaseStock.quantitySl.setMaximum(StockData.getQuantity(key));
                    PurchaseStock.quantityLimiter.setMaximum(StockData.getQuantity(key));
                }else{
                    PurchaseStock.quantitySl.setMaximum(50);
                    PurchaseStock.quantityLimiter.setMaximum(50);
                }
            }
            
        }if(ae.getSource() == cancel||ae.getSource() == cancel2){
            frame.dispose();
        }
    }
    @Override
    public void stateChanged(ChangeEvent ce) {
    }    
}
