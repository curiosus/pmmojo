package me.curiosus.pmmojo.controller.listeners.technician;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.persistence.dao.TechnicianDaoImpl;
import me.curiosus.pmmojo.view.TechnicianTableModel;
import me.curiosus.pmmojo.view.TechnicianTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: May 9, 2011
 * Time: 5:35:04 PM
 */
public class EditTechnicianActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TechnicianDaoImpl dao = new TechnicianDaoImpl();
        //todo - here we are doing a db retrieval on the edt, not good, but no noticable lag
        List<Technician> technicians = dao.retrieve();
        TechnicianTableModel tableModel = new TechnicianTableModel(technicians);
        TechnicianTableView view = new TechnicianTableView(tableModel);
        view.setTableViewEditListener(new TechnicianTableSelectionListener(view));
        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        view.setTableRowRightClickListener(rowRightClickListener);
        view.setVisible(true);
    }


}
