package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String apellidoPaterno;
    @Getter
    @Setter
    private String apellidoMaterno;
    @Getter
    @Setter
    private String idRole;


}