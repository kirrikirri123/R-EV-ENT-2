package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // Databas för produkter.
    private List<Item> itemsList = new ArrayList<>();

    public Inventory (){}

    public List<Item> getItemsList(){
        return itemsList;
    }
}

