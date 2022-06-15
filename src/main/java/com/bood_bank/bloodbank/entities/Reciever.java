package com.bood_bank.bloodbank.entities;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reciever")
public class Reciever {
    @Id
    public String _id;
    @NotBlank(message = "Name Should not be blank!")
    @Size(min = 1, max = 64, message = "Size should be min 1")
    private String rname;
    @NotNull(message = "Mobile Number not be Null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number length should be 10")
    private String rmobile;
    @NotBlank(message = "Gender not be Blank!!!")
    private String rgender;
    @NotNull(message = "Age not be Null")
    @Min(18)
    private int rage;
    @NotNull(message = "Please input your email.")
    @Email(message = "Email format is wrong.")
    private String remail;
    @NotBlank(message = "Blood Group not be blank!!!")
    @Pattern(regexp = "(A|B|AB|O)[+-]")
    private String bname;
    @NotBlank(message = "Organization Name not be blank!!!")
    private String oname;
    @NotNull(message = "Quantity Donated not be Null")
    @Min(value = 1, message = "must be greater than 0")
    private int quantityReq;
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // private Date date = new Date();
    private LocalDate date = LocalDate.now();

    // private LocalDate date = LocalDate.parse("2021-12-01");
    private String status = "Pending";

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRmobile() {
        return rmobile;
    }

    public void setRmobile(String rmobile) {
        this.rmobile = rmobile;
    }

    public String getRgender() {
        return rgender;
    }

    public void setRgender(String rgender) {
        this.rgender = rgender;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
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

    public int getQuantityReq() {
        return quantityReq;
    }

    public void setQuantityReq(int quantityReq) {
        this.quantityReq = quantityReq;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
