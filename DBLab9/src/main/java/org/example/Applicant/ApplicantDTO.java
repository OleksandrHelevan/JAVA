package org.example.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.EC.EcDTO;
import org.example.Passport.PassportDTO;
import org.example.SGC.SgcDTO;
import org.example.Specialty.SpecialtyDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
    private String id;
    private String name;
    private String surname;
    private EcDTO ec;
    private SgcDTO sgc;
    private PassportDTO passport;
    private SpecialtyDTO specialty;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
