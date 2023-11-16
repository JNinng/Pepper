package top.ninng.pepper.model;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:02
 * @Version 1.0
 */
public class InputStreamResponseHandler extends ValidatingResponseHandler<InputStream> {

    @Override
    public InputStream handleResponse(Response response) throws IOException {
        validateResponse(response);
        return response.body().byteStream();
    }
}
