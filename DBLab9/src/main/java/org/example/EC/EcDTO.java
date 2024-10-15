package org.example.EC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
