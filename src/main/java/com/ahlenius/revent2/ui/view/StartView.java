package com.ahlenius.revent2.ui.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StartView {
    // Startsidan. Bild, länk till Huvudsidan och kanske en timer som automatiskt skickar en till huduvsidan efter 10 sekunder.
    //bottenlist med lite halv osynlig info. Avslutsknapp?
    private BorderPane startview = new BorderPane();



    public StartView(){
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/icon_small.png")); // vad gör man om de är NUll! Hantera!
        ImageView imageStart = new ImageView(image);
        Rectangle rectangle = new Rectangle(250,250);
        rectangle.setFill(Color.BLUEVIOLET);
        flowPane.getChildren().addAll(imageStart);
        HBox hboxBottom = new HBox();
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(10,25,15,25));
        Label bottomLabel = new Label("Re-Invent your event. Just Rent! ");
        bottomLabel.setStyle("-fx-font-size:10px;");
        hboxBottom.getChildren().add(bottomLabel);
        imageStart.setOnMouseClicked(mouseEvent -> {
         bottomLabel.setText("Du klickade!");
        });


        // Sätt Pane
        startview.setCenter(flowPane);
        startview.setBottom(hboxBottom);
    }
     public BorderPane getStartView(){
            return startview;
 }



}

