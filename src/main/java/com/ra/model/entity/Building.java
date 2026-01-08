package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 100)
    private String buildingName;
    @Column(nullable = false)
    private Double buildingArea;
    @Column(nullable = false,length = 10)
    private String areaUnit;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false,length = 10)
    private int time;
    @Column(nullable = false,length = 10)
    private String timeUnit;
    @Column(nullable = false,length = 255)
    private String design;
    @Column(nullable = false,length = 255)
    private String content;
    @Column(nullable = false)
    private Byte status =1;
}
