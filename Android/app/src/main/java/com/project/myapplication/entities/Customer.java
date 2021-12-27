package com.project.myapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class Customer implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = false )
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "fullname")
    private String fullName;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "phonenumber")
    private String phoneNumber;

    @ColumnInfo(name = "email")
    private String email;

    public Customer() {
    }

    public Customer(@NonNull String username, String password, String fullName, String address, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }
}
