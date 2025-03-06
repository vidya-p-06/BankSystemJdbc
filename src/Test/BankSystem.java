package Test;
import java.sql.*;
import java.util.*;


public class BankSystem 
{

	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		try(sc;)
		{
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		   PreparedStatement ps = con.prepareStatement("insert into BankUser (acc_no, user_name, useradd, mobile,balance) VALUES (?, ?, ?, ?, 0.0)");
		   PreparedStatement ps1 = con.prepareStatement("update BankUser set balance = balance+ ? where acc_no=?"); 
		   PreparedStatement ps2 = con.prepareStatement("update BankUser set balance = balance - ? where acc_no = ? and balance >= ?");
		   PreparedStatement ps3 = con.prepareStatement("SELECT balance FROM BankUser WHERE acc_no = ?");
		   PreparedStatement ps4 = con.prepareStatement("SELECT * FROM BankUser WHERE acc_no = ?");
		   while(true)
		    {
		   System.out.println("Bank System Menu.......");
           System.out.println("1. Create Account");
           System.out.println("2. Deposit");
           System.out.println("3. Withdraw");
           System.out.println("4. View Account");
           System.out.println("5. Exit");
           System.out.print("Enter your choice: ");
           
           int choice =Integer.parseInt(sc.next());
           
           switch (choice)
            {
               case 1:
            	
      			 System.out.println("Enter User AccNo.");
      			 int acc_no= sc.nextInt();
      			 System.out.println("Enter User Name.");
      			 String username=sc.nextLine();
      			 sc.nextLine();
      		     System.out.println("Enter User Address.");
      		     String useradd= sc.nextLine();
      		     System.out.println("Enter User Mobile Number.");
      		    double empPhNo= sc.nextDouble();
      		    ps.setInt(1,acc_no);
      		 	ps.setString(2, username);
				ps.setString(3, useradd);
				ps.setDouble(4, empPhNo);      
				
      			int k = ps.executeUpdate();
      			
      			if(k > 0) 
				{
					System.out.println("Account Created successfully...");
				}
                   break;
                   
               case 2:
            	  System.out.println("Enter Account Number.");
            	  int acc_no1= sc.nextInt();

                  System.out.print("Enter Deposit Amount: ");
                  double amount = sc.nextDouble();
                  sc.nextLine();

                  if (amount <= 0) 
                  {
                      System.out.println("Error: Deposit amount must be positive.");
                      return;
                  }
                  ps1.setDouble(1, amount);
                  ps1.setInt(2, acc_no1); 

                  int rs1 = ps1.executeUpdate();
                  if (rs1 > 0) {
                      System.out.println(amount + " Deposited successfully...");
                  } else {
                      System.out.println("Error: Account not found.");
                  }
				  
                   break;
               case 3:
               	    System.out.println("Enter Account Number:");
            	    int acc_no2 = sc.nextInt();

            	    System.out.print("Enter Withdrawal Amount: ");
            	    double amount2 = sc.nextDouble();
            	    sc.nextLine();

            	    if (amount2 <= 0) {
            	        System.out.println("Error: Withdrawal amount must be positive.");
            	        break;
            	    }
            	    ps2.setDouble(1, amount2);
            	    ps2.setInt(2, acc_no2);
            	    ps2.setDouble(3, amount2); 

            	    int rs2 = ps2.executeUpdate();
            	    if (rs2 > 0) {
            	       
            	        ps3.setInt(1, acc_no2);
            	        ResultSet rs3 = ps3.executeQuery();

            	        if (rs3.next()) {
            	            double newBalance = rs3.getDouble("balance");
            	            System.out.println(amount2 + " withdrawn successfully.");
            	            System.out.println("Your Current Balance is: " + newBalance);
            	        }
            	    } else {
            	        System.out.println("Error: Insufficient funds or Account not found.");
            	    }
            	    break;


               case 4:
            	   System.out.println("Enter Account Number:");
                   int acc_no3 = sc.nextInt();
                   sc.nextLine();

                   ps4.setInt(1, acc_no3);
            	   ResultSet rs4 = ps4.executeQuery();

                   if (rs4.next()) {
                       System.out.println("\n ********Account Details *****");
                       System.out.println("Account No:" + rs4.getInt("acc_no"));
                       System.out.println("Name:" + rs4.getString("user_name"));
                       System.out.println("Address:" + rs4.getString("useradd"));
                       System.out.println("Mobile No:" + rs4.getDouble("mobile"));
                       System.out.println("Balance:" + rs4.getDouble("balance"));
                   } else {
                       System.out.println("Error: Account not found.");
                   }    

           	   
                   
                   break;
               case 5:
                   System.out.println("Exiting.");
                   
               default:
                   System.out.println("Invalid choice.");
           }
		    
		    } 
		  }
       
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
		    }
		   
	}


