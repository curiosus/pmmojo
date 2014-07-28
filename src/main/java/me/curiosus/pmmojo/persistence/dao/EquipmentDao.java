package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;

import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Aug 20, 2011
 * Time: 1:15:35 PM
 */
public interface EquipmentDao {
    void save(Equipment equipment);
    List<Equipment> retrieve();
    Equipment retrieveByEquipmentId(String equipmentId);
    List<Equipment> retrieveByCustomer(Customer customer);
}
