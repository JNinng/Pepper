package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import top.ninng.pepper.model.EntityWithAnyElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:11
 * @Version 1.0
 */
@Root(strict = false)
@Namespace(prefix = "D", reference = "DAV:")
public class Prop implements EntityWithAnyElement {

    @Element(required = false)
    protected String creationdate;

    @Element(required = false)
    protected String displayname;

    @Element(required = false)
    protected String getcontentlanguage;

    @Element(required = false)
    protected String getcontentlength;

    @Element(required = false)
    protected String getcontenttype;

    @Element(required = false)
    protected String getetag;

    @Element(required = false)
    protected String getlastmodified;

    @Element(required = false)
    protected Lockdiscovery lockdiscovery;

    @Element(required = false)
    protected Resourcetype resourcetype;

    @Element(required = false)
    protected Supportedlock supportedlock;

    @Element(name = "quota-available-bytes", required = false)
    protected QuotaAvailableBytes quotaAvailableBytes;

    @Element(name = "quota-used-bytes", required = false)
    protected QuotaUsedBytes quotaUsedBytes;

    protected List<org.w3c.dom.Element> any;
    //ACL elements
    @Element(required = false)
    protected Owner owner;
    @Element(required = false)
    protected Group group;
    @Element(required = false)
    protected Acl acl;
    @Element(name = "principal-collection-set", required = false)
    private PrincipalCollectionSet principalCollectionSet;
    @Element(name = "current-user-principal", required = false)
    private PrincipalURL principalURL;
    @Element(name = "current-user-privilege-set")
    private CurrentUserPrivilegeSet currentUserPrivilegeSet;

    /**
     * Gets the value of the any property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     */
    @Override
    public List<org.w3c.dom.Element> getAny() {
        if (any == null) {
            any = new ArrayList<>();
        }
        return this.any;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public CurrentUserPrivilegeSet getCurrentUserPrivilegeSet() {
        return currentUserPrivilegeSet;
    }

    public void setCurrentUserPrivilegeSet(CurrentUserPrivilegeSet currentUserPrivilegeSet) {
        this.currentUserPrivilegeSet = currentUserPrivilegeSet;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getGetcontentlanguage() {
        return getcontentlanguage;
    }

    public void setGetcontentlanguage(String getcontentlanguage) {
        this.getcontentlanguage = getcontentlanguage;
    }

    public String getGetcontentlength() {
        return getcontentlength;
    }

    public void setGetcontentlength(String getcontentlength) {
        this.getcontentlength = getcontentlength;
    }

    public String getGetcontenttype() {
        return getcontenttype;
    }

    public void setGetcontenttype(String getcontenttype) {
        this.getcontenttype = getcontenttype;
    }

    public String getGetetag() {
        return getetag;
    }

    public void setGetetag(String getetag) {
        this.getetag = getetag;
    }

    public String getGetlastmodified() {
        return getlastmodified;
    }

    public void setGetlastmodified(String getlastmodified) {
        this.getlastmodified = getlastmodified;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Gets the value of the lockdiscovery property.
     *
     * @return possible object is
     * {@link Lockdiscovery }
     */
    public Lockdiscovery getLockdiscovery() {
        return lockdiscovery;
    }

    /**
     * Sets the value of the lockdiscovery property.
     *
     * @param value allowed object is
     *              {@link Lockdiscovery }
     */
    public void setLockdiscovery(Lockdiscovery value) {
        this.lockdiscovery = value;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PrincipalCollectionSet getPrincipalCollectionSet() {
        return principalCollectionSet;
    }

    public void setPrincipalCollectionSet(PrincipalCollectionSet principalCollectionSet) {
        this.principalCollectionSet = principalCollectionSet;
    }

    public PrincipalURL getPrincipalURL() {
        return principalURL;
    }

    public void setPrincipalURL(PrincipalURL principalURL) {
        this.principalURL = principalURL;
    }

    /**
     * Gets the value of the quotaAvailableBytes property.
     *
     * @return possible object is
     * {@link QuotaAvailableBytes }
     */
    public QuotaAvailableBytes getQuotaAvailableBytes() {
        return quotaAvailableBytes;
    }

    /**
     * Sets the value of the quotaAvailableBytes property.
     *
     * @param value allowed object is
     *              {@link QuotaAvailableBytes }
     */
    public void setQuotaAvailableBytes(QuotaAvailableBytes value) {
        this.quotaAvailableBytes = value;
    }

    /**
     * Gets the value of the quotaUsedBytes property.
     *
     * @return possible object is
     * {@link QuotaUsedBytes }
     */
    public QuotaUsedBytes getQuotaUsedBytes() {
        return quotaUsedBytes;
    }

    /**
     * Sets the value of the quotaUsedBytes property.
     *
     * @param value allowed object is
     *              {@link QuotaUsedBytes }
     */
    public void setQuotaUsedBytes(QuotaUsedBytes value) {
        this.quotaUsedBytes = value;
    }

    public Resourcetype getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(Resourcetype resourcetype) {
        this.resourcetype = resourcetype;
    }

    public Supportedlock getSupportedlock() {
        return supportedlock;
    }

    public void setSupportedlock(Supportedlock supportedlock) {
        this.supportedlock = supportedlock;
    }
}
