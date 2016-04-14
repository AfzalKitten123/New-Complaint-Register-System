package com.echo.complaintregistersystem.ListItems;

/**
 * Created by Rohit on 4/14/16.
 */
public class ResolvedEntry {
    private String title;
    private String description;
    private String category;
    private String createdDate;
    private String resolvedDate;
    private String byName;
    private String username;
    private String roomNo;
    private String residence;
    private String level;
    private int votes;
    private int id;

    public ResolvedEntry(){

    }

    public ResolvedEntry(String title, String description, String category, String createdDate, String resolvedDate, String byName, String username, String roomNo, String residence, String level, int votes, int id) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdDate = createdDate;
        this.resolvedDate = resolvedDate;
        this.byName = byName;
        this.username = username;
        this.roomNo = roomNo;
        this.residence = residence;
        this.level = level;
        this.votes = votes;
        this.id = id;
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

    public String getByName() {
        return byName;
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

    public String getLevel() {
        return level;
    }

    public int getVotes() {
        return votes;
    }

    public int getId() {
        return id;
    }
}
