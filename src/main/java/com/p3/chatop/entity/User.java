package com.p3.chatop.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;
    private String name;
    private String password;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
     // Pour ne pas avoir : "2026-05-04 13:15:00.582939" au niveau d'affichage
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     //  "createdAt": "2026-05-04 15:21:15"
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}