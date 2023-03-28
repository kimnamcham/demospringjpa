package com.example.demojpa.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.*;

@Table(name = "mobile")
@Entity
@Setter
@Getter
@ToString
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
    private Timestamp createdTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", nullable = false) // thông qua khóa ngoại cart_id
    private Cart cart;

    private int type = 1;
    private int active = 0;
}
