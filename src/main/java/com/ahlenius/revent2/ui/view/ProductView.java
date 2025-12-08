package com.ahlenius.revent2.ui.view;

import com.ahlenius.revent2.entity.Item;
import com.ahlenius.revent2.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Optional;

public class ProductView {
    // Här läggs allt som har med produkterna att göra. Foto-info, boka osv.
    private RentalService rentalService;
    private BorderPane productPane = new BorderPane();
    private FlowPane itemView = new FlowPane();
    private VBox newProdBox = new VBox();
    private VBox updateProdPane = new VBox();
    private Button products;
    private Button newProd;
    private Button editProd;
    private final Button OKBTN = new Button("OK");
    private Label confrimationText= new Label();
    private Label exceptionInfo= new Label();
    private Label updProdInfo = new Label();


    public ProductView(){}

    public ProductView(RentalService rentalService){
        this.rentalService = rentalService;
        // GalleriVY
        products = new Button("Galleri");
        VBox item1 = new VBox();
        Label headerGallery = new Label("Ett urval av produkter");
        headerGallery.setPrefSize(400,35);
        item1.setAlignment(Pos.CENTER);
        item1.setSpacing(10);
        item1.setMaxWidth(250);
        Image image1 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/gurk_costume.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(250);
        String nameItem = "Gurk-man";
        Label nameItem1 = new Label(nameItem);
        String descripItem = "Grön och ståtlig dräkt. \nGaranterar skratt!";
        Label descripItem1 = new Label(descripItem);
        String dayPriceItem = "275";
        Label dayPriceItem1 = new Label(dayPriceItem+ "kr");
        item1.setSpacing(5);
        item1.getChildren().addAll(headerGallery,imageView1,nameItem1,descripItem1,dayPriceItem1);

        VBox item2 = new VBox();
        item2.setMaxWidth(250);
        Image image2 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/dino_costume.png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(250);
        item2.getChildren().addAll(imageView2);

        VBox item3 = new VBox();
        Image image3 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/bear_costume.png"));
        ImageView imageView3 = new ImageView(image3);
        imageView3.setPreserveRatio(true);
        imageView3.setFitWidth(250);
        item3.getChildren().addAll(imageView3);

        VBox item4 = new VBox();
        Image image4 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/trampoline.png"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitWidth(250);
        item4.getChildren().addAll(imageView4);

        VBox item5 = new VBox();
        Image image5 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/house.png"));
        ImageView imageView5 = new ImageView(image5);
        imageView5.setPreserveRatio(true);
        imageView5.setFitWidth(250);
        item5.getChildren().addAll(imageView5);

        VBox item6 = new VBox();
        Image image6 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent2/Castle.png"));
        ImageView imageView6 = new ImageView(image6);
        imageView6.setPreserveRatio(true);
        imageView6.setFitWidth(250);
        item6.getChildren().addAll(imageView6);

        itemView.setHgap(20);
        itemView.setVgap(20);
        itemView.getChildren().addAll(item1,item2,item3,item4,item5,item6);

        // Ny produktVY
        Label headerNewProd = new Label(" Skapa ny produkt");
        newProdBox.setSpacing(10);
        newProdBox.setAlignment(Pos.CENTER);
        newProd = new Button("Ny produkt");
        Label prodName = new Label("Produktnamn ");
        TextField prodNameField = new TextField();
        prodNameField.setPromptText("Stora stygga vargen");
        prodNameField.setMaxWidth(250);
        ComboBox<String> itemTypeCombo = new ComboBox<>();
        Label itemTypeL = new Label("Vilken typ av produkt?");
        String costume = "Dräkt";
        String bouncyC = "Hoppborg";
        itemTypeCombo.getItems().addAll(costume,bouncyC);
        Label prodDescript = new Label("Beskrivning ");
        TextField prodDescriptField = new TextField();
        prodDescriptField.setPromptText("tex. Lurvig svart varg med löstagbar svans");
        Label dayPrice = new Label("Dagspris i sek ");
        TextField dayPriceField= new TextField();
        dayPriceField.setPromptText("tex. 750");
        dayPriceField.setMaxWidth(250);
        GridPane newProdPane =new GridPane();
        newProdPane.add(itemTypeL,0,0);
        newProdPane.add(itemTypeCombo,2,0);
        newProdPane.add(prodName,0,1);
        newProdPane.add(prodNameField,2,1);
        newProdPane.add(prodDescript,0,2);
        newProdPane.add(prodDescriptField,2,2);
        newProdPane.add(dayPrice,0,3);
        newProdPane.add(dayPriceField,2,3);
        newProdPane.add(OKBTN,3,4);
        newProdPane.add(confrimationText,1,5);
        newProdPane.add(exceptionInfo,1,6);
        newProdPane.setVgap(5);
        newProdPane.setHgap(5);
        newProdPane.setAlignment(Pos.CENTER);
        newProdBox.getChildren().addAll(headerNewProd,newProdPane);

        // Redigera produktVY
        editProd = new Button("Redigera produkt");
        Label headerUpd = new Label("Redigera produkt");
        Label updateProdLabel = new Label("Sök produktnamn för redigering");
        TextField updateProd = new TextField();
        updateProd.setMaxWidth(250);
        updateProd.setPromptText("Produktnamn");
        Button searchBtnUpd = new Button("Sök och redigera");

        Alert confrUpdProd = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType yesBtn = new ButtonType("Ja");
        ButtonType noBtn = new ButtonType("Avbryt");
        //Button btnYES = (Button)confrUpdProd.getDialogPane().lookupButton(yesBtn);
        //Button btnNO = (Button)confrUpdProd.getDialogPane().lookupButton(noBtn);
        confrUpdProd.getButtonTypes().setAll(yesBtn,noBtn);
        confrUpdProd.setTitle("Redigera produkt - Validering");
        confrUpdProd.setHeaderText("Vill du redigera en produkt?");

        updateProdPane.setSpacing(5);
        updateProdPane.setAlignment(Pos.CENTER);
        updateProdPane.getChildren().addAll(headerUpd,updateProdLabel,updateProd,searchBtnUpd,updProdInfo);

        //Knappar Layout
        products.setOnAction(actionEvent -> {
            productPane.setCenter(itemView);
        });
        newProd.setOnAction( actionEvent -> {
            productPane.setCenter(newProdBox);
        });
        editProd.setOnAction(actionEvent -> {
            productPane.setCenter(updateProdPane);
        });
        // Knappar funktion

        OKBTN.setOnAction(actionEvent -> {
             double day = Double.parseDouble(dayPriceField.getText());
                if(itemTypeCombo.getValue().equals(costume)){
                    try { rentalService.newMascotItem(prodNameField.getText(),prodDescriptField.getText(),day,"Året runt");
                        confrimationText.setText("Ny produkt tillagd");
                    } catch (IOException e) {exceptionInfo.setText(e.getMessage()+"Dräktproblem");}
                    }
                if(itemTypeCombo.getValue().equals(bouncyC)){
                    try {
                        rentalService.newBouncyItem(prodNameField.getText(),prodDescriptField.getText(),day,false);
                        confrimationText.setText("Ny produkt tillagd");
                    } catch (IOException e) {exceptionInfo.setText(e.getMessage()+"Hoppborgssfail");
                    }
                }
                prodNameField.clear();prodDescriptField.clear();dayPriceField.clear();exceptionInfo.setText(" ");
                rentalService.getInventory().getItemList().stream().forEach(System.out::println); // istället för forEachLoop
        });
        searchBtnUpd.setOnAction(actionEvent -> {
            Item foundProd = rentalService.searchItemByNameReturnItem(updateProd.getText());
            confrUpdProd.setContentText("Hittade produkten - " + foundProd.getName() + ". Stämmer det?");
            Optional<ButtonType> userResult = confrUpdProd.showAndWait();
            if(userResult.isPresent()) {
                if (userResult.get() == yesBtn) {
                    updProdInfo.setText("Medlem bekräftad. Laddar sida för uppdatering av medlemsinfo.");}
                else if(userResult.get() == noBtn) {  updateProd.clear();
                searchBtnUpd.setText("Sök");
                confrUpdProd.close();
            }}});

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(products,newProd,editProd);

        // Layout ProductView
        productPane.setCenter(itemView);
        productPane.setLeft(leftField);
    }

    public VBox getUpdateProdPane() {
        return updateProdPane;
    }
    public VBox getNewProdBox() {
        return newProdBox;
    }
    public FlowPane getItemView() {
        return itemView;
    }
    public BorderPane getProductPane(){
     return productPane;}
}
