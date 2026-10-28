package com.sellerflow.sales.application.service;

import com.sellerflow.sales.application.port.in.CreateSaleUseCase;
import com.sellerflow.sales.application.port.out.SaleRepositoryPort;
import com.sellerflow.sales.domain.Sale;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateSaleService implements CreateSaleUseCase{

    private final SaleRepositoryPort saleRepositoryPort;

    public CreateSaleService(SaleRepositoryPort saleRepositoryPort) {
        this.saleRepositoryPort = saleRepositoryPort;
    }

    @Override
    public Sale create(UUID sellerId, BigDecimal amount) {

        Sale sale = new Sale (sellerId, amount);
        return saleRepositoryPort.save(sale);
    }
}
