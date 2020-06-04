package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.dao.MembershipDAO;
import com.ssafy.backend.vo.Membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipDAO dao;

    @Override
    public List<Membership> getAllMembership() throws Exception {
        return dao.getAllMembership();
    }

    @Override
    public Membership getMembershipById(int id) throws Exception {
        return dao.getMembershipById(id);
    }

    @Override
    public void insertMembership(Membership membership) throws Exception {
        dao.insertMembership(membership);
    }

    @Override
    public void updateMembership(Membership membership) throws Exception {
        dao.updateMembership(membership);
    }

    @Override
    public void deleteMembership(int id) throws Exception {
        dao.deleteMembership(id);
    }
    
}