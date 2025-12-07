package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HistoryView {
    // Här ligger valmöjgliheter kring att hitta igen historiken.

    private RentalService rentalService;
    private BorderPane historyPane = new BorderPane();
    private VBox historyViewBox = new VBox();
    private VBox memberHistoryBox = new VBox();
    private Button viewHistBtn = new Button();
    private Button memberHistBtn = new Button();

    public HistoryView() {
    }

    public HistoryView(RentalService rentalService) {
        this.rentalService = rentalService;

        // Vänstrafältet
        VBox leftBox = new VBox();
        viewHistBtn.setText("Uthyrningshistorik");
        memberHistBtn.setText("Historik - Medlemsspecifik");
        leftBox.setPadding(new Insets(15, 15, 5, 10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewHistBtn, memberHistBtn);

        // Genrell historik
        Label headerHistory = new Label("Uthyrningshistorik");
        historyViewBox.setAlignment(Pos.CENTER);
        historyViewBox.getChildren().addAll(headerHistory);

        // medlem specifikhistoprik
        Label headerMemberHist = new Label("Medlemsspecifk historik");
        memberHistoryBox.setAlignment(Pos.CENTER);

        memberHistoryBox.getChildren().addAll(headerMemberHist);

        // Knappar Layout

        viewHistBtn.setOnAction(actionEvent -> {
            historyPane.setCenter(historyViewBox);
        });
        memberHistBtn.setOnAction(actionEvent -> {
            historyPane.setCenter(memberHistoryBox);
        });

        // Knappar Funktioner


        // Layout RentalPane
        historyPane.setLeft(leftBox);
        historyPane.setCenter(historyViewBox);
    }

    public Button getMemberHistBtn() {
        return memberHistBtn;
    }
    public Button getViewHistBtn() {
        return viewHistBtn;
    }
    public VBox getMemberHistoryBox() {
        return memberHistoryBox;
    }
    public VBox getHistoryViewBox() {
        return historyViewBox;
    }
    public BorderPane getHistoryPane() {
        return historyPane;
    }
}