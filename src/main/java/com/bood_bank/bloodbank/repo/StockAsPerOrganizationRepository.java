package com.bood_bank.bloodbank.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bood_bank.bloodbank.entities.StockAsPerOrganization;

public interface StockAsPerOrganizationRepository extends MongoRepository<StockAsPerOrganization, String> {

    StockAsPerOrganization findByOidAndBid(String temp_oid, String temp_bid);

    List<StockAsPerOrganization> findAllByOid(String get_id);

}
