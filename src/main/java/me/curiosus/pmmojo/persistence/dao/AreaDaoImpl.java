package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.persistence.HibernateUtils;

import java.util.List;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:25:31 PM
 */
public class AreaDaoImpl implements AreaDao {

    @Override
    public void save(Area area) {
        HibernateUtils.save(area);
    }

    @Override
    public List<Area> retrieve() {
        return HibernateUtils.retrieve("from AreaImpl");
    }

    @Override
    public Area retrieveAreaById(String areaId) {
        List<Area> list = HibernateUtils.retrieve("from AreaImpl where id = '" + areaId + "'");
        return list.get(0);
    }
}
