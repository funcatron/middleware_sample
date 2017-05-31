package funcatron.sample_middleware;

import funcatron.intf.MiddlewareProvider;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by dpp on 5/31/17.
 */
public class MiddlwareInstaller implements MiddlewareProvider {
    public BiFunction<InputStream, Map<Object, Object>, Map<Object, Object>> wrap(BiFunction<InputStream, Map<Object, Object>, Map<Object, Object>> biFunction) {

        return (inputStream, params) -> {

            HashMap<Object, Object> updatedMap = new HashMap<>(params);

            Object maybeParameters = params.get("query-params");

            if (null != maybeParameters && maybeParameters instanceof Map) {
                Map parameters = (Map) maybeParameters;
                // FIXME -- insert something into updatedMap

                updatedMap.put("decodedValue", "Hello World");
            } else {
                // return an error
                Map<Object, Object> ret = new HashMap<>();
                Map<String, Object> headers = new HashMap<>();
                ret.put("status", 500);
                headers.put("Content-Type", "text/plain");
                ret.put("headers", headers);
                ret.put("body", "Unable to get query params");
                return ret;
            }

            return biFunction.apply(inputStream, updatedMap);
        };
    }
}
