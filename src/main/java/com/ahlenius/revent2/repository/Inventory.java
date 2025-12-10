package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
    // Databas för produkter.
    private List<Item> itemList = new ArrayList<>();
    private ObservableList<Item> itemsObsList = FXCollections.observableArrayList();

    public Inventory (){}

    public List<Item> getItemList() { // synkas med Json
        return itemList;
    }
    public ObservableList<Item> getItemsObsList(){ // används för View
        return itemsObsList;

    }
     public void add(Item item){
        itemList.add(item);
        itemsObsList.add(item);
     }
     public void remove(Item item){
        itemList.remove(item);
        itemsObsList.remove(item);
     }
    public void addList(List<Item> tempItems){
        itemList.addAll(tempItems);
        itemsObsList.addAll(tempItems);
    }


}

