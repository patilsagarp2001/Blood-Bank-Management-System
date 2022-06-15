package com.bood_bank.bloodbank.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.repo.BloodRepository;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;
import com.bood_bank.bloodbank.service.OrganizationService;

@Component
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private BloodRepository bloodRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;

    public void addOrganization(Organization organization)
            throws ConstraintViolationException, OrganizationCollectionException {

        Organization organizationOptional = organizationRepository.findByOname(organization.getOname());
        System.out.println(organizationOptional);
        if (organizationOptional != null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.OrganizationAlreadyExists(organization.getOname()));
        } else {
            Organization org = organizationRepository.save(organization);

            List<BloodGroup> listBlood = bloodRepository.findAll();
            for (BloodGroup r : listBlood) {
                StockAsPerOrganization so = new StockAsPerOrganization();
                so.setOid(org.get_id());
                so.setBid(r.get_id());
                so.setQuantityAvl(0);
                stockAsPerOrganizationRepository.save(so);
            }
            // return org;
        }

    }

    public Object getOrganization() {
        try {
            List<Organization> o = this.organizationRepository.findAll();
            if (o.size() == 0) {
                return null;
            }
            return o;
        } catch (Exception e) {
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            // }
            return null;
        }

        // if (o.size() == 0) {
        // return null;
        // }
        // return o;
    }

    public String findAllByFirstName(String organizationName) {
        Organization o = this.organizationRepository.findByOname(organizationName);
        return o.get_id();
    }

    @Override
    public void deleteOrganization(String organizationName)
            throws ConstraintViolationException, OrganizationCollectionException {
        // TODO Auto-generated method stub
        Organization o = this.organizationRepository.findByOname(organizationName);

        if (o == null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.NotFoundException(organizationName));
        } else {
            this.stockAsPerOrganizationRepository.deleteAllByOid(o.get_id());
            this.organizationRepository.deleteBy_id(o.get_id());
            // return org;
        }

        // if (o == null) {
        // return null;
        // } else {
        // this.stockAsPerOrganizationRepository.deleteAllByOid(o.get_id());
        // this.organizationRepository.deleteBy_id(o.get_id());
        // return "Succesffully deleted";
        // }

    }
}
