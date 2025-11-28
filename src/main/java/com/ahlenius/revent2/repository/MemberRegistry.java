package com.ahlenius.revent2.repository;

import com.ahlenius.revent2.entity.Member;

import java.util.HashSet;
import java.util.Set;

public class MemberRegistry {
    private Set<Member> memberRegistryList = new HashSet<>();

    public MemberRegistry(){}

    public Set<Member> getMemberRegistryList() {
        return memberRegistryList;
    }

}


