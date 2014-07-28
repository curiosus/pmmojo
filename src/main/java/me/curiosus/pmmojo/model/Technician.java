package me.curiosus.pmmojo.model;

/**
 * jp
 * Date: Apr 16, 2011
 * Time: 11:44:17 AM
 */
public interface Technician extends ModelItem {

    /**
     * First name of Technician
     * @return String firstName
     */
    String getFirstName();

    /**
     * Populate the first name of a Technician
     * @param firstName
     */
    void setFirstName(String firstName);

    /**
     * Last name of Technician
     * @return String lastName
     */
    String getLastName();


    /**
     * Populate the last name of a Technician
     * @param lastName
     */
    void setLastName(String lastName);

    /**
     * Full name of Technician
     */

    String getFullName();

}
