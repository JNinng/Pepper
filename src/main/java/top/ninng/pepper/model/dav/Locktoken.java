package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:08
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Locktoken {

    @Element
    private String href;

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
        return href;
    }
}
