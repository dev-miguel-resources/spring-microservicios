package com.micros.payment.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micros.payment.infraestructure.entity.CheckBalanceEntity;

public interface CheckBalanceRepositoryJPA extends JpaRepository<CheckBalanceEntity, String> {

}
