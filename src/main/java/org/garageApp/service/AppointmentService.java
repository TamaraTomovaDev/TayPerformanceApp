package org.garageApp.service;

import org.garageApp.exception.ResourceNotFoundException;
import org.garageApp.model.Appointment;
import org.garageApp.model.SmsRequest;
import org.garageApp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final SmsService smsService;

    public AppointmentService(AppointmentRepository repository, SmsService smsService) {
        this.repository = repository;
        this.smsService = smsService;
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public List<Appointment> getByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public Appointment create(Appointment appointment) {

        Appointment saved = repository.save(appointment);

        // Bericht dat naar klant gaat
        String sms = "Beste klant, uw afspraak is bevestigd op "
                + saved.getDate() + " om " + saved.getTime()
                + ". Adres: Tay Performance Garage. Tot dan!";

        // ✔️ SmsRequest object maken
        SmsRequest smsRequest = new SmsRequest(
                saved.getPhone(),
                sms
        );

        // ✔️ Correcte aanroep
        smsService.sendSms(smsRequest);

        return saved;
    }

    public Appointment update(Long id, Appointment appointmentDetails) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afspraak met ID " + id + " niet gevonden"));
        appointment.setDate(appointmentDetails.getDate());
        appointment.setTime(appointmentDetails.getTime());
        appointment.setDescription(appointmentDetails.getDescription());
        appointment.setPrice(appointmentDetails.getPrice());
        appointment.setPhone(appointmentDetails.getPhone());
        appointment.setPickupTime(appointmentDetails.getPickupTime());
        return repository.save(appointment);
    }

    public void delete(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afspraak met ID " + id + " niet gevonden"));
        repository.delete(appointment);
    }
}
