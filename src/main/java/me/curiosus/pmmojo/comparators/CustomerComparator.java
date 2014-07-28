package me.curiosus.pmmojo.comparators;

import me.curiosus.pmmojo.model.Customer;

import java.util.Comparator;

/**
 * User: jepeterson@gmail.com
 * Date: 2/6/13
 * Time: 8:36 PM
 */
public class CustomerComparator implements Comparator<Customer> {


    @Override
    public int compare(Customer c1, Customer c2) {
        String name1 = c1.getName().toLowerCase();
        String name2 = c2.getName().toLowerCase();
        return name1.compareTo(name2);
    }
}
