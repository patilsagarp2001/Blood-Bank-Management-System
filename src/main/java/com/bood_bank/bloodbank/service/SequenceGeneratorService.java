package com.bood_bank.bloodbank.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.bood_bank.bloodbank.entities.DbSequence2;
import com.bood_bank.bloodbank.entities.DbSequence3;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequenceForReciever(String seqName) {
        DbSequence2 counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DbSequence2.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long generateSequenceForStock(String seqName) {
        DbSequence3 counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DbSequence3.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
