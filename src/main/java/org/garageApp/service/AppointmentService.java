package org.garageApp.service;

import org.garageApp.exception.ResourceNotFoundException;
import org.garageApp.model.Appointment;
import org.garageApp.model.Garage;
import org.garageApp.model.SmsRequest;
import org.garageApp.repository.AppointmentRepository;
import org.garageApp.repository.GarageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final GarageRepository garageRepository;
    private final SmsService smsService;

    public AppointmentService(AppointmentRepository repository,
                              GarageRepository garageRepository,
                              SmsService smsService) {
        this.repository = repository;
        this.garageRepository = garageRepository;
        this.smsService = smsService;
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public List<Appointment> getByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public Appointment create(Appointment appointment) {

        // Garage ophalen uit database
        Garage existingGarage = garageRepository.findById(appointment.getGarage().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Garage met ID " + appointment.getGarage().getId() + " bestaat niet"));

        appointment.setGarage(existingGarage);

        // Appointment opslaan
        Appointment saved = repository.save(appointment);

        // SMS aanmaken
        String sms = "Beste klant, uw afspraak is bevestigd op "
                + saved.getDate() + " om " + saved.getTime()
                + ". Adres: " + saved.getGarage().getAddress() + ". Tot dan!";

        SmsRequest smsRequest = new SmsRequest(saved.getPhone(), sms);

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
