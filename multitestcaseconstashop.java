import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class multitestcaseconstashop {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
	private String wishText;

    @BeforeTest
    public void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("testing1.html");
        report = new ExtentReports();
        report.attachReporter(spark);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://constashop.com/index.php");
    }

    @Test
    public void verifyTitle() {
        test = report.createTest("Verify Title Test");
        String title = driver.getTitle();
        test.info("The Title is " + title);
        try {
            Assert.assertEquals(title, "Consta Shop");
            test.pass("The title verification passed.");
        } catch (AssertionError e) {
            test.fail("The title verification failed. Expected 'Consta Shop', but found: " + title);
            throw e;
        }
    }

    @Test
    public void verifyTitleFail() {
        test = report.createTest("Fail Title Test");
        String title = driver.getTitle();
        test.info("The Title is " + title);
        try {
            Assert.assertEquals(title, "Incorrect Title");
            test.pass("The title verification passed.");
        } catch (AssertionError e) {
            test.fail("The title verification failed. Expected 'Incorrect Title', but found: " );
            throw e;
        }
    }

    @Test
    public void search() {
        test = report.createTest("Search Functionality Test");
        try {
            WebElement searchBox = driver.findElement(By.className("search__input"));
            test.info("Located the search input box.");
            
            Assert.assertEquals(searchBox, "Search");  // Assuming the placeholder text is "Search"
            test.pass("Search input box found with correct placeholder text.");
        } catch (AssertionError e) {
            test.fail("Search input box placeholder text did not match.");
            throw e;
        }
    }

    @Test
    public void login() {
        test = report.createTest("Login Button Test");
        try {
            WebElement login = driver.findElement(By.xpath("//span[normalize-space()='Sign in']"));
            String loginText = login.getText();
            Assert.assertEquals(loginText, "Sign in");
            test.pass("Login button verified successfully.");
        } catch (AssertionError e) {
            test.fail("Login button text mismatch. Expected 'Sign in', but found: " + wishText);
            throw e;
        } catch (Exception e) {
            test.fail("Failed to locate the login button.");
            throw e;
        }
    }

    @Test
    public void wishTryCatch() {
        test = report.createTest("Wishlist Button Test");
        try {
            WebElement wish = driver.findElement(By.xpath("//li[@class='onhover-div wishlist-icon hny_top_links']//span[contains(text(),'Wish Lists')]"));
            String wishText = wish.getText();
            Assert.assertEquals(wishText, "Wish Lists1");
            test.pass("Wishlist button displayed correctly.");
        } catch (AssertionError e) {
            test.fail("assert-Wishlist button text mismatch. Expected 'Wish Lists', but found: " + wishText);
            throw e;
        } catch (Exception e) {
            test.fail("exception-Wishlist button not displayed as expected.");
            throw e;
        }
    }
    @Test
    public void  category1()
    {
    	test=report.createTest("catgeory chek");
    	try { 	
   WebElement cat=driver.findElement(By.cssSelector("body > header:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > nav:nth-child(1) > ul:nth-child(3) > li:nth-child(3) > a:nth-child(1)"));
  String cat1 = cat.getText();
	   Assert.assertEquals(cat1,"fff");
	    test.pass("tetsing pass");
	
} 
    	
    	catch (AssertionError e)
   {
		 test.skip("fail hogyaa");
	// TODO: handle exception
	throw e;
}
    	
    	
   
    
    }
   
    

    @AfterTest
    public void close() {
        driver.quit();
        report.flush();
    }
}
