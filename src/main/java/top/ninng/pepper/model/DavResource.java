package top.ninng.pepper.model;

import okhttp3.internal.http.StatusLine;
import org.w3c.dom.Element;
import top.ninng.pepper.model.dav.Propstat;
import top.ninng.pepper.model.dav.Resourcetype;
import top.ninng.pepper.model.dav.Response;
import top.ninng.pepper.utils.PepperClientUtil;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author OhmLaw
 * @Date 2023/11/7 17:54
 * @Version 1.0
 */
public class DavResource {

    /**
     * 如果未设置 Getcontenttype，则为默认内容类型
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    /**
     * 如果未设置 Getcontentlength，则为默认内容长度
     */
    public static final long DEFAULT_CONTENT_LENGTH = -1;
    public static final String HTTPD_UNIX_DIRECTORY_CONTENT_TYPE = "httpd/unix-directory";
    /**
     * 如果 Response Status未设置，则默认状态码
     */
    public static final int DEFAULT_STATUS_CODE = 200;
    private static final Logger log = Logger.getLogger(DavResource.class.getName());
    /**
     * Path component separator
     */
    private static final String SEPARATOR = "/";

    private final URI href;
    private final int status;
    private final DavProperties props;

    public DavResource(Response response) throws URISyntaxException {
        this.href = new URI(response.getHref()/*.get(0)*/);
        this.status = getStatusCode(response);
        this.props = new DavProperties(response);
    }

    @Override
    public String toString() {
        return this.getPath();
    }

    /**
     * 从 prop 中检索内容语言
     *
     * @param response The response complex type of the multistatus
     * @return the content language; {@code null} if it is not avaialble
     */
    private String getContentLanguage(Response response) {
        // Make sure that directories have the correct content type.
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                Resourcetype resourcetype = propstat.getProp().getResourcetype();
                if ((resourcetype != null) && (resourcetype.getCollection() != null)) {
                    // Need to correct the contentType to identify as a directory.
                    return HTTPD_UNIX_DIRECTORY_CONTENT_TYPE;
                } else {
                    return propstat.getProp().getGetcontentlanguage();
                }
            }
        }
        return null;
    }

    /**
     * @return Content language
     */
    public String getContentLanguage() {
        return this.props.contentLanguage;
    }

    /**
     * 从道具中检索内容长度。如果不可用，返回{@link #DEFAULT_CONTENT_LENGTH}
     *
     * @param response 多状态的响应复合体类型
     * @return contentlength
     */
    private long getContentLength(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return DEFAULT_CONTENT_LENGTH;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                String gcl = propstat.getProp().getGetcontentlength();
                /*&& (gcl.getContent().size() == 1)*/
                if ((gcl != null)) {
                    try {
                        /*gcl .getContent().get(0)*/
                        return Long.parseLong(gcl);
                    } catch (NumberFormatException e) {
                        /*gcl .getContent().get(0)*/
                        log.warning(String.format("Failed to parse content length %s", gcl));
                    }
                }
            }
        }
        return DEFAULT_CONTENT_LENGTH;
    }

    /**
     * @return Size
     */
    public Long getContentLength() {
        return this.props.contentLength;
    }

    /**
     * 从prop中检索内容类型或将其设置为{@link #DEFAULT_CONTENT_TYPE}。如果是 isDirectory，则始终将内容类型设置为
     * {@link #HTTPD_UNIX_DIRECTORY_CONTENT_TYPE}。
     *
     * @param response 多状态的响应复合体类型
     * @return 内容类型
     */
    private String getContentType(Response response) {
        // Make sure that directories have the correct content type.
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                Resourcetype resourcetype = propstat.getProp().getResourcetype();
                if ((resourcetype != null) && (resourcetype.getCollection() != null)) {
                    // Need to correct the contentType to identify as a directory.
                    return HTTPD_UNIX_DIRECTORY_CONTENT_TYPE;
                } else if (propstat.getProp().getGetcontenttype() != null) {
                    return propstat.getProp().getGetcontenttype();
                }
            }
        }
        return DEFAULT_CONTENT_TYPE;
    }

    /**
     * @return MIME Type
     */
    public String getContentType() {
        return this.props.contentType;
    }

    /**
     * @return Timestamp
     */
    public Date getCreation() {
        return this.props.creation;
    }

    /**
     * 从 props 中检索 createdate。如果不可用，则返回 null
     *
     * @param response 多状态的响应复合体类型
     * @return 如果在 props 中没有找到，则为空
     */
    private String getCreationDate(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                return propstat.getProp().getCreationdate();
            }
        }
        return null;
    }

    /**
     * 根据响应的给定自定义属性创建简单复杂的 Map。
     * 这个实现将名称空间考虑在内
     *
     * @param response 多状态的响应复合体类型
     * @return 自定义属性
     */
    private Map<QName, String> getCustomProps(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        Map<QName, String> customPropsMap = new HashMap<QName, String>();
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                List<Element> props = propstat.getProp().getAny();
                for (Element element : props) {
                    customPropsMap.put(PepperClientUtil.toQName(element), element.getTextContent());
                }
            }
        }
        return customPropsMap;
    }

    /**
     * 从 props 中获取 displayName
     *
     * @param response 多状态的响应复合体类型
     * @return 显示名称;如果不可用则为 null
     */
    private String getDisplayName(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                return propstat.getProp().getDisplayname();
            }
        }
        return null;
    }

    /**
     * @return 显示名称
     */
    public String getDisplayName() {
        return this.props.displayName;
    }

    /**
     * 从 props 中检索内容长度。如果不可用，返回{@link #DEFAULT_CONTENT_LENGTH}
     *
     * @param response 多状态的响应复合体类型
     * @return contentlength
     */
    private String getEtag(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                return propstat.getProp().getGetetag();
            }
        }
        return null;
    }

    /**
     * @return Fingerprint
     */
    public String getEtag() {
        return this.props.etag;
    }

    /**
     * @return Timestamp
     */
    public Date getModified() {
        return this.props.modified;
    }

    /**
     * 从 props 中检索已修改的日期。如果不可用，则返回 null
     *
     * @param response The response complex type of the multistatus
     * @return Null if not found in props
     */
    private String getModifiedDate(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return null;
        }
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                return propstat.getProp().getGetlastmodified();
            }
        }
        return null;
    }

    /**
     * @return 资源 URI 的路径
     */
    public String getPath() {
        return this.href.getPath();
    }

    public DavProperties getProps() {
        return props;
    }

    /**
     * 从 props 中检索 resourceType。
     *
     * @param response 多状态的响应复合体类型
     * @return 资源类型列表;{@code Collections.emptyList()}如果没有提供
     */
    private List<QName> getResourceTypes(Response response) {
        List<Propstat> list = response.getPropstat();
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<QName> resourceTypes = new ArrayList<QName>();
        for (Propstat propstat : list) {
            if (propstat.getProp() != null) {
                Resourcetype rt = propstat.getProp().getResourcetype();
                if (rt != null) {
                    if (rt.getCollection() != null) {
                        resourceTypes.add(PepperClientUtil.createQNameWithDefaultNamespace("collection"));
                    }
                    if (rt.getPrincipal() != null) {
                        resourceTypes.add(PepperClientUtil.createQNameWithDefaultNamespace("principal"));
                    }
                    for (Element element : rt.getAny()) {
                        resourceTypes.add(PepperClientUtil.toQName(element));
                    }
                }
            }
        }
        return resourceTypes;
    }

    /**
     * @return Resource types
     */
    public List<QName> getResourceTypes() {
        return this.props.resourceTypes;
    }

    /**
     * @return 状态码(如果不存在则为200 ， 如果不正确则为 - 1)
     */
    public int getStatusCode() {
        return this.status;
    }

    /**
     * 检索 Response 的 status 元素的状态码部分。
     * 如果不存在，返回{@link #DEFAULT_STATUS_CODE}(也就是200)。
     *
     * @param response 多状态的响应复合体类型
     * @return 如果在响应中没有找到 DEFAULT_STATUS_CODE;-1如果状态行格式错误
     */
    private int getStatusCode(Response response) {
        String status = response.getStatus();
        if (status == null || status.isEmpty()) {
            return DEFAULT_STATUS_CODE;
        }
        try {
            return StatusLine.Companion.parse(response.getStatus()).code;
        } catch (IOException e) {
            log.warning(String.format("Failed to parse status line: %s", status));
            return -1;
        }
    }

    private class DavProperties {
        final Date creation;
        final Date modified;
        final String contentType;
        final String etag;
        final String displayName;
        final List<QName> resourceTypes;
        final String contentLanguage;
        final Long contentLength;
        //final List<QName> supportedReports;
        final Map<QName, String> customProps;

        DavProperties(Date creation, Date modified, String contentType,
                      Long contentLength, String etag, String displayName, List<QName> resourceTypes,
                      String contentLanguage, List<QName> supportedReports, Map<QName, String> customProps) {
            this.creation = creation;
            this.modified = modified;
            this.contentType = contentType;
            this.contentLength = contentLength;
            this.etag = etag;
            this.displayName = displayName;
            this.resourceTypes = resourceTypes;
            this.contentLanguage = contentLanguage;
            //this.supportedReports = supportedReports;
            this.customProps = customProps;
        }

        DavProperties(Response response) {
            this.creation = PepperClientUtil.parseDate(getCreationDate(response));
            this.modified = PepperClientUtil.parseDate(getModifiedDate(response));
            this.contentType = getContentType(response);
            this.contentLength = getContentLength(response);
            this.etag = getEtag(response);
            this.displayName = getDisplayName(response);
            this.resourceTypes = getResourceTypes(response);
            this.contentLanguage = getContentLanguage(response);
            //this.supportedReports = getSupportedReports(response);
            this.customProps = getCustomProps(response);
        }
    }
}
