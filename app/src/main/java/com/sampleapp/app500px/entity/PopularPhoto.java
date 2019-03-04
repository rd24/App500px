package com.sampleapp.app500px.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PopularPhoto implements Serializable {
    private String feature;
    private Filters filters;
    private int current_page;
    private int total_pages;
    private int total_items;
    private ArrayList<Photo> pictures;

    public PopularPhoto(){}

    public PopularPhoto(String feature,Filters filters,int current_page,int total_pages,int total_items,ArrayList<Photo> pictures){
        this.feature = feature;
        this.filters = filters;
        this.current_page = current_page;
        this.total_pages = total_pages;
        this.total_items = total_items;
        this.pictures = pictures;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }


    public ArrayList<Photo> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Photo> pictures) {
        this.pictures = pictures;
    }
}
