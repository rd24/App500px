package com.sampleapp.app500px.entity;

import java.io.Serializable;

public class Photo implements Serializable {
    private int id;
    private String name;
    private String description;
    private int times_viewed;
    private double rating;
    private String created_at;
    private String category;
    private boolean privacy;
    private int width;
    private int height;
    private int votes_count;
    private int comments_count;
    private boolean nsfw;
    private String image_url;
    private String camera;
    private User user;

    public Photo(){}

    public Photo(int id,String name,String description,int times_viewed,double rating,String created_at,
                 String category,boolean privacy,int width,int height,int votes_count,int comments_count,
                 boolean nsfw,String image_url,String camera,User user){
        this.id = id;
        this.name = name;
        this.description = description;
        this.times_viewed = times_viewed;
        this.rating = rating;
        this.created_at = created_at;
        this.category = category;
        this.privacy = privacy;
        this.width = width;
        this.height = height;
        this.votes_count = votes_count;
        this.comments_count = comments_count;
        this.nsfw = nsfw;
        this.image_url = image_url;
        this.camera = camera;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimes_viewed() {
        return times_viewed;
    }

    public void setTimes_viewed(int times_viewed) {
        this.times_viewed = times_viewed;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVotes_count() {
        return votes_count;
    }

    public void setVotes_count(int votes_count) {
        this.votes_count = votes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }
}
