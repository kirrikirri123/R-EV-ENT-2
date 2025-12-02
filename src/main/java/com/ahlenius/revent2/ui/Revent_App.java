package com.ahlenius.revent2.ui;
import com.ahlenius.revent2.ui.controller.ButtonController;
import com.ahlenius.revent2.ui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Revent_App extends Application {
    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView();
    ProductView productView = new ProductView();
    RentalView rentalView = new RentalView();
    EconomyView economyView = new EconomyView();
    ButtonController buttonController= new ButtonController(startView);
    Scene start,main;
    Button falseButton = new Button();

    public Revent_App(){}

        @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");

        //Styling
       // start.getStylesheets().add(getClass().getResource("/com/ahlenius/revent2/revent_style.css").toExternalForm());
       // main.getStylesheets().add(getClass().getResource("/com/ahlenius/revent2/revent_style.css").toExternalForm());

      // stage.getScene().setRoot(mainView.getMainView());

        // Set centerMain
        mainView.getMainView().setCenter(membershipView.getMemberPane());


        start = new Scene(startView.getStartView(),800,450);
        main = new Scene(mainView.getMainView(),800,750);
        stage.setScene(main);
        stage.show();

        // change scene
        falseButton.setOnAction(actionEvent -> {
            changeScene(stage,main);
        });
        // Action på knappar kopplade till stage och scene.
        mainView.getQuitBtn().setOnAction(actionEvent -> {
            stage.close();
        });

        }

    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
    public void changeSceneToMain(){
    //   falseButton.isPressed(); Funkar ej
    }
}
