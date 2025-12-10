package com.ahlenius.revent2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private SimpleObjectProperty<LocalDate> startOfRent = new SimpleObjectProperty<>(); // vad göra om inget datum sätts, blir knas mot Json?
    private SimpleBooleanProperty returned = new SimpleBooleanProperty();

    public Rental() {}

    public Rental(Member rentingMember,Item rentalItem, int rentDays, String startOfRent) throws DateTimeParseException {
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
        return "Medlem: "+ rentingMember.getValue().getName()+ ".\nHyr: "+ rentalItem.getValue().getName() +  "\nStart: "+ startOfRent.getValue()+". Planerad hyrestid:"+ rentDays.getValue() + " dagar." ;
    }
}
