package com.bood_bank.bloodbank.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.Donor;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.exception.DonorCollectionException;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.repo.DonorRepository;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;
import com.bood_bank.bloodbank.service.BloodService;
import com.bood_bank.bloodbank.service.DonorService;
import com.bood_bank.bloodbank.service.OrganizationService;

@Component
public class DonorServiceImpl implements DonorService {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private BloodService bloodService;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Object addToDonor(Donor donor) throws ConstraintViolationException, OrganizationCollectionException {
        // String organizationName = donor.getOname();
        // System.out.println(organizationName);
        Organization o = this.organizationRepository.findByOname(donor.getOname());
        System.out.println("donor is" + donor.getOname());
        System.out.println(o);
        if (o == null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.NotFoundException(donor.getOname()));
            // return null;
        } else {
            Donor temp = donorRepository.save(donor);
            String temp_oid = organizationService.findAllByFirstName(temp.getOname());
            String temp_bid = bloodService.findAllByFirstName(temp.getBname());

            StockAsPerOrganization upData = stockAsPerOrganizationRepository.findByOidAndBid(temp_oid, temp_bid);
            upData.setQuantityAvl(upData.getQuantityAvl() + temp.getQuantityDonate());
            stockAsPerOrganizationRepository.save(upData);
            return temp;
        }

    }

    // private StockAsPerOrganization findAllMatch(String temp_oid, String temp_bid)
    // {
    // StockAsPerOrganization so = ;
    // MongoOperations.findAll()
    // return
    // }

    public Object getDonor() {
        return this.donorRepository.findAll();
    }

    public Page<Donor> getAllDonorInPage(int pageNo, int pageSize, String sortBy) {
        // Map<String, Object> response = new HashMap<String, Object>();
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Donor> donorPage = donorRepository.findAll(page);
        return donorPage;
        // response.put("Data:- ", donorPage.getContent());
        // response.put("Total No. of Pages:- ", donorPage.getTotalPages());
        // response.put("Total No. of Elements:- ", donorPage.getNumberOfElements());
        // response.put("Current Page:- ", donorPage.getNumber());
        // return response;
    }

    public Object updateDonor(String donorId, Donor updatedonor)
            throws ConstraintViolationException, OrganizationCollectionException, DonorCollectionException {
        Optional<?> updateData = donorRepository.findBy_id(donorId);
        System.out.println(updateData);
        System.out.println(updatedonor.getBname());
        if (!(updatedonor.getBname()).matches("(A|B|AB|O)[+-]")) {
            throw new DonorCollectionException(
                    DonorCollectionException.NotFoundException(updatedonor.getBname()));
        }
        Organization o = this.organizationRepository.findByOname(updatedonor.getOname());
        if (o == null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.NotFoundException(updatedonor.getOname()));
        } else if (updateData.isPresent()) {
            Donor _donor = (Donor) updateData.get();
            String prev_oid = organizationService.findAllByFirstName(_donor.getOname());
            String prev_bid = bloodService.findAllByFirstName(_donor.getBname());
            int prev_quantityDonate = _donor.getQuantityDonate();

            StockAsPerOrganization upData = stockAsPerOrganizationRepository.findByOidAndBid(prev_oid, prev_bid);
            upData.setQuantityAvl(upData.getQuantityAvl() - prev_quantityDonate);
            stockAsPerOrganizationRepository.save(upData);

            _donor.setDname(updatedonor.getDname());
            _donor.setDmobile(updatedonor.getDmobile());
            _donor.setDgender(updatedonor.getDgender());
            _donor.setDemail(updatedonor.getDemail());
            _donor.setBname(updatedonor.getBname());
            _donor.setOname(updatedonor.getOname());
            _donor.setQuantityDonate(updatedonor.getQuantityDonate());

            String temp_oid = organizationService.findAllByFirstName(_donor.getOname());
            String temp_bid = bloodService.findAllByFirstName(_donor.getBname());

            StockAsPerOrganization upData2 = stockAsPerOrganizationRepository.findByOidAndBid(temp_oid, temp_bid);
            upData2.setQuantityAvl(upData2.getQuantityAvl() + _donor.getQuantityDonate());
            stockAsPerOrganizationRepository.save(upData2);

            return donorRepository.save(_donor);

        } else {
            throw new DonorCollectionException(
                    DonorCollectionException.NotFoundException(donorId));
        }

    }

    public void deleteById(String donorId) throws ConstraintViolationException, DonorCollectionException {
        Optional<?> updateData = donorRepository.findBy_id(donorId);
        if (updateData.isPresent()) {
            Donor _donor = (Donor) updateData.get();
            String prev_oid = organizationService.findAllByFirstName(_donor.getOname());
            String prev_bid = bloodService.findAllByFirstName(_donor.getBname());
            int prev_quantityDonate = _donor.getQuantityDonate();

            StockAsPerOrganization upData = stockAsPerOrganizationRepository.findByOidAndBid(prev_oid, prev_bid);
            upData.setQuantityAvl(upData.getQuantityAvl() - prev_quantityDonate);
            stockAsPerOrganizationRepository.save(upData);
            donorRepository.deleteBy_id(donorId);
        } else {
            throw new DonorCollectionException(
                    DonorCollectionException.NotFoundException(donorId));
        }

    }

    // public Donor addToDonor(Donor donor) {
    // donor.setId(generateSequence(Donor.SEQUENCE_NAME));
    // return donorRepository.save(donor);
    // }

    // private long generateSequence(String seqName) {
    // DbSequence counter =
    // mongoOperations.findAndModify(query(where("id").is(seqName)),
    // new Update().inc("seq", 1), options().returnNew(true).upsert(true),
    // DbSequence.class);
    // return !Objects.isNull(counter) ? counter.getSeq() : 1;
    // }

}
