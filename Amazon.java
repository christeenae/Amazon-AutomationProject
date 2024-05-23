package miniproject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Amazon {
 
		public WebDriver createDriver()
		{ 
			System.setProperty("webdriver.edge.driver","C:\\Users\\2317296\\eclipse-workspace\\Seleniumprojects\\browser\\msedgedriver.exe");
			WebDriver driver= new EdgeDriver();
			return driver;
		}

		public void searchTextEnter(WebDriver driver)
		{
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobile smartphones under 30000");
			driver.findElement(By.id("nav-search-submit-button")).click();
		}
		public void validateSearch(String searchMessageText)
		{
			if(searchMessageText.contains("mobile smartphones under 30000")) {
				System.out.println("Search message text is correct:"+searchMessageText);
			}
			else {
				System.out.println("Search message text is Incorrect:"+searchMessageText);
			}
		}

		public void validateCount(String searchMessageText )
		{
			String[] part = searchMessageText.split(" ");  //1-16 of over 1,000 results for "mobile smartphones under 30000"
			String pages = part[0];                        //1-16
			String itemText = part[3].replace(",","");     //"1000"
			String[] pagess = part[0].split("-");          // [1][16]
			int totalPages =Integer.parseInt(pagess[1]);

			if(totalPages>0) {
				System.out.println("Page range validated successfully:"+pages);
			}
			else {
				System.out.println("Page range validation failed:"+pages);
			}
			int itemCount = Integer.parseInt(itemText);
			if(itemCount > 0) {
				System.out.println("Successfully Validated the count:"+itemText);
			}
			else {
				System.out.println("Validation count Failed:"+itemText);
			}
		}

		public void sortByList(WebDriver driver)
		{
			driver.findElement(By.className("a-dropdown-label")).click();
			List<WebElement> sort=  driver.findElements(By.xpath("//*[@class='a-popover-inner'] //li[@class='a-dropdown-item']"));
			for(int i=0;i<sort.size();i++)
			{
				System.out.println(sort.get(i).getText());
			}
			System.out.println(sort.size());
			driver.findElement(By.id("s-result-sort-select_4")).click();
			WebElement selectNewArrival = driver.findElement(By.id("s-result-sort-select_4"));

			if(selectNewArrival.isEnabled())
			{
				System.out.println("Newest Arrival is Selected");
			}
			else {
				System.out.println("Newest Arrivals is not selected ");
			}
		}

		public static void main(String[] args)  {
			// TODO Auto-generated method stub
			Amazon pl = new Amazon();
			WebDriver driver= pl.createDriver();
			driver.manage().window().maximize();
			driver.get("https://www.amazon.in");
		    
		    try
			{
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			catch(Exception e)
			{
			  e.printStackTrace();
			}
		    
			pl.searchTextEnter(driver);
			WebElement searchMessage = driver.findElement(By.xpath("//*[@class='a-section a-spacing-small a-spacing-top-small'][1]"));
			String searchMessageText = searchMessage.getText();
			pl.validateSearch(searchMessageText);
	 
			pl.validateCount(searchMessageText);
			
		try {
			Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				 System.out.println("Interrupted");		
			}
		
			pl.sortByList(driver);
			
	 
			try {
				Thread.sleep(3000);
				}
				catch (InterruptedException e)
				{
					 System.out.println("Interrupted");		
				}
			driver.quit();
	  }
	}