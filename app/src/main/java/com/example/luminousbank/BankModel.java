package com.example.luminousbank;

import java.io.Serializable;

public class BankModel implements Serializable {
    private String bankName;

    public BankModel(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}





