package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.*;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.RentalRegistry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RentalService {
    //Hanterar Item-funktioner kopplade till uthyrning.
    private Inventory inventory;
    private RentalRegistry rentalRegistry;

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
    public void addItemToList(Item item) {
        getInventory().getItemsList().add(item);
    }
        public List<Item> searchItemByName(String prod) {
        List<Item> foundI = new ArrayList<>();
        for (Item i : getInventory().getItemsList()) {
            if (i.getName().equalsIgnoreCase(prod)) {
                foundI.add(i);} }
        return foundI;
    }
    public Item searchItemByNameReturnItem(String prod) { // TODO Obs! Utskrift i konsoll
        Item foundItem = null;
        for (Item it : getInventory().getItemsList()) {
            if (it.getName().equalsIgnoreCase(prod)) {
                foundItem = it;
            }
        }
        return foundItem;}

    public void checkListPrintItemsFound(String prod) { // TODO Obs! Uskrift i konsoll . Onödigt krånglig då Itemslist är en ArrayList? Borde vara streams.
        List<Item> foundMatches = searchItemByName(prod);
        if (foundMatches.size() >= 2) {
            System.out.println("Hittade flera matchningar.");
            for (int i = 0; i < foundMatches.size(); i++) {
                System.out.println("Nr." + i + foundMatches.get(i).getName());} // Måste fixa metod för att välja mellan dessa val?
        } else if (foundMatches.isEmpty()) {
            System.out.println("Hittade ingen matchning.");
        } else {
            for (Item item : foundMatches) {
                System.out.println("Hittade " + item.getName() + " med dagspris: " + item.getDayPrice());
            }}}

    public int searchItemGetListIndex(String prod){
        int indexItem=0;
        for(int i = 0; i < getInventory().getItemsList().size();i++){
            if(getInventory().getItemsList().get(i).getName().equalsIgnoreCase(prod)){
                indexItem = i;}}
        return indexItem; }

    public void removeItemFromList(String prod, Scanner scan) {//TODO Obs ! Finns utskfiter i konsoll
        List<Item> removeI = searchItemByName(prod); //listan som returneras sparas i denna lista.
        if(removeI.isEmpty()){System.out.println("Hittade ingen matchning."); return;}
        for (Item item : removeI){
            System.out.println("Hittade produkten: " + item.getName() + ", " + item.getDescription() + "." +
                    " Ska produkten tas bort från listan? JA / NEJ");
            String removeUser = scan.nextLine();
            if (removeUser.equalsIgnoreCase("ja")){
                getInventory().getItemsList().removeAll(removeI);
                System.out.println(" Produkt borttagen.");}
            System.out.println(" Se aktuell lista:");printItemList();
        }}

    public void newMascotItem(String name, String description, double day, String season) {
        Item item = new MascotCostume(name, description, day, season);
        addItemToList(item);
    }

    public void newBouncyItem(String name, String description, double day, boolean indoor) {
        Item item = new BouncyCastle(name, description, day, indoor);
        addItemToList(item);
    }

    public void printItemList(){ //TODO Obs finns utskrift i konsoll
        if(getInventory().getItemsList().isEmpty()){System.out.println("Inga produkter att visa.");}
        for(Item item:getInventory().getItemsList()){
            System.out.println(item);
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {System.out.println("Utskriften avbröts. Prova igen lite senare.");
            }
        }
    }

    public void printItemGroup(String attribut){//TODO utskrift i exception
        for(Item it : getInventory().getItemsList()) {
            if (it.getDescription().contains(attribut)){
                System.out.println(it);
                try{TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {System.out.println("Utskriften avbröts. Prova igen lite senare.");
                }}}
    }

    public void defaultList() { // För testning.
        newBouncyItem("Kungliga slottet"," Stor hoppborg,för max 15 barn",1000, false);
        newBouncyItem("Slott"," Liten hoppborg, för max 7 barn",450, true);
        newBouncyItem("UltimateXtreme"," Maxad hoppupplevelse, för max 8 vuxna",3500, false);
        newMascotItem("Nallebjörn"," Kramgo, lurvig brunbjörndräkt", 200,"Året om");
        newMascotItem("Tomten"," Premium tomtedräkt. Kvalitetskläder naturligt skägg. Inga skor medföljer.", 1000,"Jul");
    }

    //______________________________________________________________________
    //Uthyrningsmetoder
    public LocalDate createDateOfRent(String YYYYMMDD) throws DateTimeParseException {
        DateTimeFormatter styleDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetOfRent = LocalDate.parse(YYYYMMDD, styleDate);
        return datetOfRent;    }

    public Rental newRental(Item rentalItem, int rentDays, String startOfRent) { // Datum YYYY-MM-DD
        Rental rental = new Rental(rentalItem, rentDays, startOfRent);
        return rental;
    }

    public Rental newRental(Item rentalItem, int rentDays) { // Blir default dagens datum.
        Rental rental = new Rental(rentalItem, rentDays);
        return rental;
    }
    //önska antal
    public int rentDaysChoice(Scanner scan) { // TODO Obs! Finns utskrift i konsoll.
        System.out.println("Hur många dagar önskas hyra?");
        int days = scan.nextInt();
        return days;    }
    // byt antal
    /*
    public void changeRentDays(Member member, int x) {
        //getRentalRegistry().getRentalList().get(member).setRentDays(x); Ändrad Lista från Rental till List<Rental>
    }
    //visa valt antal
    public int rentalCountDays(Member member) {
        //return getRentalRegistry().getRentalList().get(member).getRentDays(); Ändrat Lista från Rental till List<Rental>
    }

    public double returnRentalDayPrice(Member member) {
        return getRentalRegistry().getRentalList().get(member).getRentalItem().getDayPrice();
    }

    public String returnRentalItemName(Member member) {
        return getRentalRegistry().getRentalList().get(member).getRentalItem().getName();
    }

    public void chooseDateInfo(String when){
        System.out.println(when + " vilket datum? Skriv i formatet ÅÅÅÅ-MM-DD.");
    }

    public String userChooseDate(String dateStartString){
        return dateStartString.replace(' ','-');}

    public void rentalsToList(Member member, Rental rentalItem) {
        getRentalRegistry().getRentalList().put(member, rentalItem);
        addHistory(rentalItem, member);
    }

    public void addHistory(Rental rentalItem, Member member) {
        member.getHistoryMember().add(rentalItem);
    }

    public void newRentAddRentListAndMemHistory(Item rentalItem, int rentDays, Member member) {
        rentalsToList(member, newRental(rentalItem, rentDays));
        // kan man göra hela kedjan i denna metod?
    }

    public void printRentalsList() {
        if (getRentalRegistry().getRentalList().isEmpty()){System.out.println("Inga uthyrningar gjorda.");}
        for(Map.Entry<Member, Rental> entry :getRentalRegistry().getRentalList().entrySet()) {
            System.out.println(entry.getKey() + " - "+ entry.getValue());
        }}

    public void countActualDays(String stopDate, Member member){ // här finns risk att det är ett förstort tal i long när de konverteras till int.
        LocalDate stopRent = createDateOfRent(stopDate);
        LocalDate theStartOfRent = getRentalRegistry().getRentalList().get(member).getStartOfRent();
        long actualDaysLong = stopRent.toEpochDay() - theStartOfRent.toEpochDay();
        int actualDays =(int) actualDaysLong;
        changeRentDays(member,actualDays);
    }

    public void sumRentalsList() {
        System.out.println("Hyrestransaktioner idag: ");
        double sum=0;
        for (Map.Entry<Member, Rental> entry : getRentalRegistry().getRentalList().entrySet()) {
            double price = calculateDay(entry.getValue().getRentalItem().getDayPrice(),entry.getValue().getRentDays());//OBS! Tar inte in pricepolicy.
            sum +=price;
            System.out.println(entry.getKey()+ " Produkt: "+ entry.getValue().getRentalItem().getName() +
                    ". Dagspris: " + entry.getValue().getRentalItem().getDayPrice()+ "kr. Hyrestid i dagar: "+ entry.getValue().getRentDays()
                    + ". Beräknad intäkt på uthyrningen bortsett från ev.rabatter: "+price+ " kr.");
        }System.out.println("Totala intäkter på affärer gjorda idag beräknas bli: "+ sum + "kr ex. moms.");}

    public double calculateDay(double dayPrice,int days) {
        double price = dayPrice * days;
        if(days>=30){ price = priceMonth(dayPrice,days);}
        return price;
    }

    public double priceMonth(double dayPrice,double days) {
        return (days/30)*((dayPrice*30)*0.7);
    }*/

}


