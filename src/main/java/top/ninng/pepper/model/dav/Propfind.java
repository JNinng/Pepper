package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:10
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Propfind {

    @Element(required = false)
    private Allprop allprop;

    @Element(required = false)
    private Propname propname;

    @Element(required = false)
    private Prop prop;

    /**
     * Gets the value of the allprop property.
     *
     * @return possible object is
     * {@link Allprop }
     */
    public Allprop getAllprop() {
        return allprop;
    }

    /**
     * Sets the value of the allprop property.
     *
     * @param value allowed object is
     *              {@link Allprop }
     */
    public void setAllprop(Allprop value) {
        this.allprop = value;
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
     * Gets the value of the propname property.
     *
     * @return possible object is
     * {@link Propname }
     */
    public Propname getPropname() {
        return propname;
    }

    /**
     * Sets the value of the propname property.
     *
     * @param value allowed object is
     *              {@link Propname }
     */
    public void setPropname(Propname value) {
        this.propname = value;
    }
}
