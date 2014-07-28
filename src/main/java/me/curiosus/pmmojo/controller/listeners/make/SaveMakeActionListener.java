package me.curiosus.pmmojo.controller.listeners.make;

import me.curiosus.pmmojo.model.Make;
import me.curiosus.pmmojo.persistence.dao.MakeDao;
import me.curiosus.pmmojo.persistence.dao.MakeDaoImpl;
import me.curiosus.pmmojo.view.MakeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: May 24, 2011
 * Time: 6:38:03 PM
 */
public class SaveMakeActionListener implements ActionListener {

    private MakeView makeView;

    public SaveMakeActionListener(MakeView makeView) {
        this.makeView = makeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Make make = makeView.getMake();
        make.setName(makeView.getNameFieldValue());
        MakeDao makeDao = new MakeDaoImpl();
        makeDao.save(make);
        makeView.setDirty(false);
        makeView.dispose();
    }
}



