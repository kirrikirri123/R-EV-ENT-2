package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Item;
import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.exceptions.*;
import com.ahlenius.revent2.service.JsonService;
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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;


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
    private Rental tempRental;
    private int days;

    public RentalView(){}

    public RentalView (RentalService rentalService, MembershipService membershipService) {
        this.rentalService = rentalService;
        this.membershipService = membershipService;

        // Vänstrafältet
        VBox leftBox = new VBox();
        viewProd.setText("Akutella produkter");
        newRental.setText("Ny uthyrning");
        endRental.setText("Avsluta uthyrning");
        leftBox.setPadding(new Insets(15, 15, 5, 10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewProd, newRental, endRental);

        // Aktuella produkter. TabelPane
        Label headerViewProd = new Label("Aktuella produkter för uthyrning: ");
        TableView<Item> itemListTableView = new TableView<>();
        itemListTableView.setItems(rentalService.getInventory().getItemsObsList());
        TableColumn<Item, String> prodNameCol = new TableColumn<Item, String>("Produktnamn");
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> prodDescriptCol = new TableColumn<>("Info");
        prodDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Item, String> dayPriceCol = new TableColumn<>("Dagspris i SEK. ex.moms");
        dayPriceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        itemListTableView.getColumns().setAll(prodNameCol, prodDescriptCol, dayPriceCol);

        prodViewBox.getChildren().addAll(headerViewProd, itemListTableView);
        prodViewBox.setSpacing(15);
        prodViewBox.setPadding(new Insets(25, 10, 10, 10));

        // Ny uthyrning
        Label headerNewRental = new Label("Ny uthyrning");
        newRentalBox.setAlignment(Pos.CENTER);
        newRentalBox.setSpacing(10);
        Label confrimationText = new Label();
        GridPane newRentalPane = new GridPane();
        Label memName = new Label("Namn på hyrande medlem: ");
        Label rentalProd = new Label("Välj produkt: ");
        Label rentFromDate = new Label("Startdatum: ");
        Label daysOfRent = new Label("Hur många dagar önskas hyra?");
        TextField rentalMemField = new TextField();
        TextField daysOfRentField = new TextField();
        daysOfRentField.setPromptText("tex. 5");
        daysOfRentField.setMaxWidth(250);
        rentalMemField.setMaxWidth(250);
        rentalMemField.setPromptText("tex. Kickan Kristersson");
        ComboBox<Member> memberComboBox = new ComboBox<>(membershipService.getMemberRegistry().convertMemberSetToObsList());
        ComboBox<Item> availableItem = new ComboBox<>(rentalService.getInventory().getItemsObsList());
        availableItem.setMaxWidth(250);
        TextField fromDateField = new TextField();
        fromDateField.setPromptText("Använd format: ÅÅÅÅ-MM-DD");
        fromDateField.setMaxWidth(250);
        newRentalPane.add(memName, 0, 0);
        newRentalPane.add(rentalMemField, 1, 0);
        newRentalPane.add(rentalProd, 0, 1);
        newRentalPane.add(availableItem, 1, 1);
        newRentalPane.add(daysOfRent, 0, 2);
        newRentalPane.add(daysOfRentField, 1, 2);
        newRentalPane.add(rentFromDate, 0, 3);
        newRentalPane.add(fromDateField, 1, 3);
        newRentalPane.add(OKBTN, 2, 4);
        newRentalPane.add(confrimationText, 0, 5);
        newRentalPane.add(exceptionInfo, 0, 6);
        newRentalPane.setVgap(5);
        newRentalPane.setHgap(5);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalBox.getChildren().addAll(headerNewRental, newRentalPane);

        // Avsluta uthyrning
        Label headerCloseRental = new Label("Avsluta uthyrning");
        endRentalBox.setAlignment(Pos.TOP_CENTER);
        endRentalBox.setSpacing(10);
        endRentalBox.setPadding(new Insets(35, 15, 15, 15));
        Label rentalChoice = new Label("Välj bland aktuella uthyrningar: ");
        ComboBox<Rental> rentingMemberComboBox = new ComboBox<>(rentalService.getRentalRegistry().getRentalObsList());
        memberComboBox.getItems().addAll();
        Button confirmRentMem = new Button("Välj medlem");
        endRentalBox.getChildren().addAll(headerCloseRental, rentalChoice, rentingMemberComboBox, confirmRentMem);

        Alert confEndRent = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType endRentBtn = new ButtonType("Ja, avsluta");
        ButtonType closeConfAlertBtn = new ButtonType("Avbryt");
        confEndRent.getButtonTypes().setAll(endRentBtn, closeConfAlertBtn);
        confEndRent.setTitle("Avsluta Uthyrning");
        confEndRent.setHeaderText("Säker på att du vill avsluta uthyrning?");

        // Steg 2 - Avsluta uthyrning
        // Ta in vald rental - sett ett slut datum ändra returned till true
        VBox finalEndRentBox = new VBox();
        finalEndRentBox.setAlignment(Pos.CENTER);
        finalEndRentBox.setSpacing(5);
        Label validateEndRent = new Label();
        Label endDateOfRent = new Label("Återlämningsdatum: ");
        TextField endDateField = new TextField("2025-12-31");
        endDateField.setMaxWidth(250);
        Button confEndRentBtn = new Button("Bekräfta avslut");
        Label exceptionEndRent = new Label();
        finalEndRentBox.getChildren().addAll(headerCloseRental, validateEndRent, endDateOfRent, endDateField, confEndRentBtn, exceptionEndRent);

         // Steg 3 - Räkna ihop uthyrning. Dagar och kostnad.
        VBox finnishedRentingBox = new VBox();
        finnishedRentingBox.setSpacing(10);
        finnishedRentingBox.setAlignment(Pos.CENTER);
        Label headerRentingInfo = new Label("Urhyrning avslutat");
        Label rentingDays = new Label("dagar");
        Label rentingCost = new Label("Totalkostnad");

        finnishedRentingBox.getChildren().addAll(headerRentingInfo,rentingDays,rentingCost);


        // Knappar Layout
        viewProd.setOnAction(actionEvent -> {
            itemListTableView.refresh();
            rentalPane.setCenter(prodViewBox);
        });
        newRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(newRentalBox);
            rentalMemField.clear();
            daysOfRentField.clear();
            fromDateField.clear();
            exceptionInfo.setText("");
            exceptionEndRent.setText("");
        });
        endRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(endRentalBox);
            exceptionEndRent.setText("");
        });

        // Knappar funktioner
        // Ny uthyrning
        OKBTN.setOnAction(actionEvent -> {
            try {
                days = Integer.parseInt(daysOfRentField.getText());
            } catch (NumberFormatException e) {
                exceptionInfo.setText("Missat antal dagar. Skriv ett ungefärligt antal dar.");
            }
           LocalDate dateStart = rentalService.createDateOfRent(fromDateField.getText());
            try {
                foundRentingMem = membershipService.searchMemberByNameOrPhoneReturnMember(rentalMemField.getText());
            } catch (NullPointerException e) {
                exceptionInfo.setText(e.getMessage() + ".\n Namnet behöver ha den exakta stavningen.");}
            if(foundRentingMem != null){
            try {
                Rental newestRental = rentalService.newRental(foundRentingMem, availableItem.getValue(),days,String.valueOf(dateStart));
               // Borde man ha en Alert för att bekräfta bokning, typ medlem hyr itemname  from - datum?
                newestRental.getRentalItem().setAvailable(false);
                confrimationText.setText("Ny uthyrning skapad.\n" + newestRental);
                newestRental.getRentalItem().setAvailable(false);
                rentalMemField.clear();
                daysOfRentField.clear();
                fromDateField.clear();
                exceptionInfo.setText("");
            } catch (IOException | InvalidAmountRentingDaysException | InvalidDateChoiceException |
                     InvalidRentalInfoInputException e) {exceptionInfo.setText(e.getMessage());
            }
        }});
        // Avsluta uthyrning
        confirmRentMem.setOnAction(actionEvent -> {
            tempRental = rentingMemberComboBox.getValue();

            confEndRent.setContentText("Vill du avsluta uthyrningen av " + tempRental.getRentalItem().getName() + " till " + tempRental.getRentingMember().getName() + " ?");
            Optional<ButtonType> userEndingRentResult = confEndRent.showAndWait();
            if (userEndingRentResult.isPresent()) {
                if (userEndingRentResult.get() == endRentBtn) {
                    rentalPane.setCenter(finalEndRentBox);}
                if (userEndingRentResult.get() == closeConfAlertBtn) {
                    exceptionEndRent.setText("Avbryter återlämning. Produkt fortfarande uthyrd.");
                }
            }
        });
        confEndRentBtn.setOnAction(actionE -> {
            tempRental.setReturned(true);
            tempRental.getRentalItem().setAvailable(true);
            try { // Ändra fel hanteringen HÄR !!!!!
                LocalDate dateStopRent = rentalService.userChooseDate(endDateField.getText());
                rentalService.countActualDays(dateStopRent,tempRental.getRentingMember());
                rentalPane.setCenter(finnishedRentingBox);
            } catch (Exception e) {
                exceptionEndRent.setText("Fel vid ihopräknande av dagar.");
            }

        });

        // Layout RentalPane
        rentalPane.setLeft(leftBox);
        rentalPane.setCenter(prodViewBox);
    }//Konstruktor slut

                public VBox getEndRentalBox () {
                    return endRentalBox;
                }
                public VBox getNewRentalBox () {
                    return newRentalBox;
                }
                public VBox getProdViewBox () {
                    return prodViewBox;
                }
                public Button getEndRental () {
                    return endRental;
                }
                public Button getNewRental () {
                    return newRental;
                }
                public Button getViewProd () {
                    return viewProd;
                }
                public BorderPane getRentalPane () {
                    return rentalPane;
                }
            }

