package top.ninng.pepper.model;

import okhttp3.Response;
import okhttp3.ResponseBody;
import top.ninng.pepper.model.dav.Multistatus;
import top.ninng.pepper.utils.PepperClientUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:16
 * @Version 1.0
 */
public class MultiStatusResponseHandler extends ValidatingResponseHandler<Multistatus> {

    @Override
    public Multistatus handleResponse(Response response) throws IOException {
        super.validateResponse(response);

        ResponseBody body = response.body();
        if (body == null) {
            throw new PepperClientException("No entity found in response", response.code(), response.message());
        }
        return getMultistatus(body.byteStream());
    }

    /**
     * Helper method for getting the Multistatus response processor.
     *
     * @param stream The input to read the status
     * @return Multistatus element parsed from the stream
     * @throws IOException When there is a JAXB error
     */
    protected Multistatus getMultistatus(InputStream stream) throws IOException {
        return PepperClientUtil.unmarshal(Multistatus.class, stream);
    }
}
