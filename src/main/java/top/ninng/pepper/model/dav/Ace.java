package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:00
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Ace {

    @Element(required = false)
    private Principal principal;

    @Element(required = false)
    private Grant grant;

    @Element(required = false)
    private Deny deny;

    @Element(required = false)
    private Inherited inherited;

    @Element(name = "protected", required = false)
    private Protected protected1;

    public Deny getDeny() {
        return deny;
    }

    public void setDeny(Deny deny) {
        this.deny = deny;
    }

    public Grant getGrant() {
        return grant;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }

    public Inherited getInherited() {
        return inherited;
    }

    public void setInherited(Inherited inherited) {
        this.inherited = inherited;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Protected getProtected() {
        return protected1;
    }

    public void setProtected(Protected protected1) {
        this.protected1 = protected1;
    }
}
