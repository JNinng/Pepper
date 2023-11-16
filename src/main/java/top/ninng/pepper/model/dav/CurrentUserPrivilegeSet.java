package top.ninng.pepper.model.dav;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:10
 * @Version 1.0
 */
@Root
public class CurrentUserPrivilegeSet {

    @ElementList(inline = true)
    public List<Privilege> privileges;
}
