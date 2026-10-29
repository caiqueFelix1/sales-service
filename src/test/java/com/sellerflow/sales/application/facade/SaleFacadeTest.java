package com.sellerflow.sales.application.facade;

import com.sellerflow.sales.application.service.CreateSaleService;
import com.sellerflow.sales.domain.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleFacadeTest {

    @Mock
    private CreateSaleService service;

    private SaleFacade saleFacade;

    @BeforeEach
    void setUp() {
        saleFacade = new SaleFacade(service);
    }

    private final String fictionalUuidStr = "123e4567-e89b-12d3-a456-426614174000";
    private final UUID fictionalSellerId = UUID.fromString(fictionalUuidStr);
    private final BigDecimal fictionalAmount = new BigDecimal("10.000");

    @Test
    void shouldCallServiceAndReturnSale(){
        Sale fictionalSale = new Sale(fictionalSellerId, fictionalAmount);

        when(service.create(fictionalSellerId,fictionalAmount)).thenReturn(fictionalSale);

        Sale saleCreate = saleFacade.create(fictionalSellerId, fictionalAmount);

        verify(service).create(fictionalSellerId,fictionalAmount);

        Assertions.assertSame(fictionalSale, saleCreate);
    }
}
