package com.example.shop.settlement.infrastructure;

import com.example.shop.settlement.domain.SellerSettlement;
import com.example.shop.settlement.domain.SellerSettlementRepository;
import com.example.shop.settlement.domain.SettlementStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SellerSettlementRepositoryAdapter implements SellerSettlementRepository {

    private final SellerSettlementJpaRepository sellerSettlementJpaRepository;

    @Override
    public List<SellerSettlement> findByStatusAndSellerId(SettlementStatus settlementStatus, UUID sellerId) {
        return sellerSettlementJpaRepository.findByStatusAndSellerId(settlementStatus, sellerId);
    }

    @Override
    public List<SellerSettlement> findByStatus(SettlementStatus settlementStatus) {
        return sellerSettlementJpaRepository.findByStatus(settlementStatus);
    }

    @Override
    public void saveAll(List<SellerSettlement> pending) {
        sellerSettlementJpaRepository.saveAll(pending);
    }
}
