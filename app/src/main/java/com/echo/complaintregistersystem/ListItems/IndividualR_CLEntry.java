package com.echo.complaintregistersystem.ListItems;

public class IndividualR_CLEntry {
    private String title;
    private String description;
    private String category;
    private String createdDate;
    private String resolvedDate;
    private String byName;
    private String username;
    private String roomNo;
    private String residence;
    private int id;

    public IndividualR_CLEntry(){

    }

    public IndividualR_CLEntry(String title, String description, String category, String createdDate, String resolvedDate, String username, String byName, String roomNo, String residence/*, String comments*/,int id) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
        this.username = username;
        this.byName = byName;
        this.roomNo = roomNo;
        this.residence = residence;
        this.id = id;
    }

    public String getByName(){
        return byName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getCreatedDate() {
        return createdDate;
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

    public int getId() {
        return  id;
    }

}
