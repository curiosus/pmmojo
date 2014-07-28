package me.curiosus.pmmojo.comparators;

import me.curiosus.pmmojo.model.Service;

import java.util.Comparator;

/**
 * User: jepeterson@gmail.com
 * Date: 2/9/13
 * Time: 7:41 AM
 */
public class ServiceComparator implements Comparator<Service> {


    @Override
    public int compare(Service s1, Service s2) {
        return s1.getDate().compareTo(s2.getDate());
    }
}
