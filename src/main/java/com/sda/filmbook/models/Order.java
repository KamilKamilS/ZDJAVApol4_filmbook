package com.sda.filmbook.models;

import java.time.LocalDate;

public class Order {
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private DelivertType delivertType;
    private List<Copy> copies;
}
