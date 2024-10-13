package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EcDTO {
    private String id;
    private double avScore;

    @Override
    public String toString() {
        return "EC: " + avScore;
    }
}
