package ua.com.lits.automation.java.mavenTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	
	@Parameters ({ "browserName" })
	@BeforeMethod
	public void init(String browserName) throws Exception {
		gridDriver = new GridDriver();
		rmtWebDriver = gridDriver.getInstance(browserName);
		threadDriver.set(rmtWebDriver);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void afterMethod() {
		getDriver().close();
	}

	public WebDriver getDriver(){
		return threadDriver.get();
	}
	
	
	@Test (threadPoolSize = 4)
	public void f() throws InterruptedException {
		getDriver().get("https://my-hit.org");
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Wait.waitUntil(getDriver(), ".//form [@class and @method and @action]//input");
		getDriver().findElement(By.xpath(".//form [@class and @method and @action]//input")).sendKeys("Interstellar");
		Wait.waitUntil(getDriver(), ".//div[@class and @role]//form[@class and @method and @action]//button");
		getDriver().findElement(By.xpath(".//div[@class and @role]//form[@class and @method and @action]//button")).click();
		Wait.waitUntil(getDriver(), ".//div[@id='film-list']//div[1]//b//a");
		getDriver().findElement(By.xpath(".//div[@id='film-list']//div[1]//b//a")).click();
		
		((JavascriptExecutor) getDriver()).executeScript("scroll(0,"+ getDriver().findElement(By.xpath(".//*[@id='hitplayer_inner']/../../following-sibling::*[1]")).getLocation() +");");
		System.out.println("---------------------------1 - Sikuli----------------------------");
		SikuliImageRecognition imageRecognition = new SikuliImageRecognition();
		System.out.println("---------------------------2 - Sikuli----------------------------");
		Thread.sleep(5000);
		imageRecognition.clickOnImage("Matthew_McConaughey.png");
		System.out.println("---------------------------4 - Sikuli----------------------------");
		Thread.sleep(5000);
		Wait.waitUntil(getDriver(), ".//div[@class and @style]//h4[contains(text(),'Matthew McConaughey')]");
		Assert.assertEquals("Matthew McConaughey", getDriver().findElement(By.xpath(".//div[@class and @style]//h4[contains(text(),'Matthew McConaughey')]")).getText().toString());
	}
}
