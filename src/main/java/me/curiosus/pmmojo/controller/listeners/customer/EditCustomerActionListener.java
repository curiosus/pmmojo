package me.curiosus.pmmojo.controller.listeners.customer;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.persistence.dao.CustomerDaoImpl;
import me.curiosus.pmmojo.view.CustomerTableModel;
import me.curiosus.pmmojo.view.CustomerTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:46:28 PM
 */
public class EditCustomerActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CustomerDaoImpl dao = new CustomerDaoImpl();
        List<Customer> customers = dao.retrieve();
        CustomerTableModel model = new CustomerTableModel(customers);
        CustomerTableView view = new CustomerTableView(model);
        view.setTableViewEditListener(new CustomerTableSelectionListener(view));
        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        view.setTableRowRightClickListener(rowRightClickListener);
        view.setVisible(true);
    }
}

