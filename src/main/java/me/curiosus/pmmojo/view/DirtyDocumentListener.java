package me.curiosus.pmmojo.view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * jp
 * Date: May 7, 2011
 * Time: 2:50:29 PM
 */
public class DirtyDocumentListener implements DocumentListener {

    private AbstractView view;

    public DirtyDocumentListener(AbstractView view) {
        this.view = view;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        view.setDirty(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        view.setDirty(true);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        view.setDirty(true);
    }
}
