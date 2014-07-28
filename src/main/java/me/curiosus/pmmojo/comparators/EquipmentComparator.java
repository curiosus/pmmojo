package me.curiosus.pmmojo.comparators;

import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;

import java.util.Comparator;

/**
 * User: jepeterson@gmail.com
 * Date: 2/7/13
 * Time: 6:32 AM
 */
public class EquipmentComparator implements Comparator<Equipment> {

    @Override
    public int compare(Equipment e1, Equipment e2) {
        Customer c1 = e1.getCustomer();
        Customer c2 = e2.getCustomer();
        String name1 = c1.getName().toLowerCase();
        String name2 = c2.getName().toLowerCase();
        return name1.compareTo(name2);
    }
}
