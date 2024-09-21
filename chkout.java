


	
		// TODO Auto-generated method stub
		import java.time.Duration;

		import org.openqa.selenium.By;

		import org.openqa.selenium.JavascriptExecutor;
		import org.openqa.selenium.Keys;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.Select;
		import org.testng.Assert;
		import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
		
		public class chkout {
		public static void main(String[] args) throws InterruptedException {

		
			WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		ExtentSparkReporter sp = new ExtentSparkReporter("fullflow.html");
		
			
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
			WebElement ck=driver.findElement(By.xpath("//input[@type='checkbox']"));
				 JavascriptExecutor js  =(JavascriptExecutor) driver;
			 js.executeScript("arguments[0].scrollIntoView();",ck);
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
			
						Thread.sleep(3000);
			 WebElement con1=driver.findElement(By.name("newShippingAddressState"));
			 Select state=new Select(con1);
			state.selectByValue("Bihar");
			String selctedstate=contry.getFirstSelectedOption().getText();
			System.out.println(selctedstate);
			
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
		
		}	


	}


