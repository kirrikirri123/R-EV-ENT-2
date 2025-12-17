package com.ahlenius.revent2.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.beans.property.SimpleStringProperty;


@JsonTypeName("MascotCostume")
public class MascotCostume extends Item {
    private SimpleStringProperty season = new SimpleStringProperty();


    public MascotCostume(){
    }
    public MascotCostume(String name,String description,double day,boolean available,String season){
        super(name, description, day,available);
        this.season.set(season);}

    @JsonProperty("season")
    public String getSeason(){
        return season.get();
    }

    @JsonProperty("season")
    public void setSeason(String season) {
        this.season.set(season);
    }
    @Override
    public String toString(){
        return this.name.getValue() + " - "+ this.description.getValue();
    }

}
