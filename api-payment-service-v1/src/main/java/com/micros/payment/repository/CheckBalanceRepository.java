package com.micros.payment.repository;

import org.springframework.stereotype.Repository;

import com.micros.payment.domain.CheckBalance;
import com.micros.payment.infraestructure.entity.CheckBalanceEntity;
import com.micros.payment.infraestructure.repository.CheckBalanceRepositoryJPA;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CheckBalanceRepository {

    private final CheckBalanceRepositoryJPA repository;

    public boolean saveCheckResult(CheckBalance checkBalance, boolean hasFunds) {

        CheckBalanceEntity entity = new CheckBalanceEntity(

                checkBalance.getId(),
                checkBalance.getCustomerId(),
                checkBalance.getAccountId(),
                checkBalance.getRequiredAmount(),
                hasFunds);

        repository.save(entity);

        return hasFunds;
    }

}
