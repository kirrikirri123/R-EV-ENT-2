package com.ahlenius.revent2.ui;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.MemberRegistry;
import com.ahlenius.revent2.repository.RentalRegistry;
import com.ahlenius.revent2.service.MembershipService;
import com.ahlenius.revent2.service.RentalService;
import com.ahlenius.revent2.ui.controller.ButtonController;
import com.ahlenius.revent2.ui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReventApp extends Application {
    Inventory inventory = new Inventory();
    MemberRegistry memberRegistry = new MemberRegistry();
    RentalRegistry rentalRegistry = new RentalRegistry();

    MembershipService memberService = new MembershipService(memberRegistry); //Plocka in även PI och S objektet?
    RentalService rentalService = new RentalService(inventory,rentalRegistry);

    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView(memberService);
    ProductView productView = new ProductView(rentalService);
    RentalView rentalView = new RentalView(rentalService);
    HistoryView historyView = new HistoryView(rentalService);
    EconomyView economyView = new EconomyView();
    ButtonController buttonController= new ButtonController(startView,mainView,membershipView,productView,rentalView,economyView,historyView);
    Scene start,main;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");
        memberService.defaultList();
        rentalService.defaultList();


        start = new Scene(startView.getStartView(),500,450);
        main = new Scene(mainView.getMainView(),825,800);
       start.getStylesheets().add("/com/ahlenius/revent2/revent_style.css");
       main.getStylesheets().add("/com/ahlenius/revent2/revent_style.css");
        stage.setScene(start);
        stage.show();

        // Action på knappar kopplade till stage och scene.
        mainView.getQuitBtn().setOnAction(actionEvent -> {
            stage.close();
        });
         startView.getImageStart().setOnMouseClicked(mouseEvent -> {
         startView.getBottomLabel().setText("Du klickade!");
         changeScene(stage,main);
          });

          }
    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
}


