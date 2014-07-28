package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.ModelItem;

import java.awt.event.MouseAdapter;

/**
 * User: jepeterson@gmail.com
 * Date: 11/25/12
 * Time: 12:08 PM
 */
public interface TableView {

    public void setTableViewEditListener(MouseAdapter mouseListener);

    public void setTableRowRightClickListener(MouseAdapter mouseListener);

    public ModelItem getSelectedItem();
}
