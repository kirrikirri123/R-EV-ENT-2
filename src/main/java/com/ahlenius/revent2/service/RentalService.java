package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.*;
import com.ahlenius.revent2.exceptions.InvalidAmountRentingDaysException;
import com.ahlenius.revent2.exceptions.InvalidDateChoiceException;
import com.ahlenius.revent2.exceptions.InvalidRentalInfoInputException;
import com.ahlenius.revent2.pricepolicy.PrivateIndividual;
import com.ahlenius.revent2.pricepolicy.Society;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.RentalRegistry;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    //Hanterar Item-funktioner kopplade till uthyrning.
    private Inventory inventory;
    private RentalRegistry rentalRegistry;
    private JsonService jsonService;
    private PrivateIndividual privateIndividual = new PrivateIndividual();
    private Society society = new Society();


    public RentalService (){}

    public RentalService(Inventory inventory, RentalRegistry rentalRegistry,JsonService jsonService){
        this.inventory = inventory;
        this.rentalRegistry = rentalRegistry;
        this.jsonService = jsonService;

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
    public Item searchItemByNameReturnItem(String prod) throws NullPointerException {
        Item foundItem = null;
        for (Item it : getInventory().getItemList()) {
            if (it.getName().equalsIgnoreCase(prod)) {
                foundItem = it;
            }
        }
        if(foundItem == null){throw new NullPointerException("Hittade ingen matchande produkt");}
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
    public void removeItem(Item item)throws IOException{
    getInventory().remove(item);
        jsonService.itemlistToJson();
    }
    public void newMascotItem(String name, String description, double day,boolean available, String season) throws IOException {
        addItemToList(new MascotCostume(name, description, day,available, season));
        jsonService.itemlistToJson();
    }
    public void newBouncyItem(String name, String description, double day,boolean available, boolean indoor) throws IOException {
        addItemToList(new BouncyCastle(name, description, day,available, indoor));
        jsonService.itemlistToJson();
    }
    public void addItemToList(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " är sparad i listan.");
    }
    public void defaultList() { // För testning.
        try{
        newBouncyItem("Kungliga slottet"," Stor hoppborg,för max 15 barn",1000,true, false);
        newBouncyItem("Slott"," Liten hoppborg, för max 7 barn",450,true, true);
        newBouncyItem("UltimateXtreme"," Maxad hoppupplevelse, för max 8 vuxna",3500,true, false);
        newMascotItem("Nallebjörn"," Kramgo, lurvig brunbjörndräkt", 200,true,"Året om");
        newMascotItem("Tomten"," Premium tomtedräkt. Kvalitetskläder naturligt skägg. Inga skor medföljer.", 1000,true,"Jul");
    } catch (IOException ex) { System.out.println(ex.getMessage());}}

    //Uthyrningsmetoder
    public LocalDate createDateOfRent(String YYYYMMDD) throws DateTimeParseException {
        DateTimeFormatter styleDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datetOfRent = LocalDate.parse(YYYYMMDD, styleDate);
        return datetOfRent;    }

    public Rental newRental(Member memberRenting, Item rentalItem, int rentDays, String startOfRent)
            throws IOException, InvalidAmountRentingDaysException, InvalidDateChoiceException,InvalidRentalInfoInputException {
        Rental rental = null;
        if(rentDays>183||rentDays<1)
        {throw new InvalidAmountRentingDaysException("Stämmer hyresdagarna? Minst 1 dag. Max 6 månader");}
        if (startOfRent.startsWith("19")||startOfRent.startsWith("2024"))
        {throw new InvalidDateChoiceException("Startdatum ej godkänt.");}
        if(memberRenting == null||rentalItem == null||startOfRent.isEmpty())
        {throw new InvalidRentalInfoInputException("Fyll i alla fält för att göra en uthyrning");
        } else {
            rental = new Rental(memberRenting, rentalItem, rentDays, startOfRent);
           try{ rentalsToList(rental);} catch (IOException e) { System.out.println("Problem uppstod när uthyrnings skulle sparas.");           }
        }
    return rental;}

    public void rentalsToList(Rental rentalItem) throws IOException{
        getRentalRegistry().add(rentalItem);
        addHistory(rentalItem,rentalItem.getRentingMember());
        jsonService.rentalistToJson();
    }
    public void addHistory(Rental rentalItem, Member member) {
        String infoToHistory = rentalItem.getRentalItem().getName() +", Hyresstart: "+ rentalItem.getStartOfRent();
        member.getHistoryMember().add(infoToHistory);
    }
    // byt antal
    public void changeRentDays(Rental rental, int x) {
        rental.setRentDays(x);
    System.out.println("I metoden sätt dagar");
    }
    //visa valt antal
    public int rentalCountDays(Rental rental) {
                return  rental.getRentDays();
    }
    public double returnRentalDayPrice(Rental rental) {
        return  rental.getRentalItem().getDayPrice();
    }
    public LocalDate userChooseDate(String dateStartString){
        String date;
        if(dateStartString.contains(" ")){
        date =dateStartString.replace(' ','-');}
        else {date = dateStartString;}
        return createDateOfRent(date);}

    public void countActualDays(LocalDate stopRent, Rental rental){ // här finns risk att det är ett förstort tal i long när de konverteras till int.
         LocalDate theStartOfRent = rental.getStartOfRent();
         long actualDaysLong = stopRent.toEpochDay() - theStartOfRent.toEpochDay();
        int actualDays =(int) actualDaysLong;
        System.out.println("I metoden räkna om dagar.");
        changeRentDays(rental,actualDays);
    }

    public void sumRentalsList() { // Ekonomi att se över vid senare tillfälle.
        System.out.println("Hyrestransaktioner idag: ");
        double sum=0;
        for (Rental rent : getRentalRegistry().getRentalList()) {
            double price = calculateDay(rent.getRentalItem().getDayPrice(),rent.getRentDays());//OBS! Tar inte in pricepolicy.
            sum +=price;
            System.out.println(rent.getRentingMember().getName() + " Produkt: "+ rent.getRentalItem().getName() +
                    ". Dagspris: " + rent.getRentalItem().getDayPrice()+ "kr. Hyrestid i dagar: "+ rent.getRentDays()
                    + ". Beräknad intäkt på uthyrningen bortsett från ev.rabatter: "+price+ " kr.");
        }System.out.println("Totala intäkter på affärer gjorda idag beräknas bli: "+ sum + "kr ex. moms.");}

    public double calculateDay(double dayPrice,int days) {
        double price = dayPrice * days;
        if(days>=30){ price = priceMonth(dayPrice,days);}
        return price;
    }
    // Rabatt vid uthyrning längre än 30 dagar. Skriv ut i UI info om detta?
    public double priceMonth(double dayPrice,double days) {
        return (days/30)*((dayPrice*30)*0.7);
    }
    public double calculateBasePrice(Rental rental) {
            return calculateDay(returnRentalDayPrice(rental), rentalCountDays(rental));
    }
    public String pricePolicyCalc(Rental rental) {
            double totalBasePrice = calculateBasePrice(rental);
            String totalPrice;
        if (rental.getRentingMember().getMemberStatus().equalsIgnoreCase("privat")) {
            totalPrice = privateIndividual.priceVAT(privateIndividual.discount(totalBasePrice));
        } else {
            totalPrice = society.priceVAT(society.discount(totalBasePrice));
        }return totalPrice;
    }

}


