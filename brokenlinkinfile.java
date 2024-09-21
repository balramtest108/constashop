
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class brokenlinkinfile {

    
    public static String checkLink(String linkUrl) {
        try {
            
            URL url = new URL(linkUrl);
            
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            
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
       
        WebDriver driver = new ChromeDriver();

       
        String url = "https://constashop.com/";
        
        driver.get(url);

        
        List<WebElement> links = driver.findElements(By.tagName("a"));

        try (FileWriter writer = new FileWriter("constashop_broken_links.txt")) {

            
            for (WebElement link : links) {
                String linkUrl = link.getAttribute("href");
                if (linkUrl != null && !linkUrl.isEmpty()) {
                    String status = checkLink(linkUrl);
                    if (!status.equals("OK")) {
                      
                        writer.write("Link: " + linkUrl + " is " + status + "\n");
                    }
                }
            }

            System.out.println("Broken links have been stored on consatshop- broken_links.txt");

        } catch (IOException e) {
            System.out.println("Error in writing to file: " + e.getMessage());
        }

        driver.quit();
    }
}
