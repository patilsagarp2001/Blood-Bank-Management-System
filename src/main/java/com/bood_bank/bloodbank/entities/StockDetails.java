package com.bood_bank.bloodbank.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bloodStocks")
public class StockDetails {

    @Transient
    public static final String SEQUENCE_NAME = "u_sequence";

    @Id
    public long stockId;
    private String group;
    private int quantityAvl;

    public StockDetails() {

    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public long getStockId() {
        return stockId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getQuantityAvl() {
        return quantityAvl;
    }

    public void setQuantityAvl(int quantityAvl) {
        this.quantityAvl = quantityAvl;
    }

}
