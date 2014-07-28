package me.curiosus.pmmojo.controller.listeners.customer;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.persistence.dao.AreaDao;
import me.curiosus.pmmojo.persistence.dao.AreaDaoImpl;
import me.curiosus.pmmojo.view.CustomerTableView;
import me.curiosus.pmmojo.view.CustomerView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:47:20 PM
 */
public class CustomerTableSelectionListener extends MouseAdapter {

    private CustomerTableView view;

    public CustomerTableSelectionListener(CustomerTableView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isPopupTrigger() && e.getClickCount() == 2) {
            Customer customer = view.getSelectedItem();
            if (customer != null) {
                AreaDao areaDao = new AreaDaoImpl();
                List<Area> areas = areaDao.retrieve();
                CustomerView customerView = new CustomerView(customer, areas);
                customerView.addSaveButtonActionListener(new SaveCustomerActionListener(customerView));
                customerView.setVisible(true);
            }
        }
    }
}

