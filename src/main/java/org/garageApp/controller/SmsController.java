package org.garageApp.controller;

import lombok.RequiredArgsConstructor;
import org.garageApp.model.SmsRequest;
import org.garageApp.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
        return ResponseEntity.ok("SMS succesvol verzonden!");
    }
}