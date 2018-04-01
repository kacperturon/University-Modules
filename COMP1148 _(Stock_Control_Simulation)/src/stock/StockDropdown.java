/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 *
 * @author Kacper
 */
public class StockDropdown extends JPanel{
    private static JComboBox itemsDropdown = new JComboBox();
    private JLabel image;
    private JLabel priceL = new JLabel();
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    StockData sD;
    private JPanel top = new JPanel();
    private JPanel middle = new JPanel();
    private JPanel bottom = new JPanel();
    private boolean exists = false;

    public StockDropdown(){
       setLayout(new BorderLayout());
       add("North", top);
       add("Center", middle);
       add("South", bottom);
       top.add(itemsDropdown);
       bottom.add(priceL);
       itemsDropdown.removeAllItems();
       for(String key : StockData.getStock().keySet())  {
         itemsDropdown.addItem(StockData.getName(key));
       }
       PurchaseStock.quantitySl.setMinimum(0);
       PurchaseStock.quantityLimiter.setMinimum(0);        
       itemsDropdown.setSelectedItem(StockData.getName("00"));
       if(StockData.getQuantity("00")<51){
           PurchaseStock.quantitySl.setMaximum(StockData.getQuantity("00"));
           PurchaseStock.quantityLimiter.setMaximum(StockData.getQuantity("00"));
       }else{
           PurchaseStock.quantitySl.setMaximum(50);
           PurchaseStock.quantityLimiter.setMaximum(50);
       }
       image =new JLabel(setImageSize("bathTowel.jpg",100));
       middle.add(image);
       priceL.setText("Price: "+pounds.format(sD.getPrice("00")));
        itemsDropdown.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    if(ie.getStateChange() == ItemEvent.SELECTED){
                        String temp = itemsDropdown.getSelectedItem().toString();
                        String filePath;

                        for(String key : sD.getStock().keySet())  {
                            if(sD.getName(key).equals(temp)) {
                                filePath = sD.getFilename(key);
                                middle.remove(image);
                                image = new JLabel(setImageSize(filePath,100));
                                middle.add(image);
                                middle.repaint();
                                middle.revalidate();
                                priceL.setText("Price: "+pounds.format(sD.getPrice(key)));
                                exists = false;
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
                                
                            }
                            
                        }
                    }
                }
            }
        );
    }
   
    
    public static String getSelectedItemKey(){
        String key = null;
        for(String keyTemp : StockData.getStock().keySet())  {
            if(itemsDropdown.getSelectedItem().toString() == StockData.getName(keyTemp)) {
                key = keyTemp;
            }
        }
        return key;
    }
    public static ImageIcon setImageSize(String path, int Size) {
        ImageIcon image1 = new ImageIcon("images/"+path);
        Image image2 = image1.getImage().getScaledInstance(Size,Size,0);
        return new ImageIcon(image2);
    };
    public static ImageIcon setImageSizeFullPath(String path, int Size) {
        ImageIcon image1 = new ImageIcon(path);
        Image image2 = image1.getImage().getScaledInstance(Size,Size,0);
        return new ImageIcon(image2);
    };
    
}
