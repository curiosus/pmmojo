package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Customer;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 4:12:16 PM
 */
public class CustomerTableView extends JDialog implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(900, 900);
    private static final Point SCREEN_POSITION = new Point(100, 400);

    private JTable table;
    private CustomerTableModel tableModel;

    public CustomerTableView(CustomerTableModel tableModel) {
        this.tableModel = tableModel;
        Dimension dim = SCREEN_SIZE;
        setSize(dim);
        setLocation(SCREEN_POSITION);
        setTitle("Customers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);         
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(dim);
        scrollPane.setMinimumSize(dim);
        scrollPane.setMaximumSize(new Dimension(dim));
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

    public Customer getSelectedItem() {
        return tableModel.getCustomer(table.getSelectedRow());
    }

}








