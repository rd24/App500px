package com.sampleapp.app500px.entity;

import java.io.Serializable;

public class Filters implements Serializable{
    private boolean category;
    private boolean exclude;

    public Filters(){}

    public  Filters(boolean category,boolean exclude){
        this.category = category;
        this.exclude = exclude;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }
}
