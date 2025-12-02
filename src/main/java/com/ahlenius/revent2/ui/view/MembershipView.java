package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.service.MembershipService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class MembershipView {
    // Här kommer under meny men olika alternativ till medlemskapshantering.
    private MembershipService membershipService;
    private BorderPane memberPane = new BorderPane();
    private GridPane newMemPane= new GridPane();
    private VBox memHistoryPane = new VBox();
    private VBox updateMemPane = new VBox();
    private VBox searchMemPane = new VBox();
    private Button newMem;
    private Button searchMem;
    private Button updateMem;
    private Button historyMem;
    private String searchString = "Sök på namn eller medlemsnummer: ";
    private String searchBtnString = "Sök";
    private Button okBtn;

    public MembershipView(){}

    public MembershipView(MembershipService membershipService) {
        this.membershipService = membershipService;
        okBtn= new Button("OK");
        Label confrimationText = new Label();
        newMem = new Button("Ny medlem");
        Label fName = new Label("Fullstädningt namn / hela föreningsnamet ");
        Label phone = new Label("Telefonnummer ");
        Label idNR= new Label("Personnummer / Organistationsnummer ");
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
        newMemPane.add(okBtn,2,4);
        newMemPane.add(confrimationText,1,5);
        newMemPane.setVgap(5);
        newMemPane.setHgap(5);
        newMemPane.setAlignment(Pos.CENTER);

        searchMem = new Button("Sök medlem");
        Label searchMemLabel = new Label(searchString);
        TextField searchMember = new TextField();
        searchMember.setMaxWidth(250);
        searchMember.setPromptText("Namn eller medlemsnummer");
        Button searchBtnMem = new Button(searchBtnString);
        searchMemPane.setSpacing(5);
        searchMemPane.setAlignment(Pos.CENTER);
        searchMemPane.getChildren().addAll(searchMemLabel,searchMember,searchBtnMem);

        updateMem = new Button("Uppdatera medlem");
        Label updateMemLabel = new Label(searchString);
        TextField updateMember = new TextField();
        updateMember.setMaxWidth(250);
        updateMember.setPromptText("Namn eller medlemsnummer");
        Button searchBtnUpd = new Button(searchBtnString);
        updateMemPane.setSpacing(5);
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(updateMemLabel,updateMember,searchBtnUpd);

        historyMem = new Button("Medlemshistorik");
        Label memberHistLab = new Label(searchString);
        TextField memberHistory = new TextField();
        memberHistory.setMaxWidth(250);
        memberHistory.setPromptText("Fullstäldigt namn eller id-nummer");
        Button searchBtnHist = new Button(searchBtnString);
        memHistoryPane.setSpacing(5);
        memHistoryPane.setAlignment(Pos.CENTER);
        memHistoryPane.getChildren().addAll(memberHistLab,memberHistory,searchBtnHist);

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(newMem,searchMem,updateMem,historyMem);

        // Knappar Layout
        newMem.setOnAction(actionEvent -> {
            memberPane.setCenter(newMemPane);
        });
        searchMem.setOnAction( actionEvent -> {
            memberPane.setCenter(searchMemPane);
        });
        updateMem.setOnAction(actionEvent -> {
            memberPane.setCenter(updateMemPane);
        });
        historyMem.setOnAction(actionEvent -> {
            memberPane.setCenter(memHistoryPane);
        });

        // Knappar funktioner
        okBtn.setOnAction(actionEvent -> {
         membershipService.newMember(userId.getText(), userName.getText(), "Privat");
         confrimationText.setText("Ny medlem skapad.");
        System.out.println("Knappen är tryckt - spara/kolla info.");
        });
        searchBtnMem.setOnAction(actionEvent -> {
            membershipService.checkListPrintMembersFound(searchMember.getText()); // skriver ut i konsoll
            searchBtnMem.setText("Söker medlem...");

        });
        searchBtnHist.setOnAction(actionEvent -> {
            searchBtnHist.setText("Söker medlem...");
        });
        searchBtnUpd.setOnAction(actionEvent -> {
            searchBtnUpd.setText("Söker medlem...");
        });

        // Layout MembershipView
        memberPane.setCenter(newMemPane);
        memberPane.setLeft(leftField);
    }

    public GridPane getNewMemPane() {
        return newMemPane;
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
