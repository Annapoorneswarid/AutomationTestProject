package Page;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NikePage {
	WebDriver driver;
	By nSignIn = By.xpath("//*[@id=\"gen-nav-commerce-header-v2\"]/nav/div[1]/div/div[2]/nav/ul/li[4]/button/p");
	By nEmail = By.xpath("//*[@id=\"username\"]");
	By nMouseOvernew =By.xpath("//*[@id=\"gen-nav-commerce-header-v2\"]/nav/header/div/div/div[2]/nav/ul/li[1]/div/button");
	By nMouseArrival = By.xpath("//*[@id=\"vl-flyout-nav\"]/ul/li[2]/div[2]/div[1]/nav[1]/ul/li[1]/a");
	By nSheo = By.xpath("//*[@id=\"skip-to-products\"]/div[1]/div/figure/a[2]/div/img");
	By nSize =By.xpath("//*[@id=\"buyTools\"]/div[1]/fieldset/div/div[11]/label");
	By naddCart = By.xpath("//*[@id=\"floating-atc-wrapper\"]/div[2]/button[1]");
	By nCart = By.xpath("//*[@id=\"gen-nav-commerce-header-v2\"]/nav/header/div/div/div[3]/div/a[3]/span");
	By nSortItem = By.xpath("//*[@id=\"dropdown-btn\"]");
	public NikePage(WebDriver driver)
	{
		this.driver=driver;
	}
	@Test
	public void titleVerification()
	{
		String ActTitle = driver.getTitle();
		System.out.println("Actual Title : "+ActTitle);
		String ExpTitle = "nike";
		
		if(ActTitle.equals(ExpTitle))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
	}
	
	@Test
	public void contentVerification()
	{
		String actCntent = driver.getPageSource();
		System.out.println("Content : "+actCntent);
		String expContent = "<html><head></head><body></body></html>";
		
		if(actCntent.equals(expContent))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	}
	
	@Test
	public void Signin(String email)
	{
		driver.findElement(nSignIn).click();
		driver.findElement(nEmail).sendKeys(email);
		driver.navigate().back();
		driver.navigate().back();
	}
	
	@Test
	public void mouseOver()
	{
		WebElement mouseOver = driver.findElement(nMouseOvernew);
		Actions a = new Actions(driver);
		a.moveToElement(mouseOver);  
		a.perform();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(nMouseArrival));
		driver.findElement(nMouseArrival).click();		
	}
	
	@Test
	public void cart()
	{
		driver.findElement(nSheo).click();
		driver.findElement(nSize).click();
		driver.findElement(naddCart).click();
		driver.findElement(nCart).click();
	}
	
	@Test
	public void displayLinks()
	{
		List<WebElement> li = driver.findElements(By.tagName("a"));
		for(WebElement s:li)
		{
			String link =s.getAttribute("href");
			verify(link);
			
		}
	}
	public void verify(String link)
	{
	try
	{
		URL ob = new URL(link);
		HttpURLConnection con = (HttpURLConnection)ob.openConnection();
		
		con.connect();
		
		if(con.getResponseCode()==200)
		{
			System.out.println("valid"+link);
		}
		else if(con.getResponseCode()==404)
		{
			System.out.println("broken link"+link);
		}
	
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	}
	
	@Test
	public void screenshot() throws IOException
	{
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,new File("D:/nike1.png"));
	}
	
	@Test
	public void dropDown()
	{
		WebElement sort=driver.findElement(nSortItem);
		Select Sortitem = new Select(sort);
		Sortitem.selectByValue("2");
		List<WebElement> li=Sortitem.getOptions();
		System.out.println("Sort Item count :"+li.size());
	}

}
