package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Customer;

import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:41:47 PM
 */
public interface CustomerDao {

    void save(Customer customer);
    List<Customer> retrieve();
    Customer retrieveByCustomerId(String customerId);
}
