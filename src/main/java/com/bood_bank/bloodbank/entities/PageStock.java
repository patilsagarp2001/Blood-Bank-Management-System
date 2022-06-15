package com.bood_bank.bloodbank.entities;

import org.springframework.data.annotation.Id;

public class PageStock {
    @Id
    private String bloodGrp;
    private String change;
    private int units;

    public PageStock(String bloodGrp, String change, int units) {
        this.bloodGrp = bloodGrp;
        this.change = change;
        this.units = units;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

}
