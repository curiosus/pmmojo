package me.curiosus.pmmojo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: jpeterson
 * Date: Sep 15, 2011
 * Time: 6:41:50 AM
 */

@Entity
public class ServiceImpl implements Service {


    private String id;
    private Equipment equipment;
    private Date date;
    private Boolean performed;
    private Technician technician;
    private String notesForNextService;
    private Boolean printed;
    private Date lastServiceDate;
    private Customer customer;




    @Override
    @Id
    public String getId() {
        return id;
    }

    @Override
    @Transient
    public String getDisplayName() {
        return "Service";
    }

    /**
     * This is not part of the Interface API
     * It is just used by hibernate and factories.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    @ManyToOne
    @JoinColumn(name="equipment_id")
    public EquipmentImpl getEquipment() {
        return (EquipmentImpl) equipment;
    }

    @Override
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    @ManyToOne
    @JoinColumn(name="technician_id")
    public TechnicianImpl getTechnician() {
        return (TechnicianImpl) technician;
    }

    @Override
    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Boolean isPerformed() {
        return performed;
    }

    @Override
    public void setPerformed(Boolean performed) {
        this.performed = performed;
    }

    @Override
    public void setNotesForNextService(String notes) {
        notesForNextService = notes;
    }

    @Override
    public String getNotesForNextService() {
        return notesForNextService;
    }

    @Override
    public void setPrinted(Boolean printed) {
        this.printed = printed;
    }

    @Override
    public Boolean isPrinted() {
        return printed;
    }

    @Override
    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    @Override
    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    @Override
    public boolean isEnabled() {
        return isPerformed();
    }

    @Override
    public void setEnabled(boolean enabled) {
        //interface requires this method but it is not needed in this Class
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
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Service)) return false;
        Service e = (Service) o;
        return e.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
