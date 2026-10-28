package com.sellerflow.sales.application.service;

import com.sellerflow.sales.application.port.out.SaleRepositoryPort;
import com.sellerflow.sales.domain.DomainError;
import com.sellerflow.sales.domain.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CreateSaleServiceTest {

    @Mock
    private SaleRepositoryPort saleRepositoryPort;

    private CreateSaleService service;

    @BeforeEach
    void setUp(){
        service = new CreateSaleService(saleRepositoryPort);
    }

    private final String fictionalUuidStr = "123e4567-e89b-12d3-a456-426614174000";
    private final UUID fictionalSellerId = UUID.fromString(fictionalUuidStr);
    private final BigDecimal fictionalAmount = new BigDecimal("10.000");
    private final BigDecimal fictionalZeroAmount = new BigDecimal("0");
    private final String fictionalInvalidValue = DomainError.INVALID_VALUE.getDescription();

    @Test
    void shouldSaveAndReturnSale(){
        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        when(saleRepositoryPort.save(any(Sale.class))).thenReturn(fictionalSale);

        Sale savedFictionalSale = service.create(fictionalSellerId, fictionalAmount);

        Assertions.assertSame(fictionalSale, savedFictionalSale);
    }

    @Test
    void shouldSaveSaleWithProvidedSellerIdAndAmount(){
        ArgumentCaptor<Sale> saleCaptor = ArgumentCaptor.forClass(Sale.class);

        service.create(fictionalSellerId, fictionalAmount);

        verify(saleRepositoryPort).save(saleCaptor.capture());

        Sale capturedSale = saleCaptor.getValue();

        Assertions.assertEquals(fictionalSellerId, capturedSale.getSellerId());
        Assertions.assertEquals(fictionalAmount, capturedSale.getAmount());
    }

    @Test
    void shouldSaleRepositoryPOrtIsNotCall(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()-> service.create(fictionalSellerId, fictionalZeroAmount));

        Assertions.assertEquals(fictionalInvalidValue, exception.getMessage());
        verifyNoInteractions(saleRepositoryPort);
    }
}
