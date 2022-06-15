package com.bood_bank.bloodbank.service;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.Reciever;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.exception.RecieverCollectionException;

@Service
public interface RecieverService {

    Object addToReciever(Reciever reciever) throws ConstraintViolationException, OrganizationCollectionException;

    Object getRecieverInPage(int pageNo, int pageSize, String sortBy, String status);

    void deleteById(String donorId) throws ConstraintViolationException, RecieverCollectionException;

    void compeletedReciever(@NotBlank String recieverId)
            throws RecieverCollectionException;

    String checkStatus(String requestId) throws RecieverCollectionException;

}
