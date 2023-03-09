package test.qot;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import okhttp3.RequestBody;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.JMock1Matchers.equalTo;


public class PetSTest {
   static final ResponseSpecification SUCCESS =  new ResponseSpecBuilder().expectStatusCode(200).build();
    static final ResponseSpecification success =  new ResponseSpecBuilder().expectStatusCode(415).build();
   static final RequestSpecification request =  new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/pet/")
           .build().filter(new AllureRestAssured());



    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Retrieve pet details by status")
    public void testThatIcanGetPetByStatus(String Status){
//        Response response =
        given()
                .spec(request)
                .when()
                .get("findByStatus?status="+Status)
                .then()
                .spec(SUCCESS)
                //.body("message", equalTo("Successfully! Pet has been retrieved."))
        ;

    }
    @Test
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the creation of a new pet")
    public void testThatIcanAddaPet(){
        given()
                .spec(request)
                //.filter(new AllureRestAssured())
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"leopard\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .post("https://petstore.swagger.io/v2/pet/")
                .then()
                .spec(success);

    }
//    @Test
//    public void testThatIcanUpdateaPet(){
//        given()
//                .spec(request)
//                .
//    }

}
