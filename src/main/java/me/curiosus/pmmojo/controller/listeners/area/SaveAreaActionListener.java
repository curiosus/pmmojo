package me.curiosus.pmmojo.controller.listeners.area;

import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.persistence.dao.AreaDao;
import me.curiosus.pmmojo.persistence.dao.AreaDaoImpl;
import me.curiosus.pmmojo.view.AreaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: jepeterson@gmail.com
 * Date: Jul 28, 2011
 * Time: 6:11:40 PM
 */
public class SaveAreaActionListener  implements ActionListener {

   private AreaView view;


   public SaveAreaActionListener(AreaView view) {
       this.view = view;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        Area area = view.getArea();
        area.setName(view.getNameFieldValue());
        AreaDao areaDao = new AreaDaoImpl();
        areaDao.save(area);
        view.setDirty(false);
        view.dispose();
    }
}
