package com.junit_project;

import java.time.Duration;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Zomato {
	
	static WebDriver driver;
	
	//precondition
	@BeforeClass
	public static void applicationLaunch() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-popups");
		options.addArguments("disable-notifications");
		driver = new EdgeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.zomato.com/");
		driver.manage().window().minimize();
	}
	
	@Before
	public void beforeMethod() {
		System.out.println("Started Executing Testcase.");
	}
	
	@After
	public void afterMethod() {
		System.out.println("Complete Executing Testcase.");
		System.out.println();
	}
	
	//checking if search box is displayed
	@Test
	public void method1() {
		WebElement searchBox = driver.findElement(By.xpath("//div[@class='searchContainer']"));
		if(searchBox.isDisplayed()) {
			System.out.println("Search Box is displayed");
		}
		else {
			System.out.println("Search Box is not displayed");
		}
	}
	
	
	//enter the food items in search box
	@Test
	public void method2() {
		WebElement enter = driver.findElement(By.xpath("//input[@placeholder='Search for restaurant, cuisine or a dish']"));
		enter.click();
		enter.sendKeys("Briyani");
	}


	//validate and printing the search options
	@Test
	public void method3() {
		try {
		List<WebElement> searchOptions = driver.findElements(By.xpath("//div[contains(@class,'sc-hgzKov sc-hCbubC fiHXXM')]"));
		for(int i=0;i<searchOptions.size();i++) {
			WebElement option = searchOptions.get(i);
			String text = option.getText();
			if(text.contains("Biryani - Delivery")) {
				System.out.println(text);
				option.click();
			}
		}
		}
		catch(Exception e) {
		}
	}

	//select a particular restaurant
	@Test
	public void method4() {
		WebElement restaurant = driver.findElement(By.xpath("//h4[contains(text(),'Al Faham')]"));
			restaurant.click();
	}
	
	//print the particular dish
	@Test
	public void method5() {
		List<WebElement> dishes = driver.findElements(By.xpath("//h4[starts-with(text(),'Chicken')]"));
		for(int i=0;i<dishes.size();i++) {
			WebElement dish = dishes.get(i);
			String dishName = dish.getText();
			if(dishName.contains("Biriyani"))
			System.out.println(dishName);
		}
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("Completed.");
		driver.close();
	}
	
}
