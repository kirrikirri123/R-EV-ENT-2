package com.ahlenius.revent2.entity;

import java.util.LinkedList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private String phone;  // lägg till i konstruktor och metod.
    private String memberStatus; //private eller society
    private List<Rental> historyMember = new LinkedList<>();

    public Member (){}
    public Member (String id, String name,String phone, String memberStatus) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.memberStatus = memberStatus;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMemberStatus() {
        return memberStatus;
    }
    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
    public List<Rental> getHistoryMember() {
        return historyMember;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Id-nummer:"+ this.id + ", Namn:"+ this.name +".";
    }}

