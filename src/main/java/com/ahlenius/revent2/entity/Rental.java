package com.ahlenius.revent2.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Rental {    // Proffstipset var att lägga ett member objekt i fält också. Varför inte gjort det??
    private Item rentalItem;
    private int rentDays;
    private LocalDate startOfRent;
    private boolean returned;

    public Rental() {
    }

    public Rental(Item rentalItem, int rentDays, String startOfRent) {
        this.rentalItem = rentalItem;
        this.rentDays = rentDays;
        this.startOfRent = createDateOfRent(startOfRent);
        this.returned = false;
    }

    public Rental(Item rentalItem, int rentDays) {
        this.rentalItem = rentalItem;
        this.rentDays = rentDays;
        this.startOfRent = LocalDate.now();
        this.returned = false;
    }

    public int getRentDays(){
        return rentDays;
    }

    public void setRentDays(int rentDays) {
        this.rentDays = rentDays;
    }

    public void setRentalItem(Item rentalItem) {
        this.rentalItem = rentalItem;
    }

    public Item getRentalItem() {
        return rentalItem;
    }
    public LocalDate getStartOfRent(){
        return startOfRent;
    }
    public void setReturned(boolean returned){
        this.returned= returned;
    }
    public boolean isReturned(){
        return returned;
    }

    //_____________________________________________________________________________________
    // skapa start
    public LocalDate createDateOfRent(String YYYYMMDD) throws DateTimeParseException {
        DateTimeFormatter styleDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetOfRent = LocalDate.parse(YYYYMMDD, styleDate);
        return datetOfRent;    }

    @Override
    public String toString() {
        return " Hyresobjekt: " + this.rentalItem.getName() + ". Hyrestid i dagar: " + this.rentDays+". Datum för hyresstart: "+ this.startOfRent + " Återlämnad ? "+ this.returned;

    }
}
