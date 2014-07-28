package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.model.Technician;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * jp
 * Date: May 7, 2011
 * Time: 1:06:55 PM
 */
public class TechnicianView extends AbstractView {

    private static final Dimension SCREEN_SIZE = new Dimension(440, 180);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private Technician technician;

    private JTextComponent firstNameField;
    private JTextComponent lastNameField;

    public TechnicianView(Technician technician) {                                            
        this.technician = technician;
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add Technician");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);

        JLabel firstNameLbl = new JLabel("First Name:");
        JLabel lastNameLbl = new JLabel("Last Name");
        firstNameField = new JTextField(50);
        firstNameField.setText(technician.getFirstName());
        lastNameField = new JTextField(50);
        lastNameField.setText(technician.getLastName());
        saveBtn = new JButton("Save");

        gbl.row().cell(firstNameLbl) ;
        gbl.row().cell(firstNameField).fillX();

        gbl.row().cell(lastNameLbl) ;
        gbl.row().cell(lastNameField).fillX();
        
        gbl.row().cell(saveBtn);
        gbl.doneAndPushEverythingToTop();
        
        addDirtyListeners();

    }

    public Technician getTechnician() {
        return technician;
    }

    public String getFirstNameFieldValue() {
        return firstNameField.getText();
    }

    public String getLastNameFieldValue() {
        return lastNameField.getText();
    }

    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
        firstNameField.getDocument().addDocumentListener(listener);
        lastNameField.getDocument().addDocumentListener(listener);
    }


}
