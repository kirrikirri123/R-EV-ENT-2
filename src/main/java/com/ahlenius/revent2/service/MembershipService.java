package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.repository.MemberRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembershipService {
    // Hanterar memberfunktioner. Medlemsrabatter? Ta isf in PI och S objekten hit istället?
    private MemberRegistry memberRegistry;

    public MembershipService(MemberRegistry memberRegistry){
        this.memberRegistry = memberRegistry;}

    public MemberRegistry getMemberRegistry() {
        return memberRegistry;
    }

    public void newMember(String id, String name, String memberStatus){
        Member member = new Member(id,name,memberStatus);
        addMemberList(member); }

    public void addMemberList(Member member) {
        getMemberRegistry().getMemberRegistryList().add(member);
    }
    public void searchInfo(){
        System.out.println("Vilken medlem? Ange namn eller personnummer/organistationsnummer.");
    }

    public List<Member> searchMemberByNameIdReturnList(String nameOrId){
        List<Member> foundM= new ArrayList<>();
        for(Member m : getMemberRegistry().getMemberRegistryList()){
            if(m.getName().contains(nameOrId)||m.getName().equalsIgnoreCase(nameOrId)|| m.getId().equals(nameOrId)){
                foundM.add(m);}}return foundM;}

    public Member searchMemberByNameOrIdReturnMember(String nameOrId) throws NullPointerException{
        Member foundMember = null;
        for (Member m : getMemberRegistry().getMemberRegistryList()) {
            if (m.getName().contains(nameOrId)||m.getName().equalsIgnoreCase(nameOrId) || m.getId().equals(nameOrId)) {
                foundMember = m;
            }
        }
        return foundMember;
    }

    public void checkListPrintMembersFound(String nameOrId){
        List<Member> foundMatches = searchMemberByNameIdReturnList(nameOrId);
        if(foundMatches.size()>1) {
            System.out.println("Hittade flera matchningar: ");
            for (int i = 0,j =1; i < foundMatches.size(); i++, j++) {
                System.out.println("Nr. " + j + " " + foundMatches.get(i).getName() +" "+ foundMatches.get(i).getMemberStatus());}
        }else if(foundMatches.isEmpty()){System.out.println("Hittade ingen matchning.");}
        else { for(Member member : foundMatches){
            String memberStatusPrint = null;
            if(member.getMemberStatus().equals("Privat")){memberStatusPrint = "privatkund."; } else if(member.getMemberStatus().equals("Förening")){ memberStatusPrint ="föreningskund.";}
            System.out.println("Hittade "+member.getName()+" med ID: "+ member.getId() + ". Är "+ memberStatusPrint);}
        }}

    public void removeMember(String nameOrId, Scanner scan){
        List<Member> foundMatches = searchMemberByNameIdReturnList(nameOrId);
        if(foundMatches.isEmpty()){System.out.println("Hittade ingen matchning."); return;}
        for(Member m : foundMatches)
        {System.out.println("Hittade "+m.getName()+" med ID: "+ m.getId()+ ". Ska "+ m.getName()+" tas bort från listan? JA / NEJ");
            String removeUser = scan.nextLine();
            if(removeUser.equalsIgnoreCase("ja")){
                getMemberRegistry().getMemberRegistryList().removeAll(foundMatches);
                System.out.println("Medlem borttagen.");
            }else{System.out.println("Avbryter borttagning.");}}}

    public void getMemberHistory(Member member){
        if(member.getHistoryMember().isEmpty()){System.out.println("Finns ingen historik på vald medlem.");}
        else { for (Rental r : member.getHistoryMember() ){
            System.out.println(r); }
        }}

    public void printMemberReg() {
        if (getMemberRegistry().getMemberRegistryList().isEmpty()){System.out.println("Listan är tom.");}
        for (Member m: getMemberRegistry().getMemberRegistryList()){
            System.out.println(m);
        }
    }

    public void findAndUpdateMember(String nameOrId, Scanner scan){
        List<Member> foundMatches = searchMemberByNameIdReturnList(nameOrId);
        if(foundMatches.isEmpty()){System.out.println("Hittade ingen matchning."); return;}
        for(Member m : foundMatches)
        {System.out.println("Hittade "+m.getName()+" med ID: "+ m.getId()+ ".");
            System.out.println("Ska profilen uppdateras?\n -  Om felaktigt ange X ! -");
            System.out.println("Vad vill du uppdatera? \n Ange : [N] Namn [M] Medlemsstatus");
            String userChoiceChange = scan.nextLine();
            if(userChoiceChange.equalsIgnoreCase("N")) {
                System.out.println("Skriv in den nya namnet:");
                String memberFname = scan.next() + " ";
                String memberLname = scan.next();
                scan.nextLine(); //cleaningcrew
                updateMemberName(m, memberFname + " " + memberLname);
                System.out.println("Medlem uppdaterad!");
            }else if (userChoiceChange.equalsIgnoreCase("M")){
                System.out.println( "Om privatperson ange P. Om förening ange F.");
                String memberStatus = scan.nextLine();
                updateMemberStatus(m,memberStatus);
                System.out.println("Medlem uppdaterad!");
            }else {System.out.println("Avbryter uppdatering av medlem.");}
        }}

    public void updateMemberName(Member member,String newName){
        member.setName(newName); }

    public void updateMemberStatus(Member member, String status) {
        if (status.equalsIgnoreCase("P")) {
            member.setMemberStatus("Privat");
        } else if (status.equalsIgnoreCase("F")) {
            member.setMemberStatus("Förening");
        }
    }

    public void defaultList() { // För testning.
        newMember("112", "Pelle Polis","Privat");
        newMember("123", "Björn Varg","Privat");
        newMember("920618", "Kickan Karlsson","Privat");
        newMember("1","Marta Mus","Privat");
        newMember("123456", "Ersboda Pingisföreningsklubb","Förening");
    }

}



