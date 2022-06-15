package com.bood_bank.bloodbank.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bood_bank.bloodbank.entities.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

    Object findAllByOname(String organizationName);

    Object findByOname(String organizationName);

}
