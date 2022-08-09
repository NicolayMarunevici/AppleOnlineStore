package com.project.spring.new_main_project.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="contact_form")
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String email;
    private String message;



    public ContactForm() {
    }

    public ContactForm(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
