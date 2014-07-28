package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.ModelItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 5, 2011
 * Time: 9:45:52 PM
 */
public class EquipmentTableModel extends AbstractTableModel implements ModelItemTableModel {

    private List<Equipment> equipment;

    public EquipmentTableModel(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public int getRowCount() {
        return equipment.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Equipment equip = equipment.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return equip.getMake();
            case 1:
                return equip.getModel();
            case 2:
                return equip.getCustomer();
            case 3:
                return equip.getSerialNumber();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Make";
            case 1: return "Model";
            case 2: return "Customer";
            case 3: return "Serial Number";
        }
        return null;
    }

    public Equipment getEquipment(int row) {
        if (row >= 0 && row <= equipment.size()) {
            return equipment.get(row);
        }
        return null;
    }

    @Override
    public ModelItem getModelItem(int row) {
        return getEquipment(row);
    }
}
