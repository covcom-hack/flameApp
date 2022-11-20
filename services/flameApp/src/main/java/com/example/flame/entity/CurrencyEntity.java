package com.example.flame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "`tCurrency`")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brief")
    private String brief;

    @OneToMany(mappedBy = "currency")
    private List<CardEntity> cards;
}
