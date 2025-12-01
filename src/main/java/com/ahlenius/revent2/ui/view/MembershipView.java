package com.ahlenius.revent2.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MembershipView {
    // Här kommer under meny men olika alternativ till medlemskapshantering.
    private BorderPane memberPane = new BorderPane();
    private GridPane newMemPane= new GridPane();
    private Button newMem;
    private Button searchMem;
    private Button updateMem;
    private Button historyMem;
    private String searchString = "Skriv namn eller medlemsid för att söka.";
    private Button okBtn;


    public MembershipView() {
        okBtn= new Button("OK");
        Label confrimationText = new Label();
        newMem = new Button("Ny medlem");
        Label fName = new Label("Förnamn / hela föreningsnamet");
        Label lName = new Label("Efternamn (Lämna tomt om förening)");
        Label idNR= new Label("Personnummer / Organistationsnummer");
        TextField userFname = new TextField();
        userFname.maxWidth(225);
        TextField userLname = new TextField();
        userLname.maxWidth(225);
        TextField userId = new TextField();
        userId.maxWidth(225);
        newMemPane.add(fName,0,0);
        newMemPane.add(userFname,1,0);
        newMemPane.add(lName,0,2);
        newMemPane.add(userLname,1,2);
        newMemPane.add(idNR,0,3);
        newMemPane.add(userId,1,3);
        newMemPane.add(okBtn,2,4);
        newMemPane.add(confrimationText,2,5);
        newMemPane.setVgap(5);
        newMemPane.setHgap(5);

        searchMem = new Button("Sök medlem");
        Label searhMemLabel = new Label(searchString);
        TextField searchMember = new TextField();
        searchMember.setPromptText("Namn eller medlemsnummer");
        FlowPane searchMemPane = new FlowPane();
        searchMemPane.setAlignment(Pos.CENTER);
        searchMemPane.getChildren().addAll(searhMemLabel,searchMember);

        updateMem = new Button("Uppdatera medlem");
        TextField updateMember = new TextField();
        searchMember.setPromptText("Namn eller medlemsnummer");
        FlowPane updateMemPane = new FlowPane();
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(searhMemLabel,updateMember);

        historyMem = new Button("Medlemshistorik");
        TextField memberHistory = new TextField();
        searchMember.setPromptText("Namn eller medlemsnummer");
        FlowPane memHistoryPane= new FlowPane();
        memHistoryPane.setAlignment(Pos.CENTER);
        memHistoryPane.getChildren().addAll(searhMemLabel,memberHistory);

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(5,5,5,5));
        leftField.setSpacing(5);
        leftField.getChildren().addAll(newMem,searchMem,updateMem,historyMem);

        // Actions knappar
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
        okBtn.setOnAction(actionEvent -> {
            confrimationText.setText("Ny medlem skapad.");
        System.out.println("Knappen är tryckt - spara/kolla info.");
        });



        // Layout MembershipView
        memberPane.setCenter(newMemPane);
        memberPane.setRight(leftField);
    }
    public BorderPane getMemberPane(){
        return memberPane;
    }
}
