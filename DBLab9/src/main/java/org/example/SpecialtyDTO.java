package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecialtyDTO {
    private String id;
    private int code;
    private String nameOfSpecialty;
    private String nameOfCurriculum;

    @Override
    public String toString() {
        return "Specialty: " + code + " " + nameOfSpecialty + " " + nameOfCurriculum;
    }
}
