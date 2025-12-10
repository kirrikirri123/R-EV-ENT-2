package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Item;
import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.exceptions.InvalidMemberInfoInputException;
import com.ahlenius.revent2.exceptions.InvalidNameInputException;
import com.ahlenius.revent2.exceptions.InvalidPhoneInputException;
import com.ahlenius.revent2.exceptions.NoMemberFoundException;
import com.ahlenius.revent2.service.MembershipService;
import com.ahlenius.revent2.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class RentalView {

    //Här hanteras bokning och återlämning
    private RentalService rentalService;
    private MembershipService membershipService;
    private BorderPane rentalPane = new BorderPane();
    private VBox prodViewBox = new VBox();
    private VBox newRentalBox = new VBox();
    private VBox endRentalBox = new VBox();
    private Button viewProd = new Button();
    private Button newRental = new Button();
    private Button endRental = new Button();
    private Button OKBTN = new Button("OK");
    private Label exceptionInfo = new Label();
    private Member foundRentingMem;

    public RentalView(){}

    public RentalView (RentalService rentalService,MembershipService membershipService) {
        this.rentalService = rentalService;
        this.membershipService = membershipService;

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
        newRentalBox.setSpacing(10);
        Label confrimationText = new Label();
        GridPane newRentalPane= new GridPane();
        Label memName = new Label("Namn på hyrande medlem: ");
        Label rentalProd= new Label("Välj produkt: ");
        Label rentFromDate = new Label("Startdatum: ");
        Label daysOfRent = new Label("Hur många dagar önskas hyra?");
        TextField rentalMemField = new TextField();
        TextField daysOfRentField = new TextField();
        rentalMemField.setMaxWidth(250);
        rentalMemField.setPromptText("tex. Kickan Kristersson");
        ComboBox<Member> memberComboBox =new ComboBox<>(membershipService.getMemberRegistry().convertMemberSetToObsList());
        ComboBox<Item> availableItem = new ComboBox<>(rentalService.getInventory().getItemsObsList());
        availableItem.setMaxWidth(250);
        TextField fromDateField = new TextField();
        fromDateField.setPromptText("Använd format: ÅÅÅÅ-MM-DD");
        fromDateField.setMaxWidth(250);
        newRentalPane.add(memName,0,0);
        newRentalPane.add(rentalMemField,1,0);
        newRentalPane.add(rentalProd,0,1);
        newRentalPane.add(availableItem,1,1);
        newRentalPane.add(daysOfRent,0,2);
        newRentalPane.add(daysOfRentField,1,2);
        newRentalPane.add(rentFromDate,0,3);
        newRentalPane.add(fromDateField,1,3);
        newRentalPane.add(OKBTN,2,4);
        newRentalPane.add(confrimationText,1,5);
        newRentalPane.add(exceptionInfo,1,6);
        newRentalPane.setVgap(5);
        newRentalPane.setHgap(5);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalBox.getChildren().addAll(headerNewRental,newRentalPane);

    // Avsluta uthyrning
        Label headerCloseRental = new Label("Avsluta uthyrning");
        endRentalBox.setAlignment(Pos.CENTER);
        endRentalBox.setSpacing(10);
        Label rentalChoice = new Label("Välj hyrande medlem: ");
        ComboBox<Rental> rentingMemberComboBox =new ComboBox<>(rentalService.getRentalRegistry().getRentalObsList());
        memberComboBox.getItems().addAll();
        Button confirmMem = new Button("Välj medlem");
        endRentalBox.getChildren().addAll(headerCloseRental,rentalChoice,rentingMemberComboBox,confirmMem);

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

    // Knappar funktioner
        OKBTN.setOnAction(actionEvent -> {
            int days = Integer.parseInt(daysOfRentField.getText());
            try {
                foundRentingMem = membershipService.searchMemberByNameOrPhoneReturnMember(rentalMemField.getText());
            } catch (NullPointerException e) { exceptionInfo.setText(e.getMessage() + ". Namnet behöver ha den exakta stavningen.");         }
            try{
                rentalService.newRental(foundRentingMem, availableItem.getValue(),days,fromDateField.getText());
                confrimationText.setText("Ny uthyrning skapad.");
            } catch (IOException e) { exceptionInfo.setText(e.getMessage());}
        });

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
