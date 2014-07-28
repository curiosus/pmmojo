package me.curiosus.pmmojo.model;

import javax.persistence.Id;

/**
 * jp
 * Date: May 21, 2011
 * Time: 11:57:44 AM
 */
public abstract class AbstractModelItem implements ModelItem {

    protected String id;
    protected boolean enabled;


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


}
