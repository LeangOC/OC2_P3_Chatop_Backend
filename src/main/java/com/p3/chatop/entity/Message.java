package com.p3.chatop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;


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

    private Timestamp createdAt;
}