package org.garageApp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequest {
    @NotBlank(message = "Telefoonnummer is verplicht")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Ongeldig telefoonnummer")
    private String phoneNumber;

    @NotBlank(message = "Bericht mag niet leeg zijn")
    private String message;
}
