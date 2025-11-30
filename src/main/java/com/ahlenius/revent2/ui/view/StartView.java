package com.ahlenius.revent2.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class StartView {
    // här skapas den synliga biten för programmet. Startsidan. Bild, länk till Huvudsidan och kanske en timer som automatiskt skickar en till huduvsidan efter 10 sekunder.
    //bottenlist med lite halv osynlig info. Avslutsknapp?
    private BorderPane startview = new BorderPane();



    public StartView(){
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/icon_small.png")); // vad gör man om de är NUll! Hantera!
        ImageView imageStart = new ImageView(image);
        flowPane.getChildren().add(imageStart);
        HBox hboxBottom = new HBox();
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(10,25,10,25));
        Label bottomLabel = new Label("Re-Invent your event. Just Rent! ");
        bottomLabel.setStyle("-fx-font-size:10px");
        hboxBottom.getChildren().add(bottomLabel);





        // Sätt Pane
        startview.setCenter(flowPane);
        startview.setBottom(hboxBottom);
    }
     public BorderPane getStartView(){
            return startview;
 }
}
