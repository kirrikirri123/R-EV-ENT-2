package com.ahlenius.revent2.ui;
import com.ahlenius.revent2.ui.controller.ButtonController;
import com.ahlenius.revent2.ui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReventApp extends Application {
    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView();
    ProductView productView = new ProductView();
    RentalView rentalView = new RentalView();
    EconomyView economyView = new EconomyView();
    ButtonController buttonController= new ButtonController(startView,mainView,membershipView,productView,rentalView,economyView);
    Scene start,main;

            @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");

        start = new Scene(startView.getStartView(),500,450);
        main = new Scene(mainView.getMainView(),800,750);
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

//Styling
// start.getStylesheets().add(getClass().getResource("/com/ahlenius/revent2/revent_style.css").toExternalForm());
// main.getStylesheets().add(getClass().getResource("/com/ahlenius/revent2/revent_style.css").toExternalForm());

