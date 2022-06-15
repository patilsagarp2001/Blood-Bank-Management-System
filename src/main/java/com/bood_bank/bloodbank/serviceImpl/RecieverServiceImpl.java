package com.bood_bank.bloodbank.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.Organization;
import com.bood_bank.bloodbank.entities.Reciever;
import com.bood_bank.bloodbank.entities.StockAsPerOrganization;
import com.bood_bank.bloodbank.exception.OrganizationCollectionException;
import com.bood_bank.bloodbank.exception.RecieverCollectionException;
import com.bood_bank.bloodbank.repo.BloodRepository;
import com.bood_bank.bloodbank.repo.OrganizationRepository;
import com.bood_bank.bloodbank.repo.RecieverRepository;
import com.bood_bank.bloodbank.repo.StockAsPerOrganizationRepository;
import com.bood_bank.bloodbank.service.RecieverService;

@Component
public class RecieverServiceImpl implements RecieverService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private RecieverRepository recieverRepository;
    @Autowired
    private BloodRepository bloodRepository;
    @Autowired
    private StockAsPerOrganizationRepository stockAsPerOrganizationRepository;

    @Override
    public Object addToReciever(Reciever reciever)
            throws ConstraintViolationException, OrganizationCollectionException {
        // TODO Auto-generated method stub
        Organization o = this.organizationRepository.findByOname(reciever.getOname());

        if (o == null) {
            throw new OrganizationCollectionException(
                    OrganizationCollectionException.NotFoundException(reciever.getOname()));
            // return null;
        } else {
            Reciever temp = recieverRepository.save(reciever);
            return temp;
        }
    }

    @Override
    public Page<List<Reciever>> getRecieverInPage(int pageNo, int pageSize, String sortBy, String status) {
        // TODO Auto-generated method stub
        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<List<Reciever>> listOfReciever = recieverRepository.findAllByStatus(status, page);
        // Page<Reciever> recieverPage = recieverRepository.findAll(page);
        // Page<List<Reciever>> recieverPageList = listOfReciever;
        return listOfReciever;
    }

    @Override
    public void deleteById(String recieverId) throws ConstraintViolationException, RecieverCollectionException {
        // TODO Auto-generated method stub
        Optional<?> updateData = recieverRepository.findBy_id(recieverId);
        if (updateData.isPresent()) {
            recieverRepository.deleteBy_id(recieverId);
        } else {
            throw new RecieverCollectionException(
                    RecieverCollectionException.NotFoundException(recieverId));
        }

    }

    @Override
    public void compeletedReciever(@NotBlank String recieverId)
            throws RecieverCollectionException {
        // TODO Auto-generated method stub
        Optional<?> updateData = recieverRepository.findBy_id(recieverId);
        if (updateData.isPresent()) {
            Reciever r = (Reciever) updateData.get();
            String temp_oid = (organizationRepository.findByOname(r.getOname())).get_id();
            String temp_bid = (bloodRepository.findByBname(r.getBname())).get_id();

            StockAsPerOrganization st = stockAsPerOrganizationRepository.findByOidAndBid(temp_oid, temp_bid);

            if (st.getQuantityAvl() >= r.getQuantityReq()) {
                r.setStatus("Completed");
                st.setQuantityAvl(st.getQuantityAvl() - r.getQuantityReq());
                stockAsPerOrganizationRepository.save(st);
                recieverRepository.save(r);
            } else {
                throw new RecieverCollectionException(RecieverCollectionException.QuantityNotAvailable());
            }

        } else {
            throw new RecieverCollectionException(RecieverCollectionException.NotFoundException(recieverId));
        }

    }

}
