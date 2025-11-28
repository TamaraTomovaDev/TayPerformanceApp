package org.garageApp.controller;

import lombok.RequiredArgsConstructor;
import org.garageApp.model.Appointment;
import org.garageApp.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> getByDate(@PathVariable String date) {
        LocalDate parsed = LocalDate.parse(date);
        return ResponseEntity.ok(service.getByDate(parsed));
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        Appointment created = service.create(appointment);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(
            @PathVariable Long id,
            @RequestBody Appointment appointment
    ) {
        Appointment updated = service.update(id, appointment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
