package com.mycompany.dsa_asg1;

	import java.util.Scanner;

	public class Driver {
            public static final String RESET = "\u001B[0m";
            public static final String RED = "\u001B[31m";
            public static final String GREEN = "\u001B[32m";
            public static final String YELLOW = "\u001B[33m";
            public static final String BLUE = "\u001B[34m";
            public static final String PURPLE = "\u001B[35m";
            public static final String CYAN = "\u001B[36m";
            public static final String WHITE = "\u001B[37m";
            
            private Scanner input = new Scanner (System.in);
            private MemberManagement manager = new MemberManagement();
                
		
		public static void main (String[]args) {     
                    
			Driver program = new Driver();
                        
                        System.out.println(" ____  _    __ __ _____     __  __  ____  __  __ _____  ____ _____     __  __   ____   __  _   ____   ____  ____  __  __  ____  __  _  _____      ____ __  __ ____  _____  ____  __  __    ");
                        System.out.println("/ (__`| |__|  |  || () )   |  \\/  || ===||  \\/  || () )| ===|| () )   |  \\/  | / () \\ |  \\| | / () \\ / (_,`| ===||  \\/  || ===||  \\| ||_   _|    (_ (_`\\ \\/ /(_ (_`|_   _|| ===||  \\/  |   ");
                        System.out.println("\\____)|____|\\___/ |_()_)   |_|\\/|_||____||_|\\/|_||_()_)|____||_|\\_\\   |_|\\/|_|/__/\\__\\|_|\\__|/__/\\__\\\\____)|____||_|\\/|_||____||_|\\__|  |_|     .__)__) |__|.__)__)  |_|  |____||_|\\/|_|   ");
                        
//                        program.manager.addTestMember(new Member(
//                                "M0001", "Alice", "20-04-2004", 21, "Female",
//                                "012-3456789", "Penang", "Gold",
//                                "01-09-2025", "Active", "20-03-2026"
//                        ));
//                        
//                        program.manager.addTestMember(new Member(
//                                "M0002", "John", "15-03-2003", 22, "Male",
//                                "03-12345678", "KL", "Platinum",
//                                "01-09-2025", "Active", "10-06-2026"
//                        ));
//                        
//                        program.manager.addTestMember(new Member(
//                                "M0003", "Siti", "01-01-2001", 25, "Female",
//                                "011-23456789", "Kedah", "Diamond",
//                                "01-09-2024", "Active", "01-03-2026"
//                        ));
//                        
//                        program.manager.addTestMember(new Member(
//                                "M0004", "Siti", "01-01-2001", 25, "Female",
//                                "011-23456789", "Kedah", "Diamond",
//                                "01-09-2024", "Active", "14-03-2026"
//                        ));
                        
                        // LOAD FROM FILE...
                        program.manager.loadFromFile();
                        program.showMainMenu();
                                     
                        
                         
                }
		
			public void showMainMenu() {

			int choice = -1;
			while (choice != 0) {
                            
                            manager.updateMembershipStatusByExpiry();
                            int expiringSoonCount = manager.countExpiringSoonMembers();
                            
                            System.out.println(" \n===== CLUB MEMBER MANAGEMENT SYSTEM =====");
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
					break;
                                
                                case 2:
                                    boolean retrySearch = true;
                                    
                                    while (retrySearch) {
                                        System.out.print("Enter Member ID: ");
                                        String memID = input.nextLine().trim();
                                        
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
					System.out.println("Enter Member ID to update: ");
					String updateMem = input.nextLine().trim();
                                        
                                        Member member = manager.searchMemberById(updateMem);
                                        
                                        if (member != null) {
                                            showUpdateSubMenu(updateMem);
                                            retryUpdate = false;
                                        } else {
                                            System.out.println("Member Not Found!!");
                                            retryUpdate = askTryAgain();
                                        }
                                    }
					break;
					
				case 4:
                                    boolean retryRenew = true;
                                    while (retryRenew) {
                                        System.out.print("Enter Member ID to renew: ");
                                        String renewMem = input.nextLine().trim();
                                        
                                        Member member = manager.searchMemberById(renewMem);
                                        if (member != null) {
                                            manager.renewMembership(renewMem);
                                            retryRenew = false;
                                        } else {
                                            System.out.println("Member is not found.");
                                            retryRenew = askTryAgain();
                                        }
                                    }
                                    break;
                                    
                                    case 5:
                                        boolean retryDelete = true;
                                        while (retryDelete) {
                                            System.out.print("Enter Member ID to delete: ");
                                            String deleteMem = input.nextLine().trim();
                                            Member member = manager.searchMemberById(deleteMem);
                                            if (member != null) {
                                                if (askForConfirmation("Are you sure you want to delete this member?")) {
                                                    manager.deleteMember(deleteMem);
                                                } else {
                                                    System.out.println("Delete operation cancelled.");
                                                }
                                                retryDelete = false;
                                            } else {
                                                System.out.println("Member is not found.");
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
                                        String status = input.nextLine().trim();
                                        
                                        if(status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("Inactive")) {
                                            manager.searchMembersByMembershipStatus(status);
                                            retryStatus = false;
                                        }else{
                                            System.out.println("Invalid membership status");
                                            retryStatus = askTryAgain();
                                        }    
                                    }
                                    break;
    
				case 8:
                                    boolean retryLevel = true;
                                    while (retryLevel) {
                                        System.out.print("Membership Level: = Gold | Platinum | Diamond =   ");
                                        System.out.print("Enter Membership Level: ");
                                        String level = input.nextLine().trim();
                                        
                                        if (level.equalsIgnoreCase("Gold") || level.equalsIgnoreCase("Platinum") || level.equalsIgnoreCase("Diamond")) {
                                            
                                            manager.searchMembersByMembershipLevel(level);
                                            retryLevel = false;
                                        } else {
                                            System.out.println("Invalid membership level.");
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
                                    System.out.println("Exiting System...Goodbye! ");
                                    break;
				
				default:
					System.out.println("Invalid choice!");				
			
				}
			}
			
			// formating
			public void displayMemberDetails (Member member) {
				if (member == null) {
					System.out.println(RED + "[ Member not found. ]" + RESET);
				}else {
					System.out.println("\n===== DETAILS =====");
					System.out.println(member.toString());
                                        System.out.println("\n===================");
				}
		}
                        
                        
                        
                        
                        // Show Update Sub Menu
                        public void showUpdateSubMenu(String memberId) {
                            
                            Member target = manager.searchMemberById(memberId);
                            
                            if(target == null){
                                
                                System.out.println("Member Not Found!!");
                                return; 
                            }
                            
                            int choice = -1;
                            
                            while(choice != 0){
                                System.out.println("\n===== UPDATE MEMBER INFORMATION =====");
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
                                            System.out.print("Enter new contact number: ");
                                            String newContactNumber = input.nextLine().trim();
                                            manager.updateContactNumber(memberId, newContactNumber);
                                            break;
                                        case 2:
                                            System.out.print("Enter new address: ");
                                            String newAddress = input.nextLine().trim();
                                            manager.updateAddress(memberId, newAddress);
                                            break;
                                        case 3:
                                            System.out.print("Enter new membership level (Gold/Platinum/Diamond): ");
                                            String newLevel = input.nextLine().trim();
                                            manager.updateMembershipLevel(memberId, newLevel);
                                            break;
                                        
                                        case 4:
                                            if (askForConfirmation("Are you sure you want to cancel this membership?")) {
                                                manager.cancelMembership(memberId); 
                                            }else {
                                                System.out.println("Cancel membership operation cancelled.");   
                                            }
                                            break;
                                            
                                        case 0:
                                            System.out.println("Returning to main menu...");
                                            break;
                                        default:
                                            System.out.println("Invalid choice!");
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
                                    System.out.println("Invalid input. Please enter Y or N.");
       
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
                                    System.out.println("Invalid input. Please enter Y or N only...");
                                }
                            }
                        }
                        
        }

