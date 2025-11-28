package org.garageApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Datum is verplicht")
    private LocalDate date;

    @NotNull(message = "Tijd is verplicht")
    private LocalTime time;

    @NotBlank(message = "Omschrijving is verplicht")
    private String description;

    @PositiveOrZero(message = "Prijs moet positief zijn of 0")
    private double price;

    @NotBlank(message = "Telefoonnummer is verplicht")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Ongeldig telefoonnummer")
    private String phone;

    private LocalTime pickupTime;

    @NotNull(message = "Garage is verplicht")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id")
    private Garage garage;

    public Appointment() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(LocalTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
