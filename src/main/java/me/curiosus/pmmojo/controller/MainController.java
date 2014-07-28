package me.curiosus.pmmojo.controller;

import me.curiosus.pmmojo.controller.listeners.area.AddAreaActionListener;
import me.curiosus.pmmojo.controller.listeners.area.EditAreaActionListener;
import me.curiosus.pmmojo.controller.listeners.customer.AddCustomerActionListener;
import me.curiosus.pmmojo.controller.listeners.customer.EditCustomerActionListener;
import me.curiosus.pmmojo.controller.listeners.equipment.AddEquipmentActionListener;
import me.curiosus.pmmojo.controller.listeners.equipment.EditEquipmentActionListener;
import me.curiosus.pmmojo.controller.listeners.make.AddMakeActionListener;
import me.curiosus.pmmojo.controller.listeners.make.EditMakeActionListener;
import me.curiosus.pmmojo.controller.listeners.technician.AddTechnicianActionListener;
import me.curiosus.pmmojo.controller.listeners.technician.EditTechnicianActionListener;
import me.curiosus.pmmojo.model.Customer;
import me.curiosus.pmmojo.model.Service;
import me.curiosus.pmmojo.persistence.dao.CustomerDao;
import me.curiosus.pmmojo.persistence.dao.CustomerDaoImpl;
import me.curiosus.pmmojo.view.MainView;
import me.curiosus.pmmojo.view.ServiceTableModel;

import java.util.List;

/**
 *
 * jp
 * Date: May 7, 2011
 * Time: 12:57:34 PM
 */
public class MainController {

    public void launch() {
        ServiceController.getInstance().initEquipmentServiceTickets();
        MainView mainView = new MainView();
        mainView.setServiceTableModel(createServiceTableModel());
        mainView.setCustomerSearchList(getCustomerList());
        mainView.init();
        addListeners(mainView);
        mainView.show();
    }

    private void addListeners(MainView mainView) {
        mainView.setAddTechnicianActionListener(new AddTechnicianActionListener());
        mainView.setEditTechnicianActionListener(new EditTechnicianActionListener());
        mainView.setAddMakeActionListener(new AddMakeActionListener());
        mainView.setEditMakeActionListener(new EditMakeActionListener());
        mainView.setAddCustomerActionListener(new AddCustomerActionListener());
        mainView.setEditCustomerActionListener(new EditCustomerActionListener());
        mainView.setAddAreaActionListener(new AddAreaActionListener());
        mainView.setEditAreaListener(new EditAreaActionListener());
        mainView.setAddEquipmentActionListener(new AddEquipmentActionListener());
        mainView.setEditEquipmentActionListener(new EditEquipmentActionListener());
    }




    public ServiceTableModel createServiceTableModel() {
        ServiceController serviceController = ServiceController.getInstance();
        List<Service> services = serviceController.getLiveServices();
        ServiceTableModel tableModel = new ServiceTableModel(services);
        return tableModel;
    }

    public List<Customer> getCustomerList() {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.retrieve();
    }


    public static void main(String[] args) {
        new MainController().launch();
    }


}
