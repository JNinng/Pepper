package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 15:56
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Lockscope {

    @Element(required = false)
    private Exclusive exclusive;

    @Element(required = false)
    private Shared shared;

    /**
     * Gets the value of the exclusive property.
     *
     * @return possible object is
     * {@link Exclusive }
     */
    public Exclusive getExclusive() {
        return exclusive;
    }

    /**
     * Sets the value of the exclusive property.
     *
     * @param value allowed object is
     *              {@link Exclusive }
     */
    public void setExclusive(Exclusive value) {
        this.exclusive = value;
    }

    /**
     * Gets the value of the shared property.
     *
     * @return possible object is
     * {@link Shared }
     */
    public Shared getShared() {
        return shared;
    }

    /**
     * Sets the value of the shared property.
     *
     * @param value allowed object is
     *              {@link Shared }
     */
    public void setShared(Shared value) {
        this.shared = value;
    }
}
