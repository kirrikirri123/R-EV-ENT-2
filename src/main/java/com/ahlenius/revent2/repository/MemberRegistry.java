package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberRegistry {
    private Set<Member> memberRegistryList = new HashSet<>();
    private ObservableSet<Member> memberRegObsList = FXCollections.observableSet();

    public MemberRegistry(){}

    public Set<Member> getMemberRegistryList() {
        return memberRegistryList;
    }
    public ObservableSet<Member> getMemberRegObsList() {
        return memberRegObsList;
    }

    public void add(Member member){
        memberRegistryList.add(member);
        memberRegObsList.add(member);
    }
    public void remove(Member member){
        memberRegistryList.remove(member);
        memberRegObsList.remove(member);
    }


    public void addList(List<Member> tempMember){
        memberRegistryList.addAll(tempMember);
        memberRegObsList.addAll(tempMember);
    }

    public ObservableList<Member> convertMemberSetToObsList(){
        ObservableList<Member> tempObsMemList = FXCollections.observableArrayList();
        tempObsMemList.addAll(memberRegObsList);
                return tempObsMemList;
    }

}


