package com.ahlenius.revent2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Rental {
    private SimpleObjectProperty<Member> rentingMember = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Item> rentalItem = new SimpleObjectProperty<>();
    private SimpleIntegerProperty rentDays = new SimpleIntegerProperty();
    private SimpleObjectProperty<LocalDate> startOfRent = new SimpleObjectProperty<>();
    private SimpleBooleanProperty returned = new SimpleBooleanProperty();

    public Rental() {}

    public Rental(Member rentingMember,Item rentalItem, int rentDays, String startOfRent) throws DateTimeParseException {
        this.rentingMember.set(rentingMember);
        this.rentalItem.set(rentalItem);
        this.rentDays.set(rentDays);
        this.startOfRent.set(createDateOfRent(startOfRent)); // Probemet?
        this.returned.set(false);
    }

    public Rental(Member rentingMember,Item rentalItem, int rentDays) {
        this.rentingMember.set(rentingMember);
        this.rentalItem.set(rentalItem);
        this.rentDays.set(rentDays);
        this.startOfRent.set(LocalDate.now());
        this.returned.set(false);
    }
    @JsonProperty("rentDays")
    public int getRentDays(){
        return rentDays.get();
    }

    @JsonProperty("rentDays")
    public void setRentDays(int rentDays) {
        this.rentDays.set(rentDays);
    }

    @JsonProperty("rentalItem")
    public void setRentalItem(Item rentalItem) {
        this.rentalItem.set(rentalItem);
    }
    @JsonProperty("rentalItem")
    public Item getRentalItem() {
        return rentalItem.get();
    }

    @JsonProperty("startOfRent")
    public LocalDate getStartOfRent(){
        return startOfRent.get();
    }

    @JsonProperty("startOfRent")
    public void setStartOfRent(LocalDate startOfRent) {
        this.startOfRent.set(startOfRent);
    }
    @JsonProperty("returned")
    public void setReturned(boolean returned){
        this.returned.set(returned);
    }

    @JsonProperty("returned")
    public boolean isReturned(){
        return returned.get();
    }

    @JsonProperty("rentingMember")
    public Member getRentingMember() {
        return rentingMember.get();
    }

    @JsonProperty("rentingMember")
    public void setRentingMember(Member rentingMember) {
        this.rentingMember.set(rentingMember);
    }
    //_____________________________________________________________________________________
    // skapa start
    public LocalDate createDateOfRent(String YYYYMMDD) throws DateTimeParseException {
        DateTimeFormatter styleDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetOfRent = LocalDate.parse(YYYYMMDD, styleDate);
        return datetOfRent;    }

    @Override
    public String toString() {
        return "Medlem: "+ rentingMember.getValue().getName()+ ".\nHyr: "+ rentalItem.getValue().getName() +  "\nStart: "+ startOfRent.getValue()+". Planerad hyrestid: "+ rentDays.getValue() + " dagar." ;
    }
}
