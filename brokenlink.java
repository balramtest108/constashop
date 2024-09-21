import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class brokenlink {

    // Method to check the status of the link
    public static String checkLink(String linkUrl) {
        try {
            // Create a URL object
            URL url = new URL(linkUrl);
            // Open connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            // Get the response code
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                return "OK";
            } else {
                return "Broken (Status code: " + responseCode + ")";
            }

        } catch (IOException e) {
            return "Broken (Error: " + e.getMessage() + ")";
        }
    }

    public static void main(String[] args) {
        // Set up WebDriver using WebDriverManager
        
        WebDriver driver = new ChromeDriver();

        String url = "https://constashop.com/"; 

        
        driver.get(url);

        // Find all the <a> tags (links) on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Loop through all the links
        for (WebElement link : links) {
            String linkUrl = link.getAttribute("href");
            if (linkUrl != null && !linkUrl.isEmpty()) {
                String status = checkLink(linkUrl);
                System.out.println("Link: " + linkUrl + " is " + status);
            }
        }

        // Close the browser
        driver.quit();
    }
}
