package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Item;
import com.ahlenius.revent2.service.RentalService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class RentalView {
    //Här hanteras bokning och återlämning
    private RentalService rentalService;
    private BorderPane rentalPane = new BorderPane();
    private VBox prodViewBox = new VBox();
    private VBox newRentalBox = new VBox();
    private VBox endRentalBox = new VBox();
    private Button viewProd = new Button();
    private Button newRental = new Button();
    private Button endRental = new Button();

    public RentalView(){}

    public RentalView (RentalService rentalService){
        this.rentalService = rentalService;

    // Vänstrafältet
        VBox leftBox = new VBox();
        viewProd.setText("Akutella produkter");
        newRental.setText("Ny uthyrning");
        endRental.setText("Avsluta uthyrning");
        leftBox.setPadding(new Insets(15,15,5,10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewProd,newRental,endRental);

    // Aktuella produkter. TabelPane
        Label headerViewProd = new Label("Aktuella produkter för uthyrning:");
        TableView<Item> itemListTableView = new TableView<>();
        itemListTableView.setItems(rentalService.getInventory().getItemsObsList());
        TableColumn<Item,String> prodNameCol = new TableColumn<Item,String>("Produktnamn");
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item,String> prodDescriptCol = new TableColumn<>("Info");
        prodDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Item,String> dayPriceCol = new TableColumn<>("Dagspris i SEK. ex.moms");
        dayPriceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        itemListTableView.getColumns().setAll(prodNameCol,prodDescriptCol,dayPriceCol);

        prodViewBox.getChildren().addAll(headerViewProd,itemListTableView);
        prodViewBox.setSpacing(15);
        prodViewBox.setPadding(new Insets(25,10,10,10));

    // Ny uthyrning
        Label headerNewRental = new Label("Ny uthyrning");
        newRentalBox.setAlignment(Pos.CENTER);

        newRentalBox.getChildren().addAll(headerNewRental);

    // Avsluta uthyrning
        Label headerCloseRental = new Label("Avsluta uthyrning");
        endRentalBox.setAlignment(Pos.CENTER);

        endRentalBox.getChildren().addAll(headerCloseRental);

    // Knappar Layout

        viewProd.setOnAction(actionEvent -> {
            itemListTableView.refresh();
            rentalPane.setCenter(prodViewBox);
        });
        newRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(newRentalBox);
        });
        endRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(endRentalBox);
        });

    // Knappar Funktioner


    // Layout RentalPane
        rentalPane.setLeft(leftBox);
        rentalPane.setCenter(prodViewBox);
    }

    public VBox getEndRentalBox() {
        return endRentalBox;
    }
    public VBox getNewRentalBox() {
        return newRentalBox;
    }
    public VBox getProdViewBox() {
        return prodViewBox;
    }
    public Button getEndRental() {
        return endRental;
    }
    public Button getNewRental() {
        return newRental;
    }
    public Button getViewProd() {
        return viewProd;
    }
    public  BorderPane getRentalPane(){
        return rentalPane;
    }
}
