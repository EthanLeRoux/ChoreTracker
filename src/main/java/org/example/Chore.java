package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Chore {
    private LocalDate date;
    private String personOnDuty;
    private String type;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Chore(LocalDate date, String personOnDuty, String type) {
        this.date = date;
        this.personOnDuty = personOnDuty;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPersonOnDuty() {
        return personOnDuty;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return date.format(formatter) + "|" + type + "|" + personOnDuty;
    }

    public static Chore fromString(String line) {
        String[] parts = line.split("\\|");
        LocalDate date = LocalDate.parse(parts[0], formatter);
        String type = parts[1];
        String personOnDuty = parts[2];
        return new Chore(date, personOnDuty, type);
    }
}
