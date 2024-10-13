package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassportDTO {
    private String id;
    private Date dateOfBirth;
    private String placeOfBirthday;
    private String sex;

    @Override
    public String toString() {
        return "Passport: " + " " + dateOfBirth + " " + placeOfBirthday + " " + sex;
    }
}
