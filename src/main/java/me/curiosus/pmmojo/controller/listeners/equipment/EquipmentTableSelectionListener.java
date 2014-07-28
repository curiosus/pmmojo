package me.curiosus.pmmojo.controller.listeners.equipment;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.persistence.dao.CustomerDao;
import me.curiosus.pmmojo.view.EquipmentTableView;
import me.curiosus.pmmojo.view.EquipmentView;
import me.curiosus.pmmojo.persistence.dao.CustomerDaoImpl;
import me.curiosus.pmmojo.persistence.dao.MakeDao;
import me.curiosus.pmmojo.persistence.dao.MakeDaoImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 6, 2011
 * Time: 8:09:09 PM
 */
public class EquipmentTableSelectionListener extends MouseAdapter {

    private EquipmentTableView view;

    public EquipmentTableSelectionListener(EquipmentTableView view) {
       this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isPopupTrigger() && e.getClickCount() == 2) {
            Equipment equipment = view.getSelectedItem();
            if (equipment != null) {
                MakeDao makeDao = new MakeDaoImpl();
                List<Make> makes = makeDao.retrieve();
                CustomerDao customerDao = new CustomerDaoImpl();
                List<Customer> customers = customerDao.retrieve();
                EquipmentView equipmentView = new EquipmentView(equipment, makes, customers, EquipmentView.EDIT_EQUIPMENT);
                equipmentView.addSaveButtonActionListener(new SaveEquipmentActionListener(equipmentView));
                equipmentView.setVisible(true);
            }
        }
    }
}

