package com.sellerflow.sales.domain;

public enum DomainError {
    INVALID_VALUE(1001, "Sale amount must be greater than zero"),
    INVALID_ID(1002, "Seller id is null"),
    INVALID_STATUS(1003, "Only pending sales can be confirmed");

    private final int code;
    private final String description;

    DomainError(int code, String description){
        this.code = code;
        this.description = description;
    }
    public int getCode(){ return code; }
    public String getDescription(){return description; }
}
