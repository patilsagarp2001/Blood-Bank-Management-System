package com.bood_bank.bloodbank.MyController;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.entities.Donor;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.exception.DonorCollectionException;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.service.BloodService;
import com.bood_bank.bloodbank.service.DonorService;
import com.bood_bank.bloodbank.service.OrganizationService;
import com.bood_bank.bloodbank.service.StockAsPerOrganizationService;
import com.mongodb.lang.NonNull;

@RestController
@Validated
public class HomeController {

    @Autowired
    private DonorService donorService;
    @Autowired
    private BloodService bloodService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private StockAsPerOrganizationService stockAsPerOrganizationService;

    @PostMapping("/organization")
    public ResponseEntity<?> addOrganization(@RequestBody Organization organization)
            throws ConstraintViolationException, OrganizationCollectionException {
        try {
            organizationService.addOrganization(organization);
            return new ResponseEntity<Organization>(organization, HttpStatus.OK);
            // return ResponseEntity.ok(o);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (OrganizationCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/organization")
    public ResponseEntity<?> deleteOrganization(
            @RequestParam(name = "organizationName") String organizationName) {
        try {
            organizationService.deleteOrganization(organizationName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (OrganizationCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        // // String s = organizationService.deleteOrganization(organizationName);
        // if (s == null) {
        // return ResponseEntity.ok("This Organization is not Available");
        // }
        // return ResponseEntity.ok(s);
    }

    @GetMapping("/organization")
    public ResponseEntity<?> getOrganization() {
        return ResponseEntity.ok(organizationService.getOrganization());
    }

    @GetMapping("/organization/{organizationName}")
    public ResponseEntity<?> getByOrganizationName(@PathVariable String organizationName) {
        return ResponseEntity.ok(this.organizationService.findAllByFirstName(organizationName));
    }

    @PostMapping("/bloodgroup")
    public ResponseEntity<?> addBloodGroup(@RequestBody BloodGroup bloodGroup) {
        return ResponseEntity.ok(bloodService.addBloodGroup(bloodGroup));
    }

    @GetMapping("/bloodgroup")
    public ResponseEntity<?> getBloodGroup() {
        return ResponseEntity.ok(bloodService.getBloodGroup());
    }

    @GetMapping("/bloodgroup/{bloodId}")
    public ResponseEntity<?> getBloodGroupById(@PathVariable String bloodId) {
        return ResponseEntity.ok(bloodService.getBloodGroupById(bloodId));
    }

    @PostMapping("/Donor")
    public ResponseEntity<?> addDonor(@RequestBody Donor donor)
            throws ConstraintViolationException, OrganizationCollectionException {

        try {
            return new ResponseEntity<Object>(donorService.addToDonor(donor), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (OrganizationCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/Donor/{donorId}")
    public ResponseEntity<?> updateDonor(@PathVariable @NotBlank String donorId, @RequestBody Donor donor)
            throws ConstraintViolationException, OrganizationCollectionException, DonorCollectionException {
        try {
            return new ResponseEntity<>(donorService.updateDonor(donorId, donor), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (OrganizationCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (DonorCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/Donor/{donorId}")
    public ResponseEntity<?> deleteDonor(@PathVariable String donorId)
            throws ConstraintViolationException, DonorCollectionException {
        try {
            donorService.deleteById(donorId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (DonorCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // @GetMapping("/Donor")
    // public ResponseEntity<?> getDonor() {
    // return ResponseEntity.ok(donorService.getDonor());
    // }

    @GetMapping("/stockAsPerOrganization")
    public ResponseEntity<?> getStockAsPerOrganization(
            @RequestParam(name = "organizationName") @NotNull String organizationName) {
        try {
            return new ResponseEntity<>(stockAsPerOrganizationService.findAllByOname(organizationName), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        // if (organizationName.isEmpty()) {
        // return ResponseEntity.ok("Please Enter Organization Name!");
        // }
        // List<?> l = (List<?>)
        // stockAsPerOrganizationService.findAllByOname(organizationName);
        // if (l == null) {
        // return ResponseEntity.ok("No such Organization is Registered");
        // }
        // return ResponseEntity.ok(l);
        // return
        // ResponseEntity.ok(stockAsPerOrganizationService.findAllByOname(organizationName));
    }

    @GetMapping("/Donor")
    public ResponseEntity<?> getDonorInPage(@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "_id") String sortBy) {
        return ResponseEntity.ok(donorService.getAllDonorInPage(pageNo, pageSize, sortBy));

        // return "Page No:-" + pageNo + " , Page Size:-" + pageSize + " and Sort By:- "
        // + sortBy;
    }

    // // Add New Donor Here
    // @PostMapping("/home/Donor")
    // public ResponseEntity<?> addDonor(@RequestBody Donor donor) {
    // return ResponseEntity.ok(donorService.addToDonor(donor));
    // // donor.setId(service.generateSequence(Donor.SEQUENCE_NAME));
    // // return ResponseEntity.ok(this.donorRepository.save(donor));
    // }

    /*
     * 
     * // Reterive all data about all Donors.
     * 
     * @CrossOrigin(origins = "http://localhost:4200")
     * 
     * @GetMapping("/home/Donor")
     * public ResponseEntity<?> getDonors() {
     * return ResponseEntity.ok(donorService.getAllDonors());
     * // return ResponseEntity.ok(this.donorRepository.findAll());
     * }
     * 
     * @GetMapping("/home/Donor/{donorId}")
     * public ResponseEntity<?> getDonor(@PathVariable String donorId) {
     * return
     * ResponseEntity.ok(this.donorRepository.findById(Long.parseLong(donorId)));
     * }
     */

    /*
     * @PutMapping("/home/Donor/{donorId}")
     * public ResponseEntity<?> getDonor(@PathVariable String donorId, @RequestBody
     * Donor donor) {
     * Optional<?> updateData = donorRepository.findById(Long.parseLong(donorId));
     * System.out.println(updateData);
     * if (updateData.isPresent()) {
     * Donor _donor = (Donor) updateData.get();
     * _donor.setName(donor.getName());
     * _donor.setMobile(donor.getMobile());
     * _donor.setGender(donor.getGender());
     * _donor.setBloodgrp(donor.getBloodgrp());
     * return new ResponseEntity<>(donorRepository.save(_donor), HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    /*
     * @GetMapping("/home/Donor/{donorName}")
     * public ResponseEntity<?> getDonorByName(@RequestParam(name = "donorName")
     * String donorName) {
     * return ResponseEntity.ok(this.donorService.findAllByFirstName(donorName));
     * }
     */
    // @GetMapping("/home/Donor/{donorName}")
    // public ResponseEntity<?> getDonorByName(@PathVariable String donorName) {
    // return ResponseEntity.ok(this.donorService.findAllByFirstName(donorName));
    // }

    // @DeleteMapping("/home/Donor/{donorId}")
    // public ResponseEntity<?> deleteDonor(@PathVariable String donorId) {
    // try {
    // donorRepository.deleteById(Long.parseLong(donorId));
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    /*
     * @PostMapping("/home/Reciever")
     * public ResponseEntity<?> addReciever(@RequestBody RequestForBlood
     * requestForBlood) {
     * requestForBlood.setRid(service.generateSequenceForReciever(RequestForBlood.
     * SEQUENCE_NAME));
     * RequestForBlood save = this.requestForBloodRepository.save(requestForBlood);
     * return ResponseEntity.ok(save);
     * }
     * 
     * @GetMapping("/home/Reciever")
     * public ResponseEntity<?> getRecievers() {
     * return ResponseEntity.ok(this.requestForBloodRepository.findAll());
     * }
     * 
     * @PutMapping("/home/Reciever/{recieverId}")
     * public ResponseEntity<?> getReciever(@PathVariable String recieverId,
     * 
     * @RequestBody RequestForBlood requestForBlood) {
     * Optional<?> updateData =
     * requestRepository.findById(Long.parseLong(recieverId));
     * System.out.println(updateData);
     * 
     * if (updateData.isPresent()) {
     * RequestForBlood _reciever = (RequestForBlood) updateData.get();
     * _reciever.setPname(requestForBlood.getPname());
     * _reciever.setMobile(requestForBlood.getMobile());
     * _reciever.setGender(requestForBlood.getGender());
     * _reciever.setBloodgrp(requestForBlood.getBloodgrp());
     * _reciever.setQuantityReq(requestForBlood.getQuantityReq());
     * return new ResponseEntity<>(requestRepository.save(_reciever),
     * HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */

    /*
     * @GetMapping("/home/getStock")
     * public ResponseEntity<?> getStock() {
     * return ResponseEntity.ok(this.stockRepository.findAll());
     * }
     * 
     * @PutMapping("/home/updateStock/{bloodId}")
     * public ResponseEntity<?> updateStock(@PathVariable String
     * bloodId, @RequestBody PageStock pageStock) {
     * Optional<?> upData = stockRepository.findById(Long.parseLong(bloodId));
     * Optional<StockDetails> temp =
     * stockRepository.findById(Long.parseLong(bloodId));
     * // System.out.println(stockRepository.findById(Long.parseLong(bloodId)));
     * // System.out.println(temp.get().getQuantityAvl());
     * if (upData.isPresent()) {
     * // System.out.println(upData);
     * StockDetails _stock = (StockDetails) upData.get();
     * if (pageStock.getChange().equals("Increase")) {
     * // int temp = pageStock.getUnits()+upData;
     * _stock.setQuantityAvl(temp.get().getQuantityAvl() + pageStock.getUnits());
     * return new ResponseEntity<>(stockRepository.save(_stock), HttpStatus.OK);
     * // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * } else if (pageStock.getChange().equals("Decrease")) {
     * _stock.setQuantityAvl(temp.get().getQuantityAvl() - pageStock.getUnits());
     * return new ResponseEntity<>(stockRepository.save(_stock), HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * 
     * }
     */

    /*
     * @PostMapping("/home/addStock")
     * public ResponseEntity<?> addStock(@RequestBody StockDetails stockDetails) {
     * stockDetails.setStockId(service.generateSequenceForStock(StockDetails.
     * SEQUENCE_NAME));
     * StockDetails save = this.stockRepository.save(stockDetails);
     * return ResponseEntity.ok(save);
     * }
     */

}
