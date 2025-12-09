package com.ahlenius.revent2.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("MascotCostume")
public class MascotCostume extends Item {
    private String season; // se om detta stör ObservableList


    public MascotCostume(){
    }
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
        return this.name.getValue() + " - "+ this.description.getValue();
    }

}
