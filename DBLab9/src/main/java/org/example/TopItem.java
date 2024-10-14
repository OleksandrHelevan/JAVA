package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@
AllArgsConstructor
public class TopItem {
    private String id;
    private String applicantName;
    private String applicantSurname;
    double avScore;

    @Override
    public String toString() {
        return applicantName + " " + applicantSurname + " " + avScore;
    }

}
