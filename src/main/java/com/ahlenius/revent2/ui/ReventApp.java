package com.ahlenius.revent2.ui;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.MemberRegistry;
import com.ahlenius.revent2.repository.RentalRegistry;
import com.ahlenius.revent2.service.JsonService;
import com.ahlenius.revent2.service.MembershipService;
import com.ahlenius.revent2.service.RentalService;
import com.ahlenius.revent2.ui.controller.ButtonController;
import com.ahlenius.revent2.ui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ReventApp extends Application {
    Inventory inventory = new Inventory();
    MemberRegistry memberRegistry = new MemberRegistry();
    RentalRegistry rentalRegistry = new RentalRegistry();

    //Plocka in även PI och S objektet?
    JsonService jsonService = new JsonService(inventory,rentalRegistry,memberRegistry);
    RentalService rentalService = new RentalService(inventory,rentalRegistry,jsonService);
    MembershipService memberService = new MembershipService(memberRegistry,jsonService);

    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView(memberService,jsonService);
    ProductView productView = new ProductView(rentalService,jsonService);
    RentalView rentalView = new RentalView(rentalService,memberService, jsonService);
    HistoryView historyView = new HistoryView(rentalService);
    EconomyView economyView = new EconomyView();
    ButtonController buttonController= new ButtonController(startView,mainView,membershipView,productView,rentalView,economyView,historyView);
    Scene start,main;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");
        //Ladda in listor
       // rentalService.defaultList();
        try {
            jsonService.loadMemberJsonToArrayList();
        } catch (IOException e) {System.out.println(e.getMessage());}
        try {
            jsonService.loadItemJsonToArrayList(); // Funkar ej
        } catch (IOException e) {System.out.println(e.getMessage());}
        try{
            jsonService.loadRentalJsonToArrayList();
        } catch (IOException e) {System.out.println(e.getMessage());}

       start = new Scene(startView.getStartView(),500,450);
       main = new Scene(mainView.getMainView(),825,800);
       start.getStylesheets().add("/com/ahlenius/revent2/revent_style.css");
       main.getStylesheets().add("/com/ahlenius/revent2/revent_style.css");
        stage.setScene(start);
        stage.show();

        Alert saveBeforeQuit = new Alert(Alert.AlertType.CONFIRMATION);
        saveBeforeQuit.setHeaderText("Spara innan avslut?");
        saveBeforeQuit.setTitle("Spara ändringar");
        saveBeforeQuit.setContentText("Önskar du att spara dina ändringar innan programmet stängs?");
        // OM ja spara i alla listor, OM nej avsluta bara rätt av.


        // Action på knappar kopplade till stage och scene.
        mainView.getQuitBtn().setOnAction(actionEvent -> {
            saveBeforeQuit.showAndWait();
            stage.close();
        });
         startView.getImageStart().setOnMouseClicked(mouseEvent -> { // Flytta denna till buttoncontroller alt. flytta metodanrop till konstruktor?
         changeScene(stage,main);});
          }
    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
}


