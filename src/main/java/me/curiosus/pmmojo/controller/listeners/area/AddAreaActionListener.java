package me.curiosus.pmmojo.controller.listeners.area;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.view.AreaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: jepeterson@gmail.com
 * Date: Jul 27, 2011
 * Time: 7:25:03 PM
 */
public class AddAreaActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Area area = ModelFactory.createArea();
        final AreaView view = new AreaView(area);
        view.addSaveButtonActionListener(new SaveAreaActionListener(view));
        view.setVisible(true);
    }
}
