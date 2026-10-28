package com.sellerflow.sales.application.port.out;

import com.sellerflow.sales.domain.Sale;

public interface SaleRepositoryPort {

    Sale save (Sale sale);
}
