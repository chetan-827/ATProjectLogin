package seleniumJava.LinkdinLogin;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkdinLoginAutomation {

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		Scanner sc = new Scanner(System.in);

        System.out.println("please choose an option");
System.out.println("1. Sign IN");
//		System.out.println("Please enter 1:Chrome 2:Edge 3:Firefox");
//		int choice=sc.nextInt();
//		
//		System.out.println("2. Sign Up (New Registration)");
//	int choice = sc.nextInt();

//		try {
//
//			switch (choice) {
//			case 1:
//				signIn(driver, sc);
//				break;
//			case 2:
//				signUp(driver, sc);
//				break;
//			default:
//				System.out.println("Invalid choice! Please choose 1 or 2.");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			driver.quit();
//			sc.close();
//		}
		try {
			
		System.out.println("Logging to LinkdIn...");
		
		driver.get("https://www.linkedin.com/login");
		
		driver.manage().window().maximize();
		
		WebElement emailfield = driver.findElement(By.xpath("//*[@id='username']"));
		System.out.print("Email: ");
		String email = sc.next();
		emailfield.sendKeys(email);
		
		WebElement passwordField = driver.findElement(By.xpath("//*[@id='password']"));
		System.out.println("Password: ");
		String password=sc.next();
		passwordField.sendKeys(password);
		
		WebElement showbutton = driver.findElement(By.xpath("//*[@id='password-visibility-toggle']"));
		if (showbutton.isDisplayed() == false) {
			System.out.println("Show button is not displaying.");
		}
		WebElement SigninButton = driver.findElement(By.xpath("//*[@type='submit']"));
		SigninButton.click();
		
		Thread.sleep(5000);
		
		WebElement profileIcon = driver
				.findElement(By.xpath("//*[@class='global-nav__me-photo evi-image ember-view']"));
		if (profileIcon.isDisplayed()) {
			System.out.println("Login Successful!");
		} else {
			System.out.println("Login Failed.");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
			sc.close();
		}
	}
	
//	public static void signIn(WebDriver driver, Scanner sc) throws Exception {
//
//	}

//	public static void signUp(WebDriver driver, Scanner sc) throws InterruptedException {
//		System.out.println("Registrating a new account on LinkdIn...");
//
//		driver.get("https://www.linkedin.com/signup");
//
//		driver.manage().window().maximize();
//
//		WebElement emailfield = driver.findElement(By.xpath("//*[@name='email-or-phone']"));
//		System.out.print("Email: ");
//		String email = sc.next();
//		emailfield.sendKeys(email);
//
//		WebElement passwordfield = driver.findElement(By.xpath("//*[@id='password']"));
//		System.out.print("Password: ");
//		String password = sc.next();
//		passwordfield.sendKeys(password);
//
//		WebElement agreeButton = driver.findElement(By.xpath("//*[@id='join-form-submit']"));
//		agreeButton.click();
//
//		WebElement firstNameField = driver.findElement(By.xpath("//*[@id='first-name']"));
//		System.out.print("FirstName: ");
//		String firstName = sc.next();
//		firstNameField.sendKeys(firstName);
//
//		WebElement lastNameField = driver.findElement(By.xpath("//*[@id='last-name']"));
//		System.out.print("LastName: ");
//		String lastName = sc.next();
//		lastNameField.sendKeys(lastName);
//
//		WebElement continueButton = driver.findElement(By.xpath("//*[@id='join-form-submit']"));
//		continueButton.click();
//
//		WebElement phonenumField = driver.findElement(By.xpath("//*[@id='register-verification-phone-number']"));
//		System.out.print("PhoneNumber: ");
//		String phonenumber = sc.nextLine();
//		phonenumField.sendKeys(phonenumber);
//
//		WebElement submitButton = driver.findElement(By.xpath("//*[@id='register-phone-submit-button']"));
//		submitButton.click();
//		
//		Thread.sleep(4000);
//		
//		WebElement confirmationMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]"));
//        if (confirmationMessage.isDisplayed()) {
//            System.out.println("Registration Successful!");
//        } else {
//            System.out.println("Registration Failed.");
//        }
//	}

}
