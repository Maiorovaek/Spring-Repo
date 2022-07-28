package ru.models;

public class Reader {
    private int reader_id;
    private String name;
    private int year;

    public Reader() {
    }

    public Reader(int reader_id, String name, int year) {
        this.reader_id = reader_id;
        this.name = name;
        this.year = year;

    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
