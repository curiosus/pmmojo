package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Area;

import java.util.List;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:24:33 PM
 */
public interface AreaDao {

    void save(Area area);
    List<Area> retrieve();
    Area retrieveAreaById(String areaId);
}
