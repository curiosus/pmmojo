package me.curiosus.pmmojo.controller.listeners.technician;

import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.view.TechnicianTableView;
import me.curiosus.pmmojo.view.TechnicianView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * jp
 * Date: May 14, 2011
 * Time: 12:51:11 PM
 */
public class TechnicianTableSelectionListener extends MouseAdapter {

    private TechnicianTableView tableView;

    public TechnicianTableSelectionListener(TechnicianTableView tableView) {
        this.tableView = tableView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isPopupTrigger() && e.getClickCount() == 2) {
            Technician technician = tableView.getSelectedItem();
            if (technician != null) {
                TechnicianView view = new TechnicianView(technician);
                view.addSaveButtonActionListener(new SaveTechnicianActionListener(view));
                view.setVisible(true);
            }

        }
    }
}
