package org.example.springjdbcdemo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@Scope("prototype")
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
}
