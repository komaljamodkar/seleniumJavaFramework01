package testCase;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.RegisterPage;
import utility.XLUtils;

public class TCRegister01 extends BaseClass{
	private WebDriver driver;
    RegisterPage rp;
    String expected =null;
	String actual = null;
	
	private XLUtils read;
	String filePath = System.getProperty("user.dir")+"/src/test/java/testData/registerdata.xlsx";
	String sheetName = "Sheet1";
	
	@BeforeClass
	  public void setUp() {
		  driver = getDriver();
		  rp = new RegisterPage(driver);
		
	  }
	
  @Test(enabled = true, dataProvider = "Form")
  public void registerTest (String fname, String email, String PhoneNo) throws IOException{
	  
	  
	  
	  rp.goToFirstName().sendKeys(fname);
	  rp.goToEmail().sendKeys(email);
	  rp.goToPhone().sendKeys(PhoneNo);
	  
	  rp.goToGender().click();
	  rp.goToHobbies().click();
	  rp.goToCountry();
	  rp.clickOnRefresh().click();
	  
	  expected = "";
	  actual = rp.goToEmail().getText();
	  
	  //To Verify email address is blank
	  assertTrue(expected.contentEquals(actual));
	 
	  
  }
  
  
  //To provide data from excel sheet
  @DataProvider(name = "Form")
	public String[][] getValidData() throws IOException {
		
		return read.readExcel(filePath, sheetName);
	}
  
}