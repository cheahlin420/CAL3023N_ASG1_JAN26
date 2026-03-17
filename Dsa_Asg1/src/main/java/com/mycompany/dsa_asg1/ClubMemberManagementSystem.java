package com.mycompany.dsa_asg1;

import java.util.Scanner;

public class ClubMemberManagementSystem {
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    private Scanner input = new Scanner (System.in);
    private MemberManagement manager = new MemberManagement(input);
         
	public static void main (String[]args) {     
                    
		ClubMemberManagementSystem program = new ClubMemberManagementSystem();
		System.out.println(CYAN +" ____  _    __ __ _____     __  __  ____  __  __ _____  ____ _____     __  __   ____   __  _   ____   ____  ____  __  __  ____  __  _  _____      ____ __  __ ____  _____  ____  __  __    ");
        System.out.println("/ (__`| |__|  |  || () )   |  \\/  || ===||  \\/  || () )| ===|| () )   |  \\/  | / () \\ |  \\| | / () \\ / (_,`| ===||  \\/  || ===||  \\| ||_   _|    (_ (_`\\ \\/ /(_ (_`|_   _|| ===||  \\/  |   ");
        System.out.println("\\____)|____|\\___/ |_()_)   |_|\\/|_||____||_|\\/|_||_()_)|____||_|\\_\\   |_|\\/|_|/__/\\__\\|_|\\__|/__/\\__\\\\____)|____||_|\\/|_||____||_|\\__|  |_|     .__)__) |__|.__)__)  |_|  |____||_|\\/|_|   " +RESET);
        
        program.manager.loadFromFile();
        program.showMainMenu();
                        
	}
		
	public void showMainMenu() {

		int choice = -1;
		while (choice != 0) {
                            
			manager.updateMembershipStatusByExpiry();
			int expiringSoonCount = manager.countExpiringSoonMembers();
			
			System.out.println(BLUE+" \n===== CLUB MEMBER MANAGEMENT SYSTEM ====="+RESET);
            System.out.println("Expiring Soon Members: " + expiringSoonCount);
            System.out.println("1. Register New Member ");
            System.out.println("2. Search Member ");
            System.out.println("3. Update Member Information ");
            System.out.println("4. Renew Membership ");
            System.out.println("5. Delete Member ");
            System.out.println("6. View All Members ");
            System.out.println("7. View Membership Status ");
            System.out.println("8. View Membership Level ");
            System.out.println("9. View Expiring Soon Members");
            System.out.println("0. Exit ");
            System.out.println("\nPlease select an option: ");
				
            try {
            	
            	choice = Integer.parseInt(input.nextLine());
				handleMenuChoice(choice);
				
			}catch (NumberFormatException e) {
				System.out.println(RED + " [ Please enter a valid number. ] " + RESET);
			}
		}
	}			
				
	private void handleMenuChoice(int choice) {
		switch (choice) {
				
		case 1:
			manager.registerMember(); // call A's method
			manager.saveToFile();
			break;
			
		case 2:
			boolean retrySearch = true;
                                    
			while (retrySearch) {
				System.out.print("Enter Member ID: ");
				String memID = input.nextLine().trim().toUpperCase();
                                        
				Member member = manager.searchMemberById(memID);
                                        
				if (member != null) {
					displayMemberDetails(member);
					retrySearch = false;
        
				} else {
					System.out.println(RED + "[ Member not found. ]" + RESET);
					retrySearch = askTryAgain();
				}
			}
			break;
					
		case 3:
			boolean retryUpdate = true;
			while (retryUpdate) {
				System.out.print("Enter Member ID to update: ");
				String updateMem = input.nextLine().trim().toUpperCase();
                                        
				Member member = manager.searchMemberById(updateMem);
                                        
				if (member != null) {
					showUpdateSubMenu(updateMem);
					retryUpdate = false;
				} else {
					System.out.println(RED+"Member Not Found!!"+RESET);
					retryUpdate = askTryAgain();
				}
			}
			break;
					
		case 4:
			boolean retryRenew = true;
			while (retryRenew) {
				System.out.print("Enter Member ID to renew: ");
				String renewMem = input.nextLine().trim().toUpperCase();
                                        
				Member member = manager.searchMemberById(renewMem);
				if (member != null) {
					manager.renewMembership(renewMem);
					manager.saveToFile();
					retryRenew = false;
				} else {
					System.out.println(RED+"Member Not Found!!"+RESET);
					retryRenew = askTryAgain();
				}
			}
			break;
                                
		case 5:
			boolean retryDelete = true;
			while (retryDelete) {
				System.out.print("Enter Member ID to delete: ");
				String deleteMem = input.nextLine().trim().toUpperCase();
				Member member = manager.searchMemberById(deleteMem);
				if (member != null) {
					if (askForConfirmation(PURPLE+"Are you sure you want to delete this member?"+RESET)) {
						manager.deleteMember(deleteMem);
						manager.saveToFile();
					} else {
						System.out.println(YELLOW+"Delete operation cancelled."+RESET);
					}
					retryDelete = false;
				} else {
					System.out.println(RED+"Member is not found."+RESET);
					retryDelete = askTryAgain();
				}
			}
			break;
					
		case 6:
			manager.viewAllMembers();
			break;
				
		case 7:
			boolean retryStatus = true;         
			while(retryStatus){
				System.out.println("Enter Membership Status (Active/Inactive): ");
				String status = input.nextLine().trim().toUpperCase();                    
				if(status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("Inactive")) {
					manager.searchMembersByMembershipStatus(status);
					retryStatus = false;
				}else{
					System.out.println(RED+"Invalid membership status"+RESET);
					retryStatus = askTryAgain();
				}    
			}
			break;
    
		case 8:
			boolean retryLevel = true;
			while (retryLevel) {
				System.out.print("Membership Level: = Gold | Platinum | Diamond = \n");
				System.out.print("Enter Membership Level: ");
				String level = input.nextLine().trim().toUpperCase();
                                        
				if (level.equalsIgnoreCase("Gold") || level.equalsIgnoreCase("Platinum") || level.equalsIgnoreCase("Diamond")) {                      
					manager.searchMembersByMembershipLevel(level);
					retryLevel = false;
				} else {
					System.out.println(RED+"Invalid membership level."+RESET);
					retryLevel = askTryAgain();
				}
			}
			break;
                                        
		case 9:
			manager.searchMembersExpiringSoon();
			break;
					
		case 0:
			// SAVE TO FILE
			manager.saveToFile();
			System.out.println(YELLOW+"Exiting System...Goodbye! "+RESET);
			break;
				
		default:
			System.out.println(RED+"Invalid choice!"+RESET);				
			
		}
	}
			
			// formating
	public void displayMemberDetails (Member member) {
		if (member == null) {
			System.out.println(RED + "[ Member not found. ]" + RESET);
		}else {
			System.out.println(CYAN+"\n===== DETAILS ====="+RESET);
			System.out.println(member.toString());
			System.out.println("===================\n");
		}
	}
                        
                        
                        
                        
	 // Show Update Sub Menu
	public void showUpdateSubMenu(String memberId) {
                            
		Member target = manager.searchMemberById(memberId);
                            
		if(target == null){
                                
			System.out.println(RED+"Member Not Found!!"+RESET);
			return; 
		}
                            
		int choice = -1;
                            
		while(choice != 0){
			System.out.println(BLUE+"\n===== UPDATE MEMBER INFORMATION ====="+RESET);
            System.out.println("Member ID: " + memberId);
            System.out.println("1. Update Contact Number");
            System.out.println("2. Update Address");
            System.out.println("3. Update Membership Level");
            System.out.println("4. Cancel Membership");
            System.out.println("0. Back");
            System.out.print("Please select an option: ");
                               
            try {
            	choice = Integer.parseInt(input.nextLine());
            	switch (choice) {
            	case 1:
                    System.out.print(CYAN+"Enter new contact number: "+RESET);
                    String newContactNumber = input.nextLine().trim();
                    manager.updateContactNumber(memberId, newContactNumber);
                    manager.saveToFile();
                    break;
                case 2:
                    System.out.print(CYAN+"Enter new address: "+RESET);
                    String newAddress = input.nextLine().trim().toUpperCase();
                    manager.updateAddress(memberId, newAddress);
                    manager.saveToFile();
                    break;
                case 3:
                    System.out.print(CYAN+"Enter new membership level (Gold/Platinum/Diamond): "+RESET);
                    String newLevel = input.nextLine().trim().toUpperCase();
                    manager.updateMembershipLevel(memberId, newLevel);
                    manager.saveToFile();
                    break;
                
                case 4:
                    if (askForConfirmation(PURPLE+"Are you sure you want to cancel this membership?"+RESET)) {
                        manager.cancelMembership(memberId); 
                        manager.saveToFile();
                    }else {
                        System.out.println(YELLOW+"Cancel membership operation cancelled."+RESET);   
                    }
                    break;
                    
                case 0:
                    System.out.println(GREEN+"Returning to main menu..."+RESET);
                    break;
                default:
                    System.out.println(RED+"Invalid choice!"+RESET);
                                        
            	}
            } catch (NumberFormatException e) {
            	System.out.println(RED + "[ Please enter a valid number. ]" + RESET);
            }
		}
	}
	
	public boolean askTryAgain() {
        while (true) {
        	System.out.print("Do you want to try again? (Y/N): ");
            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {
                return true;

            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println(RED+"Invalid input. Please enter Y or N."+RESET);

            }
        }
    }
    
    public boolean askForConfirmation(String message){
        while(true) {
            System.out.println(message + "(Y/N): ");
            String choice = input.nextLine().trim();
            
            if(choice.equalsIgnoreCase("Y")){
                return true;
            }else if(choice.equalsIgnoreCase("N")) {
                return false;
            }else{
                System.out.println(RED+"Invalid input. Please enter Y or N only..."+RESET);
            }
        }
    }
}
