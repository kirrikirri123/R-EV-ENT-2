package com.ahlenius.revent2.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Rental {
    private SimpleObjectProperty<Member> rentingMember;
    private SimpleObjectProperty<Item> rentalItem;
    private SimpleIntegerProperty rentDays = new SimpleIntegerProperty();
    private SimpleObjectProperty<LocalDate> startOfRent;
    private SimpleBooleanProperty returned = new SimpleBooleanProperty();

    public Rental() {
    }

    public Rental(Member rentingMember,Item rentalItem, int rentDays, String startOfRent) {
        this.rentingMember.set(rentingMember);
        this.rentalItem.set(rentalItem);
        this.rentDays.set(rentDays);
        this.startOfRent.set(createDateOfRent(startOfRent));
        this.returned.set(false);
    }

    public Rental(Member rentingMember,Item rentalItem, int rentDays) {
        this.rentingMember.set(rentingMember);
        this.rentalItem.set(rentalItem);
        this.rentDays.set(rentDays);
        this.startOfRent.set(LocalDate.now());
        this.returned.set(false);
    }

    public int getRentDays(){
        return rentDays.get();
    }

    public void setRentDays(int rentDays) {
        this.rentDays.set(rentDays);
    }
    public void setRentalItem(Item rentalItem) {
        this.rentalItem.set(rentalItem);
    }
    public Item getRentalItem() {
        return rentalItem.get();
    }
    public LocalDate getStartOfRent(){
        return startOfRent.get();
    }
    public void setReturned(boolean returned){
        this.returned.set(returned);
    }
    public boolean isReturned(){
        return returned.get();
    }

    public Member getRentingMember() {
        return rentingMember.get();
    }
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
        return " Hyresobjekt: " + this.rentalItem.getName() + ". Hyrestid i dagar: " + this.rentDays.getValue()+". Datum för hyresstart: "+ this.startOfRent.getValue() + " Återlämnad ? "+ this.returned.getValue();

    }
}
