package me.curiosus.pmmojo.controller.listeners.equipment;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.CustomerDao;
import me.curiosus.pmmojo.persistence.dao.CustomerDaoImpl;
import me.curiosus.pmmojo.persistence.dao.MakeDao;
import me.curiosus.pmmojo.persistence.dao.MakeDaoImpl;
import me.curiosus.pmmojo.view.EquipmentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 5, 2011
 * Time: 2:29:45 PM
 */
public class AddEquipmentActionListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        MakeDao makeDao = new MakeDaoImpl();
        List<Make> makes = makeDao.retrieve();
        CustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> customers = customerDao.retrieve();
        Equipment equipment = ModelFactory.createEquipment();
        final EquipmentView view = new EquipmentView(equipment, makes, customers, EquipmentView.ADD_EQUIPMENT);
        view.addSaveButtonActionListener(new SaveEquipmentActionListener(view));
        view.setVisible(true);



    }
}
