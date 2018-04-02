/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kacper
 */
public class StockTable{
    public int Index=0;
    private String code;
    private int Quantity;
    DecimalFormat pounds = new DecimalFormat("‎£###,##0.00");
    static String col[] = {"Stock Name","Price","Quantity"};
    static DefaultTableModel tableModel = new DefaultTableModel(col, 0){
       @Override
       public boolean isCellEditable(int row, int column) {
           table.setFocusable(false);
           return false;
       }
    };
    static JTable table = new JTable(tableModel);
    
    public StockTable(){
        tableModel.setRowCount(0); //Clearing the table 
        for(String key : StockData.getStock().keySet())  {
          String NameSTR = StockData.getName(key);
          String PriceSTR = pounds.format(StockData.getPrice(key)); 
          String QuantitySTR = Integer.toString(StockData.getQuantity(key));
          Object[] data = {NameSTR,  PriceSTR, QuantitySTR};
          tableModel.addRow(data);
        }
    }
    
    public void setCode(String c){
        code = c;
    }
    public void setQuantity(){
        Quantity =  Integer.parseInt(tableModel.getValueAt(getIndex(), 2).toString());
    }
    
    public static JTable getTable(){
        return table;
    }
    
    public void updateQuantity(int add){
        Quantity+=add;
        tableModel.setValueAt(Quantity, getIndex(),2);
        StockData.update(code, add);
    }

    public int getIndex(){
       for(int i = 0; i < table.getRowCount(); i++){
          if(tableModel.getValueAt(i, 0)==StockData.getName(code)){
               Index = i;
           }
       }      
       return Index;
   }
}
