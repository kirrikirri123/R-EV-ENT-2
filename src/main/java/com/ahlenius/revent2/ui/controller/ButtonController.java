package com.ahlenius.revent2.ui.controller;

import com.ahlenius.revent2.ui.view.*;

public class ButtonController {
    private StartView startView = new StartView();
    private MainView mainView = new MainView();
    private MembershipView membershipView = new MembershipView();
    private ProductView productView = new ProductView();
    private RentalView rentalView = new RentalView();
    private EconomyView economyView = new EconomyView();
    private HistoryView historyView = new HistoryView();


    public ButtonController(StartView start, MainView main, MembershipView membership,ProductView product,RentalView rental,EconomyView economy,HistoryView history) {
        this.startView = start;
        this.mainView = main;
        this.membershipView = membership;
        this.productView = product;
        this.rentalView = rental;
        this.economyView = economy;
        this.historyView = history;


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
            productView.getProductPane().setCenter(productView.getItemView());
        });

        mainView.getEditProduct().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(productView.getProductPane());
            productView.getProductPane().setCenter(productView.getUpdateProdPane());
        });

        mainView.getNewProducts().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(productView.getProductPane());
            productView.getProductPane().setCenter(productView.getNewProdBox());
        });

        mainView.getViewAccesibleProd().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });

         // - ProduktView funktioner
        productView.getViewAccesibleProdBtn().setOnAction(actionEvent ->{
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });

        // Uthyrningsknappar i meny

        mainView.getAccesibleProd().setOnAction(actionEvent -> {
                    mainView.getMainView().setCenter(rentalView.getRentalPane());
                    rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });

        mainView.getNewRental().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getNewRentalBox());
        });

        mainView.getEndRental().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getEndRentalBox());
        });

        // Historyknappar i meny
        mainView.getRentalHistory().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(historyView.getHistoryPane());
            historyView.getHistoryPane().setCenter(historyView.getHistoryViewBox());
        });

        mainView.getMemberhistory().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(historyView.getHistoryPane());
            historyView.getHistoryPane().setCenter(historyView.getMemberHistoryBox());
        });

        // Ekonomiknappar i meny
        mainView.getRevenue().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(economyView.getEconomyPane());
        });

    }
}
