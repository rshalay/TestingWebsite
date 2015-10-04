package ua.com.lits.automation.java.mavenTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.GridDriver;
import framework.SikuliImageRecognition;
import framework.Wait;
import junit.framework.Assert;

public class VerifyActorNameTest {

	protected RemoteWebDriver rmtWebDriver;
	protected ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<RemoteWebDriver>();
	protected GridDriver gridDriver;

	@Parameters({ "browserName" })
	@BeforeMethod
	public void init(String browserName) throws Exception {
		gridDriver = new GridDriver();
		rmtWebDriver = gridDriver.getInstance(browserName);
		threadDriver.set(rmtWebDriver);
		getDriver().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {
		getDriver().close();
	}

	public WebDriver getDriver() {
		return threadDriver.get();
	}

	@Parameters({ "browserName" })
	@Test(threadPoolSize = 2, successPercentage = 5)
	public void f(String browserName) throws InterruptedException {
		getDriver().get("https://my-hit.org");

		getDriver().manage().window().maximize();
		if (browserName.equals("chrome")) {
			getDriver().manage().window().setPosition(new Point(0, 0));
			getDriver().manage().window().setSize(new Dimension(1366, 450));
		} else if (browserName.equals("firefox")) {
			getDriver().manage().window().setPosition(new Point(0, 350));
			getDriver().manage().window().setSize(new Dimension(1366, 450));
		}
		Wait.waitUntil(getDriver(), ".//form [@class and @method and @action]//input");
		getDriver().findElement(By.xpath(".//form [@class and @method and @action]//input")).sendKeys("Interstellar");
		Wait.waitUntil(getDriver(), ".//div[@class and @role]//form[@class and @method and @action]//button");
		getDriver().findElement(By.xpath(".//div[@class and @role]//form[@class and @method and @action]//button"))
				.click();
		Wait.waitUntil(getDriver(), ".//div[@id='film-list']//div[1]//b//a");
		getDriver().findElement(By.xpath(".//div[@id='film-list']//div[1]//b//a")).click();
		((JavascriptExecutor) getDriver()).executeScript("scroll(0," + getDriver()
				.findElement(By.xpath(".//*[@id='hitplayer_inner']/../../following-sibling::*[1]")).getLocation()
				+ ");");
		SikuliImageRecognition imageRecognition = new SikuliImageRecognition();
		imageRecognition.clickOnImage("Matthew_McConaughey.png");
		Wait.waitUntil(getDriver(), ".//div[@class and @style]//h4[contains(text(),'Matthew McConaughey')]");
		Assert.assertEquals("Matthew McConaughey",
				getDriver()
						.findElement(By.xpath(".//div[@class and @style]//h4[contains(text(),'Matthew McConaughey')]"))
						.getText().toString());

	}
}
