package view;

import business.concretes.CustomerManager;
import dao.concretes.CustomerDao;
import entities.Customer;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager(new CustomerDao());

        List<Customer> customerList = customerManager.findAll();
        System.out.println(customerList.toString());

    }
}