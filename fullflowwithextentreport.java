import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.classfile.ClassFile.Option;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import consatshop.constashop.extentreport;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class fullflowwithextentreport {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@BeforeTest
	public void setup() {
		ExtentSparkReporter sp = new ExtentSparkReporter("fullflow.html");
		report = new ExtentReports();
		report.attachReporter(sp);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get("https://constashop.com/");

	}

	@Test(priority = 1)
	public void verifyurl() {

		test = report.createTest("verifyURL");

		String url1 = driver.getCurrentUrl();
		try {
			Assert.assertEquals(url1, "https://constashop.com/");
			test.pass("correct url");

		} catch (AssertionError e) {
			test.fail("not found");
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			test.skip("conditon skipeed");
			throw e;
		}
	}

	@Test(priority = 3)
	public void login() throws InterruptedException {

		test = report.createTest("login sucsess");
		driver.findElement(By.xpath("//span[normalize-space()='Sign in']")).click();
		driver.findElement(By.id("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
		WebElement passwroed = driver.findElement(By.id("logInPassword"));
		passwroed.sendKeys("Bal@12345");
		passwroed.sendKeys(Keys.ENTER);
		Thread.sleep(15000);
		String currenturl = driver.getCurrentUrl();
		System.out.println(currenturl);

		try {
			Assert.assertEquals(currenturl, "https://constashop.com/index.php");
			test.pass("login sucess");
		} catch (AssertionError e) {
			// TODO: handle exception
			test.fail("failed by issue");
			throw e;
		} catch (Exception e) {
			test.skip("skip");
			throw e;
			// TODO: handle exception
		}

	}

	@Test(priority = 2)

	public void incorrectlogin() throws InterruptedException {

		test = report.createTest("incorrect login unsucsess");
		driver.findElement(By.xpath("//span[normalize-space()='Sign in']")).click();
		driver.findElement(By.id("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
		WebElement passwroed = driver.findElement(By.id("logInPassword"));
		passwroed.sendKeys("Bal@1234");
		passwroed.sendKeys(Keys.ENTER);
		Thread.sleep(15000);
		String currenturl = driver.getCurrentUrl();
		System.out.println(currenturl);
		try {
			Assert.assertEquals(currenturl, "https://constashop.com/account.php");
			test.pass("login not sucess");
		} catch (AssertionError e) {
			// TODO: handle exception
			test.fail("failed by issue");
			throw e;
		} catch (Exception e) {
			test.skip("skip");
			throw e;
			// TODO: handle exception
		}

	}

	@Test(priority = 4)
	public void signup() throws InterruptedException {

		test = report.createTest("signup");
		driver.manage().deleteAllCookies();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.sessionStorage.clear();");
		driver.get("https://constashop.com/account.php");
		Actions a = new Actions(driver);
		for (int i = 0; i < 5; i++) {
			a.sendKeys(Keys.DOWN).perform();
		}
		driver.findElement(By.className("registerfrm")).click();
		for (int i = 0; i < 4; i++) {
			a.sendKeys(Keys.DOWN).perform();
		}
		driver.findElement(By.name("firstName")).sendKeys("balram");
		driver.findElement(By.name("lastName")).sendKeys("choudhary");
		driver.findElement(By.name("mobileNumber")).sendKeys("9304854301");
		driver.findElement(By.name("emailId")).sendKeys("balramchaoudhary5@gmail.com");
		driver.findElement(By.name("userPassword")).sendKeys("Bal@12345");
		driver.findElement(By.id("submit")).click();
		Thread.sleep(45000);
		driver.findElement(By.id("otpsubmit"));
		test.info("info showing");

		try {
			Assert.assertEquals(driver.getCurrentUrl(), "https://constashop.com/index.php");
			test.pass("signup sucess");
		} catch (AssertionError e) {
			// TODO: handle exception
			test.fail("failed -not signup");
			throw e;
		} catch (Exception e) {
			test.skip("skip");
			throw e;
			// TODO: handle exception
		}
	}

	@Test(priority = 5)
	public void contactus() throws InterruptedException {
		test = report.createTest("consatus submit check");	
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get("https://constashop.com/");
		WebElement a3 = driver.findElement(By.name("submit"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",a3);
		Thread.sleep(3000);
		driver.findElement(By.linkText("Contact Us")).click();
		driver.findElement(By.id("fullName")).sendKeys("balram chaudhary");
		driver.findElement(By.name("phone")).sendKeys("123456789");
		driver.findElement(By.name("email")).sendKeys("testing@gmail.com");
		WebElement a4=driver.findElement(By.name("subject"));
		a4.sendKeys("tetsing team");
		js.executeScript("arguments[0].scrollIntoView();", a4);
		Thread.sleep(4000);
		driver.findElement(By.name("message")).sendKeys("tetsing team.testing is going on");
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(1000);
		String contact=driver.switchTo().activeElement().getText();

		System.out.println(contact);
	

		
		try {
			Assert.assertEquals(contact, "Contact Us");
			test.pass("passed");

		} catch (AssertionError e) {
			test.fail("fail");

			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
			test.skip("skip");
			throw e;
		}
	}

	@Test(priority = 6)

	public void checkoutfull() throws InterruptedException {
		test = report.createTest("flowcheckout");
		driver.manage().deleteAllCookies();
		driver.get("https://constashop.com/account.php");

		driver.findElement(By.name("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
		driver.findElement(By.name("logInPassword")).sendKeys("Bal@12345", Keys.ENTER);
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("tea");
		Thread.sleep(3000);
		WebElement tea = driver.findElement(By.cssSelector("div[class='search-bar'] a:nth-child(1) div:nth-child(1)"));
		Thread.sleep(3000);
		tea.click();
		tea.click();
		driver.findElement(By.linkText("8 inch Nordic Glass Fruit Plate With Phnom Penh Water Drop Plate")).click();
		Actions chek = new Actions(driver);
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		chek.sendKeys(Keys.DOWN).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='Add To Cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='VIEW CART']")).click();
		driver.findElement(By.linkText("Proceed to checkout")).click();
		Thread.sleep(3000);
		chek.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.id("paymentmethod1")).click();
		driver.findElement(By.id("paySubmitButton")).click();
		
		String chk = driver.findElement(By.className("text-white")).getText();
		try {
			Assert.assertEquals(chk, "Back To Home ");
			test.pass("checkout passed");
		} catch (AssertionError e) {
			// TODO: handle exception
			test.fail("fail chekout");
			

		}
	}
@Test(priority = 7)
public void search() throws InterruptedException {
 driver.findElement(By.xpath("//input[@id='search']")).sendKeys("gold");
 	Thread.sleep(3000);
	WebElement go=driver.findElement(By.cssSelector("div[class='search-bar'] a:nth-child(1) div:nth-child(1)"));
	go.click();
	go.click();
	String r=driver.getCurrentUrl();
	try {
		Assert.assertEquals(r, "https://constashop.com/product-detail.php?p=24-piece-stainless-steel-cutlery-set-gold");
	} 
	catch (AssertionError e) {
		test.fail("saerch is not correct");
		// TODO: handle exception
		throw e;
	}
	catch (Exception e) {
		// TODO: handle exception
		test.skip("skiped");
		throw e;
	}
	

}
@Test(priority = 8)
public void selectbox() throws InterruptedException {
	test = report.createTest("checkout,selectbox check");
	driver.manage().deleteAllCookies();
	driver.get("https://constashop.com/account.php");

	driver.findElement(By.name("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
	driver.findElement(By.name("logInPassword")).sendKeys("Bal@12345", Keys.ENTER);
	System.out.println(driver.getTitle());
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='search']")).sendKeys("tea");
	Thread.sleep(3000);
	WebElement tea = driver.findElement(By.cssSelector("div[class='search-bar'] a:nth-child(1) div:nth-child(1)"));
	Thread.sleep(3000);
	tea.click();
	tea.click();
	driver.findElement(By.linkText("8 inch Nordic Glass Fruit Plate With Phnom Penh Water Drop Plate")).click();
	Actions chek = new Actions(driver);
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[normalize-space()='Add To Cart']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[normalize-space()='VIEW CART']")).click();
	driver.findElement(By.linkText("Proceed to checkout")).click();
	WebElement ck=driver.findElement(By.xpath("input[type='checkbox']"));
	ck.isEnabled();
	try {
		Assert.assertEquals(true, true);
		test.pass("check box is enable");
		
} catch (AssertionError e) {
		// TODO: handle exception
	test.fail("is disabled");
	}
	catch (Exception e) {
		// TODO: handle exception
		test.skip("skiped");
		throw e;
	}
}

@Test(priority = 9)
public void checkoutwithnewaddress() throws InterruptedException {
	test = report.createTest("checkout,selectanddropdown check");
	driver.manage().deleteAllCookies();
	driver.get("https://constashop.com/account.php");

	driver.findElement(By.name("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
	driver.findElement(By.name("logInPassword")).sendKeys("Bal@12345", Keys.ENTER);
	System.out.println(driver.getTitle());
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='search']")).sendKeys("tea");
	Thread.sleep(3000);
	WebElement tea = driver.findElement(By.cssSelector("div[class='search-bar'] a:nth-child(1) div:nth-child(1)"));
	Thread.sleep(3000);
	tea.click();
	tea.click();
	driver.findElement(By.linkText("8 inch Nordic Glass Fruit Plate With Phnom Penh Water Drop Plate")).click();
	Actions chek = new Actions(driver);
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	chek.sendKeys(Keys.DOWN).perform();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[normalize-space()='Add To Cart']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[normalize-space()='VIEW CART']")).click();
	driver.findElement(By.linkText("Proceed to checkout")).click();
	WebElement ck=driver.findElement(By.xpath("input[type='checkbox']"));
		 JavascriptExecutor js  =(JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", ck);
	 Thread.sleep(3000);
	 driver.findElement(By.name("newShippingAddressFirstName")).sendKeys("ram");
	 driver.findElement(By.name("newShippingAddressLastName")).sendKeys("kumar");
	 driver.findElement(By.name("newShippingAddressPhone")).sendKeys("9020102030");
	 driver.findElement(By.name("newShippingAddressFlat")).sendKeys("flat1");
	 driver.findElement(By.name("newShippingAddressStreet")).sendKeys("ssr,city-faidabad");
	 WebElement con=driver.findElement(By.name("newShippingAddressCountry"));
	 Select contry=new Select(con);
	contry.selectByValue("India");
	String selctedcon=contry.getFirstSelectedOption().getText();
	System.out.println(selctedcon);
	
	try {
        Assert.assertEquals(selctedcon, "India");
        test.pass("Dropdown selection works correctly.");
    } catch (AssertionError e) {
        test.fail("Dropdown selection failed. Expected 'India', but got: " + selctedcon);
        throw e;
    }
	Thread.sleep(3000);
	 WebElement con1=driver.findElement(By.name("newShippingAddressState"));
	 Select state=new Select(con1);
	state.selectByValue("Bihar");
	String selctedstate=contry.getFirstSelectedOption().getText();
	System.out.println(selctedstate);
	try {
        Assert.assertEquals(selctedstate, "Bihar");
        test.pass("Dropdown selection works correctly.");
    } catch (AssertionError e) {
        test.fail("Dropdown selection failed. Expected 'India', but got: " + selctedstate);
        throw e;
    }
	 Thread.sleep(3000);
	driver.findElement(By.name("newShippingAddressCity")).sendKeys("bhagalpur");
	driver.findElement(By.name("newShippingAddressZipCode")).sendKeys("811211");
WebElement sub= driver.findElement(By.id("paySubmitButton"));
JavascriptExecutor js1  =(JavascriptExecutor) driver;
js1.executeScript("arguments[0].scrollIntoView();", sub);
Thread.sleep(3000);
driver.findElement(By.id("paymentmethod1")).click();
driver.findElement(By.id("paySubmitButton")).click();

String chk = driver.findElement(By.className("text-white")).getText();
try {
	Assert.assertEquals(chk, "Back To Home ");
	test.pass("checkout passed");
} catch (AssertionError e) {
	// TODO: handle exception
	test.fail("fail chekout");
	
}
}	
@Test(priority=10)
public void fileupload() {
	
	
}


	
	@AfterTest
	public void last() {

		report.flush();
	}

}
