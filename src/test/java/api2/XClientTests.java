package api2;

import api.MyCustomLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XClientTests {
    public static final MediaType JSON = MediaType.get("application/json");
    String COMPANY_URL = "https://x-clients-be.onrender.com/company";
    String LOGIN_URL = "https://x-clients-be.onrender.com/auth/login";
    private OkHttpClient client;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void test() throws IOException {
        Request request = new Request.Builder().url(COMPANY_URL).get().build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();

        assertEquals(200, response.code());
        assertTrue(body.startsWith("["));
        assertTrue(body.endsWith("]"));
    }

    @Test
    public void ShouldReturn401WithoutToken() throws IOException {
        String json = """
                {
                  "name": "string",
                  "description": "string"
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request.Builder().url(COMPANY_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", body);
    }

    @Test
    public void ShouldReturn401WithoutValidToken() throws IOException {
        String json = """
                {
                  "name": "string",
                  "description": "string"
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request
                .Builder()
                .url(COMPANY_URL)
                .header("x-client-token", "NON-VALID-TOKEN")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", body);
    }

    @Test
    public void ShouldReturn201OnCompanyCreated() throws IOException {
        String jsonAuth = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;
        RequestBody requestBody = RequestBody.create(jsonAuth, JSON);
        Request request = new Request.Builder().url(LOGIN_URL).post(requestBody).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        JsonNode jsonNode = mapper.readTree(body);
        String token = jsonNode.get("userToken").asText();

        String json = """
                {
                  "name": "Twinker company",
                  "description": "ave 1613"
                }
                """;
        requestBody = RequestBody.create(json, JSON);

        request = new Request
                .Builder()
                .url(COMPANY_URL)
                .header("x-client-token", token)
                .post(requestBody)
                .build();

        response = client.newCall(request).execute();
        body = response.body().string();
        jsonNode = mapper.readTree(body);
        int id = jsonNode.get("id").asInt();

        assertEquals(201, response.code());
        assertTrue(id > 0);
    }
}
