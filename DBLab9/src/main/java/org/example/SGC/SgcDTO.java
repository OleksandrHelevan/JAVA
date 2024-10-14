package org.example.SGC;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SgcDTO {
    private String id;
    private double avScore;
    private String honor;

    @Override
    public String toString() {
            return "SGC: " + avScore + " " + honor;
    }
}
