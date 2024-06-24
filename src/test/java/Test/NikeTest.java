package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page.NikePage;

public class NikeTest {
	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		driver = new ChromeDriver();
	}
	public void url()
	{
		driver.get("https://www.nike.com/in/");
	}
	@Test
	public void test()
	{
		NikePage np = new NikePage(driver);
		np.titleVerification();
		np.contentVerification();
		np.Signin("annaammu2017@gmail.com");
		np.mouseOver();
		np.cart();
		np.displayLinks();
		np.dropDown();
	}
}
