package com.bood_bank.bloodbank.service;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.exception.OrganizationCollectionException;

@Service
public interface StockAsPerOrganizationService {

    Object findAllByOname(@NotNull String organizationName)
            throws ConstraintViolationException, OrganizationCollectionException;

}
