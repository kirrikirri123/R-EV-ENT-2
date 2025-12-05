package com.ahlenius.revent2.entity;

public class BouncyCastle extends Item {
    private boolean indoorUse; // se om detta stör ObservableList


    public BouncyCastle(){}
    public BouncyCastle(String name,String description,double day,boolean indoorUse){
        super(name, description, day);
        this.indoorUse = indoorUse;
    }

    public boolean isIndoorUse() {
        return indoorUse;
    }
    public void setIndoorUse(boolean indoorUse){
        this.indoorUse = indoorUse;

    }
    @Override
    public String toString(){
        return "Produkt: "+ this.name + "."+ this.description + ".";
    }}


