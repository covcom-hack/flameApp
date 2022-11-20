package com.example.flame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.time.LocalDateTime;

@Entity
@Table(name = "tDeal")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "deal_id_seq", sequenceName = "deal_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "num_from")
    private String num_from;

    @Column(name = "num_to")
    private String num_to;

    @Column(name = "date_time")
    private LocalDateTime datetime;

    @Column(name = "currency_brief")
    private String currency_brief;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "user_from_username")
    private String user_from_username;

    @Column(name = "user_to_username")
    private String user_to_username;

}
