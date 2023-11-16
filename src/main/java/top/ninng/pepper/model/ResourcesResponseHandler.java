package top.ninng.pepper.model;

import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ninng.pepper.model.dav.Multistatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2023/11/8 10:15
 * @Version 1.0
 */
public class ResourcesResponseHandler implements ResponseHandler<List<DavResource>> {

    private static final String TAG = ResourcesResponseHandler.class.getSimpleName();
    private static final Logger log = LoggerFactory.getLogger(ResourcesResponseHandler.class);

    @Override
    public List<DavResource> handleResponse(Response response) throws IOException {
        Multistatus multistatus = new MultiStatusResponseHandler().handleResponse(response);
        List<top.ninng.pepper.model.dav.Response> davResponses = multistatus.getResponse();
        List<DavResource> resources = new ArrayList<>(davResponses.size());
        for (top.ninng.pepper.model.dav.Response davResponse : davResponses) {
            try {
                resources.add(new DavResource(davResponse));
            } catch (URISyntaxException e) {
                log.warn(TAG, String.format("Ignore resource with invalid URI %s", davResponse.getHref()/*.get(0)*/));
            }
        }
        return resources;
    }
}
