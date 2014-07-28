package me.curiosus.pmmojo.controller.listeners.equipment;

import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.EquipmentDao;
import me.curiosus.pmmojo.persistence.dao.EquipmentDaoImpl;
import me.curiosus.pmmojo.persistence.dao.ServiceDao;
import me.curiosus.pmmojo.persistence.dao.ServiceDaoImpl;
import me.curiosus.pmmojo.view.EquipmentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 5, 2011
 * Time: 2:40:02 PM
 */
public class SaveEquipmentActionListener implements ActionListener {

    private EquipmentView equipmentView;

    public SaveEquipmentActionListener(EquipmentView equipmentView) {
        this.equipmentView = equipmentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (equipmentView.getCustomerFieldValue() == null) {
            JOptionPane.showMessageDialog(equipmentView, "Customer is Required");
            return;
        } else if (equipmentView.getMakeFieldValue() == null) {
            JOptionPane.showMessageDialog(equipmentView, "Make is Required");
            return;
        } else if (equipmentView.getSerialNumberFieldValue() == null || equipmentView.getSerialNumberFieldValue().length() <= 0) {
            JOptionPane.showMessageDialog(equipmentView, "Serial Number Required");
            return;
        } else if (equipmentView.getServiceInterval() == null || equipmentView.getServiceInterval() < 1) {
            JOptionPane.showMessageDialog(equipmentView, "Service Interval Required");
            return;
        } else if (equipmentView.getServiceIntervalUnitOfMeasure() == null || equipmentView.getServiceIntervalUnitOfMeasure().length() == 0) {
            JOptionPane.showMessageDialog(equipmentView, "Service Interval U.O.M. Required");
            return;
        } else if (equipmentView.getServiceDateFieldValue() == null || equipmentView.getServiceIntervalUnitOfMeasure().length() == 0) {
            JOptionPane.showMessageDialog(equipmentView, "Next Service Date Required");
            return;
        }


        Equipment equipment = equipmentView.getEquipment();
        equipment.setMake(equipmentView.getMakeFieldValue());
        equipment.setModel(equipmentView.getModelFieldValue());
        equipment.setCustomer(equipmentView.getCustomerFieldValue());
        equipment.setServiceInterval(equipmentView.getServiceInterval());
        equipment.setServiceIntervalUnits(equipmentView.getServiceIntervalUnitOfMeasure());
        equipment.setSerialNumber(equipmentView.getSerialNumberFieldValue());
        equipment.setTransFilter(equipmentView.getTransFilterDateFieldValue());
        equipment.setEquipmentNotes(equipmentView.getEquipmentNotesFieldValue());

        EquipmentDao equipmentDao = new EquipmentDaoImpl();


        SimpleDateFormat simpleDateFormat = Service.SERVICE_DATE_FORMAT;
        String nxtSvcDateStr = equipmentView.getServiceDateFieldValue();

        try {
            Date nextSvcDate = simpleDateFormat.parse(nxtSvcDateStr);
            ServiceDao dao = new ServiceDaoImpl();
            Service service;
            if (equipmentView.getAddOrEdit() == EquipmentView.EDIT_EQUIPMENT) {
                service = dao.getOpenServiceByEquipmentId(equipment);
                equipmentDao.save(equipment);
            } else if (equipmentView.getAddOrEdit() == EquipmentView.ADD_EQUIPMENT) {
                equipmentDao.save(equipment);
                service = ModelFactory.createService();
                service.setEquipment(equipment);
            } else {
                JOptionPane.showMessageDialog(equipmentView, "Ambiguous Add or Edit State");
                throw new IllegalStateException("Add or Edit State is Ambiguous");
            }

            service.setDate(nextSvcDate);
            dao.save(service);


        } catch (ParseException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(equipmentView, "There was a problem changing the next service date. Make sure the date is formatted correctly");
        }


        equipmentView.setDirty(false);
        equipmentView.dispose();
    }
}


