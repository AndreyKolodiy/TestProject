import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import dto.RequestIntegersBody;
import dto.ResponseIntegersBody;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class RequestRandomTask implements Callable<List<Integer>> {
    private static final String API_URL = "https://api.random.org/json-rpc/2/invoke";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final RequestIntegersBody requestIntegersBody = new RequestIntegersBody() {{
        setId(42);
        setJsonrpc("2.0");
        setMethod("generateIntegers");
        setParams(new Params() {{
            setApiKey("f314489b-fadb-40aa-b107-753d8793f488");
            setN(10000);
            setMin(0);
            setMax(1);
            setReplacement(true);
        }});
    }};

    public List<Integer> call() throws Exception {
        OkHttpClient client = new OkHttpClient();

        String strReqIntBody = MAPPER.writeValueAsString(requestIntegersBody);

        RequestBody body = RequestBody.create(MEDIA_TYPE, strReqIntBody);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        Response response = client
                .newCall(request)
                .execute();

        ResponseIntegersBody responseIntegersBody = MAPPER.readValue(response.body().string(), ResponseIntegersBody.class);

        return Arrays.stream(responseIntegersBody
                .getResult()
                .getRandom()
                .getData())
                .boxed()
                .collect(Collectors.toList());
    }
}