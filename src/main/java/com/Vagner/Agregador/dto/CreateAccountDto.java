package com.Vagner.Agregador.dto;

import com.Vagner.Agregador.entity.BillingAddress;
import com.Vagner.Agregador.entity.User;

public record CreateAccountDto(String description, User user, BillingAddress billingAddress ) {

}
