package com.bood_bank.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;

@Service
public class StockAsPerOrganizationService {

    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<StockAsPerOrganization> findAllByOname(String organizationName) {
        Organization o = (Organization) organizationRepository.findByOname(organizationName);

        List<StockAsPerOrganization> so = stockAsPerOrganizationRepository.findAllByOid(o.get_id());
        return so;
    }

}
