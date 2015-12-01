/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

import java.time.LocalDate;

/**
 * Patient Object Class.
 *
 * @author hansa
 */
public final class Patient {

    /**
     * Patient's rid.
     */
    private String rid;

    /**
     * Patient's NIC.
     */
    private String nic;

    /**
     * Patient's Title.
     */
    private String title;

    /**
     * Patient's First Name.
     */
    private String firstName;

    /**
     * Patient's Middle Name.
     */
    private String middleName;

    /**
     * Patient's Last Name.
     */
    private String lastName;

    /**
     * Patient's Birth Date.
     */
    private LocalDate birthDate;

    /**
     * Patient's Gender.
     */
    private String sex;

    /**
     * Patient's Address 1.
     */
    private String address1;

    /**
     * Patient's Address 2.
     */
    private String address2;

    /**
     * Patient's City.
     */
    private String city;

    /**
     * Patient's Email.
     */
    private String email;

    /**
     * Patient's Mobile.
     */
    private int mobile;

    /**
     * Patient's Photo.
     */
    private String imagePath;

    /**
     * Patient's Register Date.
     */
    private LocalDate registeredDate;

    /**
     * Is Patient Active.
     */
    private boolean isActive;

    /**
     * Patient's password.
     */
    private String password;

    /**
     * Patient's Contact Person.
     */
    private ContactPerson contactPerson;

    /**
     * Constructor.
     *
     * @param prid rid
     * @param pnic nic
     * @param ptitle title
     * @param pfirstName firstName
     * @param pmiddleName middleName
     * @param plastName lastName
     * @param pbirthDate birthDate
     * @param psex sex
     * @param paddress1 address1
     * @param paddress2 address2
     * @param pcity city
     * @param pemail email
     * @param pmobile mobile
     * @param pimagePath imagePath
     * @param pisActive boolean
     * @param pcontactPerson contactPerson
     */
    public Patient(final String prid, final String pnic, final String ptitle,
            final String pfirstName, final String pmiddleName,
            final String plastName, final LocalDate pbirthDate,
            final String psex, final String paddress1, final String paddress2,
            final String pcity, final String pemail, final int pmobile,
            final String pimagePath, final boolean pisActive,
            final ContactPerson pcontactPerson) {
        setRid(prid);
        setNic(pnic);
        setTitle(ptitle);
        setFirstName(pfirstName);
        setMiddleName(pmiddleName);
        setLastName(plastName);
        setBirthDate(pbirthDate);
        setSex(psex);
        setAddress1(paddress1);
        setAddress2(paddress2);
        setCity(pcity);
        setEmail(pemail);
        setMobile(pmobile);
        setImagePath(pimagePath);
        setIsActive(pisActive);
        setContactPerson(pcontactPerson);
    }

    /**
     * Default Constructor.
     *
     */
    public Patient() {
        this(null, null, null, null, null, null, null, null, null, null, null,
                null, 0, null, true, null);
    }

    /**
     * get Patient's rid.
     *
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * set Patient's rid.
     *
     * @param prid the rid to set
     */
    public void setRid(final String prid) {
        this.rid = prid;
    }

    /**
     * get Patient's NIC.
     *
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * set Patient's NIC.
     *
     * @param pnic the nic to set
     * @return success
     */
    public boolean setNic(final String pnic) {
        this.nic = pnic;
        return false;
    }

    /**
     * get Patient's Title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set Patient's Title.
     *
     * @param ptitle the title to set
     */
    public void setTitle(final String ptitle) {
        this.title = ptitle;
    }

    /**
     * get Patient's First Name.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set Patient's First Name.
     *
     * @param pfirstName the firstName to set
     */
    public void setFirstName(final String pfirstName) {
        this.firstName = pfirstName;
    }

    /**
     * get Patient's Middle Name.
     *
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * set Patient's Middle Name.
     *
     * @param pmiddleName the middleName to set
     */
    public void setMiddleName(final String pmiddleName) {
        this.middleName = pmiddleName;
    }

    /**
     * get Patient's Last Name.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set Patient's Last Name.
     *
     * @param plastName the lastName to set
     */
    public void setLastName(final String plastName) {
        this.lastName = plastName;
    }

    /**
     * get Patient's Birth Date.
     *
     * @return the birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * set Patient's Birth Date.
     *
     * @param pbirthDate the birthDate to set
     */
    public void setBirthDate(final LocalDate pbirthDate) {
        this.birthDate = pbirthDate;
    }

    /**
     * get Patient's Gender.
     *
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * set Patient's Gender.
     *
     * @param psex the sex to set
     */
    public void setSex(final String psex) {
        this.sex = psex;
    }

    /**
     * get Patient's Address 1.
     *
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * set Patient's Address 1.
     *
     * @param paddress1 the address1 to set
     */
    public void setAddress1(final String paddress1) {
        this.address1 = paddress1;
    }

    /**
     * get Patient's Address 2.
     *
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * set Patient's Address 2.
     *
     * @param paddress2 the address2 to set
     */
    public void setAddress2(final String paddress2) {
        this.address2 = paddress2;
    }

    /**
     * get Patient's City.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * set Patient's City.
     *
     * @param pcity the city to set
     */
    public void setCity(final String pcity) {
        this.city = pcity;
    }

    /**
     * get Patient's Email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set Patient's Email.
     *
     * @param pemail the email to set
     */
    public void setEmail(final String pemail) {
        this.email = pemail;
    }

    /**
     * get Patient's Mobile.
     *
     * @return the mobile
     */
    public int getMobile() {
        return mobile;
    }

    /**
     * set Patient's Mobile.
     *
     * @param pmobile the mobile to set
     */
    public void setMobile(final int pmobile) {
        this.mobile = pmobile;
    }

    /**
     * get Patient's Photo.
     *
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * set Patient's Photo.
     *
     * @param pimagePath the imagePath to set
     */
    public void setImagePath(final String pimagePath) {
        this.imagePath = pimagePath;
    }

    /**
     * get Patient's Register Date.
     *
     * @return the registeredDate
     */
    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    /**
     * set Patient's Register Date.
     *
     * @param pregisteredDate the registeredDate to set
     */
    public void setRegisteredDate(final LocalDate pregisteredDate) {
        this.registeredDate = pregisteredDate;
    }

    /**
     * get Is Patient Active.
     *
     * @return the isActive
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * set Is Patient Active.
     *
     * @param pisActive the isActive to set
     */
    public void setIsActive(final boolean pisActive) {
        this.isActive = pisActive;
    }

    /**
     * get Patient's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set Patient's password.
     *
     * @param ppassword the password to set
     */
    public void setPassword(final String ppassword) {
        this.password = ppassword;
    }

    /**
     * get Patient's Contact Person.
     *
     * @return the contactPerson
     */
    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    /**
     * set Patient's Contact Person.
     *
     * @param pcontactPerson the contactPerson to set
     */
    public void setContactPerson(final ContactPerson pcontactPerson) {
        this.contactPerson = pcontactPerson;
    }

}
