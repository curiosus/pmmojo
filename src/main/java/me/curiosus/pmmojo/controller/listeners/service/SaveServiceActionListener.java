package me.curiosus.pmmojo.controller.listeners.service;

import me.curiosus.pmmojo.view.ServiceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: jpeterson 
 * Date: May 5, 2012
 * Time: 3:15:23 PM
 */
public class SaveServiceActionListener implements ActionListener {

    private ServiceView serviceView;

    public SaveServiceActionListener(ServiceView serviceView) {
        this.serviceView = serviceView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        serviceView.save();
    }
}
