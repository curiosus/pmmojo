package me.curiosus.pmmojo.controller.listeners.area;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.persistence.dao.AreaDaoImpl;
import me.curiosus.pmmojo.view.AreaTableModel;
import me.curiosus.pmmojo.view.AreaTableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Jul 28, 2011
 * Time: 6:35:06 PM
 */
public class EditAreaActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        AreaDaoImpl dao = new AreaDaoImpl();
        List<Area> areas = dao.retrieve();
        AreaTableModel model = new AreaTableModel(areas);
        AreaTableView view = new AreaTableView(model);
        view.setTableViewEditListener(new AreaTableSelectionListener(view));
        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        view.setTableRowRightClickListener(rowRightClickListener);
        view.setVisible(true);
    }
}
