package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Area;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * jp
 * Date: Jun 23, 2011
 * Time: 6:42:13 PM
 */
public class AreaView extends AbstractView {

    public static final Dimension SCREEN_SIZE = new Dimension(440, 110);
    public static final Point SCREEN_POSITION = new Point(100, 300);

    private Area area;
    private JTextComponent nameField;

    public AreaView(Area area) {
        this.area = area;
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add Area");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);
        JLabel nameLbl = new JLabel("Name:");
        nameField = new JTextField(50);
        nameField.setText(area.getName());
        saveBtn = new JButton("Save");
        addDirtyListeners();
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        gbl.row().cell(nameLbl).cell(nameField).fillX();
        gbl.row().cell(saveBtn);
        gbl.doneAndPushEverythingToTop();
    }

    public Area getArea() {
        return area;
    }

    public String getNameFieldValue() {
        return nameField.getText();
    }

    @Override
    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
        nameField.getDocument().addDocumentListener(listener);
    }
}

