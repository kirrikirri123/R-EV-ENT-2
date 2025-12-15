package com.ahlenius.revent2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.beans.property.SimpleBooleanProperty;

//@JsonTypeName("BouncyCastle")
public class BouncyCastle extends Item {
     private SimpleBooleanProperty indoorUse = new SimpleBooleanProperty();

    public BouncyCastle(){
    }

    public BouncyCastle(String name,String description,double day,boolean available, boolean indoorUse){
        super(name, description,day,available);
        this.indoorUse.set(indoorUse);
    }
    @JsonProperty("indoorUse")
    public boolean isIndoorUse() {
        return indoorUse.get();
    }

    @JsonProperty("indoorUse")
    public void setIndoorUse(boolean indoorUse){
        this.indoorUse.set(indoorUse);

    }
    @Override
    public String toString(){
        return this.name.getValue() + " - "+ this.description.getValue() + ".";
    }}


