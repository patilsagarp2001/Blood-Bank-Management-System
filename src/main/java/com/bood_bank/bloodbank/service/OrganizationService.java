package com.bood_bank.bloodbank.service;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;

@Service
public interface OrganizationService {

    String findAllByFirstName(String organizationName);

    Object getOrganization();

    void addOrganization(Organization organization)
            throws ConstraintViolationException, OrganizationCollectionException;

    void deleteOrganization(String organizationId) throws ConstraintViolationException, OrganizationCollectionException;

}
