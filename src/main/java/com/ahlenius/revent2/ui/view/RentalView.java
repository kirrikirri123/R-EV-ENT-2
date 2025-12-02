package com.ahlenius.revent2.ui.view;

import javafx.scene.layout.BorderPane;

public class RentalView {
    //Här hanteras bokning och återlämning
    private BorderPane rentalPane = new BorderPane();

    public RentalView (){

    }

    public  BorderPane getRentalPane(){
        return rentalPane;
    }
}
