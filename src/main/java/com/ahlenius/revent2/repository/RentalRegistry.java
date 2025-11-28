package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;

import java.util.HashMap;

public class RentalRegistry {
    private HashMap<Member,Rental> rentalList = new HashMap<Member, Rental>();// borde ändras till <Member,List<Rental> .med Rentalobjekts lista som värde så kan samma medlem hyra flera saker.

    public RentalRegistry(){}

    public HashMap<Member,Rental> getRentalList() {
        return rentalList;
    }}


