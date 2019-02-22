package com.fiaschetti.bankdemo.dto;

import java.util.Date;

public class OperationDTO {

    private Date date;
    private String description;
    private double amount;

    public OperationDTO() {

    }

    public OperationDTO(Date date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
