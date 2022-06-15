package com.bood_bank.bloodbank.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.BloodNameQuant;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;
import com.bood_bank.bloodbank.service.BloodService;
import com.bood_bank.bloodbank.service.StockAsPerOrganizationService;

@Component
public class StockAsPerOrganizationServiceImpl implements StockAsPerOrganizationService {

    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private BloodService bloodService;

    public List<BloodNameQuant> findAllByOname(String organizationName)
            throws ConstraintViolationException, OrganizationCollectionException {

        Organization o = (Organization) organizationRepository.findByOname(organizationName);
        if (o == null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.NotFoundException(organizationName));
        } else {
            List<StockAsPerOrganization> so = stockAsPerOrganizationRepository.findBidAndQuantityAvlByOid(o.get_id());
            List<BloodNameQuant> list = new ArrayList<BloodNameQuant>();
            for (StockAsPerOrganization s : so) {
                BloodNameQuant bq = new BloodNameQuant(this.bloodService.getBloodGroupById(s.getBid()),
                        s.getQuantityAvl());
                list.add(bq);
            }
            return list;
        }

    }
}
