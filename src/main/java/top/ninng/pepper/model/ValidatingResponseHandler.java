package top.ninng.pepper.model;

import okhttp3.Response;

import java.io.IOException;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:45
 * @Version 1.0
 */
public abstract class ValidatingResponseHandler<T> implements ResponseHandler<T> {

    /**
     * 检查状态码在200到300之间的响应，否则抛出{@link PepperClientException}。
     *
     * @param response 检查目标
     * @throws PepperClientException 当状态码不可接受时
     */
    protected void validateResponse(Response response) throws PepperClientException {
        if (response != null && !response.isSuccessful()) {
            String message = null;
            try {
                message = "Error contacting " + response.request().url() + "\nbody::" + response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            throw new PepperClientException(message, response.code(), response.message());
        }
    }
}
