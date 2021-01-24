package com.sda.filmbook.service;

import com.sda.filmbook.model.Customer;
import com.sda.filmbook.repository.CustomerRepository;
import com.sda.filmbook.service.exception.CustomerDataNotValidException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public Customer createNewCustomer(Customer customer) throws CustomerDataNotValidException {
        if(customerDataValid(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new CustomerDataNotValidException();
        }
    }

    private boolean customerDataValid(Customer customer) {
        return validEmail(customer.getEmail()) && validPhoneNumber(customer.getPhoneNumber())
                && validAdress(customer.getAdress());
    }

    private boolean validEmail(String email) {
        return email.length() > 4 ? true : false;
    }

    private boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 9 ? true : false;
    }

    private boolean validAdress(String adress) {
        return true;
    }
}
