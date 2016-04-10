package com.echo.complaintregistersystem.ListItems;

public class Individual_WardenEntry {
    private String title;
    private String description;
    private String category;
    private String createdDate;
    private String resolvedDate;
    private String byName;
    private String username;
    private String roomNo;
    private String residence;
    private String Comments;
    private int id;


    public Individual_WardenEntry(){

    }

    public Individual_WardenEntry(String title, String description, String category, String createdDate, String resolvedDate, String byName, String username, String roomNo, String residence/*, String comments*/,int id ) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
        this.byName = byName;
        this.username = username;
        this.roomNo = roomNo;
        this.residence = residence;
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getCreatedDate(){
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public String getByName(){
        return byName;
    }

    public String getCategory() {
        return category;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public String getUsername() {
        return username;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getResidence() {
        return residence;
    }

    public int getid() {
        return id;
    }
}
