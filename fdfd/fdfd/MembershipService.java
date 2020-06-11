package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.vo.Membership;

public interface MembershipService {
    List<Membership> getAllMembership() throws Exception;

    List<Membership> getMembershipByUserId(int userId) throws Exception;

    Membership getMembershipById(int id) throws Exception;

    void insertMembership(Membership membership) throws Exception;

    void updateMembership(Membership membership) throws Exception;

    void deleteMembership(int id) throws Exception;
}