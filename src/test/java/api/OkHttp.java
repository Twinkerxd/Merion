package api;

import api.model.AuthResponse;
import api.model.CompanyResponse;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class OkHttp {
    public static final String TODOURL = "https://todo-app-sky.herokuapp.com/";
    public static final MediaType JSON = MediaType.get("application/json");

    public static final String swagger = "x-clients-be.onrender.com/docs/#";
    public static final String URL = "https://x-clients-be.onrender.com/";
    public static final String COMPANY = "company/";
    public static final String LOGIN = "auth/login";
    public static final String DELETE = "delete/";


    @Test
    public void toDo() throws IOException {
        //https://sky-todo-list.herokuapp.com/

        //client
        OkHttpClient client = new OkHttpClient();

        //request
        Request getAllTasksRequest = new Request.Builder().url(TODOURL).build();
        Response response = client.newCall(getAllTasksRequest).execute();

        String json = "{\"title\":\"My task OkHttp v1\",\"completed\":false}";
        RequestBody requestBody = RequestBody.create(json, JSON);

        Request createNewTaskRequest = new Request.Builder()
                .url(TODOURL)
                .post(requestBody)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();

        response = client.newCall(createNewTaskRequest).execute();
    }


    @Test
    public void xClients() throws IOException {
        //login
        XClientsWebClient xClientsWebClient = new XClientsWebClient();
        AuthResponse authResponse = xClientsWebClient.auth("leonardo", "leads");
        String token = authResponse.userToken();

        //create company
        xClientsWebClient.createNewCompany("Twinker company", "1613", token);

        //print companies
        List<String> companyNamesAndIds = xClientsWebClient.getCompanyNames();
        System.out.println(companyNamesAndIds);

        //delete company
        CompanyResponse response = xClientsWebClient.deleteCompany(649, token);
        System.out.println(response);
    }
}
