package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Make;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * jp
 * Date: May 24, 2011
 * Time: 6:54:53 PM
 */
public class MakeTableView extends JDialog implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(600, 600);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private JTable table;
    private MakeTableModel tableModel;

    public MakeTableView(MakeTableModel tableModel) {
        this.tableModel = tableModel;
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension dim = SCREEN_SIZE;
        setLocation(SCREEN_POSITION);
        setSize(dim);
        setTitle("Makes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(tableModel);
        table.setPreferredSize(dim);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dim);
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        gbl.row().cell(scrollPane).fillXY();
        gbl.doneAndPushEverythingToTop();

    }


    public void setTableViewEditListener(MouseAdapter mouseListener) {
        table.addMouseListener(mouseListener);
    }

    @Override
    public void setTableRowRightClickListener(MouseAdapter mouseListener) {
        table.addMouseListener(mouseListener);
    }

    public Make getSelectedItem() {
        return tableModel.getMake(table.getSelectedRow());
    }



}


