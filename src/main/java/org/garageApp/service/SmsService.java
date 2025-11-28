package org.garageApp.service;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.garageApp.model.SmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {
    public void sendSms(SmsRequest request) {
        // Voor nu tijdelijk een mock/console output
        System.out.println("SMS naar " + request.getPhoneNumber() + ": " + request.getMessage());

        // Later kan hier Twilio code of andere SMS API komen
    }

//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.authToken}")
//    private String authToken;
//
//    @Value("${twilio.phoneNumber}")
//    private String fromPhoneNumber;
//
//    public void sendSms(SmsRequest smsRequest) {
//
//        validatePhoneNumber(smsRequest.getPhoneNumber());
//
//        Twilio.init(accountSid, authToken);
//
//        try {
//            Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber(smsRequest.getPhoneNumber()),
//                    new com.twilio.type.PhoneNumber(fromPhoneNumber),
//                    smsRequest.getMessage()
//            ).create();
//
//            log.info("SMS verzonden naar {}: SID={}",
//                    smsRequest.getPhoneNumber(), message.getSid());
//
//        } catch (ApiException e) {
//            log.error("Twilio fout: {}", e.getMessage());
//            throw new RuntimeException("SMS verzenden mislukt: " + e.getMessage());
//        }
//    }
//
//    private void validatePhoneNumber(String phone) {
//        if (phone == null || phone.isEmpty()) {
//            throw new IllegalArgumentException("Telefoonnummer is leeg");
//        }
//
//        if (!phone.startsWith("+")) {
//            throw new IllegalArgumentException("Telefoonnummer moet beginnen met + (bijv. +32...)");
//        }
//    }
}
