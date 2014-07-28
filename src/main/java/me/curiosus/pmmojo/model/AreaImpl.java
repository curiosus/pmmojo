package me.curiosus.pmmojo.model;

import javax.persistence.*;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:12:07 PM
 */

@Entity
public class AreaImpl implements Area {

    private String id;
    private boolean enabled;
    private String name;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    @Transient
    public String getDisplayName() {
        return "Area";
    }

    /**
     * This method is for use by persistance. It is not part of the ModelItem Interface
     * because it's not intended to be part of the API. There should never be any reason why
     * this needs to be called by application code.
     *
     * @param id Unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Area)) return false;
        Area a = (Area) o;
        return a.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



