package me.curiosus.pmmojo.controller.listeners.technician;

import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.view.TechnicianView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: May 7, 2011
 * Time: 1:21:07 PM
 */
public class AddTechnicianActionListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        Technician technician = ModelFactory.createTechnician();
        final TechnicianView view = new TechnicianView(technician);
        view.addSaveButtonActionListener(new SaveTechnicianActionListener(view));
        view.setVisible(true);
    }
}
