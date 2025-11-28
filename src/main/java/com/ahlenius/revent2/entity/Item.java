package com.ahlenius.revent2.entity;

public class Item {
    protected String name;
    protected String description;
    protected double dayPrice;


    public Item (){}
    public Item(String name,String description,double day){
        this.name = name;
        this.description = description;
        this.dayPrice = day;}

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
    public double getDayPrice() {
        return dayPrice;
    }
    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

}

