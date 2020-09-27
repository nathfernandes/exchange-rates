package configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.ProjectConstants;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DateTimeFormatter;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {
    private ExtentReports extentReports;
    private ExtentSparkReporter extentSparkReporter;
    protected ExtentTest extentTest;
    private String timestamp;

    public TestBase(String test){
        this.timestamp = DateTimeFormatter.timestamp();
        this.extentSparkReporter = new ExtentSparkReporter(ProjectConstants.REPORT_PATH + "/" +
                timestamp + "/" + test + " " + "_Report.html");
        this.extentReports = new ExtentReports();
        this.extentReports.attachReporter(extentSparkReporter);
    }

    @BeforeMethod
    public void initializeReport(Method method){
        this.extentTest = this.extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        }else if(result.getStatus() == ITestResult.SUCCESS){
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        }else{
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
    }

    @AfterClass
    public void tearDown(){
        extentReports.flush();
    }
}
