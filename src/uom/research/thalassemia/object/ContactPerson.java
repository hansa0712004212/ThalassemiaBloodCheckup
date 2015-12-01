/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.research.thalassemia.object;

/**
 * Contact Person Object Class.
 *
 * @author hansa
 */
public final class ContactPerson {

    /**
     * Contact person's title.
     */
    private String title;

    /**
     * Contact person's name.
     */
    private String name;

    /**
     * Contact person's email.
     */
    private String email;

    /**
     * Contact person's mobile.
     */
    private int mobile;

    /**
     * Default Constructor.
     */
    public ContactPerson() {
        this(null, null, null, 0);
    }

    /**
     * Parameterized Constructor.
     *
     * @param ptitle title
     * @param pname name
     * @param pemail email
     * @param pmobile mobile
     */
    public ContactPerson(final String ptitle,
            final String pname, final String pemail, final int pmobile) {
        setTitle(ptitle);
        setName(pname);
        setEmail(pemail);
        setMobile(pmobile);
    }

    /**
     * get contact person's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set contact person's title.
     *
     * @param ptitle the title to set
     */
    public void setTitle(final String ptitle) {
        this.title = ptitle;
    }

    /**
     * get contact person's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * set contact person's name.
     *
     * @param pname the name to set
     */
    public void setName(final String pname) {
        this.name = pname;
    }

    /**
     * get contact person's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set contact person's email.
     *
     * @param pemail the email to set
     */
    public void setEmail(final String pemail) {
        this.email = pemail;
    }

    /**
     * get contact person's mobile.
     *
     * @return the mobile
     */
    public int getMobile() {
        return mobile;
    }

    /**
     * set contact person's mobile.
     *
     * @param pmobile the mobile to set
     */
    public void setMobile(final int pmobile) {
        this.mobile = pmobile;
    }
}
