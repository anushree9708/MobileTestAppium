package IBM.Appiumframework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Capabilities {
	
	protected static String deviceName;
	protected static String appPackage;
	protected static String appActivity;
	public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startserver()
	{
		boolean flag =checkifserviceRunning(4723);
		if(!flag)
		{
		//service =AppiumDriverLocalService.buildDefaultService();
		service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
		.withAppiumJS(new File("C://Users//AnushreeBS//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
		.withIPAddress("127.0.0.1").usingPort(4723));
		service.start();
		}
		return service;
	}
	
	public static boolean checkifserviceRunning(int port)
	{
		boolean isServerRunning =false;
		ServerSocket serversocket;
		try {
			serversocket=new ServerSocket(port);
			serversocket.close();
		}
		catch(IOException e)
		{
			isServerRunning=true;
		}
		finally {
			serversocket=null;
		}
		
		return isServerRunning;
		
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+ "\\src\\main\\java\\emulator.bat");
		Thread.sleep(9000);
	}
	

public static AndroidDriver<AndroidElement> capabilities(String deviceName, String appPackage, String appActivity) throws IOException, InterruptedException {

	
	FileReader fis=new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\global.properties");
	Properties pro=new Properties();
	pro.load(fis);
	deviceName=pro.getProperty("deviceName");
	appPackage=pro.getProperty("apppackage");
	appActivity=pro.getProperty("appActivity");
	
	if(deviceName.contains("Anushree Android"))
	{
		startEmulator();
	}
	
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
				cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
				cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
				cap.setCapability(MobileCapabilityType.NO_RESET, "True");
				
				cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,
						"C:\\Users\\AnushreeBS\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
				
				AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),
						cap);
				return driver;
				
	}

}

