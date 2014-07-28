package me.curiosus.pmmojo.view;

import me.curiosus.pmmojo.controller.ServiceController;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.model.Technician;
import org.painlessgridbag.PainlessGridBag;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * User: jepeterson@gmail.com
 * Date: Apr 3, 2012
 * Time: 5:58:38 PM
 */
public class ServiceView extends AbstractView {

    private static final Dimension SCREEN_SIZE = new Dimension(800, 640);
    private static final Point SCREEN_POSITION = new Point(100, 300);

    private final Service service;
    private JTextComponent serviceDateField;
    private JTextComponent notesForNextTime;
    private JComboBox technicianCombo;
    private ServiceTableView serviceTableView;

    public ServiceView(final Service service, List<Technician> technicians, final ServiceTableView serviceTableView) {
        this.service = service;
        this.serviceTableView = serviceTableView;
        PainlessGridBag gbl = new PainlessGridBag(this, false);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Service");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_SIZE);
        setLocation(SCREEN_POSITION);

        JLabel companyNameLabel = new JLabel("Company Name:");
        JLabel makeLabel = new JLabel("Make:");
        JLabel modelLabel = new JLabel("Model");
        JLabel serviceDateLabel = new JLabel("Service Date:");

        JTextComponent companyNameField = new JTextField();
        companyNameField.setEditable(false);
        JTextComponent makeField = new JTextField();
        makeField.setEditable(false);
        JTextComponent modelField = new JTextField();
        modelField.setEditable(false);
        serviceDateField = new JTextField();

        companyNameField.setText(service.getEquipment().getCustomer().getName());
        makeField.setText(service.getEquipment().getMake().getName());
        modelField.setText(service.getEquipment().getModel());
        String svcDateStr = Service.SERVICE_DATE_FORMAT.format(service.getDate());
        serviceDateField.setText(svcDateStr);

        technicianCombo = new JComboBox(technicians.toArray());
        technicianCombo.setSelectedItem(service.getTechnician());

        notesForNextTime = new JTextArea(service.getNotesForNextService());
        notesForNextTime.setEditable(false);


        saveBtn = new JButton("Save");

        JButton closeButton = new JButton("Close Ticket");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prepareServiceForSaving()) {
                    ServiceController.getInstance().saveService(service);

                    CloseServiceDialog closeServiceDialog = new CloseServiceDialog(ServiceView.this);
                    closeServiceDialog.setVisible(true);

                    if (closeServiceDialog.getStatus() == CloseServiceDialog.STATUS_CLOSED) {
                        setDirty(false);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(ServiceView.this, "Service could not be saved nor closed");
                    return;
                }
            }
        });

        JTextComponent serviceId = new JTextField(service.getId());
        serviceId.setEditable(false);

        gbl.row().cell(companyNameLabel);
        gbl.row().cell(companyNameField).fillX();
        gbl.row().cell(makeLabel);
        gbl.row().cell(makeField).fillX();
        gbl.row().cell(modelLabel);
        gbl.row().cell(modelField).fillX();
        gbl.row().cell(serviceDateLabel);
        gbl.row().cell(serviceDateField).fillX();
        gbl.row().cell(new JLabel("Mechanic"));
        gbl.row().cell(technicianCombo).fillX();
        gbl.row().cell(new JLabel("Service Notes"));
        gbl.row().cell(notesForNextTime).fillX();
        gbl.row().cell(saveBtn);
        gbl.row().cell(closeButton);
        gbl.row().cell(new JLabel("Service ID: ")).cell(serviceId);
        gbl.doneAndPushEverythingToTop();

        addDirtyListeners();
    }

    public void save() {

        if (prepareServiceForSaving()) {
            ServiceController.getInstance().saveService(service);
            setDirty(false);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Service could not be saved");
        }

    }

    private boolean prepareServiceForSaving() {
        if (getServiceDateFieldValue() == null || getServiceDateFieldValue().length() <= 0) {
            JOptionPane.showMessageDialog(this, "Service Date is Required");
            return false;
        }
        Date serviceDate;
        try {
            serviceDate = Service.SERVICE_DATE_FORMAT.parse(getServiceDateFieldValue());
            service.setDate(serviceDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error parsing the next Service Date. Please make sure it is formatted correctly.");
            return false;
        }
        service.setTechnician(getTechnician());
        return true;
    }


    public String getServiceDateFieldValue() {
        return serviceDateField.getText();
    }

    public Technician getTechnician() {
        return (Technician) technicianCombo.getSelectedItem();
    }


    @Override
    protected void addDirtyListeners() {
        DirtyDocumentListener listener = new DirtyDocumentListener(this);
//        serviceDateField.getDocument().addDocumentListener(listener);

    }

    public Service getService() {
        return service;
    }

    public ServiceTableView getServiceTableView() {
        return serviceTableView;
    }

    public JComboBox getTechnicianCombo() {
        return technicianCombo;
    }

    public JTextComponent getServiceDateField() {
        return serviceDateField;
    }


}
