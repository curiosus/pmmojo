package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.controller.ServiceController;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.persistence.dao.EquipmentDao;
import me.curiosus.pmmojo.persistence.dao.EquipmentDaoImpl;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: 2/4/13
 * Time: 6:16 PM
 */

public class CloseServiceDialog extends JDialog  {

    private static final Dimension SCREEN_SIZE = new Dimension(800, 640);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    public static final int STATUS_CLOSED = 0;
    public static final int STATUS_CANCELLED = 1;



    private ServiceView serviceView;
    private Service service;
    private JComboBox technicianCombo;
    private JTextComponent notesForNextTime;
    private JButton closeBtn;
    private JButton cancelBtn;
    private JCheckBox airFilterChk;
    private JCheckBox oilFilterChk;
    private JCheckBox transFilterChk;
    private JCheckBox fuelFilterChk;
    private JCheckBox hydraulicFilterChk;
    private int status;

    public CloseServiceDialog(ServiceView serviceView) {
        this.serviceView = serviceView;
        this.service = serviceView.getService();
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);


        JTextComponent closeDateField = serviceView.getServiceDateField();
        technicianCombo = serviceView.getTechnicianCombo();
        technicianCombo.setSelectedItem(serviceView.getService().getTechnician());
        notesForNextTime = new JTextArea();

        airFilterChk = new JCheckBox("Air Filter");
        oilFilterChk = new JCheckBox("Oil Filter");
        transFilterChk = new JCheckBox("Trans Filter");
        fuelFilterChk = new JCheckBox("Fuel Filter");
        hydraulicFilterChk = new JCheckBox("Hydraulic Filter");

        closeBtn = new JButton("Close");
        closeBtn.addActionListener(new CloseActionListener(serviceView, this));
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new CancelActionListener(this));

        String svcDateStr = Service.SERVICE_DATE_FORMAT.format(service.getDate());
        closeDateField.setText(svcDateStr);

        PainlessGridBag gbl = new PainlessGridBag(this, false);
        gbl.row().cell(new JLabel("Close Date")).cell(closeDateField).fillX().cell(new JLabel("Mechanic")).cell(technicianCombo).fillX();
        gbl.row().cell(new JLabel("Notes for Next Service:")).fillX();
        gbl.row().cellX(notesForNextTime, 2).fillXY();
        gbl.row().cell(airFilterChk);
        gbl.row().cell(oilFilterChk);
        gbl.row().cell(transFilterChk);
        gbl.row().cell(fuelFilterChk);
        gbl.row().cell(hydraulicFilterChk);
        gbl.row().cell(closeBtn).cell(cancelBtn);
        gbl.doneAndPushEverythingToTop();
    }


    public int getStatus() {
        return status;
    }

    private void setStatus(int status) {
        this.status = status;
    }

    public JCheckBox getAirFilterChk() {
        return airFilterChk;
    }

    public JCheckBox getOilFilterChk() {
        return oilFilterChk;
    }

    public JCheckBox getTransFilterChk() {
        return transFilterChk;
    }

    public JCheckBox getFuelFilterChk() {
        return fuelFilterChk;
    }

    public JCheckBox getHydraulicFilterChk() {
        return hydraulicFilterChk;
    }

    public String getNotesForNextTime() {
        return notesForNextTime.getText();
    }

    public JComboBox getTechnicianCombo() {
        return technicianCombo;
    }

    private static class CancelActionListener implements ActionListener {

        private CloseServiceDialog closeServiceDialog;

        private CancelActionListener(CloseServiceDialog closeServiceDialog) {
            this.closeServiceDialog = closeServiceDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            closeServiceDialog.setStatus(STATUS_CANCELLED);
            closeServiceDialog.dispose();
        }
    }


    private static class CloseActionListener implements ActionListener {

        private ServiceView serviceView;
        private CloseServiceDialog closeServiceDialog;


        private CloseActionListener(ServiceView serviceView, CloseServiceDialog closeServiceDialog) {
            this.serviceView = serviceView;
            this.closeServiceDialog = closeServiceDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ServiceTableView serviceTableView = serviceView.getServiceTableView();
            Service service = serviceView.getService();
            Technician technician = (Technician) closeServiceDialog.getTechnicianCombo().getSelectedItem();
            if (technician == null) {
                JOptionPane.showMessageDialog(closeServiceDialog, "A mechanic must be selected in order to close ticket");
                return;
            } else if (serviceView.getServiceDateFieldValue() == null || serviceView.getServiceDateFieldValue().length() <= 0) {
                JOptionPane.showMessageDialog(closeServiceDialog, "A date for the service being closed is required");
                return;
            }

            Date closeDate;
            try {
                closeDate = Service.SERVICE_DATE_FORMAT.parse(serviceView.getServiceDateFieldValue());
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(closeServiceDialog, "There was an error parsing the Close Service Date. Please make sure it is formatted correctly.");
                e1.printStackTrace();
                return;
            }

            closeServiceDialog.setStatus(STATUS_CLOSED);


            service.setDate(closeDate);

            setFilterChangeDates(service);
            service.getEquipment().setLastServiceDate(service.getDate());
            ServiceController.getInstance().closeService(service);
            serviceTableView.removeServiceFromTable(service);
            String notesForNextTime = (closeServiceDialog.getNotesForNextTime() != null ? closeServiceDialog.getNotesForNextTime(): "");

            Service nextService = ServiceController.getInstance().generateNewService(service, notesForNextTime);
            if (ServiceController.getInstance().readyToDisplayService(nextService)) {
                serviceTableView.addServiceToTable(nextService);
            }
            Equipment equipment = service.getEquipment();
            EquipmentDao equipmentDao = new EquipmentDaoImpl();
            equipmentDao.save(equipment);
            closeServiceDialog.dispose();


        }

        private void setFilterChangeDates(Service service) {
            Equipment equipment = service.getEquipment();
            if (closeServiceDialog.getAirFilterChk().isSelected()) equipment.setAirFilterDate(service.getDate());
            if (closeServiceDialog.getOilFilterChk().isSelected()) equipment.setOilFilterDate(service.getDate());
            if (closeServiceDialog.getFuelFilterChk().isSelected()) equipment.setFuelFilterDate(service.getDate());
            if (closeServiceDialog.getHydraulicFilterChk().isSelected()) equipment.setHydraulicFilterDate(service.getDate());
            if (closeServiceDialog.getTransFilterChk().isSelected()) equipment.setTransFilterDate(service.getDate());

        }
    }






}
