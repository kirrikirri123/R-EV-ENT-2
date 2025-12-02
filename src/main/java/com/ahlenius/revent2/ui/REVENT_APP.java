package com.ahlenius.revent2.ui;
import com.ahlenius.revent2.ui.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class REVENT_APP extends Application {
    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView();
    ProductView productView = new ProductView();
    RentalView rentalView = new RentalView();
    EconomyView economyView = new EconomyView();
    Scene start,main,membership,products,rental,economy;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");


        start = new Scene(startView.getStartView(),800,450);
        stage.setScene(start);
        stage.show();


    }

    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
}
