package me.curiosus.pmmojo.controller.listeners.make;

import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.view.MakeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: May 24, 2011
 * Time: 6:34:53 PM
 */
public class AddMakeActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Make make = ModelFactory.createMake();
        final MakeView view = new MakeView(make);
        view.addSaveButtonActionListener(new SaveMakeActionListener(view));
        view.setVisible(true);
    }
}




