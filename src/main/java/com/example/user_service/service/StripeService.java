package com.example.user_service.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    private final String secretKey;


    public StripeService(@Value("${stripe.apiKey}") String secretKey) {
        this.secretKey = secretKey;
        Stripe.apiKey = secretKey;
    }

    public Charge createCharge(int amount, String currency, String cardToken) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("source", cardToken);

        return Charge.create(params);
    }


}
