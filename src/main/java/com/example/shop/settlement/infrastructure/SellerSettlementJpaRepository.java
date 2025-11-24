package com.example.shop.settlement.infrastructure;

import com.example.shop.settlement.domain.SellerSettlement;
import com.example.shop.settlement.domain.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SellerSettlementJpaRepository extends JpaRepository<SellerSettlement,String> {
    List<SellerSettlement> findByStatusAndSellerId(SettlementStatus settlementStatus, UUID sellerId);

    List<SellerSettlement> findByStatus(SettlementStatus settlementStatus);
}
