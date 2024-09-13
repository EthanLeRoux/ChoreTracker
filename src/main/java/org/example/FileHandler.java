package org.example;

import java.io.*;
import java.util.*;

public class FileHandler {
    BufferedWriter writer;
    FileWriter fileWriter;

    BufferedReader reader;
    FileReader fileReader;
    ArrayList<Chore> arrChores;
    public void setWriter() throws IOException {
        fileWriter = new FileWriter("chores.txt", true);
        writer = new BufferedWriter(fileWriter);
    }

    ArrayList<Chore> setReader() throws IOException {
        fileReader = new FileReader("chores.txt");
        reader = new BufferedReader(fileReader);
        arrChores = new ArrayList<Chore>();

        String line;
        while ((line = reader.readLine()) != null) {
                Chore chore = Chore.fromString(line);
                arrChores.add(chore);
        }
        reader.close(); // Close reader after use
        return arrChores;
    }

    void writeChore(Chore chore) throws IOException {
        writer.write(chore.toString());
        writer.newLine();

        System.out.println(chore.toString());
        writer.close();
    }


}