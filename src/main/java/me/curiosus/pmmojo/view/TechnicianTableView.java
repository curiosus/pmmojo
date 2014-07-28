package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Technician;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * jp
 * Date: May 9, 2011
 * Time: 5:37:39 PM
 */
public class TechnicianTableView extends JDialog implements TableView {

    private static final Dimension SCREEN_SIZE = new Dimension(600, 600);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private JTable table;
    private TechnicianTableModel tableModel;

    public TechnicianTableView(TechnicianTableModel tableModel) {
        this.tableModel = tableModel;
        setModalityType(ModalityType.APPLICATION_MODAL);
        Dimension dim = SCREEN_SIZE;
        setLocation(SCREEN_POSITION);
        setTitle("Mechanics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(dim);


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

    public Technician getSelectedItem() {
        return tableModel.getTechnician(table.getSelectedRow());
    }



    

}
