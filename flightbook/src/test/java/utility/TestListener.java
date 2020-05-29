package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	@Override  
	  public void onTestStart(ITestResult result)
	  {
			System.out.println("Test started :"+result.getName());
	  }
		@Override  
	  public void onTestSuccess(ITestResult result)
	  {
		  System.out.println("Success of test cases :"+result.getName());
	  }
	  
		@Override  
	  public void onTestFailure(ITestResult result)
	  {
		  System.out.println("Failure of test cases :"+result.getName());
	  }
	  
		@Override  
	  public void onTestSkipped(ITestResult result)
	  {
		  System.out.println("Skipped of test cases :"+result.getName());
	  }
	  
	  @Override  
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	  // TODO Auto-generated method stub  
	  System.out.println("Failure of test cases and its details are : "+result.getName());  
	  }  
	    
	  @Override  
	  public void onStart(ITestContext context) {  
	  // TODO Auto-generated method stub  
	  }  
	    
	  @Override  
	  public void onFinish(ITestContext context) {  
	  // TODO Auto-generated method stub  
	  }  
	}
