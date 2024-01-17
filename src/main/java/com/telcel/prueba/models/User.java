package com.telcel.prueba.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id

    private String id;


    private String nombre;


    private String apellidoPaterno;


    private String apellidoMaterno;

    @DBRef(lazy = true)
    private List<Role> roles;
}
