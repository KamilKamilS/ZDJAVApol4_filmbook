package com.sda.filmbook.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
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
