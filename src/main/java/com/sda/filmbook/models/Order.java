package com.sda.filmbook.models;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private DelivertType delivertType;
    private PaymentType paymentType;
    private List<Copy> copies;
}
