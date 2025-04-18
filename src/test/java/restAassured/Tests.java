package restAassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Tests {
    //    public static final String URL = "https://todo-app-sky.herokuapp.com";
    public static final String COMPANY = "/company";
    public static final String LOGIN = "/auth/login";
    public static final String DELETE = "/delete";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://x-clients-be.onrender.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void test() {
        //905

//        List<Company> list1 = given().get(COMPANY).then().extract().body().jsonPath().getList("", Company.class);
//        List<Company> list2 = given().get(COMPANY).jsonPath().getList("", Company.class);
//        int id = (int) given().get(COMPANY).then().extract().jsonPath().getList("id").get(0);
//        Assertions.assertEquals(590, id);
        addNewCompany();
//        response.then().body("id",equalTo("123"));
//        boolean isEquals = list1.equals(list2);
    }


    public String getToken() {
        String json = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;

        return given()
                .body(json)
                .contentType(ContentType.JSON)
                .when()
                .post(LOGIN)
                .then()
                .extract()
                .path("userToken");
    }

    public void addNewCompany() {
        String json = """
                {
                  "name": "Twinker restassured",
                  "description": "1613"
                }
                """;

        given()
                .when()
                .header("x-client-token", getToken())
                .body(json)
                .contentType(ContentType.JSON)
                .post(COMPANY)
                .then()
                .log().body();
    }

    public void getCompanyById(int id) {
        given()
                .when()
                .get(COMPANY + "/" + id)
                .then()
                .log().body()
                .body("name", Matchers.equalTo("Twinker restassured"));

    }

    public Company editCompanyById(int id) {
        String json = """
                {
                  "name": "NEW Twinker restassured",
                  "description": "ave 1613"
                }
                """;

        return given().when()
                .header("x-client-token", getToken())
                .body(json)
                .contentType(ContentType.JSON)
                .patch(COMPANY + "/" + id)
                .then()
                .extract().as(Company.class);
    }

    public Map<Integer, String> getAllCompaniesNamesAndIds() {
        ExtractableResponse<Response> extract = given()
                .when()
                .get(COMPANY)
                .then().statusCode(200)
                .extract();

        List<Integer> idList = extract.path("id");
        List<String> nameList = extract.path("name");

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < idList.size(); i++) {
            map.put(idList.get(i), nameList.get(i));
        }

        return map;
    }

    public void deleteCompaniesByText(String text) {
        List<Integer> idListToDelete = getAllCompaniesNamesAndIds().entrySet().stream().filter(x -> x.getValue().contains(text)).map(x -> x.getKey()).toList();
        idListToDelete.forEach(x -> System.out.print(x + " "));
        idListToDelete.forEach(x -> deleteCompanyById(x));
    }

    public void deleteCompanyById(int id) {
        given().when().header("x-client-token", getToken()).get(COMPANY + DELETE + "/" + id);
    }

}
