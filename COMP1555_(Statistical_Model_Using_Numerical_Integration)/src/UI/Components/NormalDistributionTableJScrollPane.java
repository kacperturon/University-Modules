/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Components;

import NormalDistributionTableHelper.NormalDistributionTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Kacper
 */
public class NormalDistributionTableJScrollPane extends JScrollPane { //ScrollPane containing Z-Table

    JTable normalDistribution = new JTable(); //Table containing all the Z-Table data

    NormalDistributionTableModel ndtm = new NormalDistributionTableModel();

    public JTable getNormalDistribution() { //Getter for normalDistribution table
        return normalDistribution;
    }

    public void refreshTable() { //Refreshes the values in the Z-Table (-3.9 to 3.9)
        ndtm.refreshValues();
    }

    public NormalDistributionTableJScrollPane() {
        setViewportView(normalDistribution); //Display the table in jscrollpane
        normalDistribution.setModel(ndtm); //set the table model with all the data
        normalDistribution.setDefaultRenderer(Object.class, new RowHeaderRenderer()); //Renderer that changed the background color of rows and centers them
        normalDistribution.getTableHeader().setReorderingAllowed(false); //Disable reordering of columns
        normalDistribution.getTableHeader().setResizingAllowed(false); //Make columns not resizable
        normalDistribution.setDragEnabled(true); //Make columns not draggable

    }

private class RowHeaderRenderer extends DefaultTableCellRenderer {
  public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {

   Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
     row, column);
        
        this.setHorizontalAlignment(CENTER); //Center text in cells
        if(column==0){ //Change background color of row header (column 0)
            if(isSelected){ //Change font of the name of selected row
                rendererComp.setFont( getFont().deriveFont(Font.BOLD) );
            }
            rendererComp.setBackground(Color.lightGray);
            
        }else{
            if(isSelected){ //Change color of the row selected
                rendererComp.setBackground(Color.blue);
            }else{
                rendererComp.setBackground(Color.white);
                rendererComp.setForeground(Color.black);
            }
        }
   return rendererComp ;
  }
  
 }

}
