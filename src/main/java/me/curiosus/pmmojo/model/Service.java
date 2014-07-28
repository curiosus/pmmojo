package me.curiosus.pmmojo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: Sep 15, 2011
 * Time: 6:40:48 AM
 */


public interface Service extends ModelItem {

    static final String SERVICE_INTERVAL_UNITS_DAYS = "Days";
    static final String SERVICE_INTERVAL_UNITS_WEEKS = "Weeks";
    static final String SERVICE_INTERVAL_UNITS_MONTHS = "Months";
    static final String SERVICE_INTERVAL_UNITS_YEARS = "Years";
    static final String DEFAULT_SERVICE_INTERVAL_UNITS = SERVICE_INTERVAL_UNITS_DAYS;
    static final int DEFAULT_SERVICE_INTERVAL = 14;

    static final SimpleDateFormat SERVICE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");


    Equipment getEquipment();
    void setEquipment(Equipment equipment);
    Technician getTechnician();
    void setTechnician(Technician technician);

    /**
     * Date is either the 'scheduled' date (when performed == false) or the performed date
     * Once the performed date is set it should not be able to change.
     * @return
     */
    Date getDate();
    void setDate(Date date);

    /**
     * If true then the service has been performed and the date
     * is the date of the performance. If false then the svc is scheduled
     * for the date.
     * @return
     */
    Boolean isPerformed();

    void setPerformed(Boolean performed);

    void setNotesForNextService(String notes);

    String getNotesForNextService();

    void setPrinted(Boolean printed);

    Boolean isPrinted();

    Date getLastServiceDate();

    void setLastServiceDate(Date lastServiceDate);

    Customer getCustomer();

    /**
     * Customer is generally only set when service is closed. Remember equipments can change customers.
     * @param customer
     */
    void setCustomer(Customer customer);


}
