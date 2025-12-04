package com.ahlenius.revent2.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MainView {
    // Här hälsas man välkommen och får info. HEMSKÄRM.
    private BorderPane mainPane = new BorderPane();
    private VBox header = new VBox();
    private Label headerLabel = new Label();
    private HBox bottom = new HBox();
    private Label bottomLabel = new Label();
    private Button quitBtn = new Button();
    private MenuBar menuHeader = new MenuBar();
    private MenuItem home;
    private MenuItem newMem;
    private MenuItem searchMem;
    private MenuItem updateMem;
    private MenuItem historyMem;
    private MenuItem products;
    private MenuItem newProducts;
    private MenuItem editProduct;
    private MenuItem accesibleProd;
    private MenuItem newRental;
    private MenuItem endRental;
    private MenuItem rentalHistory;
    private MenuItem memberhistory;


    public MainView() {
        //Top
        Image iconleft = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/icon_small.png"));
        ImageView iconImageView = new ImageView(iconleft);
        iconImageView.setPreserveRatio(true);
        iconImageView.setFitWidth(125);
        HBox topHeader = new HBox();
        topHeader.setPadding(new Insets(15, 15, 3, 15));
        topHeader.getChildren().addAll(iconImageView, headerLabel);

        //MenuBar
        Menu startMenu = new Menu("Start");
        home = new MenuItem("Hem");
        startMenu.getItems().add(home);
        Menu memberMenu = new Menu("Medlemshantering");
        newMem = new MenuItem("Ny medlem");
        searchMem = new MenuItem("Sök medlem");
        updateMem = new MenuItem("Uppdatera medlem");
        historyMem = new MenuItem("Se medlemshistorik");
        memberMenu.getItems().addAll(newMem, searchMem, updateMem, historyMem);
        Menu productMenu = new Menu("Produkter");
        products = new MenuItem("Galleri");
        newProducts = new MenuItem("Ny produkt");
        editProduct = new MenuItem("Redigera produkt");
        productMenu.getItems().addAll(products, newProducts, editProduct);
        Menu rentalMenu = new Menu("Uthyrning");
        accesibleProd = new MenuItem("Aktuella produkter");
        newRental = new MenuItem("Ny uthyrning");
        endRental = new MenuItem("Avsluta uthyrning");
        rentalMenu.getItems().addAll(accesibleProd,newRental, endRental);
        Menu history = new Menu("Historik");
        rentalHistory = new MenuItem("Uthyrnignshistorik");
        memberhistory = new MenuItem("Medlemsspecifik");
        history.getItems().addAll(rentalHistory, memberhistory);
        Menu economyMenu = new Menu("Ekonomi");

        menuHeader.getMenus().addAll(startMenu, memberMenu, productMenu, rentalMenu, history, economyMenu);
        menuHeader.setPadding(new Insets(2, 10, 2, 200));
        //Center Welcome
        Label mainLabel = new Label(" Välkommen, dags att börja hyra!");
        mainLabel.setStyle("-fx-font-size: 24px");
        Label mainUnderLabel = new Label("Det finns inget bättre arrangemang än det som skapar bestående minnen \ntill priset av en mindre prislapp och små besvär." +
                "\n R-EV-ENT är den hjälpande handen som hyr till dig om privatperson och förening\n när ni skapar för ett oförglömligt minne, en ökad gemenskap i föreningen \n och glädje för familj med vänner." +
                "\n Ditt nästa lyckade event börjar här. Just rent !");
        mainUnderLabel.setStyle("-fx-font-size: 12px");
         VBox centerBox = new VBox();
        centerBox.setSpacing(5);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(mainLabel,mainUnderLabel);


        //Bottom
        quitBtn.setText("Avsluta");
        bottom.setPadding(new Insets(15, 10, 10, 10));
        bottom.setAlignment(Pos.BASELINE_RIGHT);
        bottom.setPrefHeight(50);
        bottom.getChildren().addAll(bottomLabel, quitBtn);
        header.getChildren().addAll(topHeader, menuHeader);
        // Layout
        mainPane.setTop(header);
        mainPane.setCenter(centerBox);
        mainPane.setBottom(bottom);

        // Action-i menyn
        home.setOnAction(actionEvent -> {
            mainPane.setCenter(centerBox);
        });
          }
        public MenuItem getHistoryMem () {
            return historyMem;
        }
        public MenuItem getUpdateMem () {
            return updateMem;
        }
        public MenuItem getSearchMem () {
            return searchMem;
        }
        public MenuItem getNewMem () {
            return newMem;
        }
        public MenuItem getHome () {
            return home;
        }
        public Label getHeaderLabel () {
            return headerLabel;
        }
        public Label getBottomLabel () {
            return bottomLabel;
        }
        public Button getQuitBtn () {
            return quitBtn;
        }
        public BorderPane getMainView () {
            return mainPane;
        }
    public MenuItem getMemberhistory() {
        return memberhistory;
    }
    public MenuItem getRentalHistory() {
        return rentalHistory;
    }
    public MenuItem getEndRental() {
        return endRental;
    }
    public MenuItem getNewRental() {
        return newRental;
    }
    public MenuItem getEditProduct() {
        return editProduct;
    }
    public MenuItem getNewProducts() {
        return newProducts;
    }
    public MenuItem getProducts() {
        return products;
    }
    public MenuItem getAccesibleProd() {
        return accesibleProd;
    }
}
