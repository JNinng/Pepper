package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:08
 * @Version 1.0
 */
@Root(strict = false)
@Namespace(prefix = "D", reference = "DAV:")
public class Activelock {
    @Element
    private Lockscope lockscope;

    @Element
    private Locktype locktype;

    @Element
    private String depth;

    @Element(required = false)
    private Owner owner;

    @Element(required = false)
    private String timeout;

    @Element(required = false)
    private Locktoken locktoken;

    /**
     * Gets the value of the depth property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepth(String value) {
        this.depth = value;
    }

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
     * Gets the value of the locktoken property.
     *
     * @return possible object is
     * {@link Locktoken }
     */
    public Locktoken getLocktoken() {
        return locktoken;
    }

    /**
     * Sets the value of the locktoken property.
     *
     * @param value allowed object is
     *              {@link Locktoken }
     */
    public void setLocktoken(Locktoken value) {
        this.locktoken = value;
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

    /**
     * Gets the value of the timeout property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTimeout(String value) {
        this.timeout = value;
    }
}
