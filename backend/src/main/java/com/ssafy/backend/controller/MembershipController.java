package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.MembershipService;
import com.ssafy.backend.vo.Membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "MEMBERSHIP" }, description = "MEMBERSHIP API")
public class MembershipController {

    @Autowired
    private MembershipService service;

    @GetMapping("membership/all")
    public ResponseEntity getAllMembership() {
        try {
            List<Membership> list = service.getAllMembership();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("membership/{id}")
    public ResponseEntity getMembershipById(@PathVariable("id") int id) {
        try {
            Membership m = service.getMembershipById(id);
            return new ResponseEntity<>(m, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("membership")
    public ResponseEntity insertMembership(@RequestBody Membership membership) {
        try {
            service.insertMembership(membership);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("membership")
    public ResponseEntity updateMembership(@RequestBody Membership membership) {
        try {
            service.updateMembership(membership);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("membership/{id}")
    public ResponseEntity deleteMembership(@PathVariable("id") int id) {
        try {
            service.deleteMembership(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}