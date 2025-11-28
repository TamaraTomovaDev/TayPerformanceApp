package org.garageApp.controller;

import org.garageApp.model.SmsRequest;
import org.garageApp.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
        return ResponseEntity.ok("SMS verzonden naar " + smsRequest.getPhoneNumber());
    }
}
