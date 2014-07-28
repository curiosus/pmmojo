package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.persistence.HibernateUtils;

import java.util.List;

/**
 * jp
 * Date: May 21, 2011
 * Time: 10:18:11 PM
 */
public class MakeDaoImpl implements MakeDao {
    
    @Override
    public void save(Make make) {
        HibernateUtils.save(make);
    }

    @Override
    public List<Make> retrieve() {
        return HibernateUtils.retrieve("from MakeImpl");
    }

    @Override
    public Make retrieveByMakeId(String makeId) {
        List<Make> makes =  HibernateUtils.retrieve("from MakeImpl where id = '" + makeId + "'");
        return makes.get(0);
    }
}
