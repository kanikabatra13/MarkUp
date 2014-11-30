import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;   

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class UpdateRestaurant {

public static void main(String[] args) {
		
		new UpdateRestaurant();
		
	}
public UpdateRestaurant()
{
	try {
    	
    	// Taking the xml file as input.
      
        FileInputStream file = new FileInputStream(new File("C:/Users/cguliani/Desktop/MarkUp/Restaurants.xml"));
             
        //setting up DOM elements for XML file
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
         
        DocumentBuilder builder =  builderFactory.newDocumentBuilder();
         
        Document restdata = builder.parse(file);
        
        restdata.getDocumentElement().normalize();
        
        //NodeList Variables to get the Element names.
        
        NodeList nList = restdata.getElementsByTagName("RestDetails");
        Node nNode;
        int flag = 0;
 	   int updatenode = 0;
       
        Element element;
        String items[] = new String[100];
                
        String name, Id;
        
        
        System.out.println("----------------------------");
        
    	for (int temp = 0; temp < nList.getLength(); temp++) {
     
    		 nNode = nList.item(temp);
     
    		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
     
    		//if (nNode.getNodeType() == Node.ELEMENT_NODE) {
     
    			Element eElement = (Element) nNode;
    			
    			name = eElement.getAttribute("Name");
    			Id = eElement.getAttribute("ID");
    			
    			
    			 String pt1 = "<html><body Name:'";
                 String pt2 =
                     "" +
                     "<p>Name: " + name +
                     "<p>ID: "+ Id +
                     "<p><br>";
                     
                 items[temp] = name;
                 
    			//items[temp] =  pt1 + pt2;
                
    	}
    	
    	
    	              UIManager UI = new UIManager();
    	            UI.put("OptionPane.background", Color.BLUE);
    	             UI.put("OptionPane.messagebackground", Color.BLUE);
    	            UI.put("Panel.background", Color.BLUE);
    	             UIManager.put("OptionPane.background",new ColorUIResource(255,0,0));
    	             UIManager.put("Panel.background",new ColorUIResource(255,0,0));
    	             
    	             
    	             
    	JList list = new JList(items);
        JPanel panel = new JPanel();
        panel.add(list);
        
        String message = "Please Enter the ID of the Restaurant you wish to Update";
        
        JFrame frame = new JFrame("Input Dialog Example 3");
        
        String response = (String) JOptionPane.showInputDialog(frame, "Please Select the Restaurant from the list you wish to Update", "Update Restaurant", JOptionPane.QUESTION_MESSAGE,null,items,items[0]);
        panel.setPreferredSize(new Dimension(480, 150));
	    panel.setBorder(null);
	   
	    for (int temp = 0; temp < nList.getLength(); temp++) {
            
      		 nNode = nList.item(temp);
      		Element eElement = (Element) nNode;
      		if(eElement.getAttribute("Name").equals(response))
      		{
      			flag = 1;
      			updatenode = temp;
      			break;
      		}
      		
	    }
	    
	    if(flag == 1){
	    	
	   String msg1 = "Please enter the no of the field you wish to update \n" + "1. Restaurant Name" + "\n"
               + "2. Contact No" + "\n" + "3. Restaurant Cuisine" +"\n" + "4. Price" + "\n" +
			        "5. Rating" + "\n" + "6. Address"+ "\n" + "7. Website" + "\n" + "8. Add a Review!", response2 ;
	 
	    response2  = JOptionPane.showInputDialog(msg1);
	    String restName, contactNo, restCuisine, Price, Rating, Address, Website, review;
	    Node nodeAttr;

	    	 nNode = nList.item(updatenode);
	    	 Element eElement = (Element) nNode;
	    	 System.out.println(eElement.getAttribute("Name"));
	         NamedNodeMap attr = nNode.getAttributes();

			    int choice = Integer.parseInt(response2);
			    //updating the ticket with respect to the choice entered.
			    switch (choice) 
			    {
				case 1: 
				   restName  = JOptionPane.showInputDialog("Please Enter the new name for the Restaurant");	
	               nodeAttr = attr.getNamedItem("Name");
	               nodeAttr.setTextContent(restName);
	               getXmlString(restdata);
	               JOptionPane.showMessageDialog(null,"Name has been updated in the XML File");
	                break;
				case 2: 
					  contactNo  = JOptionPane.showInputDialog("Please Enter the new Contact No for the Restaurant");	
		               nodeAttr = attr.getNamedItem("Contact");
		               nodeAttr.setTextContent(contactNo);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Contact information has been updated in the XML File");
		                break;
		                
				case 3: 
					  restCuisine  = JOptionPane.showInputDialog("Please Update the Cuisine for the Restaurant");	
		               nodeAttr = attr.getNamedItem("Cuisine");
		               nodeAttr.setTextContent(restCuisine);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Cuisine has been updated in the XML File");
		                break;
		               	                
				case 4: 
					   Price  = JOptionPane.showInputDialog("Please Enter the adjusted price");	
		               nodeAttr = attr.getNamedItem("Price");
		               nodeAttr.setTextContent(Price);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Price has been updated in the XML File");
		                break;   
		                
				case 5: 
					   Rating  = JOptionPane.showInputDialog("Please Enter the recent ratings ");	
		               nodeAttr = attr.getNamedItem("Rating");
		               nodeAttr.setTextContent(Rating);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Rating has been updated in the XML File");
		                break;   
		                
				case 6: 
					   Address  = JOptionPane.showInputDialog("Please update the address");	
		               nodeAttr = attr.getNamedItem("Address");
		               nodeAttr.setTextContent(Address);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Address has been updated in the XML File");
		                break;   
		                
		                           
				case 7: 
					   Website  = JOptionPane.showInputDialog("Please Enter the adjusted price");	
		               nodeAttr = attr.getNamedItem("href");
		               nodeAttr.setTextContent(Website);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Website has been updated in the XML File");
		                break;   
				case 8: 
					   review  = JOptionPane.showInputDialog("Please write your review");	
					  /* Node rNode = nNode.getNextSibling();
					   Element reviews = (Element) rNode;
					   Element reviewEl = restdata.createElement("review");
					   reviews.appendChild(reviewEl);
		               getXmlString(restdata);
		               JOptionPane.showMessageDialog(null,"Review has been added!");*/
		                break;           
				default: choice = 0;
				JOptionPane.showMessageDialog(null,"Please enter a number: 1 - 8");
			    }
	    	
	    	
	    }
      	else
      	JOptionPane.showMessageDialog(null,"ID entered does not match ant Restaurant in the list");	
	    
	    
	    
	}
      
    catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    }
    
	
	

}
private static String getXmlString(Document document)
{
 TransformerFactory transFactory = TransformerFactory.newInstance();
 Transformer transformer;
 StringWriter sw = new StringWriter(); 
 File updatexml = new File("C:/Users/cguliani/Desktop/MarkUp/Restaurants.xml");
 try 
 {
  transformer = transFactory.newTransformer();
  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
  DOMSource source = new DOMSource(document);
  StreamResult result = new StreamResult(updatexml);
  transformer.transform(source, result);
 }
 catch (TransformerConfigurationException e) {
  e.printStackTrace();
 } catch (TransformerException e) {
  e.printStackTrace();
 } 
 return sw.toString();
}


}
