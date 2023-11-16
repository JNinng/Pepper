package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 15:58
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Locktype {

    @Element(required = false)
    private Write write;

    /**
     * Gets the value of the write property.
     *
     * @return possible object is
     * {@link Write }
     */
    public Write getWrite() {
        return write;
    }

    /**
     * Sets the value of the write property.
     *
     * @param value allowed object is
     *              {@link Write }
     */
    public void setWrite(Write value) {
        this.write = value;
    }
}
