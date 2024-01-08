package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:03
 * @Version 1.0
 */
@Root(name = "read")
@Namespace(prefix = "D", reference = "DAV:")
public class Read implements SimplePrivilege {
}
