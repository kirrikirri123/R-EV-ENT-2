package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.exceptions.*;
import com.ahlenius.revent2.service.MembershipService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class MembershipView {
    // Här kommer under meny men olika alternativ till medlemskapshantering.
    private MembershipService membershipService;
    private BorderPane memberPane = new BorderPane();
    private  VBox gridPaneNewMem = new VBox();
    private VBox memHistoryPane = new VBox();
    private VBox updateMemPane = new VBox();
    private VBox searchMemPane = new VBox();
    private Button newMem;
    private Button searchMem;
    private Button updateMem;
    private Button historyMem;
    private String searchString = "Sök på namn eller telefonnummer: ";
    private String searchBtnString = "Sök";
    private final Button OKBTN = new Button("OK");
    private Label exceptionInfo = new Label();


    public MembershipView(){}

    public MembershipView(MembershipService membershipService) {
        this.membershipService = membershipService;

        //NY medlemsVy
        Label headerText = new Label("Skapa ny medlem");
        Label confrimationText = new Label();
        GridPane newMemPane= new GridPane();
        newMem = new Button("Ny medlem");
        Label fName = new Label("Fullstädningt namn / Föreningens namn");
        Label phone = new Label("Telefonnummer ");
        Label idNR= new Label("Personnummer / Organistationsnummer "); // Just nu bara privatPersoner.
        TextField userName = new TextField();
        userName.maxWidth(225);
        userName.setPromptText("Kickan Kristersson");
        TextField userPhone = new TextField();
        userPhone.maxWidth(225);
        userPhone.setPromptText(" 070 302 48 10");
        TextField userId = new TextField();
        userId.maxWidth(225);
        userId.setPromptText("1990-01-01 / 556622-0000");
        newMemPane.add(fName,0,0);
        newMemPane.add(userName,1,0);
        newMemPane.add(phone,0,2);
        newMemPane.add(userPhone,1,2);
        newMemPane.add(idNR,0,3);
        newMemPane.add(userId,1,3);
        newMemPane.add(OKBTN,2,4);
        newMemPane.add(confrimationText,1,5);
        newMemPane.add(exceptionInfo,1,6);
        newMemPane.setVgap(5);
        newMemPane.setHgap(5);
        newMemPane.setAlignment(Pos.CENTER);
        gridPaneNewMem.setAlignment(Pos.CENTER);
        gridPaneNewMem.getChildren().addAll(headerText,newMemPane);

        //Sök medlemsVy
        searchMem = new Button("Sök medlem");
        Label headerSearch = new Label("Sök befintlig medlem");
        Label confirmationSearchMem = new Label();
        StringBuilder builder = new StringBuilder();
        Label searchMemLabel = new Label(searchString);
        TextField searchMember = new TextField();
        searchMember.setMaxWidth(250);
        searchMember.setPromptText("Tex. Bosse Bengtsson eller 0950 14841");
        Button searchBtnMem = new Button(searchBtnString);
        searchMemPane.setSpacing(5);
        searchMemPane.setAlignment(Pos.CENTER);
        searchMemPane.getChildren().addAll(headerSearch,searchMemLabel,searchMember,searchBtnMem,confirmationSearchMem);

        //HistorikVy
        historyMem = new Button("Medlemshistorik");
        Label headerHistoryMem = new Label("Medlemshistorik");
        Label memberHistLab = new Label(searchString);
        Label exceptionInfoHistory = new Label();
        TextField memberHistory = new TextField();
        memberHistory.setMaxWidth(250);
        memberHistory.setPromptText("Tex. Bosse Bengtsson eller 0950 14841");
        Button searchBtnHist = new Button(searchBtnString);
        memHistoryPane.setSpacing(5);
        memHistoryPane.setAlignment(Pos.CENTER);
        memHistoryPane.getChildren().addAll(headerHistoryMem,memberHistLab,memberHistory,searchBtnHist,exceptionInfoHistory);
         // Steg 2 Visa Historik
        TableView<Rental> historyTable = new TableView<>(); // Vid knapptryck och hittad medlem så byter vi center till en ny vy.
        //ObservableList<Rental> rentalsObsList =

        //Uppdatera medlemVy
        updateMem = new Button("Uppdatera medlem");
        Label headerUpdate = new Label("Uppdatera medlem");
        Label updateMemLabel = new Label(searchString);
        Label updateMemInfo = new Label();
        TextField updateMember = new TextField();
        updateMember.setMaxWidth(250);
        updateMember.setPromptText("Tex. Bosse Bengtsson eller 0950 14841");
        Button searchBtnUpd = new Button(searchBtnString);

        Alert confrUpdMem = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType yesBtn = new ButtonType("JA");
        ButtonType noBtn = new ButtonType("NEJ");
        Button btnYES = (Button)confrUpdMem.getDialogPane().lookupButton(yesBtn);
        Button btnNO = (Button)confrUpdMem.getDialogPane().lookupButton(noBtn);
        confrUpdMem.getButtonTypes().setAll(yesBtn,noBtn);
        confrUpdMem.setTitle("Uppdatera medlem - Validering");
        confrUpdMem.setHeaderText("Vill du uppdatera medlem?");

        updateMemPane.setSpacing(5);
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(headerUpdate,updateMemLabel,updateMember,searchBtnUpd,updateMemInfo);
            // Steg 2 uppdatera medlem.
        VBox updateMemVbox= new VBox();



        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(newMem,searchMem,updateMem,historyMem);

        // Knappar Layout
        newMem.setOnAction(actionEvent -> {
            memberPane.setCenter(gridPaneNewMem);
        });
        searchMem.setOnAction( actionEvent -> {
            memberPane.setCenter(searchMemPane);
            searchBtnMem.setText(searchBtnString); searchMember.clear();confirmationSearchMem.setText("");
        });
        updateMem.setOnAction(actionEvent -> {
            memberPane.setCenter(updateMemPane);
            searchBtnUpd.setText(searchBtnString); updateMember.clear(); updateMemInfo.setText("");
        });
        historyMem.setOnAction(actionEvent -> {
            memberPane.setCenter(memHistoryPane);
            searchBtnHist.setText(searchBtnString); memberHistory.clear();exceptionInfoHistory.setText("");
        });

        // Knappar funktioner
        OKBTN.setOnAction(actionEvent -> {
            try{
            membershipService.newMember(userId.getText(), userName.getText(), userPhone.getText(), "Privat");
                confrimationText.setText("Ny medlem skapad.");
                userId.clear();userName.clear();userPhone.clear();exceptionInfo.setText(" ");
            } catch (InvalidMemberInfoInputException | InvalidNameInputException | InvalidPhoneInputException e) {
                exceptionInfo.setText(e.getMessage());}
            });

        searchBtnMem.setOnAction(actionEvent -> {
            if(searchMember.getText().isEmpty()){confirmationSearchMem.setText("För att söka fyll i namn eller telefonummer.");}
            else {
             confirmationSearchMem.setText(" ");
            searchBtnMem.setText("Söker medlem...");// Lägga en sleep och sen återställa knapp till "Sök. Så syns det att den "gör nått"
            try {
                ArrayList<String> foundMem= membershipService.checkMemberlistReturnFormatedStringList(searchMember.getText());
                foundMem.stream().forEach(s-> builder.append(s).append("\n"));
                confirmationSearchMem.setText(builder.toString());
                searchBtnMem.setText(searchBtnString); searchMember.clear(); foundMem.clear();
            } catch (NullPointerException ex) { confirmationSearchMem.setText(ex.getMessage());searchBtnMem.setText(searchBtnString); }}});

        searchBtnHist.setOnAction(actionEvent -> {
            searchBtnHist.setText("Söker medlem...");// Lägga en sleep och sen återställa knapp till "Sök."
            try{
            Member foundMem = membershipService.searchMemberByNameOrPhoneReturnMember(memberHistory.getText());
                membershipService.getMemberHistory(foundMem);
                searchBtnHist.setText(searchBtnString); memberHistory.clear();
            } catch (NullPointerException|NoHistoryFoundException ex){exceptionInfoHistory.setText(ex.getMessage()); searchBtnHist.setText(searchBtnString);}
        });

        searchBtnUpd.setOnAction(actionEvent -> {
            searchBtnUpd.setText("Söker medlem...");// Lägga en sleep och sen återställa knapp till "Sök."
            try{
                Member foundMem = membershipService.searchMemberByNameOrPhoneReturnMember(updateMember.getText());
                confrUpdMem.setContentText("Hittade medlem " + foundMem.getName() + ". Stämmer det?");
                confrUpdMem.show();
                btnYES.setOnAction(actionE -> {
                    updateMemInfo.setText("Medlem bekräftad. Laddar sida för uppdatering av medlemsinfo."); // startar men gör inget.
                    confrUpdMem.close();
                });
                btnNO.setOnAction(aEvent -> { // Knappar i medlems
                    updateMember.clear();
                    searchBtnUpd.setText(searchBtnString);
                    confrUpdMem.close();
                });
            } catch (NullPointerException e) { updateMemInfo.setText(e.getMessage()); searchBtnUpd.setText(searchBtnString);}
        });

         // Layout MembershipView
        memberPane.setCenter(gridPaneNewMem);
        memberPane.setLeft(leftField);
    }

    public VBox getGridPaneNewMem() {
        return gridPaneNewMem;
    }

    public VBox getMemHistoryPane() {
        return memHistoryPane;
    }

    public VBox getUpdateMemPane() {
        return updateMemPane;
    }

    public VBox getSearchMemPane() {
        return searchMemPane;
    }

    public BorderPane getMemberPane(){
        return memberPane;
    }


}
