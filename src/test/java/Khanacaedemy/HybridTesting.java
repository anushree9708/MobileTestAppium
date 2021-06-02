package Khanacaedemy;


	
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import IBM.Appiumframework.Capabilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
public class HybridTesting extends Capabilities {
	AndroidDriver<AndroidElement> driver;
	 String fname= "Test";
	 String lname="Project";
	 String pass="Password@1";
	String num = RandomStringUtils.randomNumeric(5);
	String mail="testprj"+num+"@mail.com";

	

		@BeforeTest

		public void setup() throws IOException, InterruptedException

		{
			driver = capabilities(deviceName, appPackage, appActivity);
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Runtime.getRuntime().exec("adb kill-server");
			Thread.sleep(5000);
			Runtime.getRuntime().exec("adb start-server");
			Thread.sleep(5000);
			
		}
		
		
		

		@Test(priority=0)
		public void signup() throws InterruptedException, IOException {
			
			
			System.out.println("Start Signup");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.className("android.widget.Button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@content-desc=\"Dismiss\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@content-desc=\"Settings\"]")).click();
			
			driver.findElement(By.xpath("//*[@text=\"Sign in\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@content-desc=\"Sign up with email\"]")).click();
			
			driver.findElementByAccessibilityId("First name").sendKeys(fname);
			
			driver.findElementByAccessibilityId("Last name").sendKeys(lname);
			driver.findElement(By.xpath("//*[@text=\"Birthday\"]")).click();
			Thread.sleep(1000);
			
			driver.switchTo().alert().accept();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Email address\"]")).sendKeys(mail);
			
			driver.findElement(By.xpath("//*[@content-desc=\"Password\"]")).sendKeys(pass);
			driver.findElement(By.xpath("//*[@text=\"CREATE\"]")).click();
			signout();
		}
		
		
		public void signout() throws InterruptedException, MalformedURLException {
			System.out.println(" Signout");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@content-desc=\"Settings\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@text=\"Sign out\"]")).click();
			driver.findElement(By.id("android:id/button1")).click();
			Thread.sleep(2000);
		}
		
		@Test(priority=2 )
		public void login() throws InterruptedException, MalformedURLException {
			System.out.println("Login");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@text=\"Sign in\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@text=\"Sign in\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@text=\"Enter an e-mail address or username\"]")).sendKeys(mail);
			driver.findElement(By.xpath("//*[@text=\"Password\"]")).sendKeys(pass);
			driver.findElementByAccessibilityId("Sign in").click();
			Thread.sleep(3000);
		}
		
		@Test(priority=3)
		public void privacy() throws InterruptedException {
			System.out.println("Privacy");
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@content-desc=\"Settings\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@text=\"Privacy policy\"]")).click();
			Thread.sleep(9000);
		    driver.pressKey(new KeyEvent(AndroidKey.BACK));
		}
		
		@Test(priority=4)
		public void languageselect() throws InterruptedException {
			System.out.println("set Language");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@text=\"Language & region\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@text=\"English\"]")).click();
			Thread.sleep(3000);
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"English\"));").click();
			Thread.sleep(5000);
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
		}
		
		@Test(priority=5)
		public void Validatetermsandcondition() throws InterruptedException {
			System.out.println("Termsandcondition");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@content-desc=\"Settings\"]")).click();
			driver.findElement(By.xpath("//*[@text=\"Terms of service\"]")).click();
			
			
			Set<String> contextNames = driver.getContextHandles();
		     for (String contextName : contextNames)
		     {
		    	     System.out.println(contextName);
		    	
		     }
		     driver.pressKey(new KeyEvent(AndroidKey.BACK));
		   
		}
		
		@Test(priority=6)
		public void startsession() throws InterruptedException {
			Thread.sleep(3000);
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			System.out.println("Get Started with course");
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Get started\").instance(0))");
	        driver.findElement(By.xpath("//*[@text='Get started']")).click();
	        driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Graduate studies\").instance(0))");
			
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@text=\"Graduate studies\"]")).click();
			
			driver.findElement(By.xpath("//*[@text=\"See All (33)\"]")).click();
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Trigonometry\").instance(0))");
			
			
			driver.findElement(By.xpath("//*[@text=\"Trigonometry\"]")).click();
			driver.findElement(By.xpath("//*[@text=\"Done\"]")).click();
			Thread.sleep(8000);
			
		}
		
		@Test(priority=7)
		public void opencourse() throws InterruptedException, MalformedURLException {
			Thread.sleep(2000);
			System.out.println("Open Course");
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"My courses\").instance(0))");
			driver.findElement(By.xpath("//*[@text=\"Trigonometry\"]")).click();
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Trigonometric equations and identities\").instance(0))");
			driver.findElement(By.xpath("//*[@text=\"Trigonometric equations and identities\"]")).click();
			
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Solving sinusoidal equations of the form sin(x)=d\").instance(0))");
			driver.findElement(By.xpath("//*[@text=\"Solving sinusoidal equations of the form sin(x)=d\"]")).click();
			
			driver.findElementByAndroidUIAutomator(
	                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Solving cos(θ)=1 and cos(θ)=-1\").instance(0))");
			driver.findElement(By.xpath("//*[@text=\"Solving cos(θ)=1 and cos(θ)=-1\"]")).click();
			
			 WebElement screen = driver.findElement(By.className("android.view.View"));
		        TouchAction t = new TouchAction(driver);
		        t.tap(tapOptions().withElement(element(screen))).perform();
		        driver.navigate().back();
		        driver.findElement(By.xpath("//*[@content-desc='Back']")).click();
		        driver.findElement(By.xpath("//*[@content-desc='Back']")).click();
		       
		        signout();
		      
		
		}

		

		

}
