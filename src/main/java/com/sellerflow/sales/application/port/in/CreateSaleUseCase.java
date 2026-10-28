package com.sellerflow.sales.application.port.in;

import com.sellerflow.sales.domain.Sale;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreateSaleUseCase {

    Sale create(UUID sellerId, BigDecimal amount);
}
