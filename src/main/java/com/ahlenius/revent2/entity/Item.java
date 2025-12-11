package com.ahlenius.revent2.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.beans.property.SimpleBooleanProperty;
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

public abstract class Item {
    protected SimpleStringProperty name = new SimpleStringProperty();
    protected SimpleStringProperty description = new SimpleStringProperty();
    protected SimpleDoubleProperty dayPrice= new SimpleDoubleProperty();
    protected SimpleBooleanProperty available = new SimpleBooleanProperty();


    public Item (){}
    public Item(String name,String description,double day,boolean available){
        this.name.set(name);
        this.description.set(description);
        this.dayPrice.set(day);
        this.available.set(true);
    }

    public String getName() {
        return name.get();
    }
    public void setAvailable(boolean available){
        this.available.set(available);
    }
    public boolean isAvailable(){
        return available.get();
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

