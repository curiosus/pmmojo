package me.curiosus.pmmojo.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import me.curiosus.pmmojo.model.Service;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: 10/26/12
 * Time: 6:38 AM
 */
public class ServicePrinter {

    private Service service;

    public ServicePrinter(Service service) {
        this.service = service;
    }

    public void print() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat ticketDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateStr = simpleDateFormat.format(new Date());
        StringBuilder output = new StringBuilder(File.separator);
        output.append("tek").append(File.separator).append("printing");
        output.append(File.separator).append(dateStr).append(service.getId()).append(".pdf");

        File outputFile = new File(output.toString());

        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            Chunk separator = new Chunk(new VerticalPositionMark(), 200);
            Chunk newLine = new Chunk(Chunk.NEWLINE);
            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, fos);
            document.open();
            Paragraph header = new Paragraph("TEK Equipment", new Font(Font.HELVETICA, 18));
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            Paragraph title = new Paragraph("Service Ticket", new Font(Font.HELVETICA, 16));
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(newLine);
            document.add(title);
            Font font = new Font(Font.HELVETICA, 10);


            Paragraph company = new Paragraph();
            Chunk companyLbl = new Chunk("Company: ", font);
            Chunk companyName = new Chunk(service.getEquipment().getCustomer().getName(), font);
            company.add(companyLbl);
            company.add(separator);
            company.add(companyName);
            company.add(newLine);

            Paragraph address1 = new Paragraph();
            String addr1 = service.getEquipment().getCustomer().getAddress1();
            Chunk address1Lbl = new Chunk("Address: ", font);
            if (addr1 == null) addr1 = "";
            Chunk address1Value = new Chunk(addr1, font);
            address1.add(address1Lbl);
            address1.add(separator);
            address1.add(address1Value);
            address1.add(newLine);

            Paragraph address2 = new Paragraph();
            String addr2 = service.getEquipment().getCustomer().getAddress2();
            if (addr2 == null) addr2 = "";
            Chunk address2Lbl = new Chunk("Address: ", font);
            Chunk address2Value = new Chunk(addr2, font);
            address2.add(address2Lbl);
            address2.add(separator);
            address2.add(address2Value);
            address2.add(newLine);

            Paragraph city = new Paragraph();
            Chunk cityLbl = new Chunk("City: ", font);
            String cityStr = service.getEquipment().getCustomer().getCity();
            if (cityStr == null) cityStr = "";
            Chunk cityValue = new Chunk(cityStr, font);
            city.add(cityLbl);
            city.add(separator);
            city.add(cityValue);
            city.add(newLine);

            Paragraph state = new Paragraph();
            String stateStr = service.getEquipment().getCustomer().getState();
            if (stateStr == null) stateStr = "";
            Chunk stateLbl = new Chunk("State: ", font);
            Chunk stateValue = new Chunk(stateStr, font);
            state.add(stateLbl);
            state.add(separator);
            state.add(stateValue);
            state.add(newLine);

            Paragraph zip = new Paragraph();
            Chunk zipLbl = new Chunk("Zip: ", font);
            String zipStr = service.getEquipment().getCustomer().getPostalCode();
            if (zipStr == null) zipStr = "";
            Chunk zipValue = new Chunk(zipStr, font);
            zip.add(zipLbl);
            zip.add(separator);
            zip.add(zipValue);
            zip.add(newLine);


            Paragraph contact1 = new Paragraph();
            String contact1Str = service.getEquipment().getCustomer().getContact1();
            if (contact1Str == null) contact1Str = "";
            Chunk contact1Lbl = new Chunk("Contact 1: ", font);
            Chunk contact1Value = new Chunk(contact1Str, font);
            contact1.add(contact1Lbl);
            contact1.add(separator);
            contact1.add(contact1Value);
            contact1.add(newLine);

            Paragraph contact2 = new Paragraph();
            String contact2Str = service.getEquipment().getCustomer().getContact2();
            if (contact2Str == null) contact2Str = "";
            Chunk contact2Lbl = new Chunk("Contact 2: ", font);
            Chunk contact2Value = new Chunk(contact2Str, font);
            contact2.add(contact2Lbl);
            contact2.add(separator);
            contact2.add(contact2Value);
            contact2.add(newLine);


            Paragraph phone1 = new Paragraph();
            String phone1Str = service.getEquipment().getCustomer().getPhone1();
            if (phone1Str == null) phone1Str = "";
            Chunk phone1Lbl = new Chunk("Phone 1: ", font);
            Chunk phone1Value = new Chunk(phone1Str, font);
            phone1.add(phone1Lbl);
            phone1.add(separator);
            phone1.add(phone1Value);
            phone1.add(newLine);

            Paragraph phone2 = new Paragraph();
            Chunk phone2Lbl = new Chunk("Phone 2: ", font);
            String phone1Str2 = service.getEquipment().getCustomer().getPhone2();
            if (phone1Str2 == null) phone1Str2 = "";
            Chunk phone2Value = new Chunk(phone1Str2, font);
            phone2.add(phone2Lbl);
            phone2.add(separator);
            phone2.add(phone2Value);
            phone2.add(newLine);

            Paragraph fax = new Paragraph();
            Chunk faxLbl = new Chunk("Fax: ", font);
            String faxStr = service.getEquipment().getCustomer().getFax();
            if (faxStr == null) faxStr = "";
            Chunk faxValue = new Chunk(faxStr, font);
            fax.add(faxLbl);
            fax.add(separator);
            fax.add(faxValue);
            fax.add(newLine);

            Paragraph email = new Paragraph();
            Chunk emailLbl = new Chunk("Email: ", font);
            String emailStr = service.getEquipment().getCustomer().getEmail();
            if (emailStr == null) emailStr = "";
            Chunk emailValue = new Chunk(emailStr, font);
            email.add(emailLbl);
            email.add(separator);
            email.add(emailValue);
            email.add(newLine);

            Paragraph make = new Paragraph();
            Chunk makeLbl = new Chunk("Make: ", font);
            Chunk makeValue = new Chunk(service.getEquipment().getMake().getName(), font);
            make.add(makeLbl);
            make.add(separator);
            make.add(makeValue);
            make.add(newLine);

            Paragraph model = new Paragraph();
            Chunk modelLbl = new Chunk("Model: ", font);
            Chunk modelValue = new Chunk(service.getEquipment().getModel(), font);
            model.add(modelLbl);
            model.add(separator);
            model.add(modelValue);
            model.add(newLine);

            Paragraph serialNumber = new Paragraph();
            Chunk serialNumberLbl = new Chunk("Serial Number: ", font);
            String serialStr = service.getEquipment().getSerialNumber();
            if (serialStr == null) serialStr = "";
            Chunk serialNumberValue = new Chunk(serialStr, font);
            serialNumber.add(serialNumberLbl);
            serialNumber.add(separator);
            serialNumber.add(serialNumberValue);
            serialNumber.add(newLine);

            Paragraph daysBetweenService = new Paragraph();
            Chunk daysBetweenServiceLbl = new Chunk("Time Between Svc:", font);
            StringBuilder sb = new StringBuilder(service.getEquipment().getServiceInterval().toString()).append(" ").append(service.getEquipment().getServiceIntervalUnits());
            Chunk daysBetweenServiceValue = new Chunk(sb.toString(), font);
            daysBetweenService.add(daysBetweenServiceLbl);
            daysBetweenService.add(separator);
            daysBetweenService.add(daysBetweenServiceValue);
            daysBetweenService.add(newLine);

            Paragraph dateLastServiced = new Paragraph();
            Chunk dateLastServicedLbl = new Chunk("Last Service: ", font);
            Date lastServiceDate = service.getEquipment().getLastServiceDate();

            String lastServiceDateDisplay;
            if (lastServiceDate == null) {
                lastServiceDateDisplay = "None";
            } else {

                lastServiceDateDisplay= ticketDateFormat.format(lastServiceDate);
            }
            Chunk dateLastServicedValue = new Chunk(lastServiceDateDisplay, font);
            dateLastServiced.add(dateLastServicedLbl);
            dateLastServiced.add(separator);
            dateLastServiced.add(dateLastServicedValue);
            dateLastServiced.add(newLine);

            Paragraph dateNextService = new Paragraph();
            Chunk dateNextServiceLbl = new Chunk("Next Service: ", font);
            Date nextServiceDate = service.getDate();
            String nextServiceDateDisplay;
            if (nextServiceDate == null) {
                nextServiceDateDisplay = "None";
            } else {

                nextServiceDateDisplay= ticketDateFormat.format(nextServiceDate);
            }
            Chunk dateNextServiceValue = new Chunk(nextServiceDateDisplay, font);
            dateNextService.add(dateNextServiceLbl);
            dateNextService.add(separator);
            dateNextService.add(dateNextServiceValue);
            dateNextService.add(newLine);

            Paragraph technician = new Paragraph();
            Chunk technicianLbl = new Chunk("Mechanic: ", font);
            Chunk technicianValue = service.getTechnician() != null ? new Chunk(service.getTechnician().getFullName(), font) : new Chunk("");
            technician.add(technicianLbl);
            technician.add(separator);
            technician.add(technicianValue);
            technician.add(newLine);

            Paragraph transFilterNotes = new Paragraph();
            Chunk transFilterNotesLbl = new Chunk("Trans Filter Notes:", font);
            String transFilterNotesText = service.getEquipment().getTransFilter() != null ? service.getEquipment().getTransFilter() : "";
            Chunk transFilterNotesValue = new Chunk(transFilterNotesText, font);
            transFilterNotes.add(transFilterNotesLbl);
            transFilterNotes.add(newLine);
            transFilterNotes.add(transFilterNotesValue);
            transFilterNotes.add(newLine);

            Paragraph equipmentNotes = new Paragraph();
            Chunk equipmentNotesLbl = new Chunk("Equipment Notes: ", font);
            Chunk equipmentNotesValue = service.getEquipment().getEquipmentNotes() != null ? new Chunk(service.getEquipment().getEquipmentNotes(), font) : new Chunk("");
            equipmentNotes.add(equipmentNotesLbl);
            equipmentNotes.add(newLine);
            equipmentNotes.add(equipmentNotesValue);
            equipmentNotes.add(newLine);

            Paragraph serviceNotes = new Paragraph();
            Chunk serviceNotesLbl = new Chunk("Service Notes: ", font);
            Chunk serviceNotesValue = service.getNotesForNextService() != null ? new Chunk(service.getNotesForNextService(), font) : new Chunk("");
            serviceNotes.add(serviceNotesLbl);
            serviceNotes.add(newLine);
            serviceNotes.add(serviceNotesValue);
            serviceNotes.add(newLine);

            Paragraph filterDates = new Paragraph();

            Chunk airFilterLbl = new Chunk("Air Filter Date: ", font);
            Date airFilterDate = service.getEquipment().getAirFilterDate();
            String airFilter = airFilterDate != null ? ticketDateFormat.format(airFilterDate) : "";
            Chunk airFilterValue = new Chunk(airFilter, font);

            Chunk oilFilterLbl = new Chunk("Oil Filter Date: ", font);
            Date oilFilterDate = service.getEquipment().getOilFilterDate();
            String oilFilter = oilFilterDate != null ? ticketDateFormat.format(oilFilterDate) : "";
            Chunk oilFilterValue = new Chunk(oilFilter, font);

            Chunk transFilterLbl = new Chunk("Trans Filter Date: ", font);
            Date transFilterDate = service.getEquipment().getTransFilterDate();
            String transFilter = transFilterDate != null ? ticketDateFormat.format(transFilterDate) : "";
            Chunk transFilterValue = new Chunk(transFilter, font);

            Chunk fuelFilterLbl = new Chunk("Fuel Filter Date: ", font);
            Date fuelFilterDate = service.getEquipment().getFuelFilterDate();
            String fuelFilter = fuelFilterDate != null ? ticketDateFormat.format(fuelFilterDate) : "";
            Chunk fuelFilterValue = new Chunk(fuelFilter, font);

            Chunk hydraulicFilterLbl = new Chunk("Hydraulic Filter Date: ", font);
            Date hydraulicFilterDate = service.getEquipment().getHydraulicFilterDate();
            String hydraulicFilter = hydraulicFilterDate != null ? ticketDateFormat.format(hydraulicFilterDate) : "";
            Chunk hydraulicFilterValue = new Chunk(hydraulicFilter, font);

            filterDates.add(airFilterLbl);
            filterDates.add(separator);
            filterDates.add(airFilterValue);
            filterDates.add(newLine);

            filterDates.add(oilFilterLbl);
            filterDates.add(separator);
            filterDates.add(oilFilterValue);
            filterDates.add(newLine);

            filterDates.add(transFilterLbl);
            filterDates.add(separator);
            filterDates.add(transFilterValue);
            filterDates.add(newLine);

            filterDates.add(fuelFilterLbl);
            filterDates.add(separator);
            filterDates.add(fuelFilterValue);
            filterDates.add(newLine);

            filterDates.add(hydraulicFilterLbl);
            filterDates.add(separator);
            filterDates.add(hydraulicFilterValue);
            filterDates.add(newLine);


            Paragraph ids = new Paragraph();
            Font idFont = new Font(Font.HELVETICA, 8);
            Chunk serviceIdLbl = new Chunk("Service Ticket ID: ", idFont);
            Chunk serviceId = new Chunk(service.getId(), idFont);
            Chunk equipmentIdLbl = new Chunk("Equipment ID: ", idFont);
            Chunk equipmentId = new Chunk(service.getEquipment().getId(), idFont);
            ids.add(serviceIdLbl);
            ids.add(separator);
            ids.add(serviceId);
            ids.add(newLine);
            ids.add(equipmentIdLbl);
            ids.add(separator);
            ids.add(equipmentId);

            document.add(company);
            document.add(address1);
            document.add(city);
            document.add(state);
            document.add(zip);
            document.add(contact1);
            document.add(contact2);
            document.add(phone1);
            document.add(phone2);
            document.add(fax);
            document.add(email);
            document.add(make);
            document.add(model);
            document.add(serialNumber);
            document.add(daysBetweenService);
            document.add(dateLastServiced);
            document.add(dateNextService);
            document.add(technician);
            document.add(transFilterNotes);
            document.add(equipmentNotes);
            document.add(serviceNotes);
            document.add(filterDates);
            document.add(ids);

            document.close();
            Desktop.getDesktop().open(outputFile);
//            Desktop.getDesktop().print(outputFile);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Printing Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (DocumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Printing Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Printing Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch(UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Printing Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
