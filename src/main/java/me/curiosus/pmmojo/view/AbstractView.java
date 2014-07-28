package me.curiosus.pmmojo.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * jp
 * Date: May 9, 2011
 * Time: 4:52:07 PM
 */
public abstract class AbstractView extends JDialog {



    protected JButton saveBtn;
    protected boolean dirty;
    

    public void addSaveButtonActionListener(ActionListener listener) {
        saveBtn.addActionListener(listener);
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }


    @Override
    public void dispose() {
        if (dirty) {
            String msg = "You are about to close this view with unsaved changes";
            String title = "Wait, think about this";
            int answer = JOptionPane.showConfirmDialog(this, msg, title, JOptionPane.OK_CANCEL_OPTION);
            if (answer == JOptionPane.CANCEL_OPTION) return;
        }
        super.dispose();
    }

    protected abstract void addDirtyListeners();


}
