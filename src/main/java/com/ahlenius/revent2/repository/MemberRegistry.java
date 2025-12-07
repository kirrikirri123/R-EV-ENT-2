package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;
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
}


