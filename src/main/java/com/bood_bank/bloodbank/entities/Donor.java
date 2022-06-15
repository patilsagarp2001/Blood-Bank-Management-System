package com.bood_bank.bloodbank.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donor")
public class Donor {

    @Id
    public String _id;
    private String dname;
    private long dmobile;
    private String dgender;
    private String demail;
    private String bname;
    private String oname;
    private int quantityDonate;

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public long getDmobile() {
        return dmobile;
    }

    public void setDmobile(long dmobile) {
        this.dmobile = dmobile;
    }

    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public int getQuantityDonate() {
        return quantityDonate;
    }

    public void setQuantityDonate(int quantityDonate) {
        this.quantityDonate = quantityDonate;
    }

}
