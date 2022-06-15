package com.bood_bank.bloodbank.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.repo.BloodRepository;
import com.bood_bank.bloodbank.service.BloodService;

@Component
public class BloodServiceImpl implements BloodService {
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

    public String getBloodGroupById(String bloodId) {
        BloodGroup b = this.bloodRepository.findBy_id(bloodId);
        return b.getBname();
    }
}
