package me.curiosus.pmmojo.controller.listeners.customer;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.persistence.dao.CustomerDao;
import me.curiosus.pmmojo.persistence.dao.CustomerDaoImpl;
import me.curiosus.pmmojo.view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:48:51 PM
 */
public class SaveCustomerActionListener implements ActionListener {

    private CustomerView customerView;


    public SaveCustomerActionListener(CustomerView customerView) {
        this.customerView = customerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (customerView.getNameFieldValue() == null || customerView.getNameFieldValue().length() == 0) {
            JOptionPane.showMessageDialog(customerView, "Customer Name Required");
            return;
        } else if (customerView.getAddress1FieldValue() == null || customerView.getAddress1FieldValue().length() ==0) {
            JOptionPane.showMessageDialog(customerView, "Address required");
            return;
        } else if (customerView.getAreaFieldValue() == null) {
            JOptionPane.showMessageDialog(customerView, "Area Required");
            return;
        }



        Customer customer = customerView.getCustomer();
        customer.setName(customerView.getNameFieldValue());
        customer.setAddress1(customerView.getAddress1FieldValue());
        customer.setAddress2(customerView.getAddress2FieldValue());
        customer.setCity(customerView.getCityFieldValue());
        customer.setState(customerView.getStateFieldValue());
        customer.setPostalCode(customerView.getPostalCodeFieldValue());
        customer.setContact1(customerView.getContact1FieldValue());
        customer.setContact2(customerView.getContact2FieldValue());
        customer.setPhone1(customerView.getPhone1FieldValue());
        customer.setPhone2(customerView.getPhone2FieldValue());
        customer.setFax(customerView.getFaxFieldValue());
        customer.setEmail(customerView.getEmailFieldValue());
        customer.setArea(customerView.getAreaFieldValue());
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.save(customer);
        customerView.setDirty(false);
        customerView.dispose();
    }
}


