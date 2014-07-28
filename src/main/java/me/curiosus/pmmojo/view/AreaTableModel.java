package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.ModelItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:27:53 PM
 */
public class AreaTableModel extends AbstractTableModel implements ModelItemTableModel {

    private List<Area> areas;

    public AreaTableModel(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public int getRowCount() {
        return areas.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Area";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Area area = areas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return area.getName();
            default:
                return null;
        }
    }

    public Area getArea(int row) {
        if (row >= 0 && row < areas.size()) {
            return areas.get(row);
        }
        return null;
    }

    @Override
    public ModelItem getModelItem(int row) {
        return getArea(row);
    }
}
