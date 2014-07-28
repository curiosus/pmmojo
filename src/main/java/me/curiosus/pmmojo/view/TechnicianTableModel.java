package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.ModelItem;
import me.curiosus.pmmojo.model.Technician;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * jp
 * Date: May 9, 2011
 * Time: 6:14:21 PM
 */
public class TechnicianTableModel extends AbstractTableModel implements ModelItemTableModel {


    private List<Technician> technicians;

    public TechnicianTableModel(List<Technician> technicians) {
        this.technicians = technicians;
    }

    @Override
    public int getRowCount() {
        return technicians.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Technician technician = technicians.get(rowIndex);
        switch (columnIndex) {
            case 0: return technician.getFirstName();
            case 1: return technician.getLastName();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "First Name";
            case 1: return "Last Name";
        }
        return null;
    }

    public Technician getTechnician(int row) {
        if (row >= 0 && row < technicians.size()) {
            return technicians.get(row);
        }
        return null; 
    }


    @Override
    public ModelItem getModelItem(int row) {
        return getTechnician(row);
    }
}
