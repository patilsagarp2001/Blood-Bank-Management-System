package com.bood_bank.bloodbank.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bood_bank.bloodbank.entities.Donor;

@Repository
public interface DonorRepository extends MongoRepository<Donor, Long> {

    Optional<?> findBy_id(String donorId);

    void deleteBy_id(String donorId);

}
