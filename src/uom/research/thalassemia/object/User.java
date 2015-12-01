/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

import java.time.LocalDate;

/**
 *
 * @author hansa
 */
public final class User {

    /**
     * User's Rid.
     */
    private String rid;

    /**
     * User's User Name.
     */
    private String userName;

    /**
     * User's Role.
     */
    private String userRole;

    /**
     * User's First Name.
     */
    private String firstName;

    /**
     * User's Last Name.
     */
    private String lastName;

    /**
     * User's Email.
     */
    private String email;

    /**
     * User's Mobile.
     */
    private int mobile;

    /**
     * User's Assigned Date.
     */
    private LocalDate assignedDate;

    /**
     * User's Left Date.
     */
    private LocalDate leftDate;

    /**
     * User's Password.
     */
    private String password;

    /**
     * Default Constructor.
     */
    public User() {
        this.rid = null;
        this.userName = null;
        this.userRole = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.mobile = 0;
        this.assignedDate = null;
        this.leftDate = null;
        this.password = null;
    }

    /**
     * Custom Constructor.
     *
     * @param prid rid
     * @param puserName userName
     * @param puserRole userRole
     * @param pfirstName firstName
     * @param passignedDate assignedDate
     * @param plastName lastName
     * @param pemail email
     * @param pmobile mobile
     * @param pleftDate leftDate
     * @param ppassword password
     */
    public User(final String prid, final String puserName,
            final String puserRole, final String pfirstName,
            final String plastName, final String pemail, final int pmobile,
            final LocalDate passignedDate, final LocalDate pleftDate,
            final String ppassword) {
        setRid(prid);
        setUserName(puserName);
        setUserRole(puserRole);
        setFirstName(pfirstName);
        setLastName(plastName);
        setEmail(pemail);
        setMobile(pmobile);
        setAssignedDate(passignedDate);
        setLeftDate(pleftDate);
        setPassword(ppassword);
    }

    /**
     * get User's Rid.
     *
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * set User's Rid.
     *
     * @param prid the rid to set
     */
    public void setRid(final String prid) {
        this.rid = prid;
    }

    /**
     * get User's User Name.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param puserName the userName to set
     */
    public void setUserName(final String puserName) {
        this.userName = puserName;
    }

    /**
     * get User's Role.
     *
     * @return the userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param puserRole the userRole to set
     */
    public void setUserRole(final String puserRole) {
        this.userRole = puserRole;
    }

    /**
     * get User's First Name.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set User's First Name.
     *
     * @param pfirstName the firstName to set
     */
    public void setFirstName(final String pfirstName) {
        this.firstName = pfirstName;
    }

    /**
     * get User's Last Name.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set User's Last Name.
     *
     * @param plastName the lastName to set
     */
    public void setLastName(final String plastName) {
        this.lastName = plastName;
    }

    /**
     * get User's Email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set User's Email.
     *
     * @param pemail the email to set
     */
    public void setEmail(final String pemail) {
        this.email = pemail;
    }

    /**
     * get User's Mobile.
     *
     * @return the mobile
     */
    public int getMobile() {
        return mobile;
    }

    /**
     * set User's Mobile.
     *
     * @param pmobile the mobile to set
     */
    public void setMobile(final int pmobile) {
        this.mobile = pmobile;
    }

    /**
     * @return the assignedDate
     */
    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    /**
     * @param passignedDate the assignedDate to set
     */
    public void setAssignedDate(final LocalDate passignedDate) {
        this.assignedDate = passignedDate;
    }

    /**
     * @return the leftDate
     */
    public LocalDate getLeftDate() {
        return leftDate;
    }

    /**
     * set User's left date.
     *
     * @param pleftDate the leftDate to set
     */
    public void setLeftDate(final LocalDate pleftDate) {
        this.leftDate = pleftDate;
    }

    /**
     * get User's password.
     *
     * @return string password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set User's left date.
     *
     * @param ppassword the password to set
     */
    public void setPassword(final String ppassword) {
        this.password = ppassword;
    }

}
