package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 15:58
 * @Version 1.0
 */
@Root(name = "write")
@Namespace(prefix = "D", reference = "DAV:")
public class Write implements SimplePrivilege {
}
