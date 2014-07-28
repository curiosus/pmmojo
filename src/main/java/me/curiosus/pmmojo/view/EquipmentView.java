package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.persistence.dao.ServiceDao;
import me.curiosus.pmmojo.persistence.dao.ServiceDaoImpl;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Aug 20, 2011
 * Time: 1:38:08 PM
 */
public class EquipmentView extends AbstractView {

    public static final int ADD_EQUIPMENT = 0;
    public static final int EDIT_EQUIPMENT = 1;

    private static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private Equipment equipment;
    private List<Make> makes;
    private List<Customer> customers;
    private JTextComponent modelTextComponent;
    private JComboBox makeCombo;
    private JComboBox customerCombo;
    private JComboBox serviceIntervalUnitMeasure;
    private JTextComponent serviceInterval;
    private JTextComponent serialNumber;
    private JTextComponent lastServiceDate;
    private JTextComponent serviceDate;
    private JTextComponent transFilterDate;
    private JTextComponent equipmentNotes;

    private JLabel airFilterChanged;
    private JLabel oilFilterChanged;
    private JLabel fuelFilterChanged;
    private JLabel hydraulicFilterChanged;
    private JLabel transFilterChanged;

    private int addOrEdit;


    public EquipmentView(Equipment equipment, List<Make> makes, List<Customer> customers, int addOrEdit) {
        this.equipment = equipment;
        this.makes = makes;
        this.customers = customers;
        this.addOrEdit = addOrEdit;
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        String title =  addOrEdit == ADD_EQUIPMENT ? "Add Equipment" : "Edit Equipment";
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);

        Container container = getContentPane();
        MigLayout layout = new MigLayout();
        container.setLayout(layout);

        makeCombo = new JComboBox(makes.toArray());
        makeCombo.setSelectedItem(equipment.getMake());
        container.add(new JLabel("Make:"), "growx");
        container.add(makeCombo, "growx, wrap");

        customerCombo = new JComboBox(customers.toArray());
        customerCombo.setSelectedItem(equipment.getCustomer());
        container.add(new JLabel("Customer:"), "growx");
        container.add(customerCombo, "growx, wrap");

        modelTextComponent = new JTextField();
        modelTextComponent.setText(equipment.getModel());
        container.add(new JLabel("Model:"), "growx");
        container.add(modelTextComponent, "growx, wrap");

        serviceInterval = new JTextField();
        serviceInterval.setText(equipment.getServiceInterval().toString());
        container.add(new JLabel("Service Interval"), "growx");
        container.add(serviceInterval, "growx");

        serialNumber = new JTextField();
        serialNumber.setText(equipment.getSerialNumber());

        String [] serviceIntervalUnitMeasures = new String [4];
        serviceIntervalUnitMeasures[0] = Service.SERVICE_INTERVAL_UNITS_DAYS;
        serviceIntervalUnitMeasures[1] = Service.SERVICE_INTERVAL_UNITS_WEEKS;
        serviceIntervalUnitMeasures[2] = Service.SERVICE_INTERVAL_UNITS_MONTHS;
        serviceIntervalUnitMeasures[3] = Service.SERVICE_INTERVAL_UNITS_YEARS;
        serviceIntervalUnitMeasure = new JComboBox(serviceIntervalUnitMeasures);
        serviceIntervalUnitMeasure.setSelectedItem(equipment.getServiceIntervalUnits());
        container.add(serviceIntervalUnitMeasure, "growx, wrap");

        container.add(new JLabel("Serial Number"), "growx");
        container.add(serialNumber, "growx, wrap");

        lastServiceDate = new JTextField();
        if (addOrEdit == EDIT_EQUIPMENT) {
            SimpleDateFormat simpleDateFormat = Service.SERVICE_DATE_FORMAT;
            if (equipment.getLastServiceDate() != null) {
                lastServiceDate.setText(simpleDateFormat.format(equipment.getLastServiceDate()));
            }
        }
        lastServiceDate.setEditable(false);


        serviceDate = new JTextField();
        if (addOrEdit == ADD_EQUIPMENT) {
            SimpleDateFormat simpleDateFormat = Service.SERVICE_DATE_FORMAT;
            serviceDate.setText(simpleDateFormat.format(new Date()));
        } else if (addOrEdit == EDIT_EQUIPMENT) {
            ServiceDao serviceDao = new ServiceDaoImpl();
            Service openService = serviceDao.getOpenServiceByEquipmentId(equipment);
            SimpleDateFormat simpleDateFormat = Service.SERVICE_DATE_FORMAT;
            serviceDate.setText(simpleDateFormat.format(openService.getDate()));
        } else {
            throw new IllegalStateException("Ambiguous Add or Edit State");
        }

        container.add(new JLabel("Last Service Date"), "growx");
        container.add(lastServiceDate, "growx, wrap");

        container.add(new JLabel("Next Service Date"), "growx");
        container.add(serviceDate, "growx, wrap");

        transFilterDate = new JTextField();
        transFilterDate.setText(equipment.getTransFilter());
        container.add(new JLabel("Trans Filter Date"), "growx");
        container.add(transFilterDate, "growx, wrap");

        equipmentNotes = new JTextArea();
        equipmentNotes.setText(equipment.getEquipmentNotes());
        container.add(new JLabel("Equipment Notes"), "growx");
        container.add(new JScrollPane(equipmentNotes), "growx, wrap");

        String airFilterChangeValue = (equipment.getAirFilterDate() != null) ? equipment.getAirFilterDate().toString(): "";
        airFilterChanged = new JLabel("Air Filter Change: " + airFilterChangeValue);
        container.add(airFilterChanged, "growx, wrap");

        String oilFilterChangeValue = (equipment.getOilFilterDate() != null) ? equipment.getOilFilterDate().toString(): "";
        oilFilterChanged = new JLabel("Oil Filter Change: " + oilFilterChangeValue);
        container.add(oilFilterChanged, "growx, wrap");

        String fuelFilterChangeValue = (equipment.getFuelFilterDate() != null) ? equipment.getFuelFilterDate().toString(): "";
        fuelFilterChanged = new JLabel("Fuel Filter Changed: " + fuelFilterChangeValue);
        container.add(fuelFilterChanged, "growx, wrap");

        String hydraulicFilterChangeValue = (equipment.getHydraulicFilterDate() != null) ? equipment.getHydraulicFilterDate().toString(): "";
        hydraulicFilterChanged = new JLabel("Hydraulic Filter Changed: " + hydraulicFilterChangeValue);
        container.add(hydraulicFilterChanged, "growx, wrap");

        String transFilterChangeValue = (equipment.getTransFilterDate() != null) ? equipment.getTransFilterDate().toString(): "";
        transFilterChanged = new JLabel("Trans Filter Changed: " + transFilterChangeValue);
        container.add(transFilterChanged, "growx, wrap");




        saveBtn = new JButton("Save");
        container.add(saveBtn, "wrap");
        addDirtyListeners();

    }


    public Equipment getEquipment() {
        return equipment;
    }


    @Override
    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
        modelTextComponent.getDocument().addDocumentListener(listener);
        serviceInterval.getDocument().addDocumentListener(listener);
        serialNumber.getDocument().addDocumentListener(listener);
        transFilterDate.getDocument().addDocumentListener(listener);
        equipmentNotes.getDocument().addDocumentListener(listener);
        serviceDate.getDocument().addDocumentListener(listener);
    }

    public String getModelFieldValue() {
        return modelTextComponent.getText();
    }

    public Make getMakeFieldValue() {
        return (Make) makeCombo.getSelectedItem();
    }

    public Customer getCustomerFieldValue() {
        return (Customer) customerCombo.getSelectedItem();
    }

    public Integer getServiceInterval() {
        return Integer.valueOf(serviceInterval.getText());
    }

    public String getServiceIntervalUnitOfMeasure() {
        return (String) serviceIntervalUnitMeasure.getSelectedItem();
    }

    public String getServiceDateFieldValue() {
        return serviceDate.getText();
    }

    public String getSerialNumberFieldValue() {
        return serialNumber.getText();
    }

    public String getTransFilterDateFieldValue() {
        return transFilterDate.getText();
    }

    public String getEquipmentNotesFieldValue() {
        return equipmentNotes.getText();
    }

    public int getAddOrEdit() {
        return addOrEdit;
    }
}
