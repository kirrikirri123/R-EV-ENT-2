package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ProductView {
    // Här läggs allt som har med produkterna att göra. Foto-info, boka osv.
    private RentalService rentalService;
    private BorderPane productPane = new BorderPane();
    private FlowPane itemView = new FlowPane();
    private GridPane newProdPane =new GridPane();
    private VBox updateProdPane = new VBox();
    private Button products;
    private Button newProd;
    private Button editProd;
    private final Button OKBTN = new Button("OK");
    private Label confrimationText= new Label();

    public ProductView(){}

    public ProductView(RentalService rentalService){
        this.rentalService = rentalService;
        products = new Button("Alla produkter");
        HBox images = new HBox(new Text("Test"));
        itemView.getChildren().addAll(images);
        newProd = new Button("Ny produkt");
        Label prodName = new Label("Produktnamn ");
        TextField prodNameField = new TextField();
        prodNameField.setMaxWidth(250);
        Label prodDescript = new Label("Beskrivning ");
        TextField prodDescriptField = new TextField();
        prodDescriptField.setMaxWidth(450);
        Label dayPrice = new Label("Dagspris i sek ");
        TextField dayPriceField= new TextField();
        dayPriceField.setMaxWidth(50);
        newProdPane.add(prodName,0,0);
        newProdPane.add(prodNameField,1,0);
        newProdPane.add(prodDescript,0,1);
        newProdPane.add(prodDescriptField,1,1);
        newProdPane.add(dayPrice,0,2);
        newProdPane.add(dayPriceField,1,2);
        newProdPane.add(OKBTN,2,3);
        newProdPane.add(confrimationText,1,4);
        newProdPane.setVgap(5);
        newProdPane.setHgap(5);
        newProdPane.setAlignment(Pos.CENTER);

        editProd = new Button("Redigera produkt");
        Label updateProdLabel = new Label("Sök produktnamn");
        TextField updateProd = new TextField();
        updateProd.setMaxWidth(250);
        updateProd.setPromptText("Produktnamn");
        Button searchBtnUpd = new Button("Sök");
        updateProdPane.setSpacing(5);
        updateProdPane.setAlignment(Pos.CENTER);
        updateProdPane.getChildren().addAll(updateProdLabel,updateProd,searchBtnUpd);


        //Actions knappar.
        products.setOnAction(actionEvent -> {
            productPane.setCenter(itemView);
        });
        newProd.setOnAction( actionEvent -> {
            productPane.setCenter(newProdPane);
        });
        editProd.setOnAction(actionEvent -> {
            productPane.setCenter(updateProdPane);
        });
        OKBTN.setOnAction(actionEvent -> {
            confrimationText.setText("Ny produkt tillagd.");
            System.out.println("Knappen är tryckt - spara/kolla info.");
        });

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(products,newProd,editProd);

        // Layout ProductView
        productPane.setCenter(itemView);
        productPane.setLeft(leftField);
    }

    public VBox getUpdateProdPane() {
        return updateProdPane;
    }
    public GridPane getNewProdPane() {
        return newProdPane;
    }
    public FlowPane getItemView() {
        return itemView;
    }
    public BorderPane getProductPane(){
     return productPane; // Blir detta ett extra onödigt steg?
    }

}
