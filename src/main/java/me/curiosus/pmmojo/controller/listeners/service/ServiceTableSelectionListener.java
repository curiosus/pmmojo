package me.curiosus.pmmojo.controller.listeners.service;

import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.persistence.dao.TechnicianDao;
import me.curiosus.pmmojo.persistence.dao.TechnicianDaoImpl;
import me.curiosus.pmmojo.view.ServiceTableView;
import me.curiosus.pmmojo.view.ServiceView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * User: jpeterson
 * Date: Apr 5, 2012
 * Time: 6:16:10 PM
 */
public class ServiceTableSelectionListener extends MouseAdapter {

    private ServiceTableView serviceTableView;

    public ServiceTableSelectionListener(ServiceTableView serviceTableView) {
        this.serviceTableView = serviceTableView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        if ((!e.isPopupTrigger()) && e.getClickCount() == 2) {
            Service service = serviceTableView.getSelectedItem();
            if (service != null) {
                TechnicianDao technicianDao = new TechnicianDaoImpl();
                List<Technician> technicians = technicianDao.retrieve();
                ServiceView view = new ServiceView(service, technicians, serviceTableView);
                view.addSaveButtonActionListener(new SaveServiceActionListener(view));
                view.setVisible(true);
            }
        }
    }
}
