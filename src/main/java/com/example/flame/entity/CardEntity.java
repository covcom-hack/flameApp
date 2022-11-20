package com.example.flame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "`tCard`")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "account")
    private String account;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    private Double amount;

}
