package com.sellerflow.sales.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    private final BigDecimal fictionalAmount = new BigDecimal("10.000");
    private final BigDecimal fictionalZeroAmount = new BigDecimal("0");
    private final BigDecimal fictionalNegativeAmount = new BigDecimal("-1");
    private final String fictionalUuidStr = "123e4567-e89b-12d3-a456-426614174000";
    private final UUID fictionalSellerId = UUID.fromString(fictionalUuidStr);
    private final SaleStatus fictionalPendingStatus = SaleStatus.PENDING;
    private final SaleStatus fictionalConfirmedStatus = SaleStatus.CONFIRMED;

    @Test
    void shouldPreserveSellerId() {

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        UUID returnedId = fictionalSale.getSellerId();

        assertEquals(fictionalSellerId, returnedId);
    }

    @Test
    void shouldPreserveAmount() {

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        BigDecimal returnedAmount = fictionalSale.getAmount();

        assertEquals(fictionalAmount, returnedAmount);
    }

    @Test
    void shouldCreateSaleWithPendingStatus() {

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        SaleStatus returnedStatus = fictionalSale.getStatus();

        assertEquals(fictionalPendingStatus, returnedStatus);
    }

    @Test
    void shouldCreateSaleWithCorrectCreatedAt() {

        Instant timeBefore = Instant.now();
        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);
        Instant timeAfter = Instant.now();

        Instant returnedInstant = fictionalSale.getCreatedAt();

        assertTrue(!returnedInstant.isBefore(timeBefore) && !returnedInstant.isAfter(timeAfter));
    }

    @Test
    void shouldCreateSaleWithNonNullId() {

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        UUID fictionalId = fictionalSale.getId();

        assertNotNull(fictionalId);
    }

    @Test
    void shouldRejectNullSellerId() {

        assertThrows(IllegalArgumentException.class, ()-> new Sale(null, fictionalAmount));
    }

    @Test
    void shouldRejectNullAmount() {

        assertThrows(IllegalArgumentException.class, ()-> new Sale(fictionalSellerId, null));
    }

    @Test
    void shouldRejectZeroAmount(){

        assertThrows(IllegalArgumentException.class, ()-> new Sale(fictionalSellerId, fictionalZeroAmount));
    }

    @Test
    void shouldRejectNegativeAmount(){

        assertThrows(IllegalArgumentException.class, ()-> new Sale(fictionalSellerId, fictionalNegativeAmount));
    }

    @Test
    void shouldConfirmPendingSale(){

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);
        fictionalSale.confirm();
        SaleStatus newStatus = fictionalSale.getStatus();

        assertEquals(fictionalConfirmedStatus, newStatus);
    }

    @Test
    void shouldRejectConfirmationOfConfirmedSale(){

        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        fictionalSale.confirm();
        assertThrows(
                IllegalStateException.class,
                fictionalSale::confirm);

        assertEquals(SaleStatus.CONFIRMED, fictionalSale.getStatus());
    }
}
