package com.thamserios.notificationsserver.model;

import lombok.Data;

import java.util.Date;

@Data
public class Payment implements Comparable<Payment> {
    private String id;
    private String sender;
    private String receiver;
    private String reference;
    private double amount;
    private Bank bank;
    private String paymentType;
    private int transactionPeriod;
    private int transactionType;
    private Date transactionDate;
    private String paymentConfirmation;
    private String emailAddress;
    private String countryCode;

    @Override
    public int compareTo(final Payment o) {
        if (getTransactionDate() == null || o.getTransactionDate() == null) {
            return 0;
        }
        return getTransactionDate().compareTo(o.getTransactionDate());
    }
}