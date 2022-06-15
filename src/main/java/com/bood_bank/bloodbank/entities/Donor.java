package com.bood_bank.bloodbank.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donor")
public class Donor {

    @Id
    public String _id;
    @NotBlank(message = "Name Should not be blank!")
    @Size(min = 1, max = 64, message = "Size should be min 1")
    private String dname;
    @NotNull(message = "Mobile Number not be Null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number length should be 10")
    private String dmobile;
    @NotBlank(message = "Gender not be Blank!!!")
    private String dgender;
    @NotNull(message = "Age not be Null")
    @Min(18)
    private int dage;
    @NotNull(message = "Please input your email.")
    @Email(message = "Email format is wrong.")
    private String demail;
    @NotBlank(message = "Blood Group not be blank!!!")
    @Pattern(regexp = "(A|B|AB|O)[+-]")
    private String bname;
    @NotBlank(message = "Organization Name not be blank!!!")
    private String oname;
    @NotNull(message = "Quantity Donated not be Null")
    @Min(value = 1, message = "must be greater than 0")
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

    public String getDmobile() {
        return dmobile;
    }

    public void setDmobile(String dmobile) {
        this.dmobile = dmobile;
    }

    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    public int getDage() {
        return dage;
    }

    public void setDage(int dage) {
        this.dage = dage;
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
