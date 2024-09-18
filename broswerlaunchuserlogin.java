package consatshop;

import java.io.File;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
@Test
	
public class broswerlaunchuserlogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriver driver=new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
driver.get("https://constashop.com/account.php");
	
driver.findElement(By.name("logInMobileNumber")).sendKeys("testdemo9304@gmail.com");
driver.findElement(By.name("logInPassword")).sendKeys("Bal@12345",Keys.ENTER);
System.out.println(driver.getTitle());
Thread.sleep(2000);
driver.findElement(By.xpath("//input[@id='search']")).sendKeys("tea");
Thread.sleep(3000);
WebElement tea = driver.findElement(By.cssSelector("div[class='search-bar'] a:nth-child(1) div:nth-child(1)"));
Thread.sleep(3000);
tea.click();
tea.click();
driver.findElement(By.linkText("8 inch Nordic Glass Fruit Plate With Phnom Penh Water Drop Plate")).click();
Actions chek=new Actions(driver);
chek.sendKeys(Keys.PAGE_DOWN).perform();
driver.findElement(By.xpath("//span[normalize-space()='Add To Cart']")).click();
driver.findElement(By.xpath("//span[normalize-space()='VIEW CART']")).click();
driver.findElement(By.linkText("Proceed to checkout")).click();
Thread.sleep(3000);
chek.sendKeys(Keys.PAGE_DOWN).perform();
driver.findElement(By.id("paymentmethod1")).click();
driver.findElement(By.id("paySubmitButton")).click();
File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
try {
	FileUtils.copyFile(screenshot,new File("D:\\chekout.png"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
System.out.println("order placed");

	}

}
