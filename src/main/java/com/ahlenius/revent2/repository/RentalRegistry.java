package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class RentalRegistry {
    private HashMap<Member, List<Rental>> rentalList = new HashMap<Member, List<Rental>>();// borde ändras till <Member,List<Rental> .med Rentalobjekts lista som värde så kan samma medlem hyra flera saker.
    private ObservableMap<Member, List<Rental>> rentalObsList = FXCollections.observableHashMap();


    public RentalRegistry(){}

    public HashMap<Member,List<Rental>> getRentalList() {
        return rentalList;
    }}


