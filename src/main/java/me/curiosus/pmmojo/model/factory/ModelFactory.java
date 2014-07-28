package me.curiosus.pmmojo.model.factory;

import me.curiosus.pmmojo.model.*;

import java.util.UUID;

/**
 * jp
 * Date: Apr 30, 2011
 * Time: 3:45:51 PM
 */
public class ModelFactory {

    
    public static Technician createTechnician() {
        TechnicianImpl technician = new TechnicianImpl();
        technician.setId(UUID.randomUUID().toString());
        technician.setEnabled(true);
        return technician;
    }

    public static Technician createTechnician(String firstName, String lastName) {
        Technician technician = createTechnician();
        technician.setFirstName(firstName);
        technician.setLastName((lastName));
        return technician;
    }

    public static Area createArea() {
        AreaImpl area = new AreaImpl();
        area.setId(UUID.randomUUID().toString());
        area.setEnabled(true);
        return area;
    }

    public static Area createArea(String name) {
        Area area = createArea();
        area.setName(name);
        return area;
    }

    public static Make createMake() {
        MakeImpl make = new MakeImpl();
        make.setId(UUID.randomUUID().toString());
        make.setEnabled(true);
        return make;
    }

    public static Make createMake(String name) {
        Make make = createMake();
        make.setName(name);
        return make;
    }

    public static Customer createCustomer() {
        CustomerImpl customer = new CustomerImpl();
        customer.setId(UUID.randomUUID().toString());
        customer.setEnabled(true);
        return customer;
    }

    public static Customer createCustomer(String name, String address1, String address2, String city, String state,
                                          String postalCode, String phone1, String phone2, String fax,
                                          String email) {
        Customer customer = createCustomer();
        customer.setName(name);
        customer.setAddress1(address1);
        customer.setAddress2(address2);
        customer.setCity(city);
        customer.setState(state);
        customer.setPostalCode(postalCode);
        customer.setPhone1(phone1);
        customer.setPhone2(phone2);
        customer.setFax(fax);
        customer.setEmail(email);
        return customer;
    }

    public static Equipment createEquipment() {
        EquipmentImpl equipment = new EquipmentImpl();
        equipment.setId(UUID.randomUUID().toString());
        equipment.setEnabled(true);
        equipment.setServiceIntervalUnits(Service.SERVICE_INTERVAL_UNITS_DAYS);
        equipment.setServiceInterval(0);
        return equipment;
    }


    public static Equipment createEquipment(Make make, String model, Customer customer) {
        Equipment equipment = createEquipment();
        equipment.setMake(make);
        equipment.setModel(model);
        equipment.setCustomer(customer);
        return equipment;
    }

    public static Service createService() {
        ServiceImpl service = new ServiceImpl();
        service.setId(UUID.randomUUID().toString());
        service.setPrinted(false);
        service.setPerformed(false);
        return service;
    }

    
}
