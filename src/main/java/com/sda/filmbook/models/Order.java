package com.sda.filmbook.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private LocalDate orderedDate;
    private LocalDate returnDate;
    private DelivertType delivertType;
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order")
    private List<Copy> copies;
}
