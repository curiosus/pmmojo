package me.curiosus.pmmojo.controller.listeners.area;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.view.AreaTableView;
import me.curiosus.pmmojo.view.AreaView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: jepeterson@gmail.com
 * Date: Jul 28, 2011
 * Time: 6:27:53 PM
 */
public class AreaTableSelectionListener extends MouseAdapter {

    private AreaTableView view;

    public AreaTableSelectionListener(AreaTableView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isPopupTrigger() && e.getClickCount() == 2) {
           Area area = view.getSelectedItem();
            if (area != null) {
                AreaView view = new AreaView(area);
                view.addSaveButtonActionListener(new SaveAreaActionListener(view));
                view.setVisible(true);
            }
        }
    }
}
