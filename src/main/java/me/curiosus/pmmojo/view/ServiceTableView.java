package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Service;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * User: jpeterson
 * Date: 2/3/12 11:38 AM
 */
public class ServiceTableView extends JPanel implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(1280, 800);
    private static final Point SCREEN_POSITION = new Point(100, 200);


    private JTable table;
    private ServiceTableModel tableModel;
    
    public ServiceTableView(ServiceTableModel tableModel) {
        this.tableModel = tableModel;
        Dimension dim = SCREEN_SIZE;
        setLocation(SCREEN_POSITION);
//        setPreferredSize(dim);


        table = new JTable(tableModel);
//        table.setPreferredSize(dim);
//        table.setMinimumSize(dim);
//        table.setMaximumSize(new Dimension(1280, 600));


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

    public void setTableRowRightClickListener(MouseAdapter mouseListener) {
        table.addMouseListener(mouseListener);
    }

    public Service getSelectedItem() {
        return tableModel.getService(table.getSelectedRow());
    }

    public void removeServiceFromTable(Service service) {
        tableModel.removeService(service);
        tableModel.fireTableDataChanged();
    }

    public void addServiceToTable(Service service) {
        tableModel.addService(service);
        tableModel.fireTableDataChanged();
    }


}
