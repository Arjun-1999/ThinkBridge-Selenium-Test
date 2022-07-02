package a;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class aa {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		String checkboxPath = "//input[@type='checkbox']";
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\server\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://jt-dev.azurewebsites.net/#/SignUp");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		check drop-down contains English and Dutch....contains method was not working with xpath...so checking it as below
		driver.findElement(By.xpath("//div[@id='language']")).click();
		if (driver.getPageSource().contains("Dutch") && driver.getPageSource().contains("English")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse("Expected language options not found", true);
		}
//		supply login credentials
		driver.findElement(By.id("name")).sendKeys("Arjun");
		driver.findElement(By.id("orgName")).sendKeys("Arjun");
		driver.findElement(By.id("singUpEmail")).sendKeys("arjunpk006@gmail.com");
//		agree terms and conditions(js script executer is used as normal click was not working
		WebElement checkBox = driver.findElement(By.xpath(checkboxPath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkBox);
//		click on get started
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),' A welcome email has been sent. Please check your email. ')]")));
		driver.quit();
	}

}
