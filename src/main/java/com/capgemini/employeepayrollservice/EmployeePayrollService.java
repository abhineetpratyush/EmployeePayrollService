package com.capgemini.employeepayrollservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeePayrollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	private static final Logger log = LogManager.getLogger(EmployeePayrollService.class);

	private List<EmployeePayrollData> employeePayrollList;
	public EmployeePayrollService() {}
	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData(consoleInputReader);
		employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
	}

	public void readEmployeePayrollData(Scanner consoleInputReader) {
		log.info("Enter Employee ID : ");
		int id = consoleInputReader.nextInt();
		log.info("Enter Employee Name : ");
		String name = consoleInputReader.next();
		log.info("Enter Employee Salary : ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	public void writeEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
			log.info("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
		else if(ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData(employeePayrollList);
	}
	public long countEntries(IOService ioService) {
		long entries = 0;
		if(ioService.equals(IOService.FILE_IO))
			entries = new EmployeePayrollFileIOService().countEntries(employeePayrollList);
		return entries;
	}
}


