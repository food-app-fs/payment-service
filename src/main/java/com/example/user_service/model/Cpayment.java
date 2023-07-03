package com.example.user_service.model;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class Cpayment {

    @NotNull
    @Min(4)
    private Double amount;


}
