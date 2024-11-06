package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.PassportDTO;
import com.example.DBLab11.model.Passport;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.text.ParseException;
@Component
public class PassportMapper {

    private static final String DATE_FORMAT = "yyyy-MM-dd"; // ISO 8601 format
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public PassportDTO toDto(Passport passport) {
        PassportDTO passportDTO = new PassportDTO();
        passportDTO.setId(passport.getId());
        if (passport.getDateOfBirthday() != null) {
            passportDTO.setDateOfBirthday(dateFormat.format(passport.getDateOfBirthday()));
        }
        passportDTO.setPlaceOfBirthday(passport.getPlaceOfBirthday());
        passportDTO.setSex(passport.getSex());
        return passportDTO;
    }

    public Passport toEntity(PassportDTO passportDTO) {
        Passport passport = new Passport();
        passport.setId(passportDTO.getId() != null ? passportDTO.getId() : new ObjectId());
        if (passportDTO.getDateOfBirthday() != null) {
            try {
                passport.setDateOfBirthday(dateFormat.parse(passportDTO.getDateOfBirthday()));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        passport.setPlaceOfBirthday(passportDTO.getPlaceOfBirthday());
        passport.setSex(passportDTO.getSex());
        return passport;
    }
}
