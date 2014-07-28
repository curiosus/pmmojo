package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.ModelItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 4:04:13 PM
 */
public class CustomerTableModel extends AbstractTableModel implements ModelItemTableModel {

    private List<Customer> customers;

    public CustomerTableModel(List<Customer> customers) {
        this.customers = customers;
    }


    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer customer = customers.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return customer.getName();
            case 1:
                return customer.getAddress1();
            case 2:
                return customer.getAddress2();
            case 3:
                return customer.getCity();
            case 4:
                return customer.getState();
            case 5:
                return customer.getPostalCode();
            case 6:
                return customer.getPhone1();
            case 7:
                return customer.getPhone2();
            case 8:
                return customer.getContact1();
            case 9:
                return customer.getContact2();
            case 10:
                return customer.getFax();
            case 11:
                return customer.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Customer";
            case 1:
                return "Address";
            case 2:
                return "Address";
            case 3:
                return "City";
            case 4:
                return "State";
            case 5:
                return "Zip";
            case 6:
                return "Phone";
            case 7:
                return "Phone 2";
            case 8:
                return "Contact";
            case 9:
                return "Contact2";
            case 10:
                return "Fax";
            case 11:
                return "Email";
        }
        return null;
    }

    public Customer getCustomer(int row) {
        if (row >= 0 && row < customers.size()) {
            return customers.get(row);
        }
        return null;
    }

    @Override
    public ModelItem getModelItem(int row) {
        return getCustomer(row);
    }
}


