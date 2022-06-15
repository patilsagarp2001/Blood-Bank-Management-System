package com.bood_bank.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

//import com.bood_bank.bloodbank.entities.DbSequence;
import com.bood_bank.bloodbank.entities.Donor;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.repo.DonorRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;

//import org.springframework.data.mongodb.core.query.Update;

//import static org.springframework.data.mongodb.core.query.Query.query;
//import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
//import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class DonorService {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private BloodService bloodService;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Object addToDonor(Donor donor) {
        Donor temp = donorRepository.save(donor);
        String temp_oid = organizationService.findAllByFirstName(temp.getOname());
        String temp_bid = bloodService.findAllByFirstName(temp.getBname());

        StockAsPerOrganization upData = stockAsPerOrganizationRepository.findByOidAndBid(temp_oid, temp_bid);
        upData.setQuantityAvl(upData.getQuantityAvl() + temp.getQuantityDonate());
        stockAsPerOrganizationRepository.save(upData);
        return temp;
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
