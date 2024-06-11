package com.doddanna.SSEDemo.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String eventType;
}
