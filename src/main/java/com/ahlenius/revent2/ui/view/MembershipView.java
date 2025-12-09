package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.exceptions.*;
import com.ahlenius.revent2.service.MembershipService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    private Member tempMember;
    private Member tempHistMember;


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


        //Uppdatera medlemVy
        updateMem = new Button("Uppdatera medlem");
        Label headerUpdate = new Label("Uppdatera medlem");
        Label updateMemLabel = new Label(searchString);
        Label updateMemInfo = new Label();
        TextField updateMember = new TextField();
        updateMember.setMaxWidth(250);
        updateMember.setPromptText("Tex. Bosse Bengtsson eller 0950 14841");
        Button searchBtnUpd = new Button(searchBtnString);
        updateMemPane.setSpacing(5);
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(headerUpdate,updateMemLabel,updateMember,searchBtnUpd,updateMemInfo);
          Alert confrUpdMem = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType yesBtn = new ButtonType("Ja");
            ButtonType noBtn = new ButtonType("Avbryt");
            confrUpdMem.getButtonTypes().setAll(yesBtn,noBtn);
            confrUpdMem.setTitle("Uppdatera medlem - Validering");
            confrUpdMem.setHeaderText("Vill du uppdatera medlem?");

            // Steg 2 uppdatera medlem.
        VBox updateMemVbox= new VBox();
        Label update2ndView = new Label("Redigering av medlemsinformation");
        Label validatedMem = new Label();
        Label updName = new Label(" Ändra stavning i namn : ");
        Label updPhone = new Label("Ändra telefonnummer : ");
        Label updStatus = new Label("Välj korrekt medlemsstatus ");
        TextField updUserNameField = new TextField();
        userName.maxWidth(225);
        TextField updUserPhoneField = new TextField();
        userPhone.maxWidth(225);
        ComboBox<String>updUserStatusCombo = new ComboBox<>();
        String privateIndividual = "Privatperson";
        String society = "Förening";
        updUserStatusCombo.getItems().addAll(privateIndividual,society);
        updUserStatusCombo.maxWidth(225);
        Button confBtn = new Button(" Bekräfta ändring ");
        Button removeMemBtn = new Button("Eliminera medlem");
        Label confrmUpdText = new Label();
        Label updMemExceptionInfo = new Label();
        GridPane updMemPane = new GridPane();
        updMemPane.setHgap(5);
        updMemPane.setVgap(5);
        updMemPane.setAlignment(Pos.CENTER);
        updMemPane.add(updName,0,0);
        updMemPane.add(updUserNameField,1,0);
        updMemPane.add(updPhone,0,2);
        updMemPane.add(updUserPhoneField,1,2);
        updMemPane.add(updStatus,0,3);
        updMemPane.add(updUserStatusCombo,1,3);
        updMemPane.add(confBtn,2,4);
        updMemPane.add(confrmUpdText,1,5);
        updMemPane.add(updMemExceptionInfo,1,6);
        updMemPane.add (removeMemBtn,1,7);
        updateMemVbox.setSpacing(15);
        updateMemVbox.setAlignment(Pos.CENTER);
        updateMemVbox.getChildren().addAll(update2ndView,validatedMem,updMemPane);

        Alert confRemoveMem = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType removeBtn = new ButtonType("Avsluta");
        ButtonType noRemoveBtn = new ButtonType("Avbryt");
        confRemoveMem.getButtonTypes().setAll(removeBtn,noRemoveBtn);
        confRemoveMem.setTitle("Avsluta Medlemskap");
        confRemoveMem.setHeaderText("Vill du ta bort medlem ur register?");

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
            searchBtnUpd.setText(searchBtnString); updateMember.clear(); updateMemInfo.setText("");confrmUpdText.setText("");
        });
        historyMem.setOnAction(actionEvent -> {
            memberPane.setCenter(memHistoryPane);
            searchBtnHist.setText(searchBtnString); memberHistory.clear();exceptionInfoHistory.setText("");
        });

        // Knappar funktioner
        //Ny medlem -OK
        OKBTN.setOnAction(actionEvent -> {
            try{
            membershipService.newMember(userId.getText(), userName.getText(), userPhone.getText(), "Privat");
                confrimationText.setText("Ny medlem skapad.");  // syns inte länger.
                userId.clear();userName.clear();userPhone.clear();exceptionInfo.setText(" ");
            } catch (InvalidMemberInfoInputException | InvalidNameInputException | InvalidPhoneInputException |
            IOException e) {
                exceptionInfo.setText(e.getMessage());}
            });
        //Vanlig sök
        searchBtnMem.setOnAction(actionEvent -> { // Nånting gör så att det tidigare sökningar syns i resultatet. även om ett exception kommer emellan.
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
        //Historik
        searchBtnHist.setOnAction(actionEvent -> {
            searchBtnHist.setText("Söker medlem...");// Lägga en sleep och sen återställa knapp till "Sök."
            try{
            Member foundMem = membershipService.searchMemberByNameOrPhoneReturnMember(memberHistory.getText());
                membershipService.getMemberHistory(foundMem);
                searchBtnHist.setText(searchBtnString); memberHistory.clear();
            } catch (NullPointerException|NoHistoryFoundException ex){exceptionInfoHistory.setText(ex.getMessage()); searchBtnHist.setText(searchBtnString);}
        });
        // Uppdatera
        searchBtnUpd.setOnAction(actionEvent -> {
            searchBtnUpd.setText("Söker medlem...");
              try{
                Member foundMem = membershipService.searchMemberByNameOrPhoneReturnMember(updateMember.getText());
                confrUpdMem.setContentText("Hittade medlem " + foundMem.getName() + ". Stämmer det?");
                  Optional<ButtonType> userResult = confrUpdMem.showAndWait();
                   if(userResult.isPresent()){
                        if(userResult.get() == yesBtn) {
                        updateMemInfo.setText("Medlem bekräftad. Laddar sida för uppdatering av medlemsinfo.");// detta skrivs aldrig ut
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                                       } catch (InterruptedException e) {System.out.println("Fel uppstod vid sleep");}
                        memberPane.setCenter(updateMemVbox);
                        tempMember = foundMem;
                        validatedMem.setText("Vald medlem : "+ foundMem.getName());
                        updUserNameField.setPromptText(foundMem.getName());
                        updUserPhoneField.setPromptText(foundMem.getPhone());
                        }else if(userResult.get() == noBtn) {updateMember.clear(); searchBtnUpd.setText(searchBtnString); confrUpdMem.close();}}
                } catch (NullPointerException e) { updateMemInfo.setText(e.getMessage()); searchBtnUpd.setText(searchBtnString);}});
            //Uppdatera mot register
        confBtn.setOnAction(actionEvent -> {
            //"Ändrar tempMember till bla bla ?"
            if(!updUserNameField.getText().isEmpty()){
            membershipService.updateMemberName(tempMember, updUserNameField.getText());}
            if(!updUserPhoneField.getText().isEmpty()){
            membershipService.updateMemberPhone(tempMember,updUserPhoneField.getText());}
            if(!updUserStatusCombo.getValue().equals(tempMember.getMemberStatus())){
            membershipService.updateMemberStatus(tempMember,updUserStatusCombo.getValue());}
            try {
                membershipService.listToJson();
                confrmUpdText.setText("Efter uppdatering:\n"+ tempMember + "," +tempMember.getMemberStatus());
            } catch (IOException e) {confrmUpdText.setText(e.getMessage());}
        });
        removeMemBtn.setOnAction(actionEvet -> {
            confRemoveMem.setContentText("Vill du avsluta "+ tempMember.getName() + "s medemskap?"); // tillägg senare om det påverkar uthyrning + kostnad kan man dra en chech här innan.
            Optional<ButtonType> userRemoveResult = confRemoveMem.showAndWait();
            if(userRemoveResult.isPresent()){
                if(userRemoveResult.get() ==removeBtn){
                    try {
                    membershipService.removeMember(tempMember);
                    membershipService.listToJson(); // behövs denna??
                    confrmUpdText.setText(tempMember.getName() + "s medlemskap är avslutat.");
                    } catch (IOException e) { confrmUpdText.setText(e.getMessage());}
                }else{
                    confrmUpdText.setText("Avbröt medlemsavslut");
                }
            }
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
