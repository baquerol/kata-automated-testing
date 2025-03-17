package tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
public class GetAllProductsTest extends ReportTest{
    private ExtentTest test;
    private static final String BASE_URI = "https://fakestoreapi.com";
    private Response getProductResponse(String endpoint) {
        return SerenityRest.given()
                .baseUri(BASE_URI)
                .when()
                .get(endpoint);
    }
    @Test
    public void getAllProductsSuccessful (){
        test = extent.createTest("Test API GET All Products Successful");
        String endPoint = "/products";
        Response response= getProductResponse(endPoint);
        test.info("Respuesta de la API: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        test.pass("El codigo de respuesta es: "+statusCode);
        Assert.assertEquals(statusCode, 200);
        test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );
    }
    @Test
    public void getAllProductsFailedEndPoint (){
        test = extent.createTest("Test API GET All Products Failed EndPoint");
        String endPoint = "/producto";
        Response response=getProductResponse(endPoint);
        test.info("La respuesta de la API es: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        test.pass("El codigo de respuesta es: "+statusCode);
        Assert.assertEquals(statusCode, 404);
        test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );
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
