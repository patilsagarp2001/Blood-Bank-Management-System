package com.bood_bank.bloodbank.entities;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bloodgroups")
public class BloodGroup {

    @Id
    private String _id;
    @NotBlank(message = "Blood Group Name not be blank in bloodgroups")
    private String bname;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

}
