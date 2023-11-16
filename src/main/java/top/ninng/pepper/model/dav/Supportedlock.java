package top.ninng.pepper.model.dav;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:57
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Supportedlock {
    @ElementList(inline = true, required = false)
    private List<Lockentry> lockentryList;

    /**
     * Gets the value of the lockentry property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lockentry property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLockentry().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lockentry }
     */
    public List<Lockentry> getLockentryList() {
        if (lockentryList == null) {
            lockentryList = new ArrayList<>();
        }
        return lockentryList;
    }
}
