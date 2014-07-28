package me.curiosus.pmmojo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: jpeterson
 * Date: Aug 20, 2011
 * Time: 1:01:10 PM
 */

@Entity
public class EquipmentImpl implements Equipment {

    private String id;
    private boolean enabled;
    private Make make;
    private String model;
    private Customer customer;
    private String serviceIntervalUnits;
    private Integer serviceInterval;
    private String serialNumber;
    private String transFilter;
    private String equipmentNotes;
    private Date lastServiceDate;

    private Date airFilterDate;
    private Date oilFilterDate;
    private Date fuelFilterDate;
    private Date hydraulicFilterDate;
    private Date transFilterDate;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    @Transient
    public String getDisplayName() {
        return "Equipment";
    }

    @Override
    @ManyToOne
    @JoinColumn(name="make_id")
    public MakeImpl getMake() {
        return (MakeImpl) make;
    }

    @Override
    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    @ManyToOne
    @JoinColumn(name="customer_id")
    public CustomerImpl getCustomer() {
        return (CustomerImpl) customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getServiceIntervalUnits() {
        return serviceIntervalUnits;
    }

    @Override
    public void setServiceIntervalUnits(String serviceIntervalUnits) {
        this.serviceIntervalUnits = serviceIntervalUnits;
    }

    @Override
    public Integer getServiceInterval() {
        return serviceInterval;
    }

    @Override
    public void setServiceInterval(Integer serviceInterval) {
        this.serviceInterval = serviceInterval;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String getTransFilter() {
        return transFilter;
    }

    @Override
    public void setTransFilter(String transFilter) {
        this.transFilter = transFilter;
    }

    @Override
    @Lob
    public String getEquipmentNotes() {
        return equipmentNotes;
    }

    @Override
    public void setEquipmentNotes(String equipmentNotes) {
        this.equipmentNotes = equipmentNotes;
    }

    @Override
    public Date getAirFilterDate() {
        return airFilterDate;
    }

    @Override
    public void setAirFilterDate(Date airFilterDate) {
        this.airFilterDate = airFilterDate;
    }

    @Override
    public Date getOilFilterDate() {
        return oilFilterDate;
    }

    @Override
    public void setOilFilterDate(Date oilFilterDate) {
        this.oilFilterDate = oilFilterDate;
    }

    @Override
    public Date getFuelFilterDate() {
        return fuelFilterDate;
    }

    @Override
    public void setFuelFilterDate(Date fuelFilterDate) {
        this.fuelFilterDate = fuelFilterDate;
    }

    @Override
    public Date getHydraulicFilterDate() {
        return hydraulicFilterDate;
    }

    @Override
    public void setHydraulicFilterDate(Date hydraulicFilterDate) {
        this.hydraulicFilterDate = hydraulicFilterDate;
    }

    @Override
    public Date getTransFilterDate() {
        return transFilterDate;
    }

    @Override
    public void setTransFilterDate(Date transFilterDate) {
        this.transFilterDate = transFilterDate;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    @Override
    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }



    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Equipment)) return false;
        Equipment e = (Equipment) o;
        return e.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
