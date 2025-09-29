package EcommVege.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static 	ExtentReports getReportObject()
	{
		//ExtentSparkReporter , ExtentReports
		
		File file =	new File(System.getProperty("user.dir")+"//reports//index.html");
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		
		reporter.config().setDocumentTitle("Web Automation");
		
		reporter.config().setReportName("Ecommerce Automation");
		
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Harpreet Sindhia", "Automation Engineer");
		
		return extent;
		
	}
	
}
