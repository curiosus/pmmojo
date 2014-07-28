package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.persistence.HibernateUtils;
import me.curiosus.pmmojo.comparators.EquipmentComparator;

import java.util.Collections;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Aug 20, 2011
 * Time: 1:17:44 PM
 */
public class EquipmentDaoImpl implements EquipmentDao {
    @Override
    public void save(Equipment equipment) {
        HibernateUtils.save(equipment);
    }

    @Override
    public List<Equipment> retrieve() {
        List<Equipment> equipmentList = HibernateUtils.retrieve("from EquipmentImpl");
        Collections.sort(equipmentList, new EquipmentComparator());
        return equipmentList;
    }

    @Override
    public Equipment retrieveByEquipmentId(String equipmentId) {
        List<Equipment> equipments = HibernateUtils.retrieve("from EquipmentImpl where id = '" + equipmentId + "'");
        return equipments.get(0);
    }

    @Override
    public List<Equipment> retrieveByCustomer(Customer customer) {
        List<Equipment> equipmentList = HibernateUtils.retrieve("from EquipmentImpl where customer_id = '" + customer.getId() + "'");
        return equipmentList;
    }
}
