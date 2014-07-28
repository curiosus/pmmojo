package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Technician;
import me.curiosus.pmmojo.persistence.HibernateUtils;

import java.util.List;

/**
 * jp
 * Date: Apr 30, 2011
 * Time: 12:00:57 PM
 */
public class TechnicianDaoImpl implements TechnicianDao {

    @Override
    public void save(Technician technician) {
        HibernateUtils.save(technician);
    }

    @Override
    public List<Technician> retrieve() {
        return HibernateUtils.retrieve("from TechnicianImpl");
    }

    @Override
    public Technician retrieveByTechnicianId(String technicianId) {
        List<Technician> technicians = HibernateUtils.retrieve("from TechnicianImpl where id = '" + technicianId + "'");
        return technicians.get(0);
    }
}
