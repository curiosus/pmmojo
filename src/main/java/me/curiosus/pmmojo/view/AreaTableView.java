package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Area;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:33:42 PM
 */
public class AreaTableView extends JDialog implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(600, 600);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private JTable table;
    private AreaTableModel tableModel;

    public AreaTableView(AreaTableModel tableModel) {
        this.tableModel = tableModel;
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension dim = new Dimension(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);
        setSize(dim);
        setTitle("Areas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(tableModel);
        table.setPreferredSize(dim);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dim);
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        gbl.row().cell(scrollPane).fillXY();
        gbl.doneAndPushEverythingToTop();

    }

    public void setTableViewEditListener(MouseAdapter mouseAdapter) {
        table.addMouseListener(mouseAdapter);
    }

    public void setTableRowRightClickListener(MouseAdapter mouseListener) {
        table.addMouseListener(mouseListener);
    }

    public Area getSelectedItem() {
        return tableModel.getArea(table.getSelectedRow());
    }

}
