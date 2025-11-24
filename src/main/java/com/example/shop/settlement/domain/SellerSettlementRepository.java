package com.example.shop.settlement.domain;

import java.util.List;
import java.util.UUID;

public interface SellerSettlementRepository {
    List<SellerSettlement> findByStatusAndSellerId(SettlementStatus settlementStatus, UUID sellerId);

    List<SellerSettlement> findByStatus(SettlementStatus settlementStatus);

    void saveAll(List<SellerSettlement> pending);
}
