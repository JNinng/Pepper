package top.ninng.pepper.model;

import java.io.IOException;

import okhttp3.Response;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 9:44
 * @Version 1.0
 */
public interface ResponseHandler<T> {

    T handleResponse(Response response) throws IOException;
}
