package com.ahlenius.revent2.service;

import com.ahlenius.revent2.entity.Member;
import com.ahlenius.revent2.entity.Rental;
import com.ahlenius.revent2.exceptions.*;
import com.ahlenius.revent2.repository.MemberRegistry;

import java.util.*;

public class MembershipService {
    // Hanterar memberfunktioner. Medlemsrabatter? Ta isf in PI och S objekten hit istället?
    private MemberRegistry memberRegistry;

    public MembershipService(){}

    public MembershipService(MemberRegistry memberRegistry){
        this.memberRegistry = memberRegistry;}

    public MemberRegistry getMemberRegistry() {
        return memberRegistry;
    }

    public void newMember(String id, String name,String phone, String memberStatus)throws InvalidMemberInfoInputException,InvalidPhoneInputException,InvalidNameInputException {
        if(phone.isEmpty()||!phone.startsWith("07")&& !phone.startsWith("09")) {throw new InvalidPhoneInputException("Dubbelkolla ditt mobilnummer. Ex. 070 123 45 67");}
        if(name.equalsIgnoreCase("bajs")){throw new InvalidNameInputException ("STOPP! Bajs är inte ett godkänt namn");}
        if(id.isEmpty()||name.isEmpty()||memberStatus.isEmpty()){throw new InvalidMemberInfoInputException("Dubbelkolla att alla fält är ifyllda.");}
        else {Member member = new Member(id,name,phone,memberStatus);
        addMemberList(member); }}

    public void addMemberList(Member member) {
        getMemberRegistry().getMemberRegistryList().add(member);
    }

    public List<Member> searchMemberByNameIdReturnList(String nameOrPhone) throws NullPointerException {
        List<Member> foundM = new ArrayList<>();
        for (Member m : getMemberRegistry().getMemberRegistryList()) {
            if (m.getName().contains(nameOrPhone) && m.getName().length()>3|| m.getName().equalsIgnoreCase(nameOrPhone) || m.getPhone().equals(nameOrPhone)) {
                foundM.add(m);}}
        if (foundM.isEmpty()) { throw new NullPointerException("Hittade ingen matchande medlem.");}
        return foundM;} // sorterar med contains om det är mer än 3 chars och inte bara blanksteg.

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


    public void removeMember(String nameOrId, Scanner scan){ //TODO Obs! Finns utskrift och scanner i konsoll
        List<Member> foundMatches = searchMemberByNameIdReturnList(nameOrId);
        if(foundMatches.isEmpty()){System.out.println("Hittade ingen matchning."); return;} // lägg exceptions
        for(Member m : foundMatches)
        {System.out.println("Hittade "+m.getName()+" med ID: "+ m.getId()+ ". Ska "+ m.getName()+" tas bort från listan? JA / NEJ");
            String removeUser = scan.nextLine();
            if(removeUser.equalsIgnoreCase("ja")){
                foundMatches.forEach(getMemberRegistry().getMemberRegistryList()::remove); // Ändrad till funktion med ::
                System.out.println("Medlem borttagen.");
            }else{System.out.println("Avbryter borttagning.");}}}

    public List<Rental> getMemberHistory(Member member)throws NoHistoryFoundException{
         if(member.getHistoryMember().isEmpty()){ throw new NoHistoryFoundException("Finns ingen historik på vald medlem.");}
         return member.getHistoryMember();} //returnerar riktiga listan

    public void printMemberReg() { //TODO Obs finns utkskrift i konsoll. FÅ utskriften genom Egetexception?
        if (getMemberRegistry().getMemberRegistryList().isEmpty()){System.out.println("Listan är tom.");}
        for (Member m: getMemberRegistry().getMemberRegistryList()){
            System.out.println(m);
        }
    }
    public void findAndUpdateMember(String nameOrId, Scanner scan){ // TODO Obs ! Finns uskrift och scanner i konsoll
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
        try {
            newMember("112", "Pelle Polis", "090669966", "Privat");
            newMember("123", "Björn Varg", "095037417", "Privat");
            newMember("920618", "Kickan Karlsson", "095014841", "Privat");
            newMember("970517", "Kickan Knäppsson", "091077993", "Privat");
            newMember("1", "Marta Mus", "0950-10832", "Privat");
            newMember("123456", "Ersboda Pingisföreningsklubb", "0950-12363", "Förening");
        } catch (InvalidMemberInfoInputException | InvalidNameInputException | InvalidPhoneInputException e) {
            System.out.println("Fel i testlistan"+ e.getMessage());

        }
    }
}



