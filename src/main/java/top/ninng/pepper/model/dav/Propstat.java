package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:56
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Propstat {

    @Element
    protected Prop prop;

    @Element
    protected String status;

    @Element(required = false)
    protected Error error;

    @Element(required = false)
    protected String responsedescription;

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
     * Gets the value of the prop property.
     *
     * @return possible object is
     * {@link Prop }
     */
    public Prop getProp() {
        return prop;
    }

    /**
     * Sets the value of the prop property.
     *
     * @param value allowed object is
     *              {@link Prop }
     */
    public void setProp(Prop value) {
        this.prop = value;
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
