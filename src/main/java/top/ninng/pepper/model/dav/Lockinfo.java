package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 15:55
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Lockinfo {
    @Element
    private Lockscope lockscope;

    @Element
    private Locktype locktype;

    @Element(required = false)
    private Owner owner;

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

    /**
     * Gets the value of the owner property.
     *
     * @return possible object is
     * {@link Owner }
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     *
     * @param value allowed object is
     *              {@link Owner }
     */
    public void setOwner(Owner value) {
        this.owner = value;
    }
}
