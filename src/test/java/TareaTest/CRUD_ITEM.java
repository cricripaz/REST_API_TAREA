package TareaTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUD_ITEM {



    @Test
    public void CRUD_PROJECT(){




        System.out.println("-------------CREATE------------");

        String user = "upb_api@api.com";
        String pwd = "12345";

        int OK = 200;
        //CREATE

        JSONObject body_c = new JSONObject();

        body_c.put("Content","CristianJSON");

        Response response =
                given()
                .auth()
                .preemptive()
                .basic(user,pwd)
                .body(body_c.toString())
                .log().all()
                        .when()

                        .post("https://todo.ly/api/items.json");

        response.then()
                .statusCode(OK)
                .body("Content",equalTo("CristianJSON"))
                .log().all();




        int id_project = response.then().extract().path("Id");


        // ID = 11003328 , 11003331





        //READ

        System.out.println("-------------READ------------");
        response = given()
                .auth()
                .preemptive()
                .basic(user,pwd)
                .log().all()

                .when()
                .get("https://todo.ly/api/items.json");

        response.then()
                .statusCode(OK)
                .log().all();



        //UPDATE


        System.out.println("-------------UPDATE------------");

        body_c.put("Content","CristianJSON_UPDATE");

        response = given()
                .auth()
                .preemptive()
                .basic(user,pwd)
                .body(body_c.toString())
                .log().all()

                .when()
                .put("https://todo.ly/api/items/"+id_project+".json");

        response.then()
                .statusCode(OK)
                .body("Content",equalTo("CristianJSON_UPDATE"))
                .log().all();





        System.out.println("-------------DELETE------------");

        response = given()
                .auth()
                .preemptive()
                .basic(user,pwd)
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/11003342.json");

       //ITEM A ELIMINAR ID =  11003342

        response.then()
                .statusCode(OK)
                .body("Content",equalTo("CristianJSON"))
                .body("Deleted",equalTo(true))
                .log().all();

















    }
}
