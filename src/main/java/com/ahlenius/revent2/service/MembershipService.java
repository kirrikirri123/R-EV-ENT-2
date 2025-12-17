package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.exceptions.*;
import com.ahlenius.revent2.repository.MemberRegistry;
import java.io.IOException;
import java.util.*;

public class MembershipService {
    private MemberRegistry memberRegistry;
    private JsonService jsonService;

    public MembershipService(){}

    public MembershipService(MemberRegistry memberRegistry, JsonService jsonService){
        this.memberRegistry = memberRegistry;
        this.jsonService = jsonService;}

    public MemberRegistry getMemberRegistry() {
        return memberRegistry;
    }

// Skapa spara, ladda medlem.
    public void newMember(String id, String name,String phone, String memberStatus)
            throws InvalidMemberInfoInputException, InvalidPhoneInputException, InvalidNameInputException, IOException {
        if(phone.isEmpty()||!phone.startsWith("07")&& !phone.startsWith("09"))
                {throw new InvalidPhoneInputException("Dubbelkolla ditt mobilnummer. Ex. 070 123 45 67");}
        if(name.equalsIgnoreCase("bajs"))
                {throw new InvalidNameInputException ("STOPP! Bajs är inte ett godkänt namn");}
        if(id.isEmpty()||name.isEmpty()||memberStatus.isEmpty())
                {throw new InvalidMemberInfoInputException("Dubbelkolla att alla fält är ifyllda.");}
        else {Member member = new Member(id,name,phone,memberStatus);
        addMemberList(member); }}

    public void addMemberList(Member member) throws IOException {
        getMemberRegistry().add(member);
        System.out.println(member.getName() + " är sparad i listan.");
        jsonService.memberlistToJson();}

// Söka ändra medlem.
    public List<Member> searchMemberByNameIdReturnList(String nameOrPhone) throws NullPointerException {
        List<Member> foundM = new ArrayList<>();
        for (Member m : getMemberRegistry().getMemberRegistryList()) {
            if (m.getName().contains(nameOrPhone) && m.getName().length()>3|| m.getName().equalsIgnoreCase(nameOrPhone) || m.getPhone().equals(nameOrPhone)) {
                foundM.add(m);}}
        if (foundM.isEmpty()) { throw new NullPointerException("Hittade ingen matchande medlem.");}
        return foundM;}

    public Member searchMemberByNameOrPhoneReturnMember(String nameOrPhone) throws NullPointerException{
        Member foundMember = null;
        for (Member m : getMemberRegistry().getMemberRegistryList()) {
            if (m.getName().equalsIgnoreCase(nameOrPhone) || m.getPhone().equals(nameOrPhone)) {
                foundMember = m;}
            }
        if(foundMember==null){throw new NullPointerException("Hittade ingen matchande medlem.");}
        return foundMember;
    }
    public ArrayList<String> checkMemberlistReturnFormatedStringList(String nameOrPhone) throws NullPointerException {
        List<Member> foundMatches = searchMemberByNameIdReturnList(nameOrPhone);
        ArrayList<String> foundMemInfo = new ArrayList<>();
        int j =0;
        for(Member m : foundMatches){j++; foundMemInfo.add( j + ".\n " + m.toString());}
    return foundMemInfo; }

    public List<String> getMemberHistory(Member member)throws NoHistoryFoundException {
        if (member.getHistoryMember().isEmpty()) {
            throw new NoHistoryFoundException("Finns ingen historik på vald medlem.");
        } return member.getHistoryMember();    }


    public void updateMemberName(Member member,String newName){
        member.setName(newName); }

    public void updateMemberPhone(Member member, String phone){
        member.setPhone(phone);
    }
    public void updateMemberStatus(Member member, String status) {
        if (status.equalsIgnoreCase("Privatperson")) {
            member.setMemberStatus("Privat");
        } else if (status.equalsIgnoreCase("Förening")) {
            member.setMemberStatus("Förening");}
    }

    public String createMemberStatus(String status){
        String userStatus ="";
        if (status.equalsIgnoreCase("Privatperson")) {
            userStatus ="Privat";
        } else if (status.equalsIgnoreCase("Förening")) {
            userStatus ="Förening";
                    }return userStatus;
    }


}



