package com.bood_bank.bloodbank.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bood_bank.bloodbank.entities.BloodGroup;

public interface BloodRepository extends MongoRepository<BloodGroup, Integer> {

    BloodGroup findByBname(String bname);

    BloodGroup findBy_id(String bloodId);

}
