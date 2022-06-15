package com.bood_bank.bloodbank.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class BloodNameQuant {
    @NotBlank(message = "Blood Group Name not be blank in BloodNameQuant")
    private String bname;
    @Min(0)
    private int quantityAvl;

    public BloodNameQuant(String bname, int quantityAvl) {
        this.bname = bname;
        this.quantityAvl = quantityAvl;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getQuantityAvl() {
        return quantityAvl;
    }

    public void setQuantityAvl(int quantityAvl) {
        this.quantityAvl = quantityAvl;
    }

}
