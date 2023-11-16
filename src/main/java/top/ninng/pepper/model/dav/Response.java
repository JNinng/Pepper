package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:29
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Response {

    @Element
    protected String href;
    protected String status;
    @ElementList(inline = true, required = false)
    protected List<Propstat> propstat;
    protected Error error;
    protected String responsedescription;
    protected Location location;

    /**
     * Gets the value of the error property.
     *
     * @return possible object is
     * {@link Error }
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     * @param value allowed object is
     *              {@link Error }
     */
    public void setError(Error value) {
        this.error = value;
    }

    /**
     * Gets the value of the href property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the href property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHref().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public String getHref() {
//        if (href == null) {
//            href = new ArrayList<String>();
//        }
        return this.href;
    }

    /**
     * Gets the value of the location property.
     *
     * @return possible object is
     * {@link Location }
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     *
     * @param value allowed object is
     *              {@link Location }
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the propstat property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propstat property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropstat().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Propstat }
     */
    public List<Propstat> getPropstat() {
        if (propstat == null) {
            propstat = new ArrayList<Propstat>();
        }
        return this.propstat;
    }

    /**
     * Gets the value of the responsedescription property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getResponsedescription() {
        return responsedescription;
    }

    /**
     * Sets the value of the responsedescription property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResponsedescription(String value) {
        this.responsedescription = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatus(String value) {
        this.status = value;
    }
}
