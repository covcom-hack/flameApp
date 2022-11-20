package com.example.flame.entity;

import com.example.flame.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "`tDeal`")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_from_username")
    private String userFrom;

    @Column(name = "user_to_username")
    private String userTo;

    @Column(name = "num_from")
    private String numFrom;

    @Column(name = "num_to")
    private String numTo;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "currency_brief")
    private String currencyBrief;

    @Column(name = "amount")
    private Double amount;

}
