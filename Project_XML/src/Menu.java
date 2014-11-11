
import java.awt.Graphics;   

import javax.swing.JOptionPane;
//programmer: Kanika Student 
public class Menu {
public Menu()
{
String message = "Welcome to the Restaurants XML Store " + "\n", response;

  message += "\n" + "Enter...";
  message += "\n" + "  1 Create a entry for new restaurants";
  message += "\n" + "  2 View the list of restaurants";
  message += "\n" + "  3 Manipulate entry for a restaurant";	
  message += "\n" + "  4 Delete a restaurant";	
  message += "\n" + "  5 Exit Program" + "\n" + " ";

  
  char ansmenu ='Y';

  do {
	
   try {  
  
    response  = JOptionPane.showInputDialog(message);

    int choice = Integer.parseInt(response);
	
    switch (choice) {
	case 1:  
		     InsertRestaurant id = new InsertRestaurant();
	         break;	
     case 2: 
     	    ViewRestaurants vt = new ViewRestaurants(); 
 	         break;
	case 3: 
		
				UpdateRestaurant ut = new UpdateRestaurant();
		    // UpdateTicket ut = new UpdateTicket();
	         break;
	case 4:  
		     DeleteRestaurant  dt = new DeleteRestaurant();
		      break;
	case 5:  
		     ansmenu = 'N';  System.exit(1);
             break;
	default: { ansmenu = 'Y'; choice = 0;
	JOptionPane.showMessageDialog(null,"Enter a number: 1 - 5");
	} 
    }//end switch
   }//end try
   
   // catch block to catch character entering exception.
   catch (NumberFormatException ne)
   {
	   JOptionPane.showMessageDialog(null,"Character is not a valid choice Please Enter a number: 1 - 5");
   }
   
   // general catch block for all exceptions
   catch (Exception e )
   { 
	   System.out.println(e); 
   }  
  }while(ansmenu == 'Y' || ansmenu == 'y');  

}
public static void main(String[] args)  
{ 
 new Menu();
}//end main
}//end class 

