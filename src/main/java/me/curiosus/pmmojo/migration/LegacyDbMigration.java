package me.curiosus.pmmojo.migration;


import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import me.curiosus.pmmojo.model.*;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: jepeterson@gmail.com
 * Date: 12/5/12
 * Time: 7:08 PM
 */
public class LegacyDbMigration {

    public static final SimpleDateFormat LEGACY_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    private Database database;
    private Map<String, String> areaIdMap;
    private Map<String, String> mechIdMap;
    private Map<String, String> makeIdMap;
    private Map<String, String> customerIdMap;
    private Map<String, String> equipmentIdMap;
    private Map<String, String> serviceIdMap;

    private Map<String, Date> lastServiceDateMap;
    private Map<String, Date> transFilterDateMap;
    private Map<String, String> notesMap;
    private Map<String, Date> nextServiceDateMap;




    public void migrate() throws IOException, ParseException {
        database = Database.open(new File("db" + File.separator + "TEK.MDB"));
        migrateAreas();
        migrateMechs();
        migrateMakes();
        migrateCustomers();
        migrateEquipments();
        migrateServices();
        generateNextServices();
        database.close();
    }

    private void migrateAreas() throws IOException {
        areaIdMap = new HashMap<String, String>();
        Table areaTable = database.getTable("tblarea");
        AreaDao dao = new AreaDaoImpl();
        for (Map<String, Object> row : areaTable) {
            Object areaId = row.get("areaid");
            Object areaName = row.get("area");
            Area area = ModelFactory.createArea();
            areaIdMap.put(areaId.toString(), area.getId());
            area.setName(areaName.toString());
            dao.save(area);
        }
    }

    private void migrateMechs() throws IOException {
        mechIdMap = new HashMap<String, String>();
        Table mechTable = database.getTable("tblmech");
        TechnicianDao dao = new TechnicianDaoImpl();
        for (Map<String, Object> row : mechTable) {
            Object mechId = row.get("mechid");
            Object firstName = row.get("mechfirst");
            Object lastName = row.get("mechlast");
            Technician tech = ModelFactory.createTechnician();
            mechIdMap.put(mechId.toString(), tech.getId());
            tech.setFirstName(firstName.toString());
            tech.setLastName(lastName.toString());
            dao.save(tech);
        }
    }

    private void migrateMakes() throws IOException {
        makeIdMap = new HashMap<String, String>();
        Table maketable = database.getTable("tblmake");
        MakeDao dao = new MakeDaoImpl();
        for (Map<String, Object> row : maketable) {
            Object makeId = row.get("makeid");
            Object makeName = row.get("make");
            Make make = ModelFactory.createMake();
            makeIdMap.put(makeId.toString(), make.getId());
            make.setName(makeName.toString());
            dao.save(make);
        }
    }

    private void migrateCustomers() throws IOException {
        customerIdMap = new HashMap<String, String>();
        Table custTable = database.getTable("tblcompany");
        CustomerDao dao = new CustomerDaoImpl();
        for (Map<String, Object> row : custTable) {
            Object customerId = row.get("companyid");
            Object companyName = row.get("companyname");
            Object address1 = row.get("addr1");
            Object address2 = row.get("addr2");
            Object city = row.get("city");
            Object state = row.get("state");
            Object zip = row.get("zip");
            Object contact1 = row.get("contact1");
            Object contact2 = row.get("contact2");
            Object phone = row.get("phone");
            Object fax = row.get("fax");
            Object email = row.get("email");
            Object areaId = row.get("areaid");
            AreaDao areaDao = new AreaDaoImpl();
            Area area = areaDao.retrieveAreaById(areaIdMap.get(areaId.toString()));
            Customer customer = ModelFactory.createCustomer();
            customerIdMap.put(customerId.toString(), customer.getId());
            customer.setName(companyName.toString());
            customer.setAddress1(address1.toString());
            customer.setAddress2(address2.toString());
            customer.setCity(city.toString());
            customer.setState(state.toString());
            customer.setPostalCode(zip.toString());
            customer.setContact1(contact1.toString());
            customer.setContact2(contact2.toString());
            customer.setPhone1(phone.toString());
            customer.setFax(fax.toString());
            customer.setEmail(email.toString());
            customer.setArea(area);
            dao.save(customer);
        }
    }


    private void migrateEquipments() throws IOException, ParseException {
        nextServiceDateMap = new HashMap<String, Date>();
        equipmentIdMap = new HashMap<String, String>();
        Table table = database.getTable("tblequipment");
        EquipmentDao equipmentDao = new EquipmentDaoImpl();
        for (Map<String, Object> row : table) {

            Object equipId = row.get("equipid");
            Object companyId = row.get("companyid");
            Object makeId = row.get("makeid");
            Object model = row.get("model");
            Object serialNumber = row.get("serial");
            Object numDaysBetweenSvc = row.get("schedule");
            Object lastSvc = row.get("lastsvc");
            Object transFilter = row.get("transfilterdate");
            Object notes = row.get("notes");

            Object nextSvc = row.get("nextsvc");



            CustomerDao customerDao = new CustomerDaoImpl();
            Customer customer = customerDao.retrieveByCustomerId(customerIdMap.get(companyId.toString()));

            MakeDao makeDao = new MakeDaoImpl();
            Make make = makeDao.retrieveByMakeId(makeIdMap.get(makeId.toString()));

            Equipment equipment = ModelFactory.createEquipment();
            equipmentIdMap.put(equipId.toString(), equipment.getId());
            Date nextServiceDate = LEGACY_DATE_FORMAT.parse(nextSvc.toString());
            nextServiceDateMap.put(equipment.getId(), nextServiceDate);

            equipment.setCustomer(customer);

            equipment.setMake(make);
            equipment.setModel(model.toString());
            equipment.setServiceIntervalUnits(Service.SERVICE_INTERVAL_UNITS_DAYS);
            equipment.setServiceInterval((Integer) numDaysBetweenSvc);
            equipment.setSerialNumber(serialNumber.toString());
            equipment.setTransFilter(transFilter.toString());
            equipment.setEquipmentNotes(notes.toString());
            SimpleDateFormat serviceDateFormat = LEGACY_DATE_FORMAT;
            Date lastServiceDate = serviceDateFormat.parse(lastSvc.toString());
            equipment.setLastServiceDate(lastServiceDate);
            equipmentDao.save(equipment);

        }

    }

    private void migrateServices() throws IOException, ParseException {
        serviceIdMap = new HashMap<String, String>();
        Table table = database.getTable("tblsvc");
        ServiceDao dao = new ServiceDaoImpl();
        for (Map<String, Object> row : table) {
            Object svcid = row.get("svcid");
            Object equipid = row.get("equipid");
            Object companyid = row.get("companyid");
            Object svcdate = row.get("svcdate");
            Object mechid = row.get("mechid");

            EquipmentDao equipmentDao = new EquipmentDaoImpl();
            Equipment equipment = equipmentDao.retrieveByEquipmentId(equipmentIdMap.get(equipid.toString()));

            TechnicianDao technicianDao = new TechnicianDaoImpl();
            Technician technician = technicianDao.retrieveByTechnicianId(mechIdMap.get(mechid.toString()));

            CustomerDao customerDao = new CustomerDaoImpl();
            Customer customer = customerDao.retrieveByCustomerId(customerIdMap.get(companyid.toString()));

            SimpleDateFormat serviceDateFormat = LEGACY_DATE_FORMAT;
            Date serviceDate = serviceDateFormat.parse(svcdate.toString());

            Service service = ModelFactory.createService();
            serviceIdMap.put(svcid.toString(), service.getId());

            service.setEquipment(equipment);
            service.setCustomer(customer);
            service.setPerformed(true);
            service.setPrinted(true);
            service.setTechnician(technician);


            service.setDate(serviceDate);

            dao.save(service);

        }
    }

    private void generateNextServices() {
        EquipmentDaoImpl equipmentDao = new EquipmentDaoImpl();
        ServiceDao serviceDao = new ServiceDaoImpl();
        List<Equipment> equipments = equipmentDao.retrieve();
        for (Equipment equipment : equipments) {
            Service svc = serviceDao.getOpenServiceByEquipmentId(equipment);
            if (svc == null) {
                ServiceImpl service = (ServiceImpl) ModelFactory.createService();
                service.setPerformed(false);
                service.setPrinted(false);
                Date nextServiceDate = nextServiceDateMap.get(equipment.getId());
                service.setEquipment(equipment);
                service.setEnabled(true);
                service.setDate(nextServiceDate);
                serviceDao.save(service);
            } else {
                JOptionPane.showMessageDialog(null, "WTF?");
            }
        }
    }










    public static void main(String[] args) throws IOException, ParseException {
        new LegacyDbMigration().migrate();
    }


}
