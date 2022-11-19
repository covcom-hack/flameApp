package com.example.flame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tAudit")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "course")
    private Double course;

    @OneToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currencyEntity;

}
