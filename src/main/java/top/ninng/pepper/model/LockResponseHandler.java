package top.ninng.pepper.model;

import okhttp3.Response;
import okhttp3.ResponseBody;
import top.ninng.pepper.model.dav.Prop;
import top.ninng.pepper.utils.PepperClientUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author OhmLaw
 * @Date 2023/11/9 16:05
 * @Version 1.0
 */
public class LockResponseHandler extends ValidatingResponseHandler<String> {

    @Override
    public String handleResponse(Response response) throws IOException {
        validateResponse(response);
        ResponseBody body = response.body();
        if (body == null) {
            throw new PepperClientException("No entity found in response", response.code(), response.message());
        }

        return getToken(body.byteStream());
    }

    /**
     * Helper method for getting the Multistatus response processor.
     *
     * @param stream The input to read the status
     * @return Multistatus element parsed from the stream
     * @throws IOException When there is a JAXB error
     */
    protected String getToken(InputStream stream) throws IOException {
        Prop prop = PepperClientUtil.unmarshal(Prop.class, stream);
        return prop.getLockdiscovery().getActivelock().iterator().next().getLocktoken().getHref();
    }
}
