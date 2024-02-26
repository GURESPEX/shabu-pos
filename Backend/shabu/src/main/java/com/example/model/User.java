package com.example.model;

import java.util.Date;
import java.util.List;

import com.example.generator.CookieGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(unique = true, nullable = false, length = 13)
    private String SID;
    @Column(nullable = false, length = 100)
    private String Firstname;
    @Column(nullable = false, length = 100)
    private String Lastname;
    @Column(unique = true, nullable = true, length = 100)
    private String Email;
    @Column(unique = true, nullable = false, length = 10)
    private String Tel;
    @Column(nullable = false, length = 500)
    private String Address;
    @Column(nullable = false, length = 100)
    private String City;
    @Column(nullable = false, length = 100)
    private String District;
    @Column(nullable = false, length = 100)
    private String SubDistrict;
    @Column(nullable = false, length = 5)
    private String PostCode;
    @Column(nullable = false, length = 100)
    private String Password;
    @Column(nullable = true, length = 500)
    private String Session;
    @Column(nullable = false)
    private Date CreateDate;
    @Column(nullable = true)
    private Date UpdateDate;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Payment> Payments;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<LoginHistory> LoginHistories;

    /* ----- Method ----- */

    public User() {
    }

    public User(String sID, String firstname, String lastname, String email, String tel, String address, String city,
            String district, String subDistrict, String postCode, String password, List<Payment> payments,
            List<LoginHistory> loginHistories) {
        SID = sID;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Tel = tel;
        Address = address;
        City = city;
        District = district;
        SubDistrict = subDistrict;
        PostCode = postCode;
        Password = password;
        Payments = payments;
        LoginHistories = loginHistories;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String sID) {
        SID = sID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getSubDistrict() {
        return SubDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        SubDistrict = subDistrict;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }

    @JsonIgnore
    public List<Payment> getPayments() {
        return Payments;
    }

    public void setPayments(List<Payment> payments) {
        Payments = payments;
    }

    public List<LoginHistory> getLoginHistories() {
        return LoginHistories;
    }

    public void setLoginHistories(List<LoginHistory> loginHistories) {
        LoginHistories = loginHistories;
    }

    /* ----- Other ----- */

    public User login(String SID, String password) {
        if (this.isExist(SID, password)) {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setUser(this);
            this.LoginHistories.add(loginHistory);
            this.setSession(CookieGenerator.generator.generate());
            return this;
        }
        return null;
    }

    public CustomerTableOrder checkBill(String SID, String password, CustomerTableOrder customerTableOrder) {
        if (this.isExist(SID, password)) {
            Payment payment = new Payment(this, customerTableOrder);
            payment.setCustomerTableOrder(customerTableOrder);
            this.Payments.add(payment);
            return payment.getCustomerTableOrder();
        }
        return null;
    }

    public Boolean isExist(String userID, String password) {
        return userID.equals(this.SID) && password.equals(this.Password);
    }

}
