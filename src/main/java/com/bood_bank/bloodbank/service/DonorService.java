package com.bood_bank.bloodbank.service;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.Donor;
import com.bood_bank.bloodbank.exception.DonorCollectionException;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;

@Service
public interface DonorService {

    Object getAllDonorInPage(int pageNo, int pageSize, String sortBy);

    void deleteById(String donorId) throws ConstraintViolationException, DonorCollectionException;

    Object updateDonor(@NotBlank String donorId, @Valid Donor donor)
            throws ConstraintViolationException, OrganizationCollectionException, DonorCollectionException;

    Object addToDonor(@Valid Donor donor) throws ConstraintViolationException, OrganizationCollectionException;

}
