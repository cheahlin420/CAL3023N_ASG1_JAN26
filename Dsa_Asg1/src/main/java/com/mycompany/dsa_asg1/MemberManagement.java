/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_asg1;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.io.*;

public class MemberManagement {
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<Member> memberList = new LinkedList<>();
    
    /* 
     * DC
     * 
     * 1. Registration 
     * - registerMember()
     * - generateMemberId()
     * - getRegistrationFee(String membershipLevel)
     * - getRenewalFee(String membershipLevel)
     * 
     * 2. Validation Method
     * - isDuplicateMemberId(String memberId)
     * - isValidGender(String gender)
     * - isValidContactNumber(String contactNumber)
     * - isValidMembershipLevel(String membershipLevel)
     * - isValidMembershipStatus(String membershipStatus)
     * - isValidAddress(String address)
     * 
     * 3. Date & Age Processing
     * - calculateAge(String dateOfBirth)
     * - convertToLocalDate(String dateStr)
     * - isValidDate(String dateStr)
     * - isLeapYear(int year)
     * 
     * 4. Expiry & Status Monitoring
     * - updateMembershipStatusByExpiry()
     * - countExpiringSoonMembers()
     * - searchMembersExpiringSoon()
     * 
     * 5. Filter & Display
     * - searchMembersByMembershipLevel(String membershipLevel)
     * - searchMembersByMembershipStatus(String membershipStatus)
     * - viewAllMembers()
     * 
     */
   
  
    //==========================================
    // +++++++ Registration ++++++++ -DC
    // ==========================================
    // Add member
    public void registerMember(){
        LocalDate today = LocalDate.now();
   
        String memberId = generateMemberId(); //Cannot setter (unique identifier)
        String name;
        String dateOfBirth;
        int age;
        String gender;
        String contactNumber;
        String address;
        String membershipLevel;
        String dateOfJoining = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String membershipStatus;
        String expiryDate = calculateExpiryDate(dateOfJoining);
        
        System.out.println(BLUE+"\n=== Register New Member ==="+RESET);
        System.out.println("Member ID: " + memberId);
        System.out.println("-----------------------");
        
        // Enter name
        do {
            System.out.print("Enter Member Name: ");
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
            System.out.println(RED+"Name cannot be empty."+RESET);
            }
        } while (name.isEmpty());
        
    // Date of Birth + Age
    do {
        System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
        dateOfBirth = scanner.nextLine().trim();
        
        age = calculateAge(dateOfBirth);

        if (age == -1) {
            System.out.println(RED+"Invalid date of birth."+RESET);
            System.out.println(RED+"Please use dd-mm-yyyy and ensure it is not a future date."+RESET);
        }
    } while (age == -1);
    
    
    // Gender
    do {
        System.out.print("Enter Gender (Male/Female): ");
        gender = scanner.nextLine().trim();

        if (!isValidGender(gender)) {
            System.out.println(RED+"Invalid gender. Please enter Male or Female."+RESET);
        }
    } while (!isValidGender(gender));
    
    // Contact Number
    do {
        System.out.print("Enter Contact Number: ");
        contactNumber = scanner.nextLine().trim();

        if (!isValidContactNumber(contactNumber)) {
            System.out.println(RED+"Invalid contact number format.");
            System.out.println("Examples:");
            System.out.println("Mobile: 0123456789 / 012-3456789 / 01123456789 / 011-23456789");
            System.out.println("Landline: 03-12345678 / 0312345678 / 082-123456 / 082123456"+RESET);
        }
    } while (!isValidContactNumber(contactNumber));
    
    // Address
    do {
        System.out.print("Enter Address: ");
        address = scanner.nextLine().trim();

        if (!isValidAddress(address)) {
            System.out.println(RED+"Address cannot be empty."+RESET);
        }
    } while (!isValidAddress(address));
    
    // Membership Level
    do {
        System.out.print("Enter Membership Level (Gold/Platinum/Diamond): ");
        membershipLevel = scanner.nextLine().trim();

        if (!isValidMembershipLevel(membershipLevel)) {
            System.out.println(RED+"Invalid membership level. Please enter Gold, Platinum, or Diamond."+RESET);
        }
    } while (!isValidMembershipLevel(membershipLevel));
    
    // Membership Status
    do {
        System.out.print("Enter Membership Status (Active/Inactive): ");
        membershipStatus = scanner.nextLine().trim();

        if (!isValidMembershipStatus(membershipStatus)) {
            System.out.println(RED+"Invalid membership status. Please enter Active or Inactive."+RESET);
        }

    } while (!isValidMembershipStatus(membershipStatus));
    
     // Registration Fee
    double registrationFee = getRegistrationFee(membershipLevel);
        
    Member newMember = new Member(
            memberId,
            name, 
            dateOfBirth,
            age,
            gender,
            contactNumber,
            address,
            membershipLevel,
            dateOfJoining,
            membershipStatus,
            expiryDate
    );
    
    // Insert member into LinkedList
    memberList.add(newMember);
    
    // Display output here!
    System.out.println(GREEN+"\n======================================");
    System.out.println("=      Registration Successful       =");
    System.out.println("======================================"+RESET);
    System.out.println("Registration Fee | " + registrationFee);
    System.out.println("Expiry Date      | " + expiryDate);
    System.out.println(BLUE+"--------------------------------------");
    System.out.println("Member Details");
    System.out.println("--------------------------------------");
    System.out.println(newMember);
    System.out.println("--------------------------------------"+RESET);
        
    }
    
    public String generateMemberId() {
        int nextNumber = memberList.size() + 1;
        String newId = String.format("M%04d", nextNumber);
        
        while(isDuplicateMemberId(newId)){
            nextNumber++;
            newId = String.format("M%04d", nextNumber);
        }
        return newId;
    }
    
    public double getRegistrationFee(String membershipLevel) {
        
       if(membershipLevel.equalsIgnoreCase("Gold")){
           return 180.0;
       }else if(membershipLevel.equalsIgnoreCase("Platinum")) {
           return 220.0;
       }else if(membershipLevel.equalsIgnoreCase("Diamond")){
           return 300.0;
       }else{
           return 0.0;
       }  
    }
    
    public int getRenewalFee(String membershipLevel) {
		if(membershipLevel.equalsIgnoreCase("Gold")) {
			return 80;
		}else if(membershipLevel.equalsIgnoreCase("Platinum")) {
			return 110;
		}else if(membershipLevel.equalsIgnoreCase("Diamond")) {
			return 150;
		}
		return 0;//default
	}
    
    // ==========================================
    // +++++++  VALIDATION METHOD ++++++++ -DC
    // ==========================================
    public boolean isDuplicateMemberId(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female");
    }
    
    
    public boolean isValidContactNumber(String contactNumber) {
        contactNumber = contactNumber.trim();
        contactNumber = contactNumber.replace(" ", "");
        
        //Mobile Format  0123456789, 0137654321
       String mobileWithoutDash10 = "^01[02346789][0-9]{7}$";
       String mobileWithoutDash11 = "^011[0-9]{8}$";
       String mobileWithDash10 = "^01[02346789]-[0-9]{7}$";
       String mobileWithDash11 = "^011-[0-9]{8}$";
       
       //Landline Format 
       String landlineWithoutDash2DigitArea = "^0[345679][0-9]{7,8}$"; // 2-digit area code examples: 03-12345678, 04-1234567
       String landlineWithDash2DigitArea = "^0[345679]-[0-9]{7,8}$";
       String landlineWithoutDash3DigitArea = "^08[2-9][0-9]{6}$";  // 3-digit area code examples: 082 123456, 088 123456
       String landlineWithDash3DigitArea = "^08[2-9]-[0-9]{6}$";
       
       return contactNumber.matches(mobileWithoutDash10)
            || contactNumber.matches(mobileWithoutDash11)
            || contactNumber.matches(mobileWithDash10)
            || contactNumber.matches(mobileWithDash11)
            || contactNumber.matches(landlineWithoutDash2DigitArea)
            || contactNumber.matches(landlineWithDash2DigitArea)
            || contactNumber.matches(landlineWithoutDash3DigitArea)
            || contactNumber.matches(landlineWithDash3DigitArea);
       
    }
    
    public boolean isValidMembershipLevel(String membershipLevel) {
        return membershipLevel.equalsIgnoreCase("Gold") || membershipLevel.equalsIgnoreCase("Platinum") || membershipLevel.equalsIgnoreCase("Diamond");
    }
    
    public boolean isValidMembershipStatus(String membershipStatus) {
        return membershipStatus.equalsIgnoreCase("Active") || membershipStatus.equalsIgnoreCase("Inactive");
    }
    
    public boolean isValidAddress(String address) {
        return !address.trim().isEmpty();
    }
    
    // ==========================================
    // +++++++  Date & Age Processing ++++++++ -DC
    // ==========================================
    
    public int calculateAge(String dateOfBirth) {
        if (!isValidDate(dateOfBirth)) {
            return -1;
        }
        
        LocalDate dob = convertToLocalDate(dateOfBirth);
        LocalDate today = LocalDate.now();
        
        if (dob.isAfter(today)) {
            return -1;
        }
        
        return Period.between(dob, today).getYears();
    }
    
    public LocalDate convertToLocalDate(String dateStr) {
        String[] parts = dateStr.split("-");
        
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        
        return LocalDate.of(year, month, day);
    }
    
   
    // Check the user input date Format
    public boolean isValidDate(String dateStr) {
        if (!dateStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        
        String[] parts = dateStr.split("-");
        
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check valid month
        if (month < 1 || month > 12) {
            return false;
        }

    // Check valid day
    if (day < 1) {
        return false;
    }
    
    int maxDay;
    
    switch (month) {
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            maxDay = 31;
            break;
        case 4: case 6: case 9: case 11:
            maxDay = 30;
            break;
        case 2:
            if (isLeapYear(year)) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
            break;
        default:
            return false;
    }
    return day <= maxDay;
    }
    
        // Checking year
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    // ==========================================
    // +++++++  Expiry & Status Monitoring ++++++++ -DC
    // ==========================================
    
        public void updateMembershipStatusByExpiry(){
            LocalDate today = LocalDate.now();
            boolean updated = false;
            
            for(Member member : memberList){
                String expiryDate = member.getExpiryDate();
                
                if(isValidDate(expiryDate)){
                    LocalDate expiry = convertToLocalDate(expiryDate);
                    
                    if(expiry.isBefore(today) && !member.getMembershipStatus().equalsIgnoreCase("Inactive")){
                        member.setMembershipStatus("Inactive");
                        updated = true;
                    }
                }
            }
            
            if(updated) {
                System.out.println(BLUE+"\n========================================================");
                System.out.println("Membership status have been updated based on expiry dates.");
                System.out.println("========================================================\n"+RESET);
            } 
        }
        
        
      
     // Count the membership will expiry soon
        public int countExpiringSoonMembers(){
            LocalDate today = LocalDate.now();
            LocalDate thresholdDate = today.plusDays(30);
            
            int count = 0;
            
            for (Member member : memberList) {
                String expiryDate = member.getExpiryDate();
                
                if (isValidDate(expiryDate)) {
                    LocalDate expiry = convertToLocalDate(expiryDate);
                    
                    if ((expiry.isEqual(today) || expiry.isAfter(today)) && (expiry.isEqual(thresholdDate)
                            || expiry.isBefore(thresholdDate))) {
                        count++;
                    }
                }
            }
            return count;
        }
 
        public void searchMembersExpiringSoon() {
            LocalDate today = LocalDate.now();
            LocalDate thresholdDate = today.plusDays(30);
            
            boolean found = false;
            
            System.out.println(BLUE+"\n===========================================");
            System.out.println("         MEMBERS EXPIRING SOON             ");
            System.out.println("      (within the next 30 days)            ");
            System.out.println("==========================================="+RESET);
            
            for (Member member : memberList) {
                
                String expiryDate = member.getExpiryDate();
                
                if (isValidDate(expiryDate)) {
                    
                    LocalDate expiry = convertToLocalDate(expiryDate);
                    
                    if ((expiry.isEqual(today) || expiry.isAfter(today)) &&
                            (expiry.isEqual(thresholdDate) || expiry.isBefore(thresholdDate))) {
                        System.out.println(member);
                        System.out.println(BLUE+"-------------------------------------------"+RESET);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println(RED+"No members are expiring within the next 30 days."+RESET);
                System.out.println(BLUE+"------------------------------------------------\n"+RESET);
            }
        }
        
     // ==========================================
     // +++++++  Filter & Display ++++++++ -DC
     // ==========================================
    
    // - Search Membership by level
    public void searchMembersByMembershipLevel(String membershipLevel) {
        // check first it's the level is valid
        if (!isValidMembershipLevel(membershipLevel)) {
            System.out.println(RED+"Invalid membership level.");
            System.out.println("Please enter Gold, Platinum, or Diamond."+RESET);
            return;
        }
        boolean found = false;
        
        System.out.println(BLUE+"\n=== Search Result by Membership Level: " + membershipLevel + " ==="+ RESET);
        for (Member member : memberList) {
            if (member.getMembershipLevel().equalsIgnoreCase(membershipLevel)) {
                System.out.println(member);
                System.out.println(BLUE+"--------------------------------------------------------------"+RESET);
                found = true;
            }
        }
        if (!found) {
            System.out.println(RED+"No members found with membership level: " + membershipLevel+RESET);
        }
    }
    
    // - Search Membership by status
    //First, check whether the input status is valid
    public void searchMembersByMembershipStatus(String membershipStatus){
        boolean found = false;
        
        // to update the latest version before search.
        updateMembershipStatusByExpiry();
        
        if(!isValidMembershipStatus(membershipStatus)){
            System.out.println(RED+"Invalid membership status.");
            System.out.println("Please enter Active or Inactive."+RESET);
            return;
        }
        
        System.out.println(BLUE+"-------------------------------------------");
        System.out.println("                MEMBER STATUS              ");
        System.out.println("-------------------------------------------");
        System.out.println("                   " + membershipStatus + "                  "+RESET);
        
        for(Member member : memberList) {
            if(member.getMembershipStatus().equalsIgnoreCase(membershipStatus)) {
                System.out.println(BLUE+"-------------------------------------------"+RESET);
                 System.out.println(member);
                 System.out.println(BLUE+"-------------------------------------------"+RESET);
                 found = true;
            }
        }
        
        if (!found) {
            System.out.println(RED+"No member found with membership status: " + membershipStatus+RESET);
        }
    }
    
    
        
        public void viewAllMembers() {
            
            if (memberList.isEmpty()) {
                System.out.println(RED+"No members in system."+RESET);
                return;
            }
            
            System.out.print(CYAN +"-------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("\n%-10s %-20s %-18s %-12s %-15s %-15s\n", "ID", "Name", "Level", "Status", "Join Date", "Expiry Date" );
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------"+RESET);
            
            for (Member m : memberList) {
                System.out.printf("%-10s %-20s %-18s %-12s %-15s %-15s\n",
                        m.getMemberId(), m.getName(), m.getMembershipLevel(), m.getMembershipStatus(), m.getDateOfJoining(), m.getExpiryDate());
            }
            System.out.println(CYAN +"-------------------------------------------------------------------------------------------------------------------------------------"+RESET);
        }



// ====================================================== DC PART DONE HERE ======================================================
	
        
        /* SUKI
         * 1. Search
         * searchMemberById
         * 
         * 2. Renew
         * calculateExpiryDate(String baseDate)
         * renewMembership(String memberId)
         * 
         * 3. Update
         * updateContactNumber(String memberId,String newContactNumber)
         * updateAddress(String memberId,String address)
         * updateMembershipLevel(String memberId,String level)
         * cancelMembership(String memberId)
         * 
         * 4. Delete
         * deleteMember(String memberId)
         * 
         * 5. Text file(Storage)
         * saveToFile()
         * loadFromFile()
         * 
         */
        
        public Member searchMemberById(String memberId){
        	for(int i =0;i<memberList.size();i++) {
        		Member m=memberList.get(i);
        		if(m.getMemberId().equalsIgnoreCase(memberId)) {
        			return m;
        		}
        	}
        	return null;
        } 
        
       public String calculateExpiryDate(String baseDate) {
    		
    		// 1. Use "-" to split the string into three parts(Array List):
    		// parts[0] is date, parts[1] is month, parts[2] is year.
    		String[] parts = baseDate.split("-"); 
    		
    		// 2. Convert the parts[2] into integer.
    		int year = Integer.parseInt(parts[2]);
    		
    		// 3. Add 1 to the year
    		year = year + 1;
    		
    		// 4. Reassemble them into a string using "-" and go back.
    		String newExpiryDate = parts[0] + "-" + parts[1] + "-" + year;
    		
    		return newExpiryDate;
       }
       
      public void renewMembership(String memberId){
		
		// 1. Find the member (by Member ID).
		Member target = searchMemberById(memberId);
		
		String level;
		double fee;
		
       // 2. Check if the member exists         
		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
			return;
		}else {
			// Get their level to calculate the renewal fee
			level = target.getMembershipLevel();
			fee=getRenewalFee(level);
		}
		
        //3. Get today's date and the old expiry date
		LocalDate today = LocalDate.now();
        LocalDate oldExpiry = convertToLocalDate(target.getExpiryDate());
		
        String baseDate;
                
        //4. Check if the membership is already expired
        if(oldExpiry.isBefore(today)){
        	// If already expired, start the new year from TODAY
        	baseDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }else {
            // If not expired yet, add one year to the OLD expiry date
        	baseDate = target.getExpiryDate();
        }
        
		//5. Calculate the new date and update the member's record
		String newDate=calculateExpiryDate(baseDate);
		
        target.setExpiryDate(newDate);
		target.setMembershipStatus("Active");
		
		System.out.println(GREEN+"Renewal Successful "+RESET);
		System.out.println("Member ID: " + target.getMemberId());
		System.out.println("Membership Level: " + level);
		System.out.println("Renewal Fee: RM" + fee);
		System.out.println("New Expiry Date: " + newDate);
	}
	
	public void updateContactNumber(String memberId,String newContactNumber) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
			return;
		}else {
			if (isValidContactNumber(newContactNumber)) { 
	            target.setContactNumber(newContactNumber);
	            System.out.println(GREEN+"Update Successful!"+RESET);
	        } else {
	            System.out.println(RED+"Error: Invalid contact number format!"+RESET);
	        }
		}
	}
	
	public void updateAddress(String memberId,String address) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
			return;
		}else {
			if (isValidAddress(address)) { 
				target.setAddress(address);
		        System.out.println(GREEN+"Update Successful!"+RESET);
	        } else {
	            System.out.println(RED+"Error: Invalid address format!"+RESET);
	        }
		}
	}
	
	public void updateMembershipLevel(String memberId,String level) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
			return;
		}else {
			if (isValidMembershipLevel(level)) { 
				target.setMembershipLevel(level);
		        System.out.println(GREEN+"Update Successful!"+RESET);
	        } else {
	            System.out.println(RED+"Error: Invalid membership level!"+RESET);
	        }
		}
	}
	
	public void cancelMembership(String memberId) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
		}else {
			target.setMembershipStatus("Inactive");
	        System.out.println(GREEN+"Update Successful!"+RESET);
		}
	}
	
	
	public void deleteMember(String memberId) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).
	    
		if(target==null) {
			System.out.println(RED+"Member is not found"+RESET);
			return;
		}else {
			memberList.remove(target);
			System.out.println(GREEN+"Member has been completely deleted!"+RESET);
		}

	}
	
	//Text file persistent storage
	public void saveToFile() {
	    try {
	        // 1. write text into "members.txt".
	        PrintWriter writer = new PrintWriter(new FileWriter("members.txt"));
	        
	        // 2. Call out everyone on the member list one by one.
	        for (int i = 0; i < memberList.size(); i++) {
	        	
	        	Member m = memberList.get(i);
	        	
	            // 3. Store each member's the data into record
	            String record = m.getMemberId() + ";" + m.getName() + ";" + m.getDateOfBirth() + ";" +m.getAge() + ";" + 
	            m.getGender() + ";" + m.getContactNumber() + ";" + m.getAddress() + ";" + m.getMembershipLevel() + ";" +
	            m.getDateOfJoining() + ";" + m.getMembershipStatus() + ";" + m.getExpiryDate();
	            
	            // 4. write this string of text into a file and add a newline character (println):
	            writer.println(record); 
	        }
	        
	        writer.close(); 
	        System.out.println(GREEN+"Store the data into members.txt successfully!"+RESET);
	        
	    } catch (Exception e) {
	        System.out.println(RED+"Store unsuccessful：" + e.getMessage()+RESET);
	    }
	}
	
	public void loadFromFile(){
		
		try {
			File file = new File("members.txt");
			
			// If the file doesn't exist at all (e.g., the first time using the system)
			// then do nothing and just end the process.
			if (!file.exists()) 
	            return;
			
			//2. use scanner to read the text in the document
			Scanner fileScanner = new Scanner(file);
			
			//3. Keep reading as long as there is another line in the file
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				
				String[] data =line.split(";");
				if(data.length == 11) {
					Member m = new Member (data[0],data[1],data[2],Integer.parseInt(data[3]),data[4],
							data[5],data[6],data[7],data[8],data[9],data[10]);
					memberList.add(m);
				}	
			}
			
			fileScanner.close();
	        System.out.println(GREEN+"Load data successfully!"+RESET);
	        
		}catch (Exception e) {
	        System.out.println(RED+"Load unsuccessful：" + e.getMessage()+RESET);
	    }
	}
    
	
}
