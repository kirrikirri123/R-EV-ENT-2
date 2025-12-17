package com.ahlenius.revent2.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class StartView {
    // Startsidan. Bild, länk till Huvudsidan och kanske en timer som automatiskt skickar en till huduvsidan efter 10 sekunder.
    private BorderPane startview = new BorderPane();
    private ImageView imageStart;
    private Label bottomLabel;



    public StartView(){
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/icon_small.png"));
        imageStart = new ImageView(image);
        imageStart.setCursor(Cursor.HAND);
        flowPane.getChildren().addAll(imageStart);
        HBox hboxBottom = new HBox();
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(10,25,15,25));
        bottomLabel = new Label("Re-Invent your event. Just Rent! ");
        bottomLabel.setStyle("-fx-font-size:10px;");
        hboxBottom.getChildren().add(bottomLabel);


        // Sätt Pane
        startview.setCenter(flowPane);
        startview.setBottom(hboxBottom);
    }
    public ImageView getImageStart(){
        return imageStart;
    }

    public BorderPane getStartView(){
            return startview;
 }



}

