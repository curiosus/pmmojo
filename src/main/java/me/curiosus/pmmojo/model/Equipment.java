package me.curiosus.pmmojo.model;

import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: Aug 20, 2011
 * Time: 10:43:16 AM
 */
public interface Equipment extends ModelItem {
    Make getMake();
    void setMake(Make make);
    String getModel();
    void setModel(String model);
    Customer getCustomer();
    void setCustomer(Customer customer);
    String getServiceIntervalUnits();
    void setServiceIntervalUnits(String serviceIntervalUnits);
    Integer getServiceInterval();
    void setServiceInterval(Integer serviceInterval);
    String getSerialNumber();
    void setSerialNumber(String serialNumber);
    Date getLastServiceDate();
    void setLastServiceDate(Date lastServiceDate);
    String getTransFilter();
    void setTransFilter(String transFilter);
    String getEquipmentNotes();
    void setEquipmentNotes(String equipmentNotes);

    Date getAirFilterDate();
    void setAirFilterDate(Date airFilterDate);

    Date getOilFilterDate();
    void setOilFilterDate(Date oilFilterDate);

    Date getFuelFilterDate();
    void setFuelFilterDate(Date fuelFilterDate);

    Date getHydraulicFilterDate();
    void setHydraulicFilterDate(Date hydraulicFilterDate);


    Date getTransFilterDate();
    void setTransFilterDate(Date transFilterDater);

}
