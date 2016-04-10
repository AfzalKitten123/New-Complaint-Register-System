package com.echo.complaintregistersystem.ListItems;

/**
 * Created by Rohit on 4/9/16.
 */
public class CommentEntry {
    private String title;
    private String name;
    private String date;

    public CommentEntry(String title, String name, String date) {
        this.title = title;
        this.name = name;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
