package top.ninng.pepper.model;

import java.io.IOException;
import java.util.Locale;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:47
 * @Version 1.0
 */
public class PepperClientException extends IOException {

    private final int statusCode;
    private String responsePhrase;

    public PepperClientException(String msg, int statusCode, String responsePhrase) {
        super(msg);
        this.statusCode = statusCode;
        this.responsePhrase = responsePhrase;
    }

    @Override
    public String getMessage() {
        return String.format(Locale.US, "%s (%d %s)", super.getMessage(), this.getStatusCode(), this.getResponsePhrase());
    }

    /**
     * 服务器返回的响应短语
     *
     * @return 如果未知，则为空
     */
    public String getResponsePhrase() {
        return this.responsePhrase;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
