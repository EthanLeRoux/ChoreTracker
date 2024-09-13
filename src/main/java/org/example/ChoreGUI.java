package org.example;

import org.example.Chore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ChoreGUI extends JFrame implements ActionListener {
    JPanel panel;
    JLabel choreLabel;
    String[] chores;
    JComboBox<String> choreComboBox;
    JLabel personOnDutyLabel;
    JTextField personOnDutyField;
    JButton addButton;
    JButton viewButton;
    JButton closeButton;
    ArrayList<Chore> choresList;
    FileHandler sfh;

    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    public ChoreGUI() {
        super("Chore Tracker");
        // Create a panel to hold the components
        panel = new JPanel();
        //panel.setLayout(new GridLayout(4, 2, 10, 10));  // 4 rows, 2 columns, spacing of 10

        // Create a label and combo box for the chores
        choreLabel = new JLabel("Chore:");
        chores = new String[]{"Dishes", "Yard", "Laundry"};
        choreComboBox = new JComboBox<>(chores);

        // Create a label and text field for person on duty
        personOnDutyLabel = new JLabel("Person On Duty:");
        personOnDutyField = new JTextField(15);

        // Create the "Add Chore" button
        addButton = new JButton("Add Chore");
        viewButton = new JButton("View Chores");
        closeButton = new JButton("Close Button");

        // Initialize SerFileHandler
        sfh = new FileHandler();

        // Table setup
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        tableModel.addColumn("Date");
        tableModel.addColumn("Type");
        tableModel.addColumn("Person On Duty");

        setGui();
    }

    public void setGui() {
        this.setLayout(new BorderLayout());
        panel.add(choreLabel);
        panel.add(choreComboBox);

        panel.add(personOnDutyLabel);
        panel.add(personOnDutyField);

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(closeButton);

        scrollPane.setViewportView(table);
        panel.add(scrollPane);

        this.add(panel);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    private void refreshTable() {
        try {
            ArrayList<Chore> arr = sfh.setReader();
            tableModel.setRowCount(0); // Clear existing rows

            for (Chore chore : arr) {
                Object[] obj = {chore.getDate(), chore.getType(), chore.getPersonOnDuty()};
                tableModel.addRow(obj);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sfh.setWriter();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        if (e.getSource() == addButton) {
            String person = personOnDutyField.getText();
            LocalDate time = LocalDate.now();
            String type = choreComboBox.getSelectedItem().toString();

            Chore chore = new Chore(time, person, type);
            try {
                sfh.writeChore(chore);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            personOnDutyField.setText("");
            refreshTable();
        }

        if (e.getSource() == viewButton) {
            try {
                ArrayList<Chore> arr= sfh.setReader();
                tableModel.setRowCount(0);

                for(int i = 0; i<arr.size();i++){
                    Object[] obj = {arr.get(i).getDate(),arr.get(i).getType(),arr.get(i).getPersonOnDuty()};
                    tableModel.addRow(obj);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        if (e.getSource() == closeButton) {

        }
    }
}
