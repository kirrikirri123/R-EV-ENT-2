package com.ahlenius.revent2.entity;

import javafx.beans.property.SimpleStringProperty;
import java.util.LinkedList;
import java.util.List;

// Sätta annotationer på alla dessa också även om de inte är "special"datatyper som ska skickas  runt ?

public class Member {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty name= new SimpleStringProperty();
    private SimpleStringProperty phone= new SimpleStringProperty();
    private SimpleStringProperty memberStatus= new SimpleStringProperty(); //private eller society
    private List<String> historyMember = new LinkedList<>();

    public Member (){}
    public Member (String id, String name,String phone, String memberStatus) {
        this.id.set(id);
        this.name.set(name);
        this.phone.set(phone);
       this.memberStatus.set(memberStatus);
    }
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMemberStatus() {
        return memberStatus.get();
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus.set(memberStatus);
    }
    public List<String> getHistoryMember() {
        return historyMember;
    }
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "Id-nummer: "+ this.id.getValue() + "\nNamn: "+ this.name.getValue() +"\nTelefonnummer:  "+ phone.getValue()+"\n Status: "+ memberStatus.getValue();
    }}

