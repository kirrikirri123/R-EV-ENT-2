package com.ahlenius.revent2.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ProductView {
    // Här läggs allt som har med produkterna att göra. Foto-info, boka osv.
    private BorderPane productPane = new BorderPane();
    private Button products;
    private Button newProd;
    private Button editProd;
    private Button okBtn;
    private Label confrimationText= new Label();


    public ProductView(){
        products = new Button("Alla prdukter");
        FlowPane itemView = new FlowPane();
        HBox images = new HBox(new Text("Test"));
        itemView.getChildren().addAll(images);

        newProd = new Button("Ny produkt");
        GridPane newProdPane =new GridPane(); // Det blir ett NUllpointerException av detta..
        Label prodName = new Label("Produktnamn :");
        TextField prodNameField = new TextField();
        prodNameField.setMaxWidth(250);
        Label prodDescript = new Label("Beskrivning :");
        TextField prodDescriptField = new TextField();
        prodDescriptField.setMaxWidth(450);
        Label dayPrice = new Label("Dagspris i sek :");
        TextField dayPriceField= new TextField();
        dayPriceField.setMaxWidth(50);
        newProdPane.add(prodName,0,0);
        newProdPane.add(prodNameField,1,0);
        newProdPane.add(prodDescript,0,1);
        newProdPane.add(prodDescriptField,1,2);
        newProdPane.add(dayPrice,0,3);
        newProdPane.add(dayPriceField,1,2);
        newProdPane.add(okBtn,2,3);
        newProdPane.add(confrimationText,3,3);
        newProdPane.setVgap(5);
        newProdPane.setHgap(5);
        newProdPane.setAlignment(Pos.CENTER);

        editProd = new Button("Redigera produkt");
        Label updateProdLabel = new Label("Sök produktnamn");
        TextField updateProd = new TextField();
        updateProd.setMaxWidth(250);
        updateProd.setPromptText("Produktnamn");
        Button searchBtnUpd = new Button("Sök");
        VBox updateMemPane = new VBox();
        updateMemPane.setSpacing(5);
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(updateProdLabel,updateProd,searchBtnUpd);


        //Actions knappar.
        products.setOnAction(actionEvent -> {
            productPane.setCenter(itemView);
        });
        newProd.setOnAction( actionEvent -> {
            productPane.setCenter(newProdPane);
        });
        editProd.setOnAction(actionEvent -> {
            productPane.setCenter(updateMemPane);
        });
        okBtn.setOnAction(actionEvent -> {
            confrimationText.setText("Ny medlem skapad.");
            System.out.println("Knappen är tryckt - spara/kolla info.");
        });

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(products,newProd,editProd);

        // Layout ProductView
        productPane.setCenter(newProdPane);
        productPane.setLeft(leftField);
    }

    public BorderPane getProductPane(){
     return productPane; // Blir detta ett extra onödigt steg?
    }

}
