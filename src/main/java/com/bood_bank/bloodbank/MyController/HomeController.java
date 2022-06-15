package com.bood_bank.bloodbank.MyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bood_bank.bloodbank.entities.BloodGroup;
import com.bood_bank.bloodbank.entities.Donor;
import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.service.BloodService;
import com.bood_bank.bloodbank.service.DonorService;
import com.bood_bank.bloodbank.service.OrganizationService;
import com.bood_bank.bloodbank.service.StockAsPerOrganizationService;

@RestController
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
    public ResponseEntity<?> addOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(organizationService.addOrganization(organization));
    }

    @GetMapping("/organization")
    public ResponseEntity<?> getOrganization() {
        return ResponseEntity.ok(organizationService.getOrganization());
    }

    @GetMapping("/organization/{organizationName}")
    public ResponseEntity<?> getDonorByName(@PathVariable String organizationName) {
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

    @PostMapping("/Donor")
    public ResponseEntity<?> addDonor(@RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.addToDonor(donor));
    }

    @GetMapping("/Donor")
    public ResponseEntity<?> getDonor() {
        return ResponseEntity.ok(donorService.getDonor());
    }

    @GetMapping("/stockAsPerOrganization/{organizationName}")
    public ResponseEntity<?> getStockAsPerOrganization(@PathVariable String organizationName) {
        return ResponseEntity.ok(stockAsPerOrganizationService.findAllByOname(organizationName));
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
