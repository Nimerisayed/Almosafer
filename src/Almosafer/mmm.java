package Almosafer;
//import io.github.bonigarcia.wdm.WebDriverManager;

//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class mmm {
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		driver.manage().window().maximize();

		driver.get("https://global.almosafer.com/en");
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
	}

	@Test(groups = "eight")
	public void myTest() throws InterruptedException {
		driver.get(
				"https://www.almosafer.com/en/hotels/Douai/13-11-2023/14-11-2023/2_adult?placeId=ChIJH3HiTzfJwkcR8G1kgT7xCgQ&city=Douai&sortBy=LOWEST_PRICE");
		Thread.sleep(8000);
		Thread.sleep(8000);

		WebElement Container = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]"));

		List<WebElement> Prices = Container.findElements(By.className("Price__Value"));

		for (int i = 0; i < Prices.size(); i++) {

			String FirstPrice = Prices.get(0).getText();
			String LastPrice = Prices.get(Prices.size() - 1).getText();
			int FirstPriceAsNumber = Integer.parseInt(FirstPrice);

			int LastPriceAsNumber = Integer.parseInt(LastPrice);
			System.out.println(FirstPriceAsNumber);
			System.out.println(LastPriceAsNumber);

			Assert.assertEquals(FirstPriceAsNumber < LastPriceAsNumber, true);
		}
	}

	@Test(groups = "saven")
	public void checkingLoadPage() {
		driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]/div")).click();
		driver.findElement(By
				.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[1]/div/div/div/div/input"))
				.sendKeys("Dubai");
		driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button"))
				.click();
		Boolean loadingBar = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/section[1]/p[1]"))
				.isDisplayed();
		while (loadingBar) {

			try {
				driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/section[1]/p[1]")).isDisplayed();
				loadingBar = true;
			} catch (NoSuchElementException e) {
				loadingBar = false;
			}
		}
		softassert.assertEquals(loadingBar, Boolean.FALSE,"hu");
		softassert.assertAll();
		System.out.println("Loading is over");
	}
}
