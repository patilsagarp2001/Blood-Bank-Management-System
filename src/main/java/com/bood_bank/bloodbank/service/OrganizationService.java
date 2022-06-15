package com.bood_bank.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.repo.BloodRepository;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    private BloodRepository bloodRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;

    public Organization addOrganization(Organization organization) {
        Organization org = organizationRepository.save(organization);

        List<BloodGroup> listBlood = bloodRepository.findAll();
        for (BloodGroup r : listBlood) {
            StockAsPerOrganization so = new StockAsPerOrganization();
            so.setOid(org.get_id());
            so.setBid(r.get_id());
            so.setQuantityAvl(0);
            stockAsPerOrganizationRepository.save(so);
        }
        return org;
    }

    public Object getOrganization() {
        return this.organizationRepository.findAll();
    }

    public String findAllByFirstName(String organizationName) {
        Organization o = (Organization) this.organizationRepository.findByOname(organizationName);
        return o.get_id();
    }

}
