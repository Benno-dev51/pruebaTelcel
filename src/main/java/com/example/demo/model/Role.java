package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
@AllArgsConstructor
public class Role {
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String tipoDeRole;


    // Constructor, getters y setters
}
