package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.*;
import com.ahlenius.revent2.repository.Inventory;
import com.ahlenius.revent2.repository.MemberRegistry;
import com.ahlenius.revent2.repository.RentalRegistry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonService {
    ObjectMapper mapper = new ObjectMapper();
    Inventory inventory;
    RentalRegistry rentalRegistry;
    MemberRegistry memberRegistry;


    public JsonService(){}

    public JsonService(Inventory inventory,RentalRegistry rentalRegistry, MemberRegistry memberRegistry){
        this.inventory = inventory;
        this.rentalRegistry = rentalRegistry;
        this.memberRegistry = memberRegistry;
        //Lägger mapper "hur ska de sparas i konstruktorn istället för i metoderna där det är mer "vad".
        mapper.registerModule(new JavaTimeModule());
        mapper.registerSubtypes(
                new NamedType(BouncyCastle.class,"BouncyCastle"),
                new NamedType(MascotCostume.class,"MascotCostume"));
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

 // dessa metoder kan nog göras till en generell!

    public void itemlistToJson() throws IOException {
        try {
            mapper.writeValue(new File("items.json"),inventory.getItemList());
            System.out.println("Listan är sparad i fil");}
        catch (IOException e){ throw new IOException("Fel uppstsod vid sparande av Items till fil.");}}

    public void loadItemJsonToArrayList() throws IOException{
        try{
            List<Item> fromFile = Arrays.asList(mapper.readValue(new File("items.json"),Item[].class));
            System.out.println("Item-data laddat i temporär lista.");
            inventory.addList(fromFile);
            System.out.println("Items laddad från Json till lista. ");}
        catch (IOException e){throw new IOException("Fel uppstod vid uppladdning av Items-data från fil.");}}


    public void rentalistToJson() throws IOException {
        try {
            mapper.writeValue(new File("rental.json"),rentalRegistry.getRentalList());
            System.out.println("Rental-listan är sparad i fil");}
        catch (IOException e){ throw new IOException("Fel uppstod vid sparande av uthyrningsinfo till fil.");}}

    public void loadRentalJsonToArrayList() throws IOException{
        try{
            List<Rental> fromFile = Arrays.asList(mapper.readValue(new File("rental.json"), Rental[].class));
            System.out.println("Laddat fil i temporär lista.");
            rentalRegistry.addList(fromFile);
            System.out.println("Rentals laddad från Json till lista.");}
        catch (IOException e){throw new IOException("Fel uppstod vid uppladdning av uthyrningsinfo från fil.");}}


    public void memberlistToJson() throws IOException {
        try {
            mapper.writeValue(new File("members.json"),memberRegistry.getMemberRegistryList());
            System.out.println("Member-listan är sparad i fil");}
        catch (IOException e){ throw new IOException("Fel uppstsod vid sparande av medlemsinfo till fil.");}}

    public void loadMemberJsonToArrayList() throws IOException{
        try{
            List<Member> fromFile = Arrays.asList(mapper.readValue(new File("members.json"),Member[].class));
            System.out.println("Laddat fil i temporär lista.");
            memberRegistry.addList(fromFile);
            System.out.println("Members laddad från Json till lista.");}
        catch (IOException e){throw new IOException("Fel uppstod vid uppladdning av medlemsinfo från fil.");}}


}
