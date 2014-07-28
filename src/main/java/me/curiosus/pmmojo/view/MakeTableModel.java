package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.model.ModelItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * jp
 * Date: May 24, 2011
 * Time: 6:56:51 PM
 */
public class MakeTableModel extends AbstractTableModel implements ModelItemTableModel {

    private List<Make> makes;

    public MakeTableModel(List<Make> makes) {
        this.makes = makes;
    }

    @Override
    public int getRowCount() {
        return makes.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Make";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Make make = makes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return make.getName();
            default:
                return null;
        }
    }

    public Make getMake(int row) {
        if (row >= 0 && row < makes.size()) {
            return makes.get(row);
        }
        return null;
    }

    @Override
    public ModelItem getModelItem(int row) {
        return getMake(row);
    }
}
