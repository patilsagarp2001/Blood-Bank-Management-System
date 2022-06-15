package com.bood_bank.bloodbank.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.entities.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

    Object findAllByOname(String organizationName);

    Organization findByOname(String organizationName);

    BloodGroup findBy_id(String bloodId);

    void deleteBy_id(String get_id);

}
