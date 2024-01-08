package top.ninng.pepper.model;

import java.io.IOException;

import okhttp3.Response;

/**
 * 只执行请求并检查答案是否在有效范围内
 *
 * @Author OhmLaw
 * @Date 2023/11/8 9:45
 * @Version 1.0
 */
public class VoidResponseHandler extends ValidatingResponseHandler<Void> {

    @Override
    public Void handleResponse(Response response) throws IOException {
        validateResponse(response);
        return null;
    }
}
