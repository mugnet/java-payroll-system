package mtrphpkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class mtrph {
		
		public static void main(String[] args) {
	    	
	    	String filePath = "C:\\Users\\vidal\\eclipse-workspace\\mtrph\\csvemployees\\4th CSV File - Employee Details.csv";
			File csvFile = new File (filePath);
			Scanner scan = new Scanner(System.in);
	    	
	        System.out.print("Enter the employee number: ");
	        String employeeNumber = scan.nextLine();

	        try {
				Scanner scanner = new Scanner(csvFile);
	            boolean foundEmployee = false;

	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] fields = line.split(",");

	                if (fields.length > 0 && fields[0].equals(employeeNumber)) {
	                    foundEmployee = true;
	                    displayEmployeeDetails(fields);
	                    break;
	                }
	            }

	            if (!foundEmployee) {
	                System.out.println("Employee not found!");
	            } else {
	                boolean foundAttendance = false;
	                LocalDate date = null;

	                while (!foundAttendance) {
	                    System.out.print("Enter the date (mm/dd/yyyy): ");
	                    String inputDate = scan.nextLine();
	                    date = parseDate(inputDate);
	                    if (date != null) {
	                        foundAttendance = true;
	                    } else {
	                        System.out.println("Invalid date format! Please try again.");
	                    }
	                }

	                scanner = new Scanner(csvFile); // Reset scanner to beginning of file

	                while (scanner.hasNextLine()) {
	                    String line = scanner.nextLine();
	                    String[] fields = line.split(",");

	                    if (fields.length > 0 && fields[0].equals(employeeNumber) && fields.length > 20 && fields[20].equals(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))) {
	                        System.out.println();
	                        System.out.println("Date Input: " + fields[20]);
	                        System.out.println("Time in: " + fields[21]);
	                        System.out.println("Time out: " + fields[22]);

	                        // Calculate total working hours
	                        float totalWorkingHours = calculateTotalWorkingHours(fields[21], fields[22]);
				
	                        System.out.println("Total Working Hours: " + totalWorkingHours);
	                        System.out.println("============================================================");		                       
		                       System.out.println("*********************Salary Details*************************");
		                       
		                        System.out.println("Staffed Time: " + fields[23]);
			       				System.out.println("Hourly Rate: " + fields[18]);
			       				System.out.println("Rice Subsidy: " + fields[14]);
			       				System.out.println("Phone Allowance: " + fields[15]);
			       				System.out.println("Clothing Allowance: " + fields[16]);
			       				System.out.println("SSS Contribution: " + fields[24]);
			       				System.out.println("Gross Pay: " + fields[17]);
			       				
			       		     System.out.println("===================================================================");					 
			       			 System.out.println("**********************CALCULATE YOUR SALARY************************");
			       			 System.out.println("follow the data above to calculate your salary");
			       			 
			       			 Scanner Calcu = new Scanner(System.in);

			       			 // Input data for calculations
			       			 double Staffed_time = Calcu.nextDouble();
			       			 double hourly_rate = Calcu.nextDouble();
			       			 double ricesub = Calcu.nextDouble();
			       			 double phoneall = Calcu.nextDouble();
			       			 double cloth = Calcu.nextDouble();
			       			 double sss=  Calcu.nextDouble();
			       			 double gross= Calcu.nextDouble();
			       			
			       			
			       			 //Calculations of staffed time and hourly rate
			       			 double workedhours = Staffed_time * hourly_rate;
			       			 double totalhours= workedhours;
			       			 
			       			 //calculations of benefits
			       			 double benefits = ricesub + phoneall + cloth;
			       			 double totalbenefits = benefits;
			       			 
			       			 //calculations of partial salary
			       			 double partialsal =totalhours + benefits;
			       			 
			       			 
			       			 // salary deductions
			       			 int phealth= (int) (gross*0.03);
			       			 int  pagibig= (int) (gross*0.04);
			       			 int totaldeduct = (int) (partialsal-sss-phealth-pagibig);
			       			 int taxcal = (int) ((partialsal- totaldeduct)*.15);
			       			 double salary= taxcal-partialsal;
			       			 
			       			 
			       			 
			       			//total salary 
			       			 System.out.println("SSS Contribution: " + fields[24]);
			       			 System.out.println("Phil-Health: " + phealth);
			       			 System.out.println("Pag-ibig Contribution: " + pagibig);
			       			 System.out.println("Total Deduction: " + totaldeduct);
			       			 System.out.println("Withholding tax: " + taxcal);
			       			 System.out.println("Net Pay: " + salary);
	                        foundAttendance = true;
	                        break;

	                    }
	                }

	                if (!foundAttendance) {
	                    System.out.println("Error Date!");
	                }
	            }

	            scanner.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    private static LocalDate parseDate(String inputDate) {
	        DateTimeFormatter[] formatters = {
	                DateTimeFormatter.ofPattern("M/d/yyyy"),
	                DateTimeFormatter.ofPattern("MM/d/yyyy"),
	                DateTimeFormatter.ofPattern("M/dd/yyyy"),
	                DateTimeFormatter.ofPattern("MM/dd/yyyy")
	        };

	        for (DateTimeFormatter formatter : formatters) {
	            try {
	                return LocalDate.parse(inputDate, formatter);
	            } catch (Exception e) {
	             
	            }
	        }
	        return null;
	    }

	    private static void displayEmployeeDetails(String[] fields) {
	        System.out.println("\n===================================================");
	        System.out.println("             EMPLOYEE DETAILS:");
	        System.out.println("Employee Number: " + fields[0] + "\t\tBirthday: " + fields[3]);
	        System.out.println("First Name: " + fields[2] + "\t\tLast Name: " + fields[1]);
	        System.out.println("\nAddress: " + fields[4]);
	        System.out.println("Phone Number: " + fields[5]);
	        System.out.println("\n===================================================");

	        System.out.println("EMPLOYEE IDENTIFICATION NUMBERS:");
	        System.out.println("SSS #: " + fields[6] + "\t\tPhilhealth #: " + fields[7]);
	        System.out.println("TIN #: " + fields[8]+ "\t\tPag-ibig #: " + fields[9]);
	        System.out.println("\n===================================================");

	        System.out.println("JOB DETAILS:");
	        System.out.println("Status: " + fields[10]);
	        System.out.println("Position: " + fields[11]);
	        System.out.println("Immediate Supervisor: " + fields[12]);
	        System.out.println("\n===================================================");

	    }

	    private static float calculateTotalWorkingHours(String timeIn, String timeOut) {
	    
	        String[] timeInParts = timeIn.split(":");
	        String[] timeOutParts = timeOut.split(":");

	  
	        int inHours = Integer.parseInt(timeInParts[0]);
	        int inMinutes = Integer.parseInt(timeInParts[1]);
	        int outHours = Integer.parseInt(timeOutParts[0]);
	        int outMinutes = Integer.parseInt(timeOutParts[1]);

	 
	        int totalMinutes = (outHours * 60 + outMinutes) - (inHours * 60 + inMinutes);

	      
	        float totalWorkingHours = totalMinutes / 60.0f;

	        return totalWorkingHours;
	    }
	}