package com.tecqasr.notes.app.tecqasrnotes.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String color;
    private Date date;

    @ManyToOne
    private User user;

}
