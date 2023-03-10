package com.example.demojpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "mobile")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mobile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "created_time")
    private Timestamp createdTime ;


    private int type = 1;
    private int active = 0;
}
