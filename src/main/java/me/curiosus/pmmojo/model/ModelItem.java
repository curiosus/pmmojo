package me.curiosus.pmmojo.model;

/**
 * jp
 * Date: May 21, 2011
 * Time: 11:24:59 AM
 */
public interface ModelItem  {
    /**
     * Enabled is a boolean property of ModelItem used instead of deleting ModelItems, generally this will mean
     * they are no longer visible or usable in the UI, although they may still be visible in historical data.
     * @return boolean whether or not the ModelItem is enabled
     */
    boolean isEnabled();

    /**
     * Enabled is a boolean property of ModelItem used instead of deleting ModelItems, generally this will mean
     * they are no longer visible or usable in the UI, although they may still be visible in historical data.
     * @param enabled boolean, set the ModelItem as enabled or not.
     */
    void setEnabled(boolean enabled);

    /**
     * Unique identifier for ModelItem in system.
     * This id is generated when obtaining a new object from a factory or populated
     * from a persistence mechanisim. It should never need to be set manually
     * and therefore setId is not part of the interface.
     * This is a String generated from a UUID because I've had so much trouble with database support for UUID
     * @return String unique id for item ModelItem in the system
     */
    String getId();

    String getDisplayName();
}
