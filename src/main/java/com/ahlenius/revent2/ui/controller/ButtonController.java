package com.ahlenius.revent2.ui.controller;

import com.ahlenius.revent2.ui.view.*;

public class ButtonController {
    // Här försöker vi lägga actions för knappar.
    StartView startView = new StartView();
    MainView mainView = new MainView();
    MembershipView membershipView = new MembershipView();
    ProductView productView = new ProductView();
    RentalView rentalView = new RentalView();
    EconomyView economyView = new EconomyView();


    public ButtonController(StartView startView, MainView mainview, MembershipView membershipView,ProductView productView,RentalView rentalView,EconomyView economyView) {
        this.startView = startView;
        this.mainView = mainview;
        this.membershipView = membershipView;
        this.productView = productView;
        this.rentalView = rentalView;
        this.economyView = economyView;


        // Medlemsknappar i meny.
        mainView.getNewMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getGridPaneNewMem());
        });

        mainView.getHistoryMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getMemHistoryPane());

        });

        mainView.getSearchMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getSearchMemPane());
        });

        mainView.getUpdateMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getUpdateMemPane());
        });

        // Produktknappar i meny
        mainView.getProducts().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(productView.getProductPane());
             });

        mainView.getEditProduct().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(productView.getProductPane());
            productView.getProductPane().setCenter(productView.getUpdateProdPane());
            });

        mainView.getNewProducts().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(productView.getProductPane());
            productView.getProductPane().setCenter(productView.getNewProdPane());
            });

            }

}
