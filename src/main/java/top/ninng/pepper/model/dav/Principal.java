package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 17:01
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class Principal {

    @Element(required = false)
    private String href;

    @Element(required = false)
    private Property property;

    @Element(required = false)
    private All all;

    @Element(required = false)
    private Authenticated authenticated;

    @Element(required = false)
    private Unauthenticated unauthenticated;

    @Element(required = false)
    private Self self;

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }

    public Authenticated getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Authenticated authenticated) {
        this.authenticated = authenticated;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Unauthenticated getUnauthenticated() {
        return unauthenticated;
    }

    public void setUnauthenticated(Unauthenticated unauthenticated) {
        this.unauthenticated = unauthenticated;
    }
}
