package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assign1 {
	public WebDriver driver;
	 
@BeforeClass
public void navigation()
{      
       
	
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\chromedriver_win32 version 79\\chromedriver.exe");
		driver=new ChromeDriver();
		
}
@Test(priority=1)
public void url() {
	driver.get("https://amazon.in");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

@Test(priority=2)
public void search() throws InterruptedException {
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.sendKeys("Apple iphone XR (64GB) -Yellow");
	Thread.sleep(1000);
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    search.sendKeys(Keys.ARROW_DOWN ,Keys.ENTER);  
 
}
@Test(priority=3)

public void amazonprice() {
	driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
	Set<String> windows=driver.getWindowHandles();
	Iterator<String> it=windows.iterator();
	String parentwindow=it.next();
	String childwindow=it.next();
	//navigate to child window
	driver.switchTo().window(childwindow);
	WebElement amazonprize=driver.findElement(By.xpath("//span[@id='priceblock_ourprice']"));
	 amazonprize.getText();
	
	
}
@Test(priority=4)
public void flipKart() throws InterruptedException  {
	//open a new tab
	 driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	 ArrayList<String> tabs=new ArrayList<String> (driver.getWindowHandles());
	 driver.switchTo().window(tabs.get(0));
	 driver.get("https://flipkart.com");
	 driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
	 WebElement search= driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
	 search.sendKeys("Apple iphone XR (64GB) -Yellow");
	 Thread.sleep(1000);	
	 search.sendKeys(Keys.ARROW_DOWN ,Keys.ENTER); 
	 driver.findElement(By.xpath("//div[contains(text(),'Apple iPhone XR (Yellow, 64 GB)')]")).click();
	
	 WebDriverWait wait= new WebDriverWait(driver,20);
	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'53,999')]"))));
	 WebElement flipkartprize=driver.findElement(By.xpath("//div[contains(text(),'53,999')]"));
	 String value2=flipkartprize.getText(); 
	 
	 String valu2=value2.replaceAll("[\\D]", "");
	 System.out.println(valu2);
	
	 int val2=Integer.parseInt(valu2);
	 driver.switchTo().window(tabs.get(1));
	 WebElement amazonprize=driver.findElement(By.xpath("//span[@id='priceblock_ourprice']"));
	 String value1 = amazonprize.getText();
	 String valu1=value2.replaceAll("[\\D]", "");
	 System.out.println(valu1);
	 int val1=Integer.parseInt(valu1);
	 if(val1>val2) {
			System.out.println("Flipkart has lesser prize");
	 }
		else if (val1==val2)
			{
				System.out.println("Both price are equal");
			}
		else {
		 System.out.println("Amazon has lesser prize");
		 
	 }
			}
	}

	 
	
