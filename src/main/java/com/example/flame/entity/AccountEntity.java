package com.example.flame.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tAccount")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "num")
    private String num;

    @Column(name = "currency_id")
    private Long currency_id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "username")
    private String username;
}
