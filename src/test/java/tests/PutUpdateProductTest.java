package tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
public class PutUpdateProductTest extends ReportTest{
    private static final String BASE_URI = "https://fakestoreapi.com";
    private static final String JSON_PATH = "src/main/resources/";
    private ExtentTest test;
    private String getJsonFileContent() throws Exception {
        File jsonFile = new File(JSON_PATH + "putUpdateProduct.json");
        if (!jsonFile.exists()) {
            throw new Exception("El archivo JSON no se encuentra en la ruta especificada: " + jsonFile.getAbsolutePath());
        }
        return new String(Files.readAllBytes(Paths.get(jsonFile.getAbsolutePath())));
    }
    private Response putRequest(String endpoint, String jsonBody) {
        return SerenityRest.given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .put(endpoint);
    }
    @Test
    public void putUpdateProductSuccessful(){
        try{
            test = extent.createTest("Test API Put Update Product Successful");
            int productID=20;
            String endPoint = "/products/"+productID;
            String jsonBody = getJsonFileContent();
            Response response=putRequest(endPoint,jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: "+statusCode);
            Assert.assertEquals(statusCode, 200);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );
        }catch (Exception e) {
            test.log(Status.FAIL, "Error al leer el archivo o al hacer la peticion: " + e.getMessage());
            Assert.fail("Error al realizar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void putUpdateProductFailedEndPoint () {
        try {
            test = extent.createTest("Test API Put Update Product Failed EndPoint");
            int productID = 33669;
            String endPoint = "/products"+productID;
            String jsonBody = getJsonFileContent();
            Response response = putRequest(endPoint, jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: " + statusCode);
            Assert.assertEquals(statusCode, 404);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error al leer el archivo o al hacer la peticion: " + e.getMessage());
            Assert.fail("Error al realizar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void putUpdateProductFailedID (){
        try{
            test = extent.createTest("Test API Put Update Product Failed ID");
            String productID="AA33669";
            String endPoint = "/products/"+productID;
            String jsonBody = getJsonFileContent();
            Response response=putRequest(endPoint,jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: "+statusCode);
            Assert.assertEquals(statusCode, 400);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );
        }catch (Exception e) {
            test.log(Status.FAIL, "Error al leer el archivo o al hacer la peticion: " + e.getMessage());
            Assert.fail("Error al realizar la prueba: " + e.getMessage());
        }

    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Fue Fallido el Test");
        } else {
            test.log(Status.PASS, "Fue exitoso el Test");
        }
        extent.flush();
    }
}
