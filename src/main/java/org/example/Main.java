package org.example;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ChoreGUI gui = new ChoreGUI();
        gui.setVisible(true);
        gui.setSize(500,500);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}