package me.curiosus.pmmojo.persistence;

import me.curiosus.pmmojo.model.*;
import me.curiosus.pmmojo.model.factory.ModelFactory;
import me.curiosus.pmmojo.persistence.dao.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.List;

/**
 * jp
 * Date: Apr 30, 2011
 * Time: 1:48:12 PM
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static void save(Object obj) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
    }

    public static List retrieve(String query) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery(query);
        List list = q.list();
        transaction.commit();
        return list;
    }


    private static Configuration getInitializedConfiguration() {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(TechnicianImpl.class);
        configuration.addAnnotatedClass(MakeImpl.class);
        configuration.addAnnotatedClass(CustomerImpl.class);
        configuration.addAnnotatedClass(AreaImpl.class);
        configuration.addAnnotatedClass(EquipmentImpl.class);
        configuration.addAnnotatedClass(ServiceImpl.class);
        configuration.configure();
        return configuration;
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration configuration = getInitializedConfiguration();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }

    /**
     * Warning - drops the existing database
     */
    public static void recreateDatabase() {
        Configuration config = getInitializedConfiguration();
        new SchemaExport(config).create(true, true);
    }

    public static void populateWithTestData() {
        populateFakeTestAreas();
        populateFakeTestModels();
        populateFakeTechnicians();
        populateFakeCustomers();
        populateFakeEquipment();
    }

    public static void populateFakeTestAreas() {
        String [] areaNames = {"Constantinople", "Yemen", "Amsterdam", "Helsenki", "Tanania"};
        AreaDao areaDao = new AreaDaoImpl();
        for (String areaName : areaNames) {
            Area area = ModelFactory.createArea(areaName);
            areaDao.save(area);
        }
    }

    public static void populateFakeTestModels() {
        String [] modelNames = {"Farah Fawcett", "Cindy Crawford", "Heidi Klum", "Tyra Banks"};
        MakeDao makeDao = new MakeDaoImpl();
        for (String modelName : modelNames) {
            Make make = ModelFactory.createMake(modelName);
            makeDao.save(make);
        }
    }

    public static void populateFakeTechnicians() {
        String [] technicianNames = {"Richard Feynman", "Rudy Rucker", "Seth Lloyd", "Issac Newton"};
        TechnicianDao technicianDao = new TechnicianDaoImpl();
        for (String technicianName : technicianNames) {
            String [] names = technicianName.split("\\s");
            String firstName = names[0];
            String lastName = names[1];
            Technician technician = ModelFactory.createTechnician(firstName, lastName);
            technicianDao.save(technician);
        }
    }

    public static void populateFakeCustomers() {
        String [] customerNames = {"ACME", "IBM", "General Dynamics"};
        String [] address1 = {"1541 Yorkshire", "101 Downing Street", "4154 Jaylene Way"};
        String [] address2 = {"East Bay Garage", "Suite 14b", "Canal Road"};
        String [] cities = {"London", "Paris", "Moscow"};
        String [] states = {"Nevada", "New York", "Wyoming"};
        String [] postalCodes = {"10111", "84106", "84302"};
        String [] phone1 = {"801-918-4589", "555-555-5432", "101-965-1212"};
        String [] phone2 = {"801-918-4590", "555-555-5433", "101-965-1213"};
        String [] fax = {"801-918-4500", "555-555-5400", "101-965-1200"};
        String [] email = {"naomi@acme.com", "michelle@ibm.com", "faye@generaldynamics.com"};
        CustomerDao customerDao = new CustomerDaoImpl();
        for (int i = 0; i < customerNames.length; i++) {
            Customer customer = ModelFactory.createCustomer();
            customer.setName(customerNames[i]);
            customer.setAddress1(address1[i]);
            customer.setAddress2(address2[i]);
            customer.setCity(cities[i]);
            customer.setState(states[i]);
            customer.setPostalCode(postalCodes[i]);
            customer.setPhone1(phone1[i]);
            customer.setPhone2(phone2[i]);
            customer.setFax(fax[i]);
            customer.setEmail(email[i]);
            customerDao.save(customer);
        }
    }

    public static void populateFakeEquipment() {
       CustomerDao customerDao = new CustomerDaoImpl();
        MakeDao makeDao = new MakeDaoImpl();
        List<Customer> customers = customerDao.retrieve();
        List<Make> makes = makeDao.retrieve();
        String [] models = {"Java", "Python", "Lisp"};
        EquipmentDao equipmentDao = new EquipmentDaoImpl();
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            Make make = makes.get(i);
            String model = models[i];
            Equipment equipment = ModelFactory.createEquipment(make, model, customer);
            equipment.setSerialNumber("serial-" + i);
            equipmentDao.save(equipment);
        }
    }


    public static void main(String[] args) {
        recreateDatabase();
        populateWithTestData();
    }


}
