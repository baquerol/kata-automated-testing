package tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ReportTest {
    protected static ExtentReports extent;

    static {
        // Configuración del reporte
        String reportPath = "./Reporte/Tests_Report_Fakestoreapi_Products.html";  // Un solo archivo de reporte para todas las pruebas
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setEncoding("UTF-8");
        extent.attachReporter(sparkReporter);

    }
}

