package api.employee;

import api.MyCustomLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Tests {

    public static final String URL = "https://x-clients-be.onrender.com";
    public static final String COMPANY = "/company";
    public static final String LOGIN = "/auth/login";
    public static final String EMPLOYEE = "/employee";
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client;
    ObjectMapper mapper;
    String token;

    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void test() throws IOException {
//        getAllCompanies();
        token = getToken();
//        addNewCompany();
        //779
//        addEmployeeToCompany();
//        getAllEmployee(779);
        updateEmployeeInfo(430);

    }

    public void getAllCompanies() throws IOException {
        Request request = new Request.Builder().url(URL + COMPANY).get().build();
        Response response = client.newCall(request).execute();
    }

    public String getToken() throws IOException {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request.Builder().url(URL + LOGIN).post(requestBody).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        return mapper.readTree(body).get("userToken").asText();
    }

    public void addNewCompany() throws IOException {
        String json = """
                {
                  "name": "Twinker corp",
                  "description": "ave 1613"
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request request = new Request.Builder().url(URL + COMPANY).header("x-client-token", token).post(requestBody).build();
        Response response = client.newCall(request).execute();
    }

    public void getAllEmployee(int companyId) throws IOException {
        Request request = new Request.Builder().url(URL + EMPLOYEE + "?company=" + companyId).get().build();
        Response response = client.newCall(request).execute();
    }

    public void addEmployeeToCompany() throws IOException {
        String json = """
                {
                  "id": 1613,
                  "firstName": "Sergei",
                  "lastName": "Testowy",
                  "middleName": "Hz",
                  "companyId": 779,
                  "email": "example@gmail.com",
                  "url": "/lol",
                  "phone": "888-101-101",
                  "birthdate": "1992-06-08T13:46:38.998Z",
                  "isActive": true
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(URL + EMPLOYEE).header("x-client-token", token).post(requestBody).build();
        Response response = client.newCall(request).execute();
    }

    public void updateEmployeeInfo(int employeeId) throws IOException {
        String json = """
                {
                  "isActive": false
                }
                """;
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(URL + EMPLOYEE + "/" + employeeId).header("x-client-token", token).patch(requestBody).build();
        Response response = client.newCall(request).execute();
    }


}
