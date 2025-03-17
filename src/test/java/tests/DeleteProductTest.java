package tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
public class DeleteProductTest extends ReportTest {
    private static final String BASE_URI = "https://fakestoreapi.com";
    private ExtentTest test;
    private Response deleteProductResponse(String endpoint) {
        return SerenityRest.given()
                .baseUri(BASE_URI)
                .when()
                .delete(endpoint);
    }
    @Test
    public void deleteProductSuccessful () {
        test = extent.createTest("Test API Delete a Product Successful");
        int productID = 21;
        String endPoint = "/products/" + productID;
        Response response = deleteProductResponse(endPoint);
        test.info("Respuesta de la API: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        test.pass("El codigo de respuesta es: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado");
    }
    @Test
    public void deleteProductPassesFailedEndPoint (){
        test = extent.createTest("Test API Delete a Product Failed EndPoint");
      //  int productID=21;
        String endPoint = "/products";
        Response response= deleteProductResponse(endPoint);
        test.info("Respuesta de la API: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        test.pass("El codigo de respuesta es: "+statusCode);
        Assert.assertEquals(statusCode, 404);
        test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado" );

    }
    @Test
    public void deleteProductFailedID () {
        test = extent.createTest("Test API Delete a Product Failed ID");
        String productID = "EDS21";
        String endPoint = "/products/" + productID;
        Response response = deleteProductResponse(endPoint);
        test.info("Respuesta de la API: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        test.pass("El codigo de respuesta es: " + statusCode);
        Assert.assertEquals(statusCode, 400);
        test.log(Status.INFO, "El codigo de respuesta corresponde con el esperado");
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
