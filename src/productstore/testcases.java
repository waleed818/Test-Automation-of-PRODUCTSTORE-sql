package productstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 






public class testcases extends data    {
	
	 
	
	
	
	WebDriver driver = new EdgeDriver ();
	
	Connection con ;
	
	Statement stmt ;
	
	ResultSet rs ;

	@BeforeTest 
	
	public void geturl () throws SQLException {
		
		
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","root");
		
		driver.get("https://www.demoblaze.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	 @Test(priority = 1, enabled = true)

	 	public void AddNewRecord() throws SQLException {

	 	String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone,addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (818, 'Waleed', 'Samer', 'Omar', '  962797700235', '123 Main St', 'Los Angeles', 'Spain', 1370, 50000.00)";
	 			

	    stmt = con.createStatement();
	    int rowInserted = stmt.executeUpdate(query);
	
	 }
	
	
	 @Test(priority = 3,enabled = true)

	 	public void ReadData() throws SQLException {
		
	 		String query = "select * from customers where customerNumber =818 ";
	 		stmt = con.createStatement();

	 		rs = stmt.executeQuery(query);

	 		//System.out.println(rs);

	 		while (rs.next()) {
	 			int customerNumberInDataBase = rs.getInt("customerNumber");
	
	 		firstnameinDatabase = rs.getString("contactFirstName").toString().trim();
	 		lastnameindatabase = rs.getString("contactLastName").toString().trim ();
	 		username1 = firstnameinDatabase+lastnameindatabase ;
	
	 			
	 			
	 			
	 		}
	    }
	
	
	 @Test(priority = 2, enabled = true)

	 	public void updateData() throws SQLException {

	 		String query = "UPDATE customers SET contactLastName = 'Azam' WHERE customerNumber = 818;";

	 		stmt = con.createStatement();

	 		int rowInserted = stmt.executeUpdate(query);

	 	}
	  
	 
	 @Test(priority = 4, enabled = true)

	 	public void deleteData() throws SQLException {

	 		String query = "delete from customers where customerNumber =818";

	 		stmt = con.createStatement();

	 		int rowInserted = stmt.executeUpdate(query);

	 	}
	
	
	 
	    @Test (priority = 5,enabled = true)
	    
	    public void signup () throws InterruptedException {
	    	
	    	// press on the signup botton 
	    	
	    	WebElement signupbotton = driver.findElement(By.cssSelector("#signin2"));
	    	signupbotton.click();
	    	
	    	
	    	// fill the username and password of signup 
	    	
	    	WebElement username = driver.findElement(By.cssSelector("#sign-username"));
	    	WebElement password = driver.findElement(By.cssSelector("#sign-password"));
	    	WebElement signupbotton1 = driver.findElement(By.cssSelector("button[onclick='register()']"));
	    	
	    	
	    	username.sendKeys(username1);
	    	password.sendKeys(pass);
	    	signupbotton1.click();
	    	
	    	Thread.sleep(5000);
	    	//تعريف الاليرت
	    	Alert myalrt = driver.switchTo().alert();
	    	// طبعا النص الي جوا الاليرت 
	    	String alerttext = myalrt.getText();
	    	System.out.println(alerttext);
	    	
	    	// قبول الاليرت
	    	Thread.sleep(5000);
	    	myalrt.accept();
	    	
	    }
	
	    @Test (priority = 6,enabled = true)
	    
	    public void login () {
	    	
	    	// press on the log in botton 
	    	
	    	WebElement loginbotton = driver.findElement(By.cssSelector("#login2"));
	    	loginbotton.click();
	    	
	    	
	    	// fill the username and password of login  
	    	
	    	WebElement usernamelogin = driver.findElement(By.cssSelector("#loginusername"));
	    	WebElement passowrdlogin = driver.findElement(By.cssSelector("#loginpassword"));
	    	WebElement loginclick = driver.findElement(By.cssSelector("button[onclick='logIn()']"));
	    	
	    	usernamelogin.sendKeys(username1);
	    	passowrdlogin.sendKeys(pass);
	    	loginclick.click();
	    	
	    	boolean actullogin = driver.getPageSource().contains(Theexpectedlogin);
	    	boolean Theexpectedlogin = true ;
	    	
	    	Assert.assertEquals(actullogin, Theexpectedlogin);
	    	
	    	System.out.println("login sucssefully");
	    }
	
	
	    @Test (priority = 7,enabled = true)
	    
	    public void additems () throws InterruptedException {
	    	
	    	Random rand = new Random ();
	    	Thread.sleep(2000);
	    	
	    	List <WebElement> additems = driver.findElements(By.cssSelector(".card-img-top.img-fluid"));
	         int randomitems = rand.nextInt(additems.size());
	
	    	 additems.get(randomitems).click();
	    	  Thread.sleep(2000);
	    		driver.findElement(By.linkText("Add to cart")).click();
	    		Thread.sleep(3000);
	    		Alert myalrt = driver.switchTo().alert();
	    		String alerttext = myalrt.getText();
	    		
	    	System.out.println(alerttext);
	    		
	    	Thread.sleep(3000);
	    	
	    	myalrt.accept();
	    		
	    		
	    	}
	    	
	    	
	    	@Test (priority = 8,enabled = true)
	    	
	    	public void removeitem () throws InterruptedException {
	    		
	    		Thread.sleep(3000);
	    		//go to the cart
	    		WebElement cartbotton = driver.findElement(By.cssSelector("#cartur"));
	    		cartbotton.click();
	    		
	    		//delete items to the cart 
	    		Thread.sleep(2000);
	    		WebElement delete = driver.findElement(By.xpath("//a[normalize-space()='Delete']"));
	    		delete.click();
	    		
	    		System.out.println("The item has been deleted successfully..");
	    	}
	    	
	    	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
