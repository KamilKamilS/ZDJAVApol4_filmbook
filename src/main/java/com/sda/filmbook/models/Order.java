package com.sda.filmbook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_Id;

    private Customer customer;
    private LocalDate orderedDate;
    private LocalDate returnDate;
    private DelivertType delivertType;
    private PaymentType paymentType;

    private List<Copy> copies;
}
