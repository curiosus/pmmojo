package me.curiosus.pmmojo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * jp
 * Date: Apr 16, 2011
 * Time: 12:07:37 PM
 */

@Entity
public class TechnicianImpl implements Technician {



    private String id;
    private boolean enabled;
    private String firstName;
    private String lastName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    @Transient
    public String getFullName() {
        StringBuilder sb = new StringBuilder(firstName).append(" ").append(lastName);
        return sb.toString();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    @Transient
    public String getDisplayName() {
        return "Mechanic";
    }

    /**
     * This method is for use by persistance. It is not part of the ModelItem Interface
     * because it's not intended to be part of the API. There should never be any reason why
     * this needs to be called by application code.
     * @param id Unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Technician)) return false;
        Technician t = (Technician) o;
        return t.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
