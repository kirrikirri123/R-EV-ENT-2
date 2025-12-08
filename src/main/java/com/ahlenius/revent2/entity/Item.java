package com.ahlenius.revent2.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = BouncyCastle.class, name = "BouncyCastle"),
        @JsonSubTypes.Type(value = MascotCostume.class, name = "MascotCostume")
})

abstract public class Item {
    protected SimpleStringProperty name= new SimpleStringProperty();
    protected SimpleStringProperty description = new SimpleStringProperty();
    protected SimpleDoubleProperty dayPrice= new SimpleDoubleProperty();


    public Item (){}
    public Item(String name,String description,double day){
        this.name.set(name);
        this.description.set(description);
        this.dayPrice.set(day);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getDescription() {
        return description.get();
    }
    public void setDescription(String description) {
        this.description.set(description);
    }
    public double getDayPrice() {
        return dayPrice.get();
    }
    public void setDayPrice(double dayPrice) {
        this.dayPrice.set(dayPrice);
    }

}

