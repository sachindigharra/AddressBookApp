package com.bridgelabz.addressBookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "authuser")

public class AuthUser {

    String name;
    String email;
    String phone;
    String hashPass;
    String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public AuthUser(String name, String email, String phone, String hashPass) {
        this.name=name;
        this.email = email;
        this.phone = phone;
        this.hashPass = hashPass;

        this.token="";
        this.id = null;
    }


}