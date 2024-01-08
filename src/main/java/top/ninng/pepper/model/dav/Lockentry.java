package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:57
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Lockentry {

    @Element
    private Lockscope lockscope;

    @Element
    private Locktype locktype;

    /**
     * Gets the value of the lockscope property.
     *
     * @return possible object is
     * {@link Lockscope }
     */
    public Lockscope getLockscope() {
        return lockscope;
    }

    /**
     * Sets the value of the lockscope property.
     *
     * @param value allowed object is
     *              {@link Lockscope }
     */
    public void setLockscope(Lockscope value) {
        this.lockscope = value;
    }

    /**
     * Gets the value of the locktype property.
     *
     * @return possible object is
     * {@link Locktype }
     */
    public Locktype getLocktype() {
        return locktype;
    }

    /**
     * Sets the value of the locktype property.
     *
     * @param value allowed object is
     *              {@link Locktype }
     */
    public void setLocktype(Locktype value) {
        this.locktype = value;
    }
}
