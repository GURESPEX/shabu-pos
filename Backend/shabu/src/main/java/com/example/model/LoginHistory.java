package com.example.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false)
    private Date CreateDate;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "User_Id", nullable = false)
    private User User;

    /* ----- Method ----- */

    public LoginHistory() {
        CreateDate = new Date();
    }

    public LoginHistory(User user) {
        User = user;
        CreateDate = new Date();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    @JsonIgnore
    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

}
