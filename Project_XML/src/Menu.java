
import java.awt.Dimension;
import java.awt.Graphics;   

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
//programmer: Kanika Batra
//            Urja Desai
public class Menu {
public Menu()
{
  String message = "Welcome to the Restaurants XML Store " + "\n", response;

  message += "\n" + " Enter a number for";
  message += "\n" + "  1 - Create a entry for new restaurants";
  message += "\n" + "  2 - View the list of restaurants";
  message += "\n" + "  3 - Manipulate entry for a restaurant";	
  message += "\n" + "  4 - Delete a restaurant";	
  message += "\n" + "  5 - Exit Program" + "\n" + " ";

  
  char ansmenu ='Y';

  do {
	
   try {  
  
	    UIManager.put("OptionPane.minimumSize",new Dimension(480, 150)); 
	    UIManager.put("OptionPane.background",new ColorUIResource(127, 127, 255));
	    UIManager.put("Panel.background",new ColorUIResource(127, 127, 255));
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
	         break;
	case 4:  
		     DeleteRestaurant  dt = new DeleteRestaurant();
		     break;
	case 5:  
		     ansmenu = 'N';  System.exit(1);
             break;
             
	default: { ansmenu = 'Y'; choice = 0;
	//JOptionPane.showMessageDialog(null,"Enter a number: 1 - 5");
	UIManager.put("OptionPane.minimumSize",new Dimension(480, 150)); 
	UIManager.put("OptionPane.background",new ColorUIResource(127, 127, 255));
    UIManager.put("Panel.background",new ColorUIResource(127, 127, 255));
	JOptionPane.showMessageDialog(null, "Enter a number: 1 - 5" );
	} 
    }//end switch
   }//end try
   
   // catch block to catch character entering exception.
   catch (NumberFormatException ne)
   {
	    UIManager.put("OptionPane.minimumSize",new Dimension(480, 150)); 
		UIManager.put("OptionPane.background",new ColorUIResource(127, 127, 255));
	    UIManager.put("Panel.background",new ColorUIResource(127, 127, 255));
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

