package top.ninng.pepper.model;

import okhttp3.Response;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:57
 * @Version 1.0
 */
public class ExistsResponseHandler extends ValidatingResponseHandler<Boolean> {

    @Override
    public Boolean handleResponse(Response response) throws PepperClientException {
        if (!response.isSuccessful() && response.code() == 404) {
            return false;
        }
        validateResponse(response);
        return true;
    }
}
