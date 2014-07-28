package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Technician;

import java.util.List;

/**
 * jp
 * Date: Apr 30, 2011
 * Time: 11:56:39 AM
 */
public interface TechnicianDao {
    
    void save(Technician technician);
    List<Technician> retrieve();
    Technician retrieveByTechnicianId(String technicianId);

}
