package com.project.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.myapplication.entities.Customer;

@Dao
public interface CustomerDAO {

    @Insert
    void insertCustomer(Customer customer);

    @Query("SELECT * FROM Customer c WHERE c.username = :username")
    Customer findCustomerByUsername(String username);

    @Query("SELECT * FROM Customer c WHERE c.username = :username AND c.password = :password")
    boolean checkLogin(String username , String password);
}
