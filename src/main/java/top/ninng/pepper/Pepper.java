package top.ninng.pepper;

import top.ninng.pepper.model.DavResource;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author OhmLaw
 * @Date 2023/11/7 16:22
 * @Version 1.0
 */
public interface Pepper {

    /**
     * 使用 WebDAV COPY 将 url 从源复制到目标。假定覆盖
     *
     * @param sourceUrl      源路径，包括协议和主机名
     * @param destinationUrl 目标路径，包括协议和主机名
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void copy(String sourceUrl, String destinationUrl) throws Exception;

    /**
     * 使用 WebDAV COPY 将 URL 从源复制到目标
     *
     * @param sourceUrl      源路径，包括协议和主机名
     * @param destinationUrl 目标路径，包括协议和主机名
     * @param overwrite      true 如果目标存在，则覆盖， false 跳过。
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void copy(String sourceUrl, String destinationUrl, boolean overwrite) throws Exception;

    /**
     * 使用 WebDAV MKCOL 在指定的 url 创建目录
     *
     * @param url 资源的路径，包括协议和主机名
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void createDirectory(String url) throws Exception;

    /**
     * 在指定的 url 上使用 HTTP Delete 删除资源
     *
     * @param url Path to the resource including protocol and hostname
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void delete(String url) throws Exception;

    /**
     * 使用 HTTP HEAD 请求，查看资源是否存在
     *
     * @param url 资源路径，包括协议和主机名
     * @return 任何超出 200-299 响应码范围的返回 false
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    boolean exists(String url) throws Exception;

    /**
     * 使用 HTTP GET 从服务器下载数据。读取后必须关闭流
     *
     * @param url 资源路径，包括协议和主机名
     * @return 要读取的数据流
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    InputStream get(String url) throws Exception;

    /**
     * 使用 HTTP GET 从服务器下载数据。读取后必须关闭流
     *
     * @param url     资源路径，包括协议和主机名
     * @param headers 要添加到请求中的其他 HTTP 标头
     * @return 要读取的数据流
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    InputStream get(String url, Map<String, String> headers) throws Exception;

    /**
     * 使用 WebDAV PROPFIND 获取目录列表
     *
     * @param url 资源路径，包括协议和主机名
     * @return 此 URI 的资源列表，包括父资源本身
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    List<DavResource> list(String url) throws Exception;

    /**
     * 使用 WebDAV PROPFIND 获取目录列表
     *
     * @param url   资源的路径，包括协议和主机名
     * @param depth 要查看的深度(单个资源使用 0，目录列表使用 1，-1表示无限递归)
     * @return 此 URI 的资源列表，包括父资源本身
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    List<DavResource> list(String url, int depth) throws Exception;

    /**
     * 使用 WebDAV PROPFIND 获取目录列表
     *
     * @param url   资源的路径，包括协议和主机名
     * @param depth 要查看的深度(单个资源使用0，目录列表使用1，-1表示无限递归)
     * @param props 应该请求的其他属性
     * @return 此 URI 的资源列表，包括父资源本身
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    List<DavResource> list(String url, int depth, Set<QName> props) throws Exception;

    /**
     * 使用 WebDAV PROPFIND 获取目录列表
     *
     * @param url     资源的路径，包括协议和主机名
     * @param depth   要查看的深度(单个资源使用0，目录列表使用1，-1表示无限递归)
     * @param allProp 如果要使用所有 prop，有时会效率低下;警告:no allprop不检索自定义道具，只是基本的
     * @return 此 URI 的资源列表，包括父资源本身
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    List<DavResource> list(String url, int depth, boolean allProp) throws Exception;

    /**
     * 对该资源设置独占写锁。写锁必须防止主体没有写
     * 成功执行 PUT, POST, PROPPATCH, lock, UNLOCK, MOVE, DELETE, MKCOL 的锁
     *
     * @param url 资源的路径，包括协议和主机名
     * @return 解锁此资源的锁令牌。锁令牌是一种状态令牌。作为 URI，它标识一个特定的锁。每次成功时返回一个锁令牌
     * LOCK 操作中的 lockdiscovery 属性在响应体中，也可以通过查找锁定资源的发现。
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    String lock(String url) throws Exception;

    /**
     * 对该资源设置独占写锁。写锁必须防止主体没有写
     * 成功执行PUT, POST, PROPPATCH, lock, UNLOCK, MOVE, DELETE, MKCOL的锁
     *
     * @param url     资源的路径，包括协议和主机名
     * @param timeout 超时以锁到期前剩余的秒数为单位
     * @return 解锁此资源的锁令牌。锁令牌是一种状态令牌。作为 URI，它标识一个特定的锁。每次成功时返回一个锁令牌
     * LOCK 操作中的 lockdiscovery 属性在响应体中，也可以通过查找锁定资源的发现。
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    String lock(String url, int timeout) throws Exception;

    /**
     * 使用 WebDAV Move 将 url 从源移动到目标，默认覆盖
     *
     * @param sourceUrl      资源的路径，包括协议和主机名
     * @param destinationUrl 资源的路径，包括协议和主机名
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void move(String sourceUrl, String destinationUrl) throws Exception;

    /**
     * 使用 WebDAV Move 将 url 从源移动到目标
     *
     * @param sourceUrl      资源的路径，包括协议和主机名
     * @param destinationUrl 资源的路径，包括协议和主机名
     * @param overwrite      true：如果目标存在则覆盖
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void move(String sourceUrl, String destinationUrl, boolean overwrite) throws Exception;

    /**
     * 使用 WebDAV Move 将 url 从源移动到目标
     *
     * @param sourceUrl      资源的路径，包括协议和主机名
     * @param destinationUrl 资源的路径，包括协议和主机名
     * @param overwrite      true：如果目标存在则覆盖
     * @param lockToken      锁令牌
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void move(String sourceUrl, String destinationUrl, boolean overwrite, String lockToken) throws Exception;

    /**
     * 使用 HTTP PUT 向服务器发送数据。认证失败时可重复
     *
     * @param url  资源的路径，包括协议和主机名
     * @param data 输入源
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void put(String url, byte[] data) throws Exception;

    /**
     * 使用 PUT 向具有特定内容类型的服务器发送数据标题。认证失败时可重复。
     *
     * @param url         资源的路径，包括协议和主机名
     * @param data        输入源
     * @param contentType 要添加到 HTTP 请求头的 MIME 类型
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void put(String url, byte[] data, String contentType) throws Exception;

    /**
     * 使用 PUT 上传文件到具有特定 contentType 的服务器。认证失败时可重复
     *
     * @param url         资源的路径，包括协议和主机名
     * @param localFile   要发送的本地文件
     * @param contentType 要添加到 HTTP 请求头的 MIME 类型
     * @throws Exception I/O错误或HTTP响应失败
     */
    void put(String url, File localFile, String contentType) throws Exception;

    /**
     * 使用 PUT 上传文件到具有特定 contentType 的服务器。认证失败时可重复
     *
     * @param url            资源的路径，包括协议和主机名
     * @param localFile      要发送的本地文件
     * @param contentType    要添加到 HTTP 请求头的 MIME 类型
     * @param expectContinue 为 PUT 请求启用(Expect: continue)标头
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    void put(String url, File localFile, String contentType, boolean expectContinue) throws Exception;

    /**
     * 使用 PUT 上传文件到具有特定 contentType 的服务器。认证失败时可重复
     *
     * @param url            资源的路径，包括协议和主机名
     * @param localFile      要发送的本地文件
     * @param contentType    要添加到 HTTP 请求头的 MIME 类型
     * @param expectContinue 为 PUT 请求启用(Expect: continue)标头
     * @param lockToken      锁令牌是
     * @throws Exception I/O错误或HTTP响应失败
     */
    void put(String url, File localFile, String contentType, boolean expectContinue, String lockToken) throws Exception;

    /**
     * 执行 Webdav 存储库的搜索
     *
     * @param url      搜索目标资源
     * @param language 查询所使用的语言
     * @param query    Webdav 服务器要处理的查询字符串
     * @return 匹配资源的列表
     * @throws Exception I/O 错误或 HTTP 响应失败
     */
    List<DavResource> search(String url, String language, String query) throws Exception;

    /**
     * 添加凭据，应该使用抢占式认证
     *
     * @param username 用户名
     * @param password 密码
     */
    void setCredentials(String username, String password);

    /**
     * 解锁资源。WebDAV 兼容的服务器不需要支持任何形式的锁定。如果服务器支持它可以选择支持任何访问类型的独占锁和共享锁的组合
     *
     * @param url   Path to the resource including protocol and hostname
     * @param token The lock token to unlock this resource.
     * @throws Exception I/O error or HTTP response validation failure
     * @see #lock(String)
     */
    void unlock(String url, String token) throws Exception;
}
