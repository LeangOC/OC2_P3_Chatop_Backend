package com.p3.chatop.entity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "RENTALS")
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double surface;
    private Double price;
    private String picture;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}