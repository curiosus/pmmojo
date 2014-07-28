package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Service;

import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Jan 17, 2012
 * Time: 6:53:33 PM
 */
public interface ServiceDao {

    List<Service> getServicesByEquipmentId(Equipment equipment);
    Service getOpenServiceByEquipmentId(Equipment equipment);
    void save(Service service);
    List<Service> getOpenServices();
    List<Service> getLiveServices();

}
