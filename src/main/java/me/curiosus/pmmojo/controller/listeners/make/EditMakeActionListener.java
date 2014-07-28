package me.curiosus.pmmojo.controller.listeners.make;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.persistence.dao.MakeDaoImpl;
import me.curiosus.pmmojo.view.MakeTableModel;
import me.curiosus.pmmojo.view.MakeTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: May 31, 2011
 * Time: 6:19:00 PM
 */
public class EditMakeActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MakeDaoImpl dao = new MakeDaoImpl();
        List<Make> makes = dao.retrieve();
        MakeTableModel model = new MakeTableModel(makes);
        MakeTableView view = new MakeTableView(model);
        view.setTableViewEditListener(new MakeTableSelectionListener(view));
        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        view.setTableRowRightClickListener(rowRightClickListener);
        view.setVisible(true);
    }
}
