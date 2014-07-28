package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.controller.ServiceController;
import me.curiosus.pmmojo.model.ModelItem;
import me.curiosus.pmmojo.model.Service;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

/**
 * User: jpeterson
 * Date: 2/3/12 11:38 AM
 */
public class ServiceTableModel extends AbstractTableModel implements ModelItemTableModel{

    private List<Service> services;

    public ServiceTableModel(List<Service> services) {
        this.services = services;
    }

    @Override
    public int getRowCount() {
        return services.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Date.class;
            case 4: return Boolean.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Company";
            case 1: return "Equipment Make";
            case 2: return "Equipment Model";
            case 3: return "Service Date";
            case 4: return "Printed";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Service service = services.get(rowIndex);
        switch (columnIndex) {
            case 0: return service.getEquipment().getCustomer().getName();
            case 1: return service.getEquipment().getMake();
            case 2: return service.getEquipment().getModel();
            case 3: return service.getDate();
            case 4: return service.isPrinted();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 4) {
            Boolean printed = (Boolean) aValue;
            Service service = services.get(rowIndex);
            service.setPrinted(printed);
            fireTableCellUpdated(rowIndex, columnIndex);
            if (printed) {
                ServiceController.getInstance().printService(service);
            } else {
                ServiceController.getInstance().saveService(service);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 4);
    }

    public Service getService(int row) {
        if (row >= 0 && row < services.size()) {
            return services.get(row);
        }
        return null;
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public ModelItem getModelItem(int row) {
        return getService(row);
    }
}

