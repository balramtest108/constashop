/*
 * package consatshop.constashop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentreport {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void setup()
	{
		ExtentSparkReporter spark=new ExtentSparkReporter("reporter1.html");
		report=new ExtentReports();
		report.attachReporter(spark);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://constashop.com/index.php");
		
	}
	
@Test
public void veryfiytile()
{
	test=report.createTest("verify title testing");
	String title=driver.getTitle();
	test.info("The Title is " + title);
	Assert.assertEquals(title,"Consta Shop");
	test.pass("The title verification is passed");
	
	}
@Test
public void verifyelement() {
	// TODO Auto-generated method stub
	test=report.createTest("verifying present element testing");
	boolean elementdispaly = driver.findElement(By.className("1header-section")).isDisplayed();
		test.info("The element is being dispalyed"+ elementdispaly);
		try {
			Assert.assertTrue(elementdispaly);
			test.pass("The logo is  displaying");
		} catch (Exception e) {
			// TODO: handle exception
			test.fail("not correct");
			throw e;
			
		}
	
	
	}
@AfterTest
public void teardown()
{ driver.close();
report.flush();
	}

}
*/
package consatshop.constashop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentreport {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setting up Extent Report
        ExtentSparkReporter spark = new ExtentSparkReporter("reporter1.html");
        report = new ExtentReports();
        report.attachReporter(spark);

        // Initialize WebDriver and navigate to the site
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://constashop.com/index.php");
    }

    @Test
    public void verifyTitle() {
        test = report.createTest("Verify Title Test");
        String title = driver.getTitle();
        test.info("Page title is: " + title);

        // Check if the title is correct
        try {
            Assert.assertEquals(title, "Consta Shop");
            test.pass("The title verification passed.");
        } catch (AssertionError e) {
            test.fail("The title verification failed. Expected: 'Consta Shop' but got: " + title);
            throw e;
        }
    }

    @Test
    public void verifyElement() {
        test = report.createTest("Verify Element Test");

        // Simulating an incorrect condition to make the test fail
        boolean elementDisplayed = driver.findElement(By.className("header-section")).isDisplayed();
        test.info("The element's visibility status: " + elementDisplayed);

        // Force the test to fail
        try {
            Assert.assertFalse(elementDisplayed, "This condition will fail intentionally.");
            test.pass("The element is not displayed (this should not happen).");
        } catch (AssertionError e) {
            test.fail("Test failed intentionally. Element is displayed.");
            throw e;
        }
    }

    @Test
    public void forcedFailureTest() {
        test = report.createTest("Forced Failure Test");

        // Force a test failure using Assert.fail()
        test.info("Forcing a failure in this test.");
        Assert.fail("This test failed deliberately.");
        test.fail("This test was forced to fail.");
    }

    @AfterClass
    public void teardown() {
        // Close the browser and flush the report
        driver.quit();
        report.flush();
    }
}

