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
public class PostAddProductTest extends ReportTest {
    private static final String BASE_URI = "https://fakestoreapi.com";
    private static final String JSON_PATH = "src/main/resources/";
    private ExtentTest test;
    private String getJsonFileContent(String fileName) throws Exception {
        File jsonFile = new File(JSON_PATH + fileName);
        if (!jsonFile.exists()) {
            throw new Exception("El archivo JSON no se encuentra en la ruta especificada: " + jsonFile.getAbsolutePath());
        }
        return new String(Files.readAllBytes(Paths.get(jsonFile.getAbsolutePath())));
    }
    private Response postRequest(String endpoint, String jsonBody) {
        return SerenityRest.given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(endpoint);
    }
    @Test
    public void postAddProductSuccessful(){
        try{
            test = extent.createTest("Test API Post Add a New Product Succesful");
            String endpoint = "/products";
            String jsonBody = getJsonFileContent("postAddProduct.json");
            Response response= postRequest(endpoint,jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: " + statusCode);
            Assert.assertEquals(statusCode, 200);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado");
        }catch (Exception e) {
            test.log(Status.FAIL, "Error al leer el archivo o al hacer la peticion: " + e.getMessage());
            Assert.fail("Error al realizar la prueba: " + e.getMessage());
        }
        }
    @Test
    public void postAddProductFailedEndPoint (){
        try{
            test = extent.createTest("Test API Post Add a New Product Failed EndPoint");
            String jsonBody = getJsonFileContent("postAddProduct.json");
            String endPoint = "/productos";
            Response response=postRequest(endPoint,jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: "+statusCode);
            Assert.assertEquals(statusCode, 404);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );
        } catch (Exception e) {
            test.log(Status.FAIL, "Error al leer el archivo o al hacer la peticion: " + e.getMessage());
            Assert.fail("Error al realizar la prueba: " + e.getMessage());
        }
    }
    @Test
    public void postAddProductFailedBody () {
        try {
            test = extent.createTest("Test API Post Add a New Product Failed Body");
            String endPoint = "/products";
            String jsonBody = getJsonFileContent("postAddProductos.json");
            Response response = postRequest(endPoint, jsonBody);
            test.info("Respuesta de la API: " + response.getBody().asString());
            int statusCode = response.getStatusCode();
            test.pass("El codigo de respuesta es: " + statusCode);
            Assert.assertEquals(statusCode, 200);
            test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado");
        } catch (Exception e) {
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
