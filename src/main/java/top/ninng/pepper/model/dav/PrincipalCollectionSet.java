package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:09
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class PrincipalCollectionSet {

    @Element(required = false)
    protected String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
