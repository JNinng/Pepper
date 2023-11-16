package top.ninng.pepper.utils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.stream.Format;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import top.ninng.pepper.model.dav.Prop;
import top.ninng.pepper.model.dav.Property;
import top.ninng.pepper.model.dav.Resourcetype;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:30
 * @Version 1.0
 */
public class PepperClientUtil {

    /**
     * 默认命名空间前缀
     */
    public static final String CUSTOM_NAMESPACE_PREFIX = "s";
    /**
     * 默认名称空间 URI
     */
    public static final String CUSTOM_NAMESPACE_URI = "SAR:";
    /**
     * 默认命名空间前缀
     */
    public static final String DEFAULT_NAMESPACE_PREFIX = "D";
    /**
     * 默认名称空间 URI
     */
    public static final String DEFAULT_NAMESPACE_URI = "DAV:";
    private final static String[] SUPPORTED_DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "EEE, dd MMM yyyy HH:mm:ss zzz",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "EEE MMM dd HH:mm:ss zzz yyyy",
            "EEEEEE, dd-MMM-yy HH:mm:ss zzz",
            "EEE MMMM d HH:mm:ss yyyy"};
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    /**
     * 用于日期解析的日期格式
     */
    private static final List<ThreadLocal<SimpleDateFormat>> DATETIME_FORMATS;

    static {
        List<ThreadLocal<SimpleDateFormat>> l = new ArrayList<>(SUPPORTED_DATE_FORMATS.length);
        for (int i = 0; i < SUPPORTED_DATE_FORMATS.length; i++) {
            l.add(new ThreadLocal<SimpleDateFormat>());
        }
        DATETIME_FORMATS = Collections.unmodifiableList(l);
    }

    private PepperClientUtil() {
    }

    /**
     * @return 默认文档构建器工厂的新 XML 文档
     */
    private static Document createDocument() {
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return builder.newDocument();
    }

    /**
     * @param key 完全限定的元素名称
     */
    public static Element createElement(QName key) {
        return createDocument().createElementNS(key.getNamespaceURI(), key.getPrefix() + ":" + key.getLocalPart());
    }

    /**
     * @param key 完全限定的元素名称
     */
    public static Element createElement(Element parent, QName key) {
        return parent.getOwnerDocument().createElementNS(key.getNamespaceURI(), key.getPrefix() + ":" + key.getLocalPart());
    }

    /**
     * @param key 本地元素名称
     */
    public static QName createQNameWithCustomNamespace(String key) {
        return new QName(CUSTOM_NAMESPACE_URI, key, CUSTOM_NAMESPACE_PREFIX);
    }

    /**
     * @param key 本地元素名称
     */
    public static QName createQNameWithDefaultNamespace(String key) {
        return new QName(DEFAULT_NAMESPACE_URI, key, DEFAULT_NAMESPACE_PREFIX);
    }

    private static Serializer getSerializer() throws Exception {
        Format format = new Format("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        Registry registry = new Registry();
        Strategy strategy = new RegistryStrategy(registry);
        Serializer serializer = new Persister(strategy, format);

        registry.bind(Prop.class, new EntityWithAnyElementConverter<>(serializer, Prop.class));
        registry.bind(Resourcetype.class, new EntityWithAnyElementConverter<>(serializer, Resourcetype.class));
        registry.bind(Property.class, Property.PropertyConverter.class);

        return serializer;
    }

    /**
     * 循环遍历所有可能的日期格式，并尝试找到正确的格式
     *
     * @param value ISO 日期字符串
     * @return Null 解析失败
     */
    public static Date parseDate(String value) {
        if (value == null) {
            return null;
        }
        Date date = null;
        for (int i = 0; i < DATETIME_FORMATS.size(); i++) {
            ThreadLocal<SimpleDateFormat> format = DATETIME_FORMATS.get(i);
            SimpleDateFormat sdf = format.get();
            if (sdf == null) {
                sdf = new SimpleDateFormat(SUPPORTED_DATE_FORMATS[i], Locale.US);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                format.set(sdf);
            }
            try {
                date = sdf.parse(value);
                break;
            } catch (ParseException e) {
                // We loop through this until we found a valid one.
            }
        }
        return date;
    }

    /**
     * @return 标准 UTF8 字符集
     */
    public static Charset standardUTF8() {
//        在任何版本的 Android 上返回标准字符集
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            return StandardCharsets.UTF_8;
//        } else {
//            return Charset.forName("UTF-8");
//        }
        return StandardCharsets.UTF_8;
    }

    public static Map<QName, String> toQName(Map<String, String> setProps) {
        if (setProps == null) {
            return Collections.emptyMap();
        }
        Map<QName, String> result = new HashMap<>(setProps.size());
        for (Map.Entry<String, String> entry : setProps.entrySet()) {
            result.put(createQNameWithCustomNamespace(entry.getKey()), entry.getValue());
        }
        return result;
    }

    public static List<QName> toQName(List<String> removeProps) {
        if (removeProps == null) {
            return Collections.emptyList();
        }
        List<QName> result = new ArrayList<>(removeProps.size());
        for (String entry : removeProps) {
            result.add(createQNameWithCustomNamespace(entry));
        }
        return result;
    }

    public static QName toQName(Element element) {
        String namespace = element.getNamespaceURI();
        if (namespace == null) {
            return new QName(PepperClientUtil.DEFAULT_NAMESPACE_URI,
                    element.getLocalName(),
                    PepperClientUtil.DEFAULT_NAMESPACE_PREFIX);
        } else if (element.getPrefix() == null) {
            return new QName(element.getNamespaceURI(),
                    element.getLocalName());
        } else {
            return new QName(element.getNamespaceURI(),
                    element.getLocalName(),
                    element.getPrefix());
        }

    }

    /**
     * @param jaxbElement 来自模型的对象
     * @return WebDAV 请求的 XML 字符串
     * @throws RuntimeException JAXB 错误
     */
    public static String toXml(Object jaxbElement) {
        StringWriter writer = new StringWriter();
        try {
            getSerializer().write(jaxbElement, writer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return writer.toString();
    }

    public static <T> T unmarshal(Class<? extends T> type, InputStream in) throws IOException {
        try {
            return getSerializer().read(type, in);
        } catch (SAXException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            // 服务器不返回任何与 JAXB 上下文匹配的有效 WebDAV XML
            throw new IOException("Not a valid DAV response", e);
        }
    }
}
