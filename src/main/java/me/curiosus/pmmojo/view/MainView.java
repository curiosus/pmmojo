package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.controller.listeners.DatabaseBackupListener;
import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.controller.listeners.service.ServiceTableSelectionListener;
import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.persistence.dao.AreaDao;
import me.curiosus.pmmojo.persistence.dao.AreaDaoImpl;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: May 4, 2011
 * Time: 7:23:01 PM
 */
public class MainView {

    private static final Dimension SCREEN_SIZE = new Dimension(1280, 1024);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private JFrame frame;
    private JMenuItem addTechnicianItem;
    private JMenuItem editTechnicianItem;
    private JMenuItem addMakeItem;
    private JMenuItem editMakeItem;
    private JMenuItem addCustomerItem;
    private JMenuItem editCustomerItem;
    private JMenuItem addAreaItem;
    private JMenuItem editAreaItem;
    private JMenuItem addEquipmentItem;
    private JMenuItem editEquipmentItem;
    private JComboBox customerSearchCombo;
    private List<Customer> customerList;
    private ServiceTableModel serviceTableModel;

    public void init() {

        frame = new JFrame("TEK P.M. - Version 3.0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_SIZE);
        frame.setLocation(SCREEN_POSITION);

        JLabel customerSearchLbl = new JLabel("Customer Search:");
        customerSearchCombo = new JComboBox(customerList.toArray());
        new AutoCompleteJComboBoxer(customerSearchCombo);
        /*TODO MAJOR HACK ALERT --REFACTOR*/
        JButton searchBtn = new JButton("Open");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AreaDao areaDao = new AreaDaoImpl();
                List<Area> areaList = areaDao.retrieve();
                Customer customer = (Customer) customerSearchCombo.getSelectedItem();
                CustomerView customerView = new CustomerView(customer, areaList);
                customerView.setVisible(true);
            }
        });

        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(5000, 75));
        headerPanel.setMinimumSize(new Dimension(5000, 75));
        headerPanel.setMaximumSize(new Dimension(5000, 75));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        headerPanel.setBackground(new Color(213, 213, 213));
        headerPanel.setOpaque(true);
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(customerSearchLbl);
        headerPanel.add(customerSearchCombo);
        headerPanel.add(searchBtn);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Add/Edit");
        menuBar.add(menu);

        JMenu technicianMenu = new JMenu("Technician");
        menu.add(technicianMenu);
        addTechnicianItem = new JMenuItem("Add");
        editTechnicianItem = new JMenuItem("Edit");
        technicianMenu.add(addTechnicianItem);
        technicianMenu.add(editTechnicianItem);

        JMenu customerMenu = new JMenu("Customer");
        menu.add(customerMenu);
        addCustomerItem = new JMenuItem("Add");
        editCustomerItem = new JMenuItem("Edit");
        customerMenu.add(addCustomerItem);
        customerMenu.add(editCustomerItem);

        JMenu equipmentMenu = new JMenu("Equipment");
        menu.add(equipmentMenu);
        addEquipmentItem = new JMenuItem("Add");
        editEquipmentItem = new JMenuItem("Edit");
        equipmentMenu.add(addEquipmentItem);
        equipmentMenu.add(editEquipmentItem);


        JMenu makeMenu = new JMenu("Make");
        menu.add(makeMenu);
        addMakeItem = new JMenuItem("Add");
        editMakeItem = new JMenuItem("Edit");
        makeMenu.add(addMakeItem);
        makeMenu.add(editMakeItem);

        JMenu areaMenu = new JMenu("Area");
        menu.add(areaMenu);
        addAreaItem = new JMenuItem("Add");
        editAreaItem = new JMenuItem("Edit");
        areaMenu.add(addAreaItem);
        areaMenu.add(editAreaItem);

        JMenu utilsMenu = new JMenu("Utilities");
        menuBar.add(utilsMenu);
        JMenuItem backUpDatabaseMenuItem = new JMenuItem("Backup Database");
        utilsMenu.add(backUpDatabaseMenuItem);
        backUpDatabaseMenuItem.addActionListener(new DatabaseBackupListener());

        ServiceTableView serviceTableView = new ServiceTableView(serviceTableModel);
        ServiceTableSelectionListener listener = new ServiceTableSelectionListener(serviceTableView);
        serviceTableView.setTableViewEditListener(listener);

        TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
        serviceTableView.setTableRowRightClickListener(rowRightClickListener);

        PainlessGridBag gbl = new PainlessGridBag(frame.getContentPane(), false);
        gbl.row().cell(headerPanel).fillX();
        gbl.row().cell(new JLabel("Open Tickets Within 14 Days of Due Date"));
        gbl.row().cell(serviceTableView).fillXY();
        gbl.doneAndPushEverythingToTop();

    }

    public void show() {
        frame.setVisible(true);
    }

    public void setServiceTableModel(ServiceTableModel serviceTableModel) {
        this.serviceTableModel = serviceTableModel;
    }

    public void setCustomerSearchList(java.util.List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void setAddTechnicianActionListener(ActionListener listener) {
        addTechnicianItem.addActionListener(listener);
    }

    public void setEditTechnicianActionListener(ActionListener listener) {
        editTechnicianItem.addActionListener(listener);
    }

    public void setAddMakeActionListener(ActionListener listener) {
        addMakeItem.addActionListener(listener);
    }

    public void setEditMakeActionListener(ActionListener listener) {
        editMakeItem.addActionListener(listener);
    }

    public void setAddCustomerActionListener(ActionListener listener) {
        addCustomerItem.addActionListener(listener);
    }

    public void setEditCustomerActionListener(ActionListener listener) {
        editCustomerItem.addActionListener(listener);
    }

    public void setAddAreaActionListener(ActionListener listener) {
        addAreaItem.addActionListener(listener);
    }

    public void setEditAreaListener(ActionListener listener) {
        editAreaItem.addActionListener(listener);
    }

    public void setAddEquipmentActionListener(ActionListener listener) {
        addEquipmentItem.addActionListener(listener);
    }

    public void setEditEquipmentActionListener(ActionListener listener) {
        editEquipmentItem.addActionListener(listener);
    }

    public static void main(String[] args) {
        new MainView().init();
    }
}
