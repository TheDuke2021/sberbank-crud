package ru.theduke2021.sberbankcrud.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int quantity;

    /**
     * This value is automatically inserted by Hibernate, do NOT edit manually.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * This value is automatically inserted by Hibernate, do NOT edit manually.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
