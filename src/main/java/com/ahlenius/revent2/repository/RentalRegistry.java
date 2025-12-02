package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;

import java.util.HashMap;
import java.util.List;

public class RentalRegistry {
    private HashMap<Member, List<Rental>> rentalList = new HashMap<Member, List<Rental>>();// borde ändras till <Member,List<Rental> .med Rentalobjekts lista som värde så kan samma medlem hyra flera saker.

    public RentalRegistry(){}

    public HashMap<Member,List<Rental>> getRentalList() {
        return rentalList;
    }}


