package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Equipment;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 6, 2011
 * Time: 7:58:41 PM
 */
public class EquipmentTableView extends JDialog implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(600, 600);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private JTable table;
    private EquipmentTableModel tableModel;

    public EquipmentTableView(EquipmentTableModel tableModel) {
        this.tableModel = tableModel;
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension dim = SCREEN_SIZE;
        setSize(dim);
        setLocation(SCREEN_POSITION);
        setTitle("Equipment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table = new JTable(tableModel);
//        table.setPreferredSize(dim);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dim);
        scrollPane.setMinimumSize(dim);
        scrollPane.setMaximumSize(dim);


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

    public Equipment getSelectedItem() {
        return tableModel.getEquipment(table.getSelectedRow());
    }



}


