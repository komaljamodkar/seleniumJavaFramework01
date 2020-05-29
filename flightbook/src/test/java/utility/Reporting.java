package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listener class use to generate report

public class Reporting extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onTestStart(ITestResult testContext)
	  {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
		String repName="Time -Report"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "swati");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "swati");
		
		htmlReporter.config().setDocumentTitle("Mercury tours Airways Project");
        htmlReporter.config().setReportName("Functional Test Automation Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
		
	  }
	
	public void onTestSuccess(ITestResult result)
	  {
		  logger=extent.createTest(result.getName());
		  logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	  }
	
	public void onTestFailure(ITestResult result)
	  {
		logger=extent.createTest(result.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		String screenshotpath=System.getProperty("user.dir")+"/screenshots/"+result.getName()+".png";
		
		File f=new File(screenshotpath);
		
		if(f.exists())
		{
			try 
			{
				logger.fail("Screenshot is below :"+logger.addScreenCaptureFromPath(screenshotpath));
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	  }
	
	public void onTestSkipped(ITestResult result)
	  {
		  logger=extent.createTest(result.getName()); //new entry in report
		  logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	  }

	
	
	public void onFinish(ITestContext testContext) 
	  {  
		  extent.flush(); 
	  } 
}
