package automation.linkedinLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LinkedinActions extends ReusableMethods {

	public static void main(String[] args) throws Exception {

		LinkedinActions actions=new LinkedinActions();
		
		// TC-01 Login to Linkedin application with valid Username(Email) and Password
		//actions.login_validUnPwd();
		// TC-02 Verify login with invalid Username(Email) and Password 
		//actions.login_invalidUnPwd();
		// TC-03 Verify login with blank Username(Email) and Password
		//actions.login_emptyUnPwd();
		// TC-04 Verify the functionality of Forgot password link 
		//actions.forgotPwd_link();
		// TC-05 Verify the functionality of Show password button
		//actions.showPwd();
		// TC-06 Verify that the "Keep me logged in" checkbox function correctly 
	//	actions.checkbox();
		//actions.readExcel("G:\\Sunny\\SunnyLinkedInProject\\linkedinLogin\\Excelsheets\\LinkedinLoginTestCases.xlsx");
//		actions.readAndPerform("openBrowser", "chrome", "");
//		actions.readAndPerform("navToURL", "https://www.linkedin.com/", "");
//		actions.readAndPerform("click","//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']", "");
		actions.readtestcases("G:\\\\Sunny\\\\SunnyLinkedInProject\\\\linkedinLogin\\\\Excelsheets\\\\LinkedinLoginTestCases.xlsx");
	//	actions.readTestSteps("G:\\Sunny\\SunnyLinkedInProject\\linkedinLogin\\Excelsheets\\LinkedinLoginTestCases.xlsx");
	}
	
	public void login_validUnPwd() {
		//Open Chrome Browser
		openBrowser("chrome");
		// Access Linkedin URL.
		navToURL("https://www.linkedin.com/");
		//Click on Sign in button.
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		// Enter valid Email or phone
		input("username", "inturichetansai@gmail.com");
		//Enter valid Password 
		input("password", "Chetan@123");
		// Uncheck the Keep me logged in button.
		click("//*[@for='rememberMeOptIn-checkbox']");
		//Click on Sign in button.
		click("//*[@aria-label='Sign in']");
		// Verify successful login  
		verify_text("//*[@class='t-16 t-black t-bold']", "Inturi Chaitanya Sai");
	}
	
	public void login_invalidUnPwd() {
		// Open Chrome Browser
		openBrowser("chrome");
		// Access Linkedin URL.
		navToURL("https://www.linkedin.com/");
		//Click on Sign in button.
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		// Enter invalid Email or phone
		input("username", "inturichetani@gmail.com");
		//Enter invalid Password 
		input("password", "Chet123");
		// Uncheck the Keep me logged in button.
		click("//*[@for='rememberMeOptIn-checkbox']");
		//Click on Sign in button.
		click("//*[@aria-label='Sign in']");
	}
	
	public void login_emptyUnPwd() {
        // 1. Open Chrome Browser
		openBrowser("chrome");
		// 2. Access Linkedin URL.
		navToURL("https://www.linkedin.com/");
		// 3. Click on Sign in button.
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		// 4. Leave both the username and password fields empty.
		// 5. Click on Sign in button.
		click("//*[@aria-label='Sign in']");
	}
	
	public void forgotPwd_link() {
		//1. Open Chrome Browser
		openBrowser("chrome");
		//2. Access Linkedin URL.
		navToURL("https://www.linkedin.com/");
		//3. Click on Sign in button.
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		//4. Click on Forgot password link"
		click("//*[@class='btn__tertiary--medium forgot-password']");
	}
	
	public void showPwd() {
		//1. Open Chrome Browser
		openBrowser("chrome");
		//2. Access Linkedin URL.
		navToURL("https://www.linkedin.com/");
		//3. Click on Sign in button. 
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		//4. Enter valid Password
		input("password", "Chet123");
		//5. Click on show button
		click("//*[@id='password-visibility-toggle']");
	}
	
	public void checkbox() throws Exception {
		// Open Chrome Browser
		openBrowser("chrome");
		// Access Linkedin URL
		navToURL("https://www.linkedin.com/");
		// Click on Sign in button
		click("//*[@class='nav__button-secondary btn-secondary-emphasis btn-md']");
		// Enter valid Email or phone
		input("username", "inturichetansai@gmail.com");
		// Enter valid Password 
		input("password", "Chetan@123");
		// Check the "Keep me logged in" box
		is_selected("//*[@for='rememberMeOptIn-checkbox']");
		// Click on Sign in button.
		click("//*[@aria-label='Sign in']");
		// Click on profile icon
		Thread.sleep(5000);
		click("//*[@class='global-nav__me-photo evi-image ember-view']");
		// Logout of Linkedin
		//Scroll into view
		WebElement element = driver.findElement(By.xpath("//*[@id='ember17']/div/ul/li[3]/a"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		click("//*[@id='ember17']/div/ul/li[3]/a");
		
		//*[@id="ember17"]/div/ul/li[3]/a
		//*[@class='global-nav__secondary-link mv1']
		
		//*[@id="ember17"]/div/ul/li[2]/ul/li[2]/a
		
	}
}
