package me.curiosus.pmmojo.controller;

import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.model.ServiceImpl;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.EquipmentDaoImpl;
import me.curiosus.pmmojo.persistence.dao.ServiceDao;
import me.curiosus.pmmojo.persistence.dao.ServiceDaoImpl;
import org.joda.time.DateTime;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Oct 1, 2012
 * Time: 8:44:40 PM
 */
public class ServiceController {

    private static final int DAYS_IN_FUTURE = 10000;
    private static ServiceController instance = new ServiceController();

    private ServiceController() {}

    public static ServiceController getInstance() {
        return instance;
    }

    public synchronized void closeService(Service service) {
        service.setCustomer(service.getEquipment().getCustomer());
        service.setPerformed(true);
        ServiceDao dao = new ServiceDaoImpl();
        dao.save(service);
    }


    public synchronized Service generateNewService(Service closedService, String notesForNextTime) {
        Service nextService = ModelFactory.createService();
        nextService.setEquipment(closedService.getEquipment());
        nextService.setPerformed(false);
        nextService.setPrinted(false);
        nextService.setNotesForNextService(notesForNextTime);
        adjustDate(nextService, closedService.getDate());
        nextService.setLastServiceDate(closedService.getDate());
        ServiceDao dao = new ServiceDaoImpl();
        dao.save(nextService);
        return nextService;
    }

    private void adjustDate(Service service, Date dateOfLastService) {
        Equipment equipment = service.getEquipment();
        String serviceIntervalUnits = equipment.getServiceIntervalUnits();
        Integer serviceInterval = equipment.getServiceInterval();
        DateTime dt = new DateTime(dateOfLastService);
        DateTime dateTimeNextService;
        if (serviceIntervalUnits.equals(Service.SERVICE_INTERVAL_UNITS_DAYS)) {
            dateTimeNextService = dt.plusDays(serviceInterval.intValue());
        } else if (serviceIntervalUnits.equals(Service.SERVICE_INTERVAL_UNITS_WEEKS)) {
            dateTimeNextService = dt.plusWeeks(serviceInterval.intValue());
        } else if (serviceIntervalUnits.equals(Service.SERVICE_INTERVAL_UNITS_MONTHS)) {
            dateTimeNextService = dt.plusMonths(serviceInterval.intValue());
        } else if (serviceIntervalUnits.equals(Service.SERVICE_INTERVAL_UNITS_YEARS)) {
            dateTimeNextService = dt.plusYears(serviceInterval.intValue());
        } else {
            dateTimeNextService = dt.plusDays(serviceInterval.intValue());
        }
        Date nextServiceDate = dateTimeNextService.toDate();
        service.setDate(nextServiceDate);
    }

    public synchronized void saveService(Service service) {
        ServiceDao serviceDao = new ServiceDaoImpl();
        serviceDao.save(service);
    }

    public synchronized void initEquipmentServiceTickets() {
        EquipmentDaoImpl dao = new EquipmentDaoImpl();
        ServiceDao serviceDao = new ServiceDaoImpl();
        List<Equipment> equipments = dao.retrieve();
        for (Equipment equipment : equipments) {
            Service svc = serviceDao.getOpenServiceByEquipmentId(equipment);
            //ideally the svc will never be null at this point.
            if (svc == null) {
                ServiceImpl service = (ServiceImpl) ModelFactory.createService();
                service.setPerformed(false);
                service.setPrinted(false);
                equipment.setServiceInterval(Service.DEFAULT_SERVICE_INTERVAL);
                equipment.setServiceIntervalUnits(Service.DEFAULT_SERVICE_INTERVAL_UNITS);
                service.setEquipment(equipment);
                service.setEnabled(true);
                DateTime dt = new DateTime(new Date()).plusDays(Service.DEFAULT_SERVICE_INTERVAL);
                service.setDate(dt.toDate());
                serviceDao.save(service);
                JOptionPane.showMessageDialog(null, "W.T.F. ? Why did we need to create a service for equip " + equipment.getId());
            }
        }
    }

    public synchronized void printService(Service service) {
        service.setPrinted(true);
        saveService(service);
        ServicePrinter servicePrinter = new ServicePrinter(service);
        servicePrinter.print();
    }

    //TODO move this to ServiceDAO for performance reasons
    public synchronized List<Service> getLiveServices() {
        ServiceDao dao = new ServiceDaoImpl();
        List<Service> services = dao.getLiveServices();
        List<Service> liveServices = new ArrayList<Service>();
        for (Service service : services) {
            if (readyToDisplayService(service)) {
                liveServices.add(service);
            }
        }
        return liveServices;
    }

    public boolean readyToDisplayService(Service service) {
        DateTime today = new DateTime(new Date());
        DateTime liveDate = today.plusDays(DAYS_IN_FUTURE);
        DateTime serviceDateTime = new DateTime(service.getDate());
        return (serviceDateTime.isBefore(liveDate.getMillis()) && !service.isPerformed());
    }



}
