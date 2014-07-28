package me.curiosus.pmmojo.controller.listeners.equipment;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.persistence.dao.EquipmentDaoImpl;
import me.curiosus.pmmojo.view.EquipmentTableModel;
import me.curiosus.pmmojo.view.EquipmentTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 5, 2011
 * Time: 2:31:08 PM
 */
public class EditEquipmentActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EquipmentDaoImpl dao = new EquipmentDaoImpl();
        List<Equipment> equipment = dao.retrieve();
        EquipmentTableModel model = new EquipmentTableModel(equipment);
        EquipmentTableView view = new EquipmentTableView(model);
        view.setTableViewEditListener(new EquipmentTableSelectionListener(view));
        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        view.setTableRowRightClickListener(rowRightClickListener);
        view.setVisible(true);
    }
}



