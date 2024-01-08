package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:15
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Multistatus {

    @ElementList(inline = true)
    protected List<Response> response;
    @Element(required = false)
    protected String responsedescription;
    protected String syncToken;

    /**
     * Gets the value of the response property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the response property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponse().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Response }
     */
    public List<Response> getResponse() {
        if (response == null) {
            response = new ArrayList<Response>();
        }
        return this.response;
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
     * Gets the value of the syncToken property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSyncToken() {
        return syncToken;
    }

    /**
     * Sets the value of the syncToken property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSyncToken(String value) {
        this.syncToken = value;
    }
}
