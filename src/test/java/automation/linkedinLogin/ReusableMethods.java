package automation.linkedinLogin;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReusableMethods {

	WebDriver driver;

	public void openBrowser(String browsername) {
		if (browsername.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browsername.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (browsername.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

	public void navToURL(String URL) {
		driver.get(URL);
	}

	public void input(String controlname, String data) {
		driver.findElement(By.id(controlname)).sendKeys(data);
	}

	public void click(String controlname) {
		driver.findElement(By.xpath(controlname)).click();
	}

	// Title verification
	public void verify_text(String xpath, String exptext) {
		String act_text = driver.findElement(By.xpath(xpath)).getText().trim();
		if (!act_text.equals(exptext)) {
			System.err.println("Title not displaying as " + exptext + " but showing as-" + act_text);
		}
		System.out.println("Successfully login");
	}
	// verify the control is enable or not
	public void is_enable(String xpath,String controlcaption) {
		if(driver.findElement(By.xpath(xpath)).isEnabled()==false) {
			System.err.println(controlcaption+" control is disalbed");
			click(controlcaption);
		}
	}

	public void is_selected(String xpath) {
		if (driver.findElement(By.xpath(xpath)).isSelected() == false) {
			click(xpath);
		} else {
			System.out.println("Check box is already checked");
		}
	}

	public void readExcel(String excelfilename) {
		try {
			FileInputStream file = new FileInputStream(excelfilename);
			
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("TestCases");
			Row headerrow = sheet.getRow(0);
			if (headerrow == null) {
				System.out.println("No Row header found!");
				return;
			}
			System.out.println("Column header: ");
			for (Cell cell : headerrow) {
				if (cell.getCellType() == CellType.STRING) {
					System.out.print(cell.getStringCellValue() + "\t");
				} else if (cell.getCellType() == null) {
					System.out.print("Mention the cell type");
				} else {
					System.out.print(cell.getErrorCellValue());
				}
			}
			System.out.println();
			int teststepcount = sheet.getPhysicalNumberOfRows();
			System.out.println("Total Test steps: " + teststepcount);
			for (int rowindex = 1; rowindex <= teststepcount; rowindex++) {
				Row row = sheet.getRow(rowindex);
				if (row != null) {
					System.out.println("Test step: " + rowindex);
					for (Cell cell : row) {
						if (cell.getCellType() == CellType.STRING) {
							System.out.print(cell.getStringCellValue() + "\t");
						} else if (cell.getCellType() == null) {
							System.out.println("Mention the cell type");
						} else {
							System.out.print("Unknow value\t");
						}
					}
					System.out.println();
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// write a new method to read and run test steps from excel workbook
	public void readAndPerform(String methodname, String parameter1, String parameter2) {

		switch (methodname) {
		case "openBrowser":
			openBrowser(parameter1);
			break;
		case "navToURL":
			navToURL(parameter1);
			break;
		case "input":
			input(parameter1, parameter2);
			break;
		case "click":
			click(parameter1);
			break;
		case "verify_text":
			verify_text(parameter1, parameter2);
			break;
		case "is_selected":
			is_selected(parameter2);
		default:
			System.out.println("UNknowmethod" + methodname);
		}

		try (FileInputStream file = new FileInputStream("G:\\Sunny\\SunnyLinkedInProject\\linkedinLogin\\Excelsheets\\LinkedinLoginTestCases.xlsx")) {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet("TestCases"); // Get the sheet name

			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("Total rows: " + totalRows);

			// Loop through all rows and read the test steps
			for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) { // Start from row 1 to skip header
				Row row = sheet.getRow(rowIndex);
				methodname = row.getCell(3).getStringCellValue(); // Get the Method Name
				parameter1 = row.getCell(4).getStringCellValue(); // Get Parameter 1
				parameter2 = row.getCell(5).getStringCellValue(); // Get Parameter 2

				System.out.println("Executing Step: " + methodname + " with parameters: " + parameter1 + ", " + parameter2);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get the number of Test Cases ID in a array
	public void readtestcases(String excelfilename) {
		ArrayList<String> testcases=new ArrayList<String>();
		try(
				FileInputStream file=new FileInputStream(excelfilename)){
				
				Workbook workbook=WorkbookFactory.create(file);
				Sheet sheet=workbook.getSheet("TestCases");
				String tcid = null;
				String tcid1 = null;
				int totalrows=sheet.getPhysicalNumberOfRows();
				System.out.println("Total rows: "+totalrows);
				for(int row=1; row<totalrows;row++) {
					tcid=sheet.getRow(row).getCell(0).getStringCellValue();
					if(row+1<totalrows) {
					tcid1=sheet.getRow(row+1).getCell(0).getStringCellValue();
					}
					if(!tcid.equals(tcid1)) {
						testcases.add(tcid);
						System.out.println(tcid);
					}
				}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get the count of test steps mapped to test case id in a array in same order
	public void readTestSteps(String excelfilename) {
		TreeMap<String, Integer> testcasestepcount=new TreeMap<String, Integer>();
		try {
			FileInputStream file=new FileInputStream(excelfilename);
			
			Workbook workbook=WorkbookFactory.create(file);
			Sheet sheet=workbook.getSheet("TestCases");
			String tcid=null;
			int totalrows=sheet.getPhysicalNumberOfRows();
			System.out.println("Total rows: "+totalrows);
			for(int row=1;row<totalrows;row++) {
				tcid=sheet.getRow(row).getCell(0).getStringCellValue();
				testcasestepcount.put(tcid, testcasestepcount.getOrDefault(tcid, 0)+1);
			}
			for(String id: testcasestepcount.keySet()) {
				System.out.println("Test Case-ID: "+id+ "\nTest Steps: "+ testcasestepcount.get(id));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
