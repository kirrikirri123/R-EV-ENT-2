package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.*;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.RentalRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RentalService {
    //Hanterar Item-funktioner kopplade till uthyrning.
    private Inventory inventory;
    private RentalRegistry rentalRegistry;
    ObjectMapper mapper = new ObjectMapper();

    public RentalService (){}

    public RentalService(Inventory inventory, RentalRegistry rentalRegistry){
        this.inventory = inventory;
        this.rentalRegistry = rentalRegistry;
    }

    public RentalRegistry getRentalRegistry() {
        return rentalRegistry;}

    public Inventory getInventory() {
        return inventory;
    }

    // _______________________________________________________________________
// Produkt metoder

    public List<Item> searchItemByNameReturnList(String prod) {
        List<Item> foundI = new ArrayList<>();
        for (Item i : getInventory().getItemList()) {
            if (i.getName().equalsIgnoreCase(prod)) {
                foundI.add(i);} }
        return foundI;
    }
    public Item searchItemByNameReturnItem(String prod) {
        Item foundItem = null;
        for (Item it : getInventory().getItemList()) {
            if (it.getName().equalsIgnoreCase(prod)) {
                foundItem = it;
            }
        }
        return foundItem;}

    public int searchItemGetListIndex(String prod){
        int indexItem=0;
        for(int i = 0; i < getInventory().getItemList().size();i++){
            if(getInventory().getItemList().get(i).getName().equalsIgnoreCase(prod)){
                indexItem = i;}}
        return indexItem; }

    public void updateProdName(Item item, String prodName){
        item.setName(prodName); // null varning
    }
    public void updateProdDescrip(Item item,String prodDescr){
        item.setDescription(prodDescr); // null varning
    }

    public void updateDayPrice(Item item, String newDayPrice){
        double dayPrice = Double.parseDouble(newDayPrice);
        item.setDayPrice(dayPrice); // null varning
    }


    public void removeItem(Item item){
    getInventory().remove(item);
    }

    public void newMascotItem(String name, String description, double day, String season) throws IOException {
        addItemToList(new MascotCostume(name, description, day, season));
        listToJson();
    }
    public void newBouncyItem(String name, String description, double day, boolean indoor) throws IOException {
        addItemToList(new BouncyCastle(name, description, day, indoor));
        listToJson();
    }
    public void addItemToList(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " är sparad i listan.");
    }

    public void listToJson() throws IOException {
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("items.json"),inventory.getItemList());
            System.out.println("Listan är sparad i fil");}// bekräftelse i konsoll
        catch (IOException e){ throw new IOException("Fel uppstsod vid sparande av Items till fil.");}}

    public void loadJsonToArrayList() throws IOException{
        try{
            List<Item> fromFile = Arrays.asList(mapper.readValue(new File("items.json"),Item[].class));
            System.out.println("Item-data laddat i temporär lista.");
            inventory.addList(fromFile);
            System.out.println("Items laddad från Json till lista. ");}
        catch (IOException e){throw new IOException("Fel uppstod vid uppladdning av Items-data från fil.");}}

    /*public void defaultList() { // För testning.
        try{
        newBouncyItem("Kungliga slottet"," Stor hoppborg,för max 15 barn",1000, false);
        newBouncyItem("Slott"," Liten hoppborg, för max 7 barn",450, true);
        newBouncyItem("UltimateXtreme"," Maxad hoppupplevelse, för max 8 vuxna",3500, false);
        newMascotItem("Nallebjörn"," Kramgo, lurvig brunbjörndräkt", 200,"Året om");
        newMascotItem("Tomten"," Premium tomtedräkt. Kvalitetskläder naturligt skägg. Inga skor medföljer.", 1000,"Jul");
    } catch (IOException ex) { System.out.println(ex.getMessage());}}
*/
    //______________________________________________________________________
    //Uthyrningsmetoder
    public LocalDate createDateOfRent(String YYYYMMDD) throws DateTimeParseException {
        DateTimeFormatter styleDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetOfRent = LocalDate.parse(YYYYMMDD, styleDate);
        return datetOfRent;    }

    public Rental newRental(Member memberRenting, Item rentalItem, int rentDays, String startOfRent) { // Datum YYYY-MM-DD
        Rental rental = new Rental(memberRenting,rentalItem, rentDays, startOfRent);
        return rental;
    }

    public Rental newRental(Member rentingMember,Item rentalItem, int rentDays) { // Blir default dagens datum.
        Rental rental = new Rental(rentingMember,rentalItem, rentDays);
        return rental;
    }

    // byt antal

    public void changeRentDays(Member member, int x) {
        getRentalRegistry().getRentalList().get(getRentalRegistry().getRentalList().indexOf(member)).setRentDays(x); // Ändra till streams?
    }
    //visa valt antal
    public int rentalCountDays(Member member) {
        return  getRentalRegistry().getRentalList().get(getRentalRegistry().getRentalList().indexOf(member)).getRentDays();  // Ändra till streams?
    }

    public double returnRentalDayPrice(Member member) {
        return  getRentalRegistry().getRentalList().get(getRentalRegistry().getRentalList().indexOf(member)).getRentalItem().getDayPrice();// Ändra till streams?
    }

    public String returnRentalItemName(Member member) {
        return  getRentalRegistry().getRentalList().get(getRentalRegistry().getRentalList().indexOf(member)).getRentalItem().getName();// Ändra till streams?
    }

    public String userChooseDate(String dateStartString){
        return dateStartString.replace(' ','-');}

    public void rentalsToList(Rental rentalItem) {
        getRentalRegistry().add(rentalItem);
        addHistory(rentalItem,rentalItem.getRentingMember());
    }

    public void addHistory(Rental rentalItem, Member member) {
        member.getHistoryMember().add(rentalItem);
    }

    public void countActualDays(String stopDate, Member member){ // här finns risk att det är ett förstort tal i long när de konverteras till int.
        LocalDate stopRent = createDateOfRent(stopDate);
        LocalDate theStartOfRent = getRentalRegistry().getRentalList().get(getRentalRegistry().getRentalList().indexOf(member)).getStartOfRent(); //  Ändrad dör att funka mot List istället fölr map okänt i praktiken.
        long actualDaysLong = stopRent.toEpochDay() - theStartOfRent.toEpochDay();
        int actualDays =(int) actualDaysLong;
        changeRentDays(member,actualDays);
    }

    /*public void sumRentalsList() { // Ekonomi att se över vid senare tillfälle.
        System.out.println("Hyrestransaktioner idag: ");
        double sum=0;
        for (Map.Entry<Member, Rental> entry : getRentalRegistry().getRentalList().entrySet()) {
            double price = calculateDay(entry.getValue().getRentalItem().getDayPrice(),entry.getValue().getRentDays());//OBS! Tar inte in pricepolicy.
            sum +=price;
            System.out.println(entry.getKey()+ " Produkt: "+ entry.getValue().getRentalItem().getName() +
                    ". Dagspris: " + entry.getValue().getRentalItem().getDayPrice()+ "kr. Hyrestid i dagar: "+ entry.getValue().getRentDays()
                    + ". Beräknad intäkt på uthyrningen bortsett från ev.rabatter: "+price+ " kr.");
        }System.out.println("Totala intäkter på affärer gjorda idag beräknas bli: "+ sum + "kr ex. moms.");}
*/


    public double calculateDay(double dayPrice,int days) {
        double price = dayPrice * days;
        if(days>=30){ price = priceMonth(dayPrice,days);}
        return price;
    }

    public double priceMonth(double dayPrice,double days) {
        return (days/30)*((dayPrice*30)*0.7);
    }

}


