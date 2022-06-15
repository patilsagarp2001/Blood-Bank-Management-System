package com.bood_bank.bloodbank.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bood_bank.bloodbank.entities.BloodGroup;

@Repository
public interface BloodRepository extends MongoRepository<BloodGroup, Integer> {

    BloodGroup findByBname(String bname);

    BloodGroup findBy_id(String bloodId);

}
