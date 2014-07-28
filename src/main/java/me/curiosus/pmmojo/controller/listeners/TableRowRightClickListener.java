package me.curiosus.pmmojo.controller.listeners;

import me.curiosus.pmmojo.model.ModelItem;
import me.curiosus.pmmojo.view.ModelItemTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: jepeterson@gmail.com
 * Date: 11/22/12
 * Time: 9:45 AM
 */
public class TableRowRightClickListener extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && e.getSource() instanceof JTable) {
            final JTable table = (JTable) e.getSource();
            ModelItemTableModel tableModel = (ModelItemTableModel) table.getModel();
            Point point = e.getPoint();
            int row = table.rowAtPoint(point);
            final ModelItem modelItem = tableModel.getModelItem(row);
            table.getSelectionModel().setSelectionInterval(row, row);
            JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.setBorderPainted(true);
            JMenuItem labelItem = new JMenuItem(modelItem.getDisplayName() + " ID: " + modelItem.getId());
            popupMenu.add(labelItem);
            JMenuItem menuItem = new JMenuItem("Copy ID to clipboard");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringSelection stringSelection = new StringSelection(modelItem.getId());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
                }
            });
            popupMenu.add(menuItem);
            popupMenu.show(table, point.x, point.y);




        }
    }
}
