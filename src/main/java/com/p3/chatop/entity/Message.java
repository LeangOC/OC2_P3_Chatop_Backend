package com.p3.chatop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "MESSAGES")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private String message;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    // Pour ne pas avoir : "2026-05-04 13:15:00.582939" au niveau d'affichage
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //  "createdAt": "2026-05-04 15:21:15"
    private LocalDateTime createdAt;
}