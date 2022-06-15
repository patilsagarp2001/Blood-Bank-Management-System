package com.bood_bank.bloodbank.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bood_bank.bloodbank.entities.Reciever;

@Repository
public interface RecieverRepository extends MongoRepository<Reciever, String> {

    Optional<?> findBy_id(String recieverId);

    void deleteBy_id(String recieverId);

    Page<List<Reciever>> findAllByStatus(String string, Pageable page);

}
