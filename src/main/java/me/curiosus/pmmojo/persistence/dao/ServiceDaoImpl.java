package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.comparators.ServiceComparator;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.persistence.HibernateUtils;

import java.util.Collections;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Jan 17, 2012
 * Time: 7:44:17 PM
 */
public class ServiceDaoImpl implements ServiceDao {

    @Override
    public List<Service> getServicesByEquipmentId(Equipment equipment) {
        return HibernateUtils.retrieve("from ServiceImpl where equipment_id = '" + equipment.getId() + "'");
    }

    @Override
    public Service getOpenServiceByEquipmentId(Equipment equipment) {
        List<Service> serviceList = HibernateUtils.retrieve("from ServiceImpl where equipment_id = '" + equipment.getId() + "' and performed != true");
        return serviceList.size() > 0 ? serviceList.get(0): null;
    }


    @Override
    public void save(Service service) {
        HibernateUtils.save(service);
    }

    @Override
    public List<Service> getOpenServices() {
        return HibernateUtils.retrieve("from ServiceImpl where performed != true");
    }

    @Override
    public List<Service> getLiveServices() {
        List<Service> serviceList = getOpenServices();
        Collections.sort(serviceList, new ServiceComparator());
        return serviceList;
    }


}
