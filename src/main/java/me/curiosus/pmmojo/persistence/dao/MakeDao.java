package me.curiosus.pmmojo.persistence.dao;

import me.curiosus.pmmojo.model.Make;

import java.util.List;

/**
 * jp
 * Date: May 21, 2011
 * Time: 12:21:40 PM
 */
public interface MakeDao {

    void save(Make make);
    List<Make> retrieve();
    Make retrieveByMakeId(String makeId);
}
