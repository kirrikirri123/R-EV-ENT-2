package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;


public class RentalRegistry {
    private List<Rental> rentalList = new ArrayList<>();
    private ObservableList<Rental> rentalObsList = FXCollections.observableArrayList();


    public RentalRegistry(){}

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public ObservableList<Rental> getRentalObsList(){
    return rentalObsList;}


    public void add(Rental rental){
        rentalList.add(rental);
     rentalObsList.add(rental);
    }
    public void remove(Rental rental){
        rentalList.remove(rental);
   rentalObsList.remove(rental);
    }

    public void addList(List<Rental> tempRental){
        rentalList.addAll(tempRental);
   rentalObsList.addAll(tempRental);
    }


}


