
package org.example;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 222622172
 */
public class BuildGui extends JFrame implements ActionListener,ItemListener{
    private JLabel lblFirstName,lblLastName,lblDepart,lblEmpType, lblBlank;
    private JPanel panelTop, panelCenter, panelCenter1,panelTable, panelBottom;
    private JTextField txtFName, txtLName;
    private JComboBox combo;
    private JRadioButton radioPerm, radioTemp;
    private JButton btnAdd, btnExit;
    private ButtonGroup radioGroup;
    private String[] arrDepart;
    private ArrayList arrUser;
    private String userFName;
    private String userLName;
    private String empDepart;
    private String empPerm;
    private String empTemp;
    private String empType;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    
    public BuildGui(){
        super("Prac 7: JTable");
       
        //Panels
        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelCenter1 = new JPanel();
        panelTable = new JPanel();
        panelBottom = new JPanel();
        
        //Labels
        lblFirstName = new JLabel("First Name:");
        lblLastName = new JLabel("Last Name:");
        lblDepart = new JLabel("Department:");
        lblEmpType = new JLabel("Employment Type:");
        lblBlank = new JLabel();
        
        //Text Fields
        txtFName = new JTextField();
        txtLName = new JTextField();
        
        //Buttons
        btnAdd = new JButton("Add to Table");
        btnExit = new JButton("Exit");
        
        //Radio Buttons
        radioPerm = new JRadioButton("Permanent");
        radioTemp = new JRadioButton("Temporary");
        radioGroup = new ButtonGroup();
        
        radioGroup.add(radioPerm);
        radioGroup.add(radioTemp);
        
        //Table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        //table = new JTable();
        scrollPane = new JScrollPane(table);
 
        //Array of Deapartments
        arrDepart = new String[4];
        arrDepart[0] = "select";
        arrDepart[1] = "Graphic Design";
        arrDepart[2] = "Civil Engineering";
        arrDepart[3] = "Information Technology";

        //ComboBox menu
        combo = new JComboBox(arrDepart);
        
        //User Arr List
        arrUser = new ArrayList();
        
        //setGui();
    }
    public void setGui(){
        this.setLayout(new GridLayout(5,1,5,5));
        panelTop.setLayout(new GridLayout(0,2,5,5));
        panelCenter.setLayout(new GridLayout(2,2));
        panelCenter1.setLayout(new GridLayout(0,2));
        panelBottom.setLayout(new GridLayout(0,2));
        
          tableModel.addColumn("Department");
          tableModel.addColumn("First Name");
          tableModel.addColumn("Last Name");
          tableModel.addColumn("Employment Type");
                  
          panelTop.add(lblDepart);
          panelTop.add(combo);
          
          panelCenter.add(lblFirstName);
          panelCenter.add(txtFName);
          panelCenter.add(lblLastName);
          panelCenter.add(txtLName);
          
          panelCenter1.add(lblEmpType);
          panelCenter1.add(radioPerm);
          panelCenter1.add(lblBlank);
          panelCenter1.add(radioTemp);
          
          
          scrollPane.add(table);
          //panelTable.add(table);
          scrollPane.setViewportView(table);
          panelTable.add(scrollPane);
          
          
          panelBottom.add(btnAdd);
          panelBottom.add(btnExit);
          
          this.add(panelTop);
          this.add(panelCenter);
          this.add(panelCenter1);
          this.add(panelTable);
          this.add(panelBottom);
          
          btnAdd.addActionListener(this);
          btnExit.addActionListener(this);
          combo.addItemListener(this);
          
    }
        @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==combo){
            combo.getSelectedItem();
        }
    }

    public void resetVals(){
            txtFName.setText("");
            txtLName.setText("");
            combo.setSelectedIndex(0);
            radioGroup.clearSelection();      
    }
    
    public void checkIfValuesAdded(){
        if(txtFName.getText().equalsIgnoreCase("")
           && txtLName.getText().equalsIgnoreCase("")
           &&!radioPerm.isSelected()
           && !radioTemp.isSelected()
           ){
            JOptionPane.showMessageDialog(null, "Nothing was Selected");
        }
        
        else{
            if(radioPerm.isSelected()){ 
                empType = empPerm;
            }
            else if(radioTemp.isSelected()){
                empType = empTemp;
            }

            Object[] newEmp = {empDepart,userFName,userLName,empType};
            tableModel.addRow(newEmp);
            //tableModel.fireTableDataChanged();

            resetVals();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         //
        userFName = txtFName.getText();
        userLName = txtLName.getText();
        empDepart = combo.getSelectedItem().toString();
        empPerm = radioPerm.getText();
         empTemp = radioTemp.getText();
         empType = "";
         
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        
        if(e.getActionCommand().equals("Add to Table")){
            
            checkIfValuesAdded();
            }
       }    
    }


    
    
    
    
