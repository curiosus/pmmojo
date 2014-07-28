package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.controller.listeners.TableRowRightClickListener;
import me.curiosus.pmmojo.controller.listeners.equipment.EquipmentTableSelectionListener;
import me.curiosus.pmmojo.model.Area;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Equipment;
import me.curiosus.pmmojo.persistence.dao.EquipmentDao;
import me.curiosus.pmmojo.persistence.dao.EquipmentDaoImpl;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * jp
 * Date: Jun 3, 2011
 * Time: 4:19:41 PM
 */
public class CustomerView extends AbstractView {

    private static final Dimension SCREEN_SIZE = new Dimension(800, 800);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private Customer customer;

    private JTextComponent nameField;
    private JTextComponent address1Field;
    private JTextComponent address2Field;
    private JTextComponent cityField;
    private JTextComponent stateField;
    private JTextComponent postalCodeField;
    private JTextComponent contact1Field;
    private JTextComponent contact2Field;
    private JTextComponent phone1Field;
    private JTextComponent phone2Field;
    private JTextComponent faxField;
    private JTextComponent emailField;
    private JComboBox areaCombo;

    private List<Area> areas;

    public CustomerView(final Customer customer, List<Area> areas) {
        this.customer = customer;
        this.areas = areas;
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);

        gbl.row().cell(new JLabel("Name:")) ;
        nameField = new JTextField(50);
        nameField.setText(customer.getName());
        gbl.row().cell(nameField).fillX() ;

        gbl.row().cell(new JLabel("Address:"));
        address1Field = new JTextField(50);
        address1Field.setText(customer.getAddress1());
        gbl.row().cell(address1Field).fillX();

        gbl.row().cell(new JLabel("Address:"));
        address2Field = new JTextField(50);
        address2Field.setText(customer.getAddress2());
        gbl.row().cell(address2Field).fillX() ;

        gbl.row().cell(new JLabel("City:"));
        cityField = new JTextField(50);
        cityField.setText(customer.getCity());
        gbl.row().cell(cityField).fillX() ;


        gbl.row().cell(new JLabel("State:"));
        stateField = new JTextField(50);
        stateField.setText(customer.getState());
        gbl.row().cell(stateField).fillX() ;


        gbl.row().cell(new JLabel("Zip:"));
        postalCodeField = new JTextField(50);
        postalCodeField.setText(customer.getPostalCode());
        gbl.row().cell(postalCodeField).fillX() ;

        gbl.row().cell(new JLabel("Contact:"));
        contact1Field = new JTextField(50);
        contact1Field.setText(customer.getContact1());
        gbl.row().cell(contact1Field).fillX() ;

        gbl.row().cell(new JLabel("Contact 2:"));
        contact2Field = new JTextField(50);
        contact2Field.setText(customer.getContact2());
        gbl.row().cell(contact2Field).fillX() ;


        gbl.row().cell(new JLabel("Phone:"));
        phone1Field = new JTextField(50);
        phone1Field.setText(customer.getPhone1());
        gbl.row().cell(phone1Field).fillX() ;


        gbl.row().cell(new JLabel("Phone 2:"));
        phone2Field = new JTextField(50);
        phone2Field.setText(customer.getPhone2());
        gbl.row().cell(phone2Field).fillX() ;


        gbl.row().cell(new JLabel("Fax:"));
        faxField = new JTextField(50);
        faxField.setText(customer.getFax());
        gbl.row().cell(faxField).fillX() ;


        gbl.row().cell(new JLabel("Email:"));
        emailField = new JTextField(50);
        emailField.setText(customer.getEmail());
        gbl.row().cell(emailField).fillX() ;


        gbl.row().cell(new JLabel("Area:"));
        areaCombo = new JComboBox(areas.toArray());
        areaCombo.setSelectedItem(customer.getArea());
        gbl.row().cell(areaCombo).fillX() ;

         /*TODO MAJOR HACK ALERT --REFACTOR*/
        JButton equipBtn = new JButton("Equipment");
        equipBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EquipmentDao dao = new EquipmentDaoImpl();
                List<Equipment> equipmentList =  dao.retrieveByCustomer(customer);
                EquipmentTableModel model = new EquipmentTableModel(equipmentList);
                EquipmentTableView equipmentTableView = new EquipmentTableView(model);
                equipmentTableView.setTableViewEditListener(new EquipmentTableSelectionListener(equipmentTableView));
                TableRowRightClickListener rowRightClickListener = new TableRowRightClickListener();
                equipmentTableView.setTableRowRightClickListener(rowRightClickListener);
                equipmentTableView.setVisible(true);
            }
        });



        saveBtn = new JButton("Save");
        gbl.row().cell(saveBtn).cell().cell(equipBtn);


        gbl.doneAndPushEverythingToTop();
        addDirtyListeners();
    }


    @Override
    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
        nameField.getDocument().addDocumentListener(listener);
        address1Field.getDocument().addDocumentListener(listener);
        address2Field.getDocument().addDocumentListener(listener);
        cityField.getDocument().addDocumentListener(listener);
        stateField.getDocument().addDocumentListener(listener);
        postalCodeField.getDocument().addDocumentListener(listener);
        contact1Field.getDocument().addDocumentListener(listener);
        contact2Field.getDocument().addDocumentListener(listener);
        phone1Field.getDocument().addDocumentListener(listener);
        phone2Field.getDocument().addDocumentListener(listener);
        faxField.getDocument().addDocumentListener(listener);
        emailField.getDocument().addDocumentListener(listener);
        //todo don't we need something for areas? It's a combo.
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getNameFieldValue() {
        return nameField.getText();
    }

    public String getAddress1FieldValue() {
        return address1Field.getText();
    }

    public String getAddress2FieldValue() {
        return address2Field.getText();
    }

    public String getCityFieldValue() {
        return cityField.getText();
    }

    public String getStateFieldValue() {
        return stateField.getText();
    }

    public String getPostalCodeFieldValue() {
        return postalCodeField.getText();
    }

    public String getContact1FieldValue() {
        return contact1Field.getText();
    }

    public String getContact2FieldValue() {
        return contact2Field.getText();
    }

    public String getPhone1FieldValue() {
        return phone1Field.getText();
    }

    public String getPhone2FieldValue() {
        return phone2Field.getText();
    }

    public String getFaxFieldValue() {
        return faxField.getText();
    }

    public String getEmailFieldValue() {
        return emailField.getText();
    }

    public Area getAreaFieldValue() {
        return (Area) areaCombo.getSelectedItem();
    }

}
