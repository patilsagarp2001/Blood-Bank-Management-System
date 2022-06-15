package com.bood_bank.bloodbank.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recievers")
public class Reciever {

    @Transient
    public static final String SEQUENCE_NAME = "us_sequence";

    @Id
    private long rid;
    private String rname;
    private long rmobile;
    private String rgender;
    private String remail;
    private int oid;
    private int bid;
    private int quantityReq;

    public Reciever(String rname, long rmobile, String rgender, String remail, int oid, int bid,
            int quantityReq) {
        this.rname = rname;
        this.rmobile = rmobile;
        this.rgender = rgender;
        this.remail = remail;
        this.oid = oid;
        this.bid = bid;
        this.quantityReq = quantityReq;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public long getRmobile() {
        return rmobile;
    }

    public void setRmobile(long rmobile) {
        this.rmobile = rmobile;
    }

    public String getRgender() {
        return rgender;
    }

    public void setRgender(String rgender) {
        this.rgender = rgender;
    }

    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getQuantityReq() {
        return quantityReq;
    }

    public void setQuantityReq(int quantityReq) {
        this.quantityReq = quantityReq;
    }

}
