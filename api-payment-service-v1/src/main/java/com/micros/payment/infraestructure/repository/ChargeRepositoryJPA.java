package com.micros.payment.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micros.payment.infraestructure.entity.ChargeEntity;

public interface ChargeRepositoryJPA extends JpaRepository<ChargeEntity, Long> {

}