package com.ahlenius.revent2.ui.controller;

import com.ahlenius.revent2.ui.Revent_App;
import com.ahlenius.revent2.ui.view.MainView;
import com.ahlenius.revent2.ui.view.StartView;

public class ButtonController {
    // Här försöker vi lägga actions för knappar.
    StartView startView = new StartView();


    public ButtonController(StartView startView) {
        this.startView = startView;

        // Actions
          startView.getImageStart().setOnMouseClicked(mouseEvent -> {
              startView.getBottomLabel().setText("Du klickade!");
              System.out.println("Scenbyte planerat");
             // när man klickar här ska  vi byta scen till MainView.
        });


    }

}
