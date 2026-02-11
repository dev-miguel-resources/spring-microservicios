package com.micros.payment.repository;

import org.springframework.stereotype.Repository;

import com.micros.payment.domain.Charge;
import com.micros.payment.infraestructure.entity.ChargeEntity;
import com.micros.payment.infraestructure.repository.ChargeRepositoryJPA;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ChargeRepository {

    private final ChargeRepositoryJPA repository;

    public void save(Charge charge) {

        ChargeEntity entity = new ChargeEntity();

        entity.setCustomerId(charge.getCustomerId());
        entity.setAccountId(charge.getAccountId());
        entity.setAmount(charge.getAmount());

        repository.save(entity);

    }

}
