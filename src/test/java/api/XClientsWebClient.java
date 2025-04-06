package api;

import api.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.OkHttp.*;

public class XClientsWebClient {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public XClientsWebClient() {
        mapper = new ObjectMapper();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new MyCustomLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
    }

    public AuthResponse auth(String login, String password) throws IOException {
        AuthRequest authRequest = new AuthRequest(login, password);
        String authReqBodyMapped = mapper.writeValueAsString(authRequest);
        RequestBody authReqBody = RequestBody.create(authReqBodyMapped, JSON);

        Request requestAuth = new Request.Builder().url(URL + LOGIN).post(authReqBody).build();
        Response responseAuth = client.newCall(requestAuth).execute();
        String responseAuthBody = responseAuth.body().string();
        AuthResponse authResponse = mapper.readValue(responseAuthBody, AuthResponse.class);

        return authResponse;
    }

    public int createNewCompany(String name, String description, String token) throws IOException {
        CreateNewCompanyRequest createNewCompanyRequest = new CreateNewCompanyRequest(name, description);
        String jsonRequest = mapper.writeValueAsString(createNewCompanyRequest);
        RequestBody requestBody = RequestBody.create(jsonRequest, JSON);

        Request createNewCompanyReq = new Request
                .Builder()
                .url(URL + COMPANY)
                .header("x-client-token", token)
                .post(requestBody)
                .build();
        Response createNewCompanyRes = client.newCall(createNewCompanyReq).execute();
        String jsonResponse = createNewCompanyRes.body().string();
        CreateNewCompanyResponse r = mapper.readValue(jsonResponse, CreateNewCompanyResponse.class);
        return r.id();
    }

    public List<String> getCompanyNames() throws IOException {
        Request request = new Request.Builder().url(URL + COMPANY).get().build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
//        List<CompanyResponse> listOfCompanies = mapper.readValue(jsonResponse, new TypeReference<List<CompanyResponse>>() {});
        CollectionType listOfCompanies2 = mapper.getTypeFactory().constructCollectionType(List.class, CompanyResponse.class);

        List<CompanyResponse> listOfCompanies = mapper.readValue(jsonResponse, listOfCompanies2);

        List<String> list = new ArrayList<>();
        for (CompanyResponse companyResponse : listOfCompanies) {
            list.add(companyResponse.name());
            list.add(String.valueOf(companyResponse.id()));
        }

        return list;
    }

    public CompanyResponse deleteCompany(int id, String token) throws IOException {
        Request request = new Request
                .Builder()
                .url(URL + COMPANY + DELETE + id)
                .header("x-client-token", token)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        CompanyResponse companyResponse = mapper.readValue(responseJson, CompanyResponse.class);
        return companyResponse;
    }


}
