package com.bood_bank.bloodbank.service;

import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.BloodGroup;

@Service
public interface BloodService {

    String findAllByFirstName(String bname);

    String getBloodGroupById(String bloodId);

    Object getBloodGroup();

    Object addBloodGroup(BloodGroup bloodGroup);

}
