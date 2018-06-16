package UI;

import NormalDistributionTableHelper.NormalDistributionTableModel;
import UI.Components.NormalDistributionTableJScrollPane;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.NormalDistribution;
import main.NormalDistributionPreferences;

/**
 *
 * @author Kacper
 */
public class NormalDistributionUI extends javax.swing.JFrame {

    NormalDistribution nd; //Object for all normal distribution calculations
    DecimalFormat df = new DecimalFormat("0.0000"); //Formatter for area value which is displayed to 4 decimal places

    public NormalDistributionUI() {
        //Sets design type for the application
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NormalDistributionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NormalDistributionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NormalDistributionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NormalDistributionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        nd = new NormalDistribution(jFreeChartPanel.getMean(), jFreeChartPanel.getSd(), 0, 0, 6); //initiates nd object with default values
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaTypeGroup = new javax.swing.ButtonGroup();
        jFreeChartPanel = new UI.Components.JFreeChartPanel();
        normalDistributionInputPanel = new javax.swing.JPanel();
        MeanSdInputPanel = new javax.swing.JPanel();
        standardDeviationInput = new javax.swing.JTextField();
        meanInput = new javax.swing.JTextField();
        standardDeviationLabel = new javax.swing.JLabel();
        meanLabel = new javax.swing.JLabel();
        areaTypeInputPanel = new javax.swing.JPanel();
        aboveRadioButton = new javax.swing.JRadioButton();
        belowRadioButton = new javax.swing.JRadioButton();
        betweenRadioButton = new javax.swing.JRadioButton();
        outsideRadioButton = new javax.swing.JRadioButton();
        areaOutputPanel = new javax.swing.JPanel();
        areaLabel = new javax.swing.JLabel();
        areaOutputLabel = new javax.swing.JLabel();
        lowerBoundInputPanel = new javax.swing.JPanel();
        lowerBoundLabel = new javax.swing.JLabel();
        lowerBoundInput = new javax.swing.JTextField();
        higherBoundInputPanel = new javax.swing.JPanel();
        upperBoundLabel = new javax.swing.JLabel();
        upperBoundInput = new javax.swing.JTextField();
        normalDistributionTablePanel = new javax.swing.JPanel();
        zLabel = new javax.swing.JLabel();
        normalDistributionTableJScrollPane = new UI.Components.NormalDistributionTableJScrollPane();
        zLowerBoundInput = new javax.swing.JTextField();
        zFromLabel = new javax.swing.JLabel();
        zToLabel = new javax.swing.JLabel();
        zUpperBoundInput = new javax.swing.JTextField();
        zTableWarningLabel = new javax.swing.JLabel();
        zTableClear = new javax.swing.JButton();
        normalDistributionTableToggle = new javax.swing.JToggleButton();
        NormalDistributionWarningLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        toolsMenu = new javax.swing.JMenu();
        preferencesMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Normal Distribution analysis tool");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bellCurve.png")));
        setResizable(false);

        jFreeChartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        standardDeviationInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        standardDeviationInput.setText("1");
        standardDeviationInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateMeanAndSd();}
            public void removeUpdate(DocumentEvent e){updateMeanAndSd();}
            public void insertUpdate(DocumentEvent e){updateMeanAndSd();}
        });

        meanInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        meanInput.setText("0");
        meanInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateMeanAndSd();}
            public void removeUpdate(DocumentEvent e){updateMeanAndSd();}
            public void insertUpdate(DocumentEvent e){updateMeanAndSd();}
        });

        standardDeviationLabel.setText("Standard deviation");

        meanLabel.setText("Mean");

        javax.swing.GroupLayout MeanSdInputPanelLayout = new javax.swing.GroupLayout(MeanSdInputPanel);
        MeanSdInputPanel.setLayout(MeanSdInputPanelLayout);
        MeanSdInputPanelLayout.setHorizontalGroup(
            MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MeanSdInputPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(meanLabel)
                    .addComponent(meanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(standardDeviationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(standardDeviationLabel))
                .addGap(20, 20, 20))
        );

        MeanSdInputPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {meanInput, standardDeviationInput});

        MeanSdInputPanelLayout.setVerticalGroup(
            MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MeanSdInputPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanLabel)
                    .addComponent(standardDeviationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MeanSdInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(standardDeviationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        areaTypeGroup.add(aboveRadioButton);
        aboveRadioButton.setText("Above");
        aboveRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboveRadioButtonActionPerformed(evt);
            }
        });

        areaTypeGroup.add(belowRadioButton);
        belowRadioButton.setText("Below");
        belowRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belowRadioButtonActionPerformed(evt);
            }
        });

        areaTypeGroup.add(betweenRadioButton);
        betweenRadioButton.setText("Between");
        betweenRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betweenRadioButtonActionPerformed(evt);
            }
        });

        areaTypeGroup.add(outsideRadioButton);
        outsideRadioButton.setText("Outside");
        outsideRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outsideRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout areaTypeInputPanelLayout = new javax.swing.GroupLayout(areaTypeInputPanel);
        areaTypeInputPanel.setLayout(areaTypeInputPanelLayout);
        areaTypeInputPanelLayout.setHorizontalGroup(
            areaTypeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTypeInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaTypeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aboveRadioButton)
                    .addComponent(belowRadioButton)
                    .addComponent(betweenRadioButton)
                    .addComponent(outsideRadioButton))
                .addGap(0, 0, 0))
        );
        areaTypeInputPanelLayout.setVerticalGroup(
            areaTypeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTypeInputPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(aboveRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(belowRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(betweenRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outsideRadioButton)
                .addGap(0, 0, 0))
        );

        areaLabel.setText("Area:");

        areaOutputLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        areaOutputLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        areaOutputLabel.setText("0.0000");

        javax.swing.GroupLayout areaOutputPanelLayout = new javax.swing.GroupLayout(areaOutputPanel);
        areaOutputPanel.setLayout(areaOutputPanelLayout);
        areaOutputPanelLayout.setHorizontalGroup(
            areaOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaOutputPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(areaOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(areaLabel)
                    .addComponent(areaOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        areaOutputPanelLayout.setVerticalGroup(
            areaOutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaOutputPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(areaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaOutputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        lowerBoundInputPanel.setPreferredSize(new java.awt.Dimension(71, 58));

        lowerBoundLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lowerBoundInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lowerBoundInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lowerBoundInput.setVisible(false);

        lowerBoundInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateArea();}
            public void removeUpdate(DocumentEvent e){updateArea();}
            public void insertUpdate(DocumentEvent e){updateArea();}
        });

        javax.swing.GroupLayout lowerBoundInputPanelLayout = new javax.swing.GroupLayout(lowerBoundInputPanel);
        lowerBoundInputPanel.setLayout(lowerBoundInputPanelLayout);
        lowerBoundInputPanelLayout.setHorizontalGroup(
            lowerBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerBoundInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lowerBoundInput, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(lowerBoundLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lowerBoundInputPanelLayout.setVerticalGroup(
            lowerBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerBoundInputPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lowerBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lowerBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        higherBoundInputPanel.setPreferredSize(new java.awt.Dimension(71, 58));

        upperBoundLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        upperBoundInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        upperBoundInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        upperBoundInput.setVisible(false);

        upperBoundInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateArea();}
            public void removeUpdate(DocumentEvent e){updateArea();}
            public void insertUpdate(DocumentEvent e){updateArea();}
        });

        javax.swing.GroupLayout higherBoundInputPanelLayout = new javax.swing.GroupLayout(higherBoundInputPanel);
        higherBoundInputPanel.setLayout(higherBoundInputPanelLayout);
        higherBoundInputPanelLayout.setHorizontalGroup(
            higherBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(higherBoundInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(higherBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upperBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upperBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        higherBoundInputPanelLayout.setVerticalGroup(
            higherBoundInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(higherBoundInputPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(upperBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(upperBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout normalDistributionInputPanelLayout = new javax.swing.GroupLayout(normalDistributionInputPanel);
        normalDistributionInputPanel.setLayout(normalDistributionInputPanelLayout);
        normalDistributionInputPanelLayout.setHorizontalGroup(
            normalDistributionInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(normalDistributionInputPanelLayout.createSequentialGroup()
                .addComponent(MeanSdInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(areaTypeInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(normalDistributionInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lowerBoundInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(higherBoundInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(areaOutputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        normalDistributionInputPanelLayout.setVerticalGroup(
            normalDistributionInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MeanSdInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(areaTypeInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(areaOutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(normalDistributionInputPanelLayout.createSequentialGroup()
                .addComponent(lowerBoundInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(higherBoundInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        normalDistributionTablePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 18));
        normalDistributionTablePanel.setVisible(false);
        pack();

        zLabel.setText("Z:");

        zLowerBoundInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        zLowerBoundInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateZTable();}
            public void removeUpdate(DocumentEvent e){updateZTable();}
            public void insertUpdate(DocumentEvent e){updateZTable();}
        });

        zFromLabel.setText("From:");

        zToLabel.setText("To:");

        zUpperBoundInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        zUpperBoundInput.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent e){updateZTable();}
            public void removeUpdate(DocumentEvent e){updateZTable();}
            public void insertUpdate(DocumentEvent e){updateZTable();}
        });

        zTableWarningLabel.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        zTableWarningLabel.setForeground(new java.awt.Color(255, 0, 51));
        zTableWarningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        zTableClear.setText("Clear");
        zTableClear.setToolTipText("Reset inputs and the table");
        zTableClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zTableClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout normalDistributionTablePanelLayout = new javax.swing.GroupLayout(normalDistributionTablePanel);
        normalDistributionTablePanel.setLayout(normalDistributionTablePanelLayout);
        normalDistributionTablePanelLayout.setHorizontalGroup(
            normalDistributionTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                .addGroup(normalDistributionTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(normalDistributionTableJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(zLabel))
                    .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(normalDistributionTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                                .addComponent(zFromLabel)
                                .addGap(18, 18, 18)
                                .addComponent(zLowerBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(zToLabel))
                            .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(zTableClear)))
                        .addGap(18, 18, 18)
                        .addComponent(zUpperBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(zTableWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        normalDistributionTablePanelLayout.setVerticalGroup(
            normalDistributionTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(normalDistributionTablePanelLayout.createSequentialGroup()
                .addComponent(normalDistributionTableJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(zLabel)
                .addGap(18, 18, 18)
                .addGroup(normalDistributionTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zLowerBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zFromLabel)
                    .addComponent(zToLabel)
                    .addComponent(zUpperBoundInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(zTableClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zTableWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        normalDistributionTableToggle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tableIcon.png"))); // NOI18N
        normalDistributionTableToggle.setPreferredSize(new java.awt.Dimension(105, 85));
        normalDistributionTableToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalDistributionTableToggleActionPerformed(evt);
            }
        });

        NormalDistributionWarningLabel.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        NormalDistributionWarningLabel.setForeground(new java.awt.Color(255, 0, 51));
        NormalDistributionWarningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        toolsMenu.setText("Tools");

        preferencesMenuItem.setText("Preferences");
        preferencesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(preferencesMenuItem);

        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(normalDistributionInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(normalDistributionTableToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFreeChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NormalDistributionWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(normalDistributionTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(normalDistributionTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jFreeChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(normalDistributionInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(normalDistributionTableToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(NormalDistributionWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void outsideRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outsideRadioButtonActionPerformed
        prepareBoundaryInputs(true, "x1:", true, "x2:"); //Shows x1 and x2 input fields and adds labels x1: and x2:
    }//GEN-LAST:event_outsideRadioButtonActionPerformed

    private void betweenRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_betweenRadioButtonActionPerformed
        prepareBoundaryInputs(true, "x1:", true, "x2:"); //Shows x1 and x2 input fields and adds labels x1: and x2:
    }//GEN-LAST:event_betweenRadioButtonActionPerformed

    private void belowRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belowRadioButtonActionPerformed
        prepareBoundaryInputs(true, "x:", false, ""); //Shows x input field and adds label x::
    }//GEN-LAST:event_belowRadioButtonActionPerformed

    private void aboveRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboveRadioButtonActionPerformed
        prepareBoundaryInputs(true, "x:", false, ""); //Shows x input field and adds label x:
    }//GEN-LAST:event_aboveRadioButtonActionPerformed

    private void prepareBoundaryInputs(boolean lowerBoundInputVisible, String lowerBoundLabelText, boolean upperBoundInputVisible, String upperBoundLabelText) { //Used to display currently necessary input fields
        lowerBoundInput.setVisible(lowerBoundInputVisible); //Shows/hides input field
        upperBoundInput.setVisible(upperBoundInputVisible); //Shows/hides input field
        lowerBoundInput.setText(""); //Empties the input field
        upperBoundInput.setText(""); //Empties the input field
        lowerBoundLabel.setText(lowerBoundLabelText); //Sets the text of label
        upperBoundLabel.setText(upperBoundLabelText); //Sets the text of label
        normalDistributionInputPanel.revalidate(); //Revalidates UI
        normalDistributionInputPanel.repaint(); //Repaints components
        jFreeChartPanel.clearArea(); //Clears shading
    }

    private void normalDistributionTableToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalDistributionTableToggleActionPerformed
        normalDistributionTablePanel.setVisible(!normalDistributionTablePanel.isVisible()); //Toggle Z-Table JPanel from visible to invisible
        pack(); //Adjust JFrame to fit its contents
    }//GEN-LAST:event_normalDistributionTableToggleActionPerformed

    private void preferencesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesMenuItemActionPerformed
        //Opens the preferences window
        PreferencesUI preferences = new PreferencesUI();
        preferences.setVisible(true);
    }//GEN-LAST:event_preferencesMenuItemActionPerformed

    private void zTableClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zTableClearActionPerformed
        zTableWarningLabel.setText(""); //Clear the warning
        zLowerBoundInput.setText(""); //Clear the input
        zUpperBoundInput.setText(""); //Clear the input
        JTable normalDistributionTable = normalDistributionTableJScrollPane.getNormalDistribution(); //Access to normalDistribution JTable which allows to refresh the Z-Table and delete unnecessary rows
        ((NormalDistributionTableModel) normalDistributionTable.getModel()).refreshValues(); //Refresh the table to default range from -3.99 to 3.99
    }//GEN-LAST:event_zTableClearActionPerformed

    public void updateZTable() { //Called each time user changes a value for range in Z table
        zTableWarningLabel.setText(""); //Clears the warning label
        JTable normalDistributionTable = normalDistributionTableJScrollPane.getNormalDistribution(); //Access to normalDistribution JTable which allows to refresh the Z-Table and delete unnecessary rows
        double zLowerBound = 0, zUpperBound = 0;
        boolean zLowerBoundSet = false, zUpperBoundSet = false;
        try { //Checks if both inputs aren't empty and are not set to - 
            if (!zLowerBoundInput.getText().equals("") && !zLowerBoundInput.getText().equals("-")) {
                zLowerBound = Double.parseDouble(zLowerBoundInput.getText());
                zLowerBoundSet = true;
            }
            if (!zUpperBoundInput.getText().equals("") && !zUpperBoundInput.getText().equals("-")) {
                zUpperBound = Double.parseDouble(zUpperBoundInput.getText());
                zUpperBoundSet = true;
            }

        } catch (Exception e) { //Display warning for user to type valid values
            zTableWarningLabel.setText("Both Lower and Upper bounds need to be numeric values between -3.99 to 3.99.");
        }
        if (zLowerBoundSet && zUpperBoundSet) {
            if (zLowerBound > zUpperBound) { //Warning to make sure both lower and upper values are valid
                zTableWarningLabel.setText("Lower bound has to be smaller than Upper bound.");
            } else if (zLowerBound > 3.99 || zLowerBound < -3.99 || zUpperBound > 3.99 || zUpperBound < -3.99) {
                zTableWarningLabel.setText("Both Lower and Upper bounds need to be between -3.99 to 3.99.");
            } else {

                int zLowerBoundIndexRow = ((int) (zLowerBound * 10) + 39); //Change value from decimal to positive integer from 0 (-3.99) to 78 (3.99) 
                int zUpperBoundIndexRow = ((int) (zUpperBound * 10) + 39);

                ((NormalDistributionTableModel) normalDistributionTable.getModel()).refreshValues(); //Refresh the table to default range from -3.99 to 3.99

                for (int i = 0; i < zLowerBoundIndexRow + (zLowerBoundIndexRow > 39 ? +1 : +0); i++) { //Hiding values below lower limit, varies depending if range includes 0
                    ((NormalDistributionTableModel) normalDistributionTable.getModel()).removeRow(0); //Delete the first row 
                }

                for (int i = 0; i < 78 - zUpperBoundIndexRow + (zUpperBoundIndexRow < 39 ? +1 : +0); i++) { //Hiding values above upper bound, value varies depending if range includes 0
                    ((NormalDistributionTableModel) normalDistributionTable.getModel()).removeRow(78 + (zLowerBoundIndexRow <= 39 && zUpperBoundIndexRow >= 38 || zUpperBoundIndexRow < 39 ? +1 : +0) - zLowerBoundIndexRow - i); //Value varies depending if range includes 0

                }
            }
        }
    }

    public void updateArea() { //Called each time user changes values in x1, x2 input fields
        NormalDistributionWarningLabel.setText(""); //Reset the warning label
        //Get preferences
        int accuracy = NormalDistributionPreferences.getAccuracy(); 
        String integrationType = NormalDistributionPreferences.getIntegrationType();
        
        double area = 0.0;
        double x1 = 0, x2 = 0;
        boolean x1Set = false, x2Set = false;
        try { //Checks if both inputs aren't empty and are not set to - 
            if (!lowerBoundInput.getText().equals("") && !lowerBoundInput.getText().equals("-")) {
                x1 = Double.parseDouble(lowerBoundInput.getText());
                x1Set = true;
            }
            if (!upperBoundInput.getText().equals("") && !upperBoundInput.getText().equals("-")) {
                x2 = Double.parseDouble(upperBoundInput.getText());
                x2Set = true;
            }

        } catch (Exception e) { //Display warning for user to type valid values
            NormalDistributionWarningLabel.setText("X needs to be a numeric value e.g. 4, -3.5 etc.");
        }
        if (x1Set&&aboveRadioButton.isSelected()||x1Set&&belowRadioButton.isSelected()) { //For above and below only one X is required
            if (x1 >= nd.getMean() + nd.getSd() * 4 || x1 <= nd.getMean() - nd.getSd() * 4) { //Check if the value is within 4 deviations
                NormalDistributionWarningLabel.setText("X needs to be within 4 deviations.");
            } else {
                if (aboveRadioButton.isSelected()) {
                    jFreeChartPanel.shadeArea(Double.parseDouble(lowerBoundInput.getText()), true); //Shade the area
                    if (Double.parseDouble(lowerBoundInput.getText()) >= nd.getMean()) { //Check if the value is above or below mean
                        area = 0.5 - NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), nd.getMean(), x1, accuracy); //Calculate the value from mean to X and subtract 0.5
                    } else {
                        area = 0.5 + NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), x1, nd.getMean(), accuracy); //Calculate the value from X to mean and add 0.5
                    }
                }
                if (belowRadioButton.isSelected()) {
                    jFreeChartPanel.shadeArea(Double.parseDouble(lowerBoundInput.getText()), false);
                    if (Double.parseDouble(lowerBoundInput.getText()) <= nd.getMean()) {
                        area = 0.5 - NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), x1, nd.getMean(), accuracy); //Calculate the value from X to mean and subtract 0.5
                    } else {
                        area = 0.5 + NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), nd.getMean(), x1, accuracy); //Calculate the value from mean to X and add 0.5
                    }
                }
            }
        }
        if (x1Set && x2Set) { //For outside & between options
            if (x1 > nd.getMean() + nd.getSd() * 4 || x1 < nd.getMean() - nd.getSd() * 4 || x2 > nd.getMean() + nd.getSd() * 4 || x2 < nd.getMean() - nd.getSd() * 4) { //Check if values are within 4 deviations
                NormalDistributionWarningLabel.setText("X1 & X2 need to be within 4 deviations.");
            } else if (x1 > x2) { //Check if lower bound is smaller than upper bound
                NormalDistributionWarningLabel.setText("Lower bound needs to be smaller than upper bound.");
            } else {
                if (betweenRadioButton.isSelected()) {
                    jFreeChartPanel.shadeArea(Double.parseDouble(lowerBoundInput.getText()), Double.parseDouble(upperBoundInput.getText()), true);
                    area = NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), x1, x2, accuracy); //Calculate the value from x1 to x2

                }
                if (outsideRadioButton.isSelected()) {
                    jFreeChartPanel.shadeArea(Double.parseDouble(lowerBoundInput.getText()), Double.parseDouble(upperBoundInput.getText()), false);
                    area = 1.0 - NormalDistribution.CDF(integrationType, nd.getMean(), nd.getSd(), x1, x2, accuracy); //Calculate the value from x1 to x2 and subtract the result from 1

                }
            }
        }
        areaOutputLabel.setText(df.format(area)); //Display the area
    }

    private void updateMeanAndSd() {
        try {
            NormalDistributionWarningLabel.setText(""); //Empty the warning label
            double m = Double.parseDouble(meanInput.getText()); //Get the mean from the input field
            double sd = Double.parseDouble(standardDeviationInput.getText()); //Get the standard deviation from the input field

            jFreeChartPanel.setMean(m); //Set the mean for graph
            nd.setMean(m); //Set the mean for normal distribution
            if(sd>0){ //Standard deviation has to be a positive number
                jFreeChartPanel.setSd(sd); //Set the standard deviation for graph
                nd.setSd(sd); //set the standard deviation for normal distribution
            } else{
                throw new IllegalArgumentException("Standard deviation needs to be above 0.");
            }

            lowerBoundInput.setText(""); //Empty both input fields for area shading since the current ones are no longer valid after updating sd/mean
            upperBoundInput.setText("");
        } catch (Exception e) { //Check if mean / sd inputs are correct
            NormalDistributionWarningLabel.setText("Mean and Standard Deviation need to be a numeric value e.g. 4, -3.5 etc.");
        }
    }

    /* Getters */
    
    public NormalDistributionTableJScrollPane getNormalDistributionTableJScrollPane() {
        return normalDistributionTableJScrollPane;
    }

    public JTextField getZLowerBoundInput() {
        return zLowerBoundInput;
    }

    public JTextField getZUpperBoundInput() {
        return zUpperBoundInput;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MeanSdInputPanel;
    private javax.swing.JLabel NormalDistributionWarningLabel;
    private javax.swing.JRadioButton aboveRadioButton;
    private javax.swing.JLabel areaLabel;
    private javax.swing.JLabel areaOutputLabel;
    private javax.swing.JPanel areaOutputPanel;
    private javax.swing.ButtonGroup areaTypeGroup;
    private javax.swing.JPanel areaTypeInputPanel;
    private javax.swing.JRadioButton belowRadioButton;
    private javax.swing.JRadioButton betweenRadioButton;
    private javax.swing.JPanel higherBoundInputPanel;
    private UI.Components.JFreeChartPanel jFreeChartPanel;
    private javax.swing.JTextField lowerBoundInput;
    private javax.swing.JPanel lowerBoundInputPanel;
    private javax.swing.JLabel lowerBoundLabel;
    private javax.swing.JTextField meanInput;
    private javax.swing.JLabel meanLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel normalDistributionInputPanel;
    private UI.Components.NormalDistributionTableJScrollPane normalDistributionTableJScrollPane;
    private javax.swing.JPanel normalDistributionTablePanel;
    private javax.swing.JToggleButton normalDistributionTableToggle;
    private javax.swing.JRadioButton outsideRadioButton;
    private javax.swing.JMenuItem preferencesMenuItem;
    private javax.swing.JTextField standardDeviationInput;
    private javax.swing.JLabel standardDeviationLabel;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JTextField upperBoundInput;
    private javax.swing.JLabel upperBoundLabel;
    private javax.swing.JLabel zFromLabel;
    private javax.swing.JLabel zLabel;
    private javax.swing.JTextField zLowerBoundInput;
    private javax.swing.JButton zTableClear;
    private javax.swing.JLabel zTableWarningLabel;
    private javax.swing.JLabel zToLabel;
    private javax.swing.JTextField zUpperBoundInput;
    // End of variables declaration//GEN-END:variables

}
