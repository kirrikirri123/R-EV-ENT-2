package com.ahlenius.revent2.ui.view;

import javafx.scene.layout.BorderPane;

public class EconomyView {
    // Här sköter man sina transaktionskoller osv.
    private BorderPane economyPane = new BorderPane();

        public EconomyView(){}

    public BorderPane getEconomyPane(){
            return economyPane;
    }
}
