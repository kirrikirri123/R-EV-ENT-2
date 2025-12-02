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

    public MainView(){
        //Top
        Image iconleft = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/icon_small.png"));
        ImageView iconImageView = new ImageView(iconleft);
        iconImageView.setPreserveRatio(true);
        iconImageView.setFitWidth(125);
        HBox topHeader = new HBox();
        topHeader.setPadding(new Insets(15,15,3,15));
        topHeader.getChildren().addAll(iconImageView,headerLabel);


        //MenuBar
        Menu startMenu= new Menu("Start");
        MenuItem home = new MenuItem("Hem");
        startMenu.getItems().add(home);
        Menu memberMenu = new Menu("Medlemshantering");
        MenuItem newMemb = new MenuItem("Ny medlem");
        MenuItem searchMem = new MenuItem("Sök medlem");
        MenuItem updateMem = new MenuItem("Uppdatera medlem");
        MenuItem historyMem = new MenuItem("Se medlemshistorik");
        memberMenu.getItems().addAll(newMemb,searchMem,updateMem,historyMem);
        Menu productMenu = new Menu("Produkter");
        MenuItem products = new MenuItem("Alla prdukter");
        MenuItem newProducts = new MenuItem("Ny produkt");
        MenuItem editProduct = new MenuItem("Redigera produkt");
        productMenu.getItems().addAll(products,newProducts,editProduct);
        Menu rentalMenu = new Menu("Uthyrning");
        MenuItem newRental = new MenuItem("Ny uthyrning");
        MenuItem endRental = new MenuItem("Avsluta uthyrning");
        rentalMenu.getItems().addAll(newRental,endRental);
        Menu history = new Menu("Historik");
        MenuItem rentalHistory = new MenuItem("Uthyrnignshistorik");
        MenuItem memberhistory = new MenuItem("Medlemsspecifik");
        history.getItems().addAll(rentalHistory,memberhistory);
        Menu economyMenu = new Menu("Ekonomi");

        menuHeader.getMenus().addAll(startMenu,memberMenu,productMenu,rentalMenu,history,economyMenu);
        menuHeader.setPadding(new Insets(2,10,2,200));
    //Bottom
        quitBtn.setText("Avsluta");
        bottom.setPadding(new Insets(15,10,10,10));
        bottom.setAlignment(Pos.BASELINE_RIGHT);
        bottom.getChildren().addAll(bottomLabel,quitBtn);
        header.getChildren().addAll(topHeader,menuHeader);
    // Layout
        mainPane.setTop(header);
        mainPane.setBottom(bottom);
    }

    public Label getHeaderLabel() {
        return headerLabel;
    }
    public Label getBottomLabel() {
        return bottomLabel;
    }
    public Button getQuitBtn() {
        return quitBtn;
    }

    public BorderPane getMainView(){
    return mainPane;
}

}
