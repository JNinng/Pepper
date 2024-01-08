package top.ninng.pepper;

import okhttp3.Response;
import okhttp3.*;
import org.w3c.dom.Element;
import top.ninng.pepper.model.*;
import top.ninng.pepper.model.dav.*;
import top.ninng.pepper.utils.PepperClientUtil;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:38
 * @Version 1.0
 */
public class OkHttpPepperClient implements Pepper {

    private OkHttpClient client;

    public OkHttpPepperClient() {
        client = new OkHttpClient.Builder().build();
    }

    public OkHttpPepperClient(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public void copy(String sourceUrl, String destinationUrl) throws Exception {
        copy(sourceUrl, destinationUrl, true);
    }

    @Override
    public void copy(String sourceUrl, String destinationUrl, boolean overwrite) throws Exception {
        Request request = new Request.Builder()
                .url(sourceUrl)
                .method("COPY", null)
                .header("DESTINATION", URI.create(destinationUrl).toASCIIString())
                .header("OVERWRITE", overwrite ? "T" : "F")
                .build();
        execute(request);
    }

    @Override
    public void createDirectory(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .method("MKCOL", null)
                .build();
        execute(request);
    }

    @Override
    public void delete(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        execute(request);
    }

    @Override
    public boolean exists(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .method("HEAD", null)
                .build();
        return execute(request, new ExistsResponseHandler());
    }

    @Override
    public InputStream get(String url) throws Exception {
        return this.get(url, Collections.<String, String>emptyMap());
    }

    @Override
    public InputStream get(String url, Map<String, String> headers) throws Exception {
        return this.get(url, Headers.of(headers));
    }

    @Override
    public List<DavResource> list(String url) throws Exception {
        return list(url, 1);
    }

    @Override
    public List<DavResource> list(String url, int depth) throws Exception {
        return list(url, depth, true);
    }

    @Override
    public List<DavResource> list(String url, int depth, Set<QName> props) throws Exception {
        Propfind body = new Propfind();
        Prop prop = new Prop();
        addCustomProperties(prop, props);
        body.setProp(prop);
        return propfind(url, depth, body);
    }

    @Override
    public List<DavResource> list(String url, int depth, boolean allProp) throws Exception {
        if (allProp) {
            Propfind body = new Propfind();
            body.setAllprop(new Allprop());
            return propfind(url, depth, body);
        } else {
            return list(url, depth, Collections.<QName>emptySet());
        }
    }

    @Override
    public String lock(String url) throws Exception {
        return lock(url, 0);
    }

    @Override
    public String lock(String url, int timeout) throws Exception {
        Lockinfo body = new Lockinfo();
        Lockscope scopeType = new Lockscope();
        scopeType.setExclusive(new Exclusive());
        body.setLockscope(scopeType);
        Locktype lockType = new Locktype();
        lockType.setWrite(new Write());
        body.setLocktype(lockType);

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/xml"), PepperClientUtil.toXml(body));

        Request.Builder builder = new Request.Builder()
                .url(url)
                .method("LOCK", requestBody);
        if (timeout > 0) {
            builder.header("Timeout", "Second-" + timeout);
        }
        Request request = builder.build();
        return execute(request, new LockResponseHandler());
    }

    @Override
    public void move(String sourceUrl, String destinationUrl) throws Exception {
        move(sourceUrl, destinationUrl, true);
    }

    @Override
    public void move(String sourceUrl, String destinationUrl, boolean overwrite) throws Exception {
        move(sourceUrl, destinationUrl, overwrite, null);
    }

    @Override
    public void move(String sourceUrl, String destinationUrl, boolean overwrite, String lockToken) throws Exception {
        Request.Builder builder = new Request.Builder()
                .url(sourceUrl)
                .method("MOVE", null);

        Headers.Builder headersBuilder = new Headers.Builder();
        headersBuilder.add("DESTINATION", destinationUrl);
        headersBuilder.add("OVERWRITE", overwrite ? "T" : "F");

        if (lockToken != null) {
            addLockTokenToHeaders(headersBuilder, destinationUrl, lockToken);
        }
        builder.headers(headersBuilder.build());
        Request request = builder.build();
        execute(request);
    }

    @Override
    public void put(String url, byte[] data) throws Exception {
        put(url, data, null);
    }

    @Override
    public void put(String url, byte[] data, String contentType) throws Exception {
        MediaType mediaType = contentType == null ? null : MediaType.parse(contentType);
        RequestBody requestBody = RequestBody.create(mediaType, data);
        put(url, requestBody);
    }

    @Override
    public void put(String url, File localFile, String contentType) throws Exception {
        put(url, localFile, contentType, false);
    }

    @Override
    public void put(String url, File localFile, String contentType, boolean expectContinue) throws Exception {
        put(url, localFile, contentType, expectContinue, null);
    }

    @Override
    public void put(String url, File localFile, String contentType, boolean expectContinue, String lockToken) throws Exception {
        MediaType mediaType = contentType == null ? null : MediaType.parse(contentType);
        RequestBody requestBody = RequestBody.create(mediaType, localFile);
        Headers.Builder headersBuilder = new Headers.Builder();
        if (expectContinue) {
            headersBuilder.add("Expect", "100-Continue");
        }
        if (localFile != null) {
            addLockTokenToHeaders(headersBuilder, url, lockToken);
        }
        put(url, requestBody, headersBuilder.build());
    }

    @Override
    public List<DavResource> search(String url, String language, String query) throws Exception {
        SearchRequest searchBody = new SearchRequest(language, query);
        String body = PepperClientUtil.toXml(searchBody);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/xml"), PepperClientUtil.toXml(body));
        Request request = new Request.Builder()
                .url(url)
                .method("SEARCH", requestBody)
                .build();

        return execute(request, new ResourcesResponseHandler());
    }

    @Override
    public void setCredentials(String username, String password) {
        OkHttpClient.Builder builder = client.newBuilder();
        builder.addInterceptor(new AuthenticationInterceptor(username, password));
        this.client = builder.build();
    }

    @Override
    public void unlock(String url, String token) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .method("UNLOCK", null)
                .header("Lock-Token", "<" + token + ">")
                .build();
        execute(request, new VoidResponseHandler());
    }

    private void addCustomProperties(Prop prop, Set<QName> props) {
        List<Element> any = prop.getAny();
        for (QName entry : props) {
            Element element = PepperClientUtil.createElement(entry);
            any.add(element);
        }
    }

    private void addLockTokenToHeaders(Headers.Builder headersBuilder, String destinationUrl, String lockToken) {
        headersBuilder.add("If", "<" + destinationUrl + "> (<" + lockToken + ">)");
    }

    /**
     * 异常抛出普遍为 {@link IOException}
     * 服务器如果未关闭 chunked 解码，可能会抛出 {@link ProtocolException}
     */
    private <T> T execute(Request request, ResponseHandler<T> responseHandler) throws Exception {
        Response response = client.newCall(request).execute();
        return responseHandler.handleResponse(response);
    }

    private void execute(Request request) throws Exception {
        execute(request, new VoidResponseHandler());
    }

    public InputStream get(String url, Headers headers) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .headers(headers)
                .build();
        return execute(request, new InputStreamResponseHandler());
    }

    private List<DavResource> propfind(String url, int depth, Propfind body) throws Exception {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/xml"), PepperClientUtil.toXml(body));
        Request request = new Request.Builder()
                .url(url)
                .header("Depth", depth < 0 ? "infinity" : Integer.toString(depth))
                .method("PROPFIND", requestBody)
                .build();
        return execute(request, new ResourcesResponseHandler());
    }

    private void put(String url, RequestBody requestBody) throws Exception {
        put(url, requestBody, new Headers.Builder().build());
    }

    private void put(String url, RequestBody requestBody, Headers headers) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .headers(headers)
                .build();
        execute(request);
    }

    private class AuthenticationInterceptor implements Interceptor {

        private String userName;
        private String password;

        public AuthenticationInterceptor(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder().addHeader("Authorization", Credentials.basic(userName,
                    password, PepperClientUtil.standardUTF8())).build();
            return chain.proceed(request);
        }
    }
}
