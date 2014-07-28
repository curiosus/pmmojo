package me.curiosus.pmmojo.controller.listeners.technician;

import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.persistence.dao.TechnicianDao;
import me.curiosus.pmmojo.persistence.dao.TechnicianDaoImpl;
import me.curiosus.pmmojo.view.TechnicianView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: May 7, 2011
 * Time: 2:19:17 PM
 */
public class SaveTechnicianActionListener implements ActionListener {

    private TechnicianView technicianView;

    public SaveTechnicianActionListener(TechnicianView technicianView) {
        this.technicianView = technicianView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Technician technician = technicianView.getTechnician();
        technician.setFirstName(technicianView.getFirstNameFieldValue());
        technician.setLastName(technicianView.getLastNameFieldValue());
        TechnicianDao technicianDao = new TechnicianDaoImpl();
        technicianDao.save(technician);
        technicianView.setDirty(false);
        technicianView.dispose();
    }
}
