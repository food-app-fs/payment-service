package com.example.user_service.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CheckoutForm {

    @NotNull
    @Size(min=4)
    private Integer amount;

    @NotNull
    private String email;
}
