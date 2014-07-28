package me.curiosus.pmmojo.controller.listeners.make;

import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.view.MakeTableView;
import me.curiosus.pmmojo.view.MakeView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * jp
 * Date: May 31, 2011
 * Time: 6:31:03 PM
 */
public class MakeTableSelectionListener extends MouseAdapter {

    private MakeTableView tableView;

    public MakeTableSelectionListener(MakeTableView tableView) {
        this.tableView = tableView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isPopupTrigger() && e.getClickCount() == 2) {
            Make make = tableView.getSelectedItem();
            if (make != null) {
                MakeView view = new MakeView(make);
                view.addSaveButtonActionListener(new SaveMakeActionListener(view));
                view.setVisible(true);
            }

        }
    }
}
