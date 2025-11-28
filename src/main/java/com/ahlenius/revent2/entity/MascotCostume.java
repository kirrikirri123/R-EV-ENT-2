package com.ahlenius.revent2.entity;

public class MascotCostume extends Item {
    private String season;


    public MascotCostume(){}
    public MascotCostume(String name,String description,double day,String season){
        super(name, description, day);
        this.season = season;}

    public String getSeason(){
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    @Override
    public String toString(){
        return "Produkt: "+ this.name + ","+ this.description + ".";
    }

}
