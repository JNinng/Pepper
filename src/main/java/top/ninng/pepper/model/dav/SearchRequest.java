package top.ninng.pepper.model.dav;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:19
 * @Version 1.0
 */
@Root
@Namespace(prefix = "D", reference = "DAV:")
public class SearchRequest {
    private String language;

    private String query;

    public SearchRequest() {
        this.language = "davbasic";
        this.query = "";
    }

    public SearchRequest(String language, String query) {
        this.language = language;
        this.query = query;
    }

    public final String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public final String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
