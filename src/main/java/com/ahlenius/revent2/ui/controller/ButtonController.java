package com.ahlenius.revent2.ui.controller;

import com.ahlenius.revent2.service.RentalService;
import com.ahlenius.revent2.ui.view.*;

public class ButtonController {
    private RentalService rentalService;
    private StartView startView;
    private MainView mainView;
    private MembershipView membershipView;
    private ProductView productView;
    private RentalView rentalView;
    private EconomyView economyView;
    private HistoryView historyView;


    public ButtonController(StartView start, MainView main, MembershipView membership,ProductView product,RentalView rental,EconomyView economy,HistoryView history,RentalService rentalService) {
        this.startView = start;
        this.mainView = main;
        this.membershipView = membership;
        this.productView = product;
        this.rentalView = rental;
        this.economyView = economy;
        this.historyView = history;
        this.rentalService = rentalService;

        // Medlemsknappar i meny.
        mainView.getNewMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getNewMemBox());
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
              mainView.getMainView().setCenter(membershipView.getMemberPane());
              membershipView.getMemberPane().setCenter(membershipView.getMemHistoryPane());
          });
        historyView.getMemberHistBtn().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(membershipView.getMemberPane());
            membershipView.getMemberPane().setCenter(membershipView.getMemHistoryPane());
        });

        // Ekonomiknappar i meny
        mainView.getRevenue().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(economyView.getEconomyPane());
            economyView.getSum().setText(String.valueOf(rentalService.sumBusinessToDayReturnDouble())+" kr ex moms.*");
            });


    }
}
