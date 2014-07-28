package me.curiosus.pmmojo.model;

/**
 * jp
 * Date: Jun 1, 2011
 * Time: 7:51:12 AM
 */
public interface Customer extends ModelItem {


    String getName();
    void setName(String name);
    String getAddress1();
    void setAddress1(String address1);
    String getAddress2();
    void setAddress2(String address2);
    String getCity();
    void setCity(String city);
    String getState();
    void setState(String state);
    String getPostalCode();
    void setPostalCode(String postalCode);
    String getPhone1();
    void setPhone1(String phone1);
    String getPhone2();
    void setPhone2(String phone2);
    void setContact1(String contact);
    void setContact2(String contact);
    String getContact1();
    String getContact2();
    String getFax();
    void setFax(String fax);
    String getEmail();
    void setEmail(String email);
    Area getArea();
    void setArea(Area area);




    
}
