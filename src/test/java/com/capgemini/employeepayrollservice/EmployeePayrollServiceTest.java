package com.capgemini.employeepayrollservice;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.capgemini.employeepayrollservice.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {
	private static final Logger log = LogManager.getLogger(EmployeePayrollServiceTest.class);

	@Test
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmps = {new EmployeePayrollData(1, "Jeff Bezos", 10000.0),
				new EmployeePayrollData(2, "Bill Gates", 15000.0),
				new EmployeePayrollData(3, "Dan Bilzerian", 10500.0)};
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		Assert.assertEquals(3, entries);
	}
	
	@Test
	public void given3Employees_WhenPrinted_ShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmps = {new EmployeePayrollData(1, "Jeff Bezos", 10000.0),
				new EmployeePayrollData(2, "Bill Gates", 15000.0),
				new EmployeePayrollData(3, "Dan Bilzerian", 10500.0)};
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
		employeePayrollService.printEntries(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		Assert.assertEquals(3, entries);
		log.info("No of employees in the payroll are : " + entries);
		
	}
}
