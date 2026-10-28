package com.sellerflow.sales.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Sale {

    private final UUID id;

    private final UUID sellerId;

    private final BigDecimal amount;

    private SaleStatus status;

    private final Instant createdAt;

    public UUID getId() {
        return id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Sale(UUID sellerId, BigDecimal amount){

        if (sellerId == null) throw new IllegalArgumentException(DomainError.INVALID_ID.getDescription());
        if (amount == null || amount.compareTo(BigDecimal.ZERO)<=0) throw new IllegalArgumentException(DomainError.INVALID_VALUE.getDescription());
        this.id = UUID.randomUUID();
        this.sellerId = sellerId;
        this.amount = amount;
        this.status = SaleStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public void confirm(){

        if (status!=SaleStatus.PENDING){
            throw new IllegalStateException(DomainError.INVALID_STATUS.getDescription());
        }
        status = SaleStatus.CONFIRMED;
    }
}
