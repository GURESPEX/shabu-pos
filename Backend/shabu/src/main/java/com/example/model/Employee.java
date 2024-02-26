package com.example.model;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Employee extends User {

    public Employee() {
        super();
    }

    /* ----- Method ----- */

    public Employee(String sID, String firstname, String lastname, String email, String tel, String address,
            String city,
            String district, String subDistrict, String postCode, String password, List<Payment> payments,
            List<LoginHistory> loginHistorys) {
        super(sID, firstname, lastname, email, tel, address,
                city, district, subDistrict, postCode, password, payments, loginHistorys);
    }

}
