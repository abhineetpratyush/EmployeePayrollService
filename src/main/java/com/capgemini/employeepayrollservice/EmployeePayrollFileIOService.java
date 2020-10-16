package com.capgemini.employeepayrollservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePayrollFileIOService {
	public static String PAYROLL_FILE_NAME = "payroll-file.txt";

	public void writeData(List<EmployeePayrollData> employeePayrollList) {
		StringBuffer empBuffer = new StringBuffer();
		employeePayrollList.forEach(employee -> {
			String employeeDataString = employee.toString().concat("\n");
			empBuffer.append(employeeDataString);
		});

		try {
			Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
		}
		catch(IOException e) {
			e.printStackTrace();
	}
		return entries;
	}
	
	public void printEntries() {
		try {
			Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<EmployeePayrollData> readEntries(){
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		
		try {
			List<String> payrollStringList =  Files.lines(new File(PAYROLL_FILE_NAME).toPath()).map(line -> line.trim()).collect(Collectors.toList());
			int entries = (int) countEntries();
			for(int i = 0; i < entries; i++) {
				String detailsOfSingleEmployee = payrollStringList.get(i).replaceAll("[\\n]", "");
				String[] detailsOfSingleEmployeeSplitArray = detailsOfSingleEmployee.split("[ ](.*?)=");
				int id = Integer.parseInt(detailsOfSingleEmployeeSplitArray[1]);
				String name = detailsOfSingleEmployeeSplitArray[2];
				double salary = Double.parseDouble(detailsOfSingleEmployeeSplitArray[3]);
				EmployeePayrollData employeePayrollData = new EmployeePayrollData(id, name, salary);
				employeePayrollList.add(employeePayrollData);
			}			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}
}
