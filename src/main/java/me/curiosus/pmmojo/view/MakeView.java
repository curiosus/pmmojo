package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Make;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * jp
 * Date: May 22, 2011
 * Time: 10:37:21 AM
 */
public class MakeView extends AbstractView {

    private static final Dimension SCREEN_SIZE = new Dimension(440, 110);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private Make make;
    private JTextComponent nameField;

    public MakeView(Make make) {
        this.make = make;
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add Make");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);
        Container container = getContentPane();
        MigLayout layout = new MigLayout();
        container.setLayout(layout);

        JLabel nameLbl = new JLabel("Name:");
        nameField = new JTextField(50);
        nameField.setText(make.getName());

        saveBtn = new JButton("Save");

        container.add(nameLbl);
        container.add(nameField, "wrap");

        container.add(saveBtn);
        addDirtyListeners();

    }

    public Make getMake() {
        return make;
    }

    public String getNameFieldValue() {
        return nameField.getText();
    }

    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
        nameField.getDocument().addDocumentListener(listener);
    }


}





