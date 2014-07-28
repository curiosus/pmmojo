package me.curiosus.pmmojo.model;

import javax.persistence.*;

/**
 * jp
 * Date: Jun 1, 2011
 * Time: 7:52:46 AM
 */

@Entity
public class CustomerImpl implements Customer {

    private String id;
    private boolean enabled;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String phone1;
    private String phone2;
    private String contact1;
    private String contact2;
    private String fax;
    private String email;
    private Area area;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    @Transient
    public String getDisplayName() {
        return "Customer";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getPhone1() {
        return phone1;
    }

    @Override
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Override
    public String getPhone2() {
        return phone2;
    }

    @Override
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public void setContact1(String contact) {
        contact1 = contact;
    }

    @Override
    public void setContact2(String contact) {
        contact2 = contact;
    }

    @Override
    public String getContact1() {
        return contact1;
    }

    @Override
    public String getContact2() {
        return contact2;
    }

    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "area_id")
    public AreaImpl getArea() {
        return (AreaImpl) area;
    }

    @Override
    public void setArea(Area area) {
        this.area = area;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return c.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
