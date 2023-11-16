package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import top.ninng.pepper.model.EntityWithAnyElement;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:48
 * @Version 1.0
 */
public class Resourcetype implements EntityWithAnyElement {

    private Collection collection;

    private Principal principal;

    private List<org.w3c.dom.Element> any;

    /**
     * Gets the value of the any property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     *
     *
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

    /**
     * Gets the value of the collection property.
     *
     * @return possible object is
     * {@link Collection }
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * Sets the value of the collection property.
     *
     * @param value allowed object is
     *              {@link Collection }
     */
    public void setCollection(Collection value) {
        this.collection = value;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
