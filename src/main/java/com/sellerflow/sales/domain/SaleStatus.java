package com.sellerflow.sales.domain;

public enum SaleStatus {

    PENDING("PENDING"),
    CONFIRMED("CONFIRMED");

    private final String standardText;

    SaleStatus(String standardText){
        this.standardText = standardText;
    }

    public String getStandardText() {
        return standardText;
    }
}
