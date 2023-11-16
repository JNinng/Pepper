package top.ninng.pepper.model.dav;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:00
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Acl {

    @ElementList(inline = true, required = false)
    private List<Ace> ace;

    public List<Ace> getAce() {
        return ace;
    }

    public void setAce(List<Ace> ace) {
        this.ace = ace;
    }
}
