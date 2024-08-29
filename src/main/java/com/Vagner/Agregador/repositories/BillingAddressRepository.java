package com.Vagner.Agregador.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Vagner.Agregador.entity.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {

}
