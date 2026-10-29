package com.sellerflow.sales.application.facade;

import com.sellerflow.sales.application.service.CreateSaleService;
import com.sellerflow.sales.domain.Sale;

import java.math.BigDecimal;
import java.util.UUID;

public class SaleFacade {

    private final CreateSaleService service;

    public SaleFacade(CreateSaleService service){
        this.service = service;
    }

    public Sale create (UUID sellerId, BigDecimal amount){
        return service.create(sellerId, amount);
    }
}
