package com.example.user_service.controller;

import com.example.user_service.model.Cpayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/stripe/")
public class Controller {

    private final String apiKey;

    public Controller(@Value("${stripe.apiKey}") String apiKey) {
        this.apiKey = apiKey;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> createIntent(@RequestBody Cpayment cpayment) {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount((long)(cpayment.getAmount() * 100))
                .build();

        try {
            PaymentIntent intent = PaymentIntent.create(createParams);
            String clientSecret = intent.getClientSecret();
            System.out.println(clientSecret);
            return ResponseEntity.ok(clientSecret);
        } catch (StripeException e) {
            // Handle any Stripe-specific exception or error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment intent");
        }
    }

}
