package top.ninng.pepper.model.dav;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:07
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Lockdiscovery {

    @ElementList(inline = true, required = false)
    private List<Activelock> activelock;

    /**
     * Gets the value of the activelock property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activelock property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivelock().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Activelock }
     */
    public List<Activelock> getActivelock() {
        if (activelock == null) {
            activelock = new ArrayList<>();
        }
        return this.activelock;
    }
}
