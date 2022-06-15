package com.bood_bank.bloodbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.repo.BloodRepository;

@Service
public class BloodService {

    @Autowired
    private BloodRepository bloodRepository;

    public Object addBloodGroup(BloodGroup bloodGroup) {
        return bloodRepository.save(bloodGroup);
    }

    public Object getBloodGroup() {
        return this.bloodRepository.findAll();
    }

    public String findAllByFirstName(String bname) {
        BloodGroup b = (BloodGroup) this.bloodRepository.findByBname(bname);
        return b.get_id();
    }

}
