package brokenlinkstest;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import sun.net.www.protocol.http.HttpURLConnection;

public class VerifyLinks {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.ie.driver", "D:\\Sanjay\\Selenium_Training\\BrokenLinks\\Driver\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.co.in/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links are: " +links.size());
		
		for (int i=0; i<links.size(); i++)
		{
			WebElement wele = links.get(i);
			String url = wele.getAttribute("href");
			verifyActiveLinks(url);
		}
	}
	
	public static void verifyActiveLinks(String linkURL)
	{
		try
		{
			URL url = new URL(linkURL);
			HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode()==200)
			{
				System.out.println(linkURL+ "-" +httpURLConnect.getResponseMessage());
			}
			if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
			{
				System.out.println(linkURL+ "-" + httpURLConnect.getResponseMessage()+ " - "+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e)
		{}
	}

}
