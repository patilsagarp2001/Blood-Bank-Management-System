package com.bood_bank.bloodbank.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bood_bank.bloodbank.entities.StockDetails;

@Repository
public interface StockRepository extends MongoRepository<StockDetails, Long> {

}
