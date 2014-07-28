package me.curiosus.pmmojo.persistence.dao;


import me.curiosus.pmmojo.comparators.CustomerComparator;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.persistence.HibernateUtils;

import java.util.Collections;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:42:55 PM
 */
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void save(Customer customer) {
        HibernateUtils.save(customer);
    }

    @Override
    public List<Customer> retrieve() {
        List<Customer> customers = HibernateUtils.retrieve("from CustomerImpl");
        Collections.sort(customers, new CustomerComparator());
        return customers;
    }


    @Override
    public Customer retrieveByCustomerId(String customerId) {
        List<Customer> customers = HibernateUtils.retrieve("from CustomerImpl where id = '" + customerId + "'");
        return customers.get(0);
    }


}
