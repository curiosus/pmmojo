package me.curiosus.pmmojo.controller.listeners.customer;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.AreaDao;
import me.curiosus.pmmojo.persistence.dao.AreaDaoImpl;
import me.curiosus.pmmojo.view.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 3:45:46 PM
 */
public class AddCustomerActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AreaDao areaDao = new AreaDaoImpl();
        List<Area> areas = areaDao.retrieve();
        Customer customer = ModelFactory.createCustomer();
        final CustomerView view = new CustomerView(customer, areas);
        view.addSaveButtonActionListener(new SaveCustomerActionListener(view));
        view.setVisible(true);
    }
}
