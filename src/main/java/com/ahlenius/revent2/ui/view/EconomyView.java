package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EconomyView {
    // Här sköter man sina transaktionskoller osv.
    private RentalService rentalService;
    private BorderPane economyPane = new BorderPane();

        public EconomyView(RentalService rentalService){
            this.rentalService = rentalService;
            VBox economyBox = new VBox();
            economyBox.setAlignment(Pos.CENTER);
            economyBox.setPadding(new Insets(25,35,25,35));
            Label headerEconomy = new Label("Ekonomi");
            Label businessToDay = new Label("Totala intäkter på affärer gjorda idag beräknas bli ");
            Label disclaimer = new Label("* obs ev. rabatter ej inkluderade i beräkning.");
            String income = String.valueOf(rentalService.sumBusinessToDayReturnDouble())+" kr ex moms.*";
            Label sum = new Label(income);
            GridPane economyGPane = new GridPane();
            economyGPane.add(businessToDay,0,0);
            economyGPane.add(sum,1,0);
            economyGPane.add(disclaimer,0,1);
            economyGPane.setAlignment(Pos.CENTER);
            economyGPane.setVgap(5);
            economyBox.getChildren().addAll(headerEconomy,economyGPane);

            economyPane.setCenter(economyBox);
        }

    public BorderPane getEconomyPane(){
            return economyPane;
    }

}
