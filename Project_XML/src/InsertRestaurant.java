import java.awt.Dimension;
import java.awt.Graphics;   
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class InsertRestaurant {
	
	public InsertRestaurant()
	{
		 
        try {
        	
        	// Taking the xml file as input.
            FileInputStream file = new FileInputStream(new File("C:/MarkUp/Restaurants.xml"));
                 
            //setting up DOM elements for XML file
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
             
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            Document restdata = builder.parse(file);
            
            restdata.getDocumentElement().normalize();
            
            NodeList nList = restdata.getElementsByTagName("RestDetails");
            Node nNode;
            Element element;
            List<Integer> ids = new ArrayList<Integer>();
            int id;
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
       		 nNode = nList.item(temp);
       		 Element eElement = (Element) nNode;
       		 id = Integer.parseInt(eElement.getAttribute("ID"));
       		 ids.add(id);
       		 
            }
            
            int maxID;
            String newID;
            
            maxID = Collections.max(ids) + 1;
            newID = String.valueOf(maxID);
            
          //setting up fields and labels 
		      JTextField restname = new JTextField(50);
		      restname.setBounds(70,25,100,30);
		      JTextField website = new JTextField(60);
		      JTextField cuisine = new JTextField(50);
		      JTextField address = new JTextField(100);
		      JTextField typeof = new JTextField(50);
		      JTextField price = new JTextField(10);
		      JTextField rating = new JTextField(10);
		      JTextField review = new JTextField(10);
		      JTextField contact = new JTextField(10);
		      
        // creating a JPanel object.    
		      JPanel myPanel = new JPanel();
		      myPanel.setLayout(new GridLayout(9,4));
		      myPanel.add(new JLabel("Restaurant name"));
		      myPanel.add(restname);
		      myPanel.add(new JLabel("Cuisine"));
		      myPanel.add(cuisine);
		      myPanel.add(new JLabel("Price"));
		      myPanel.add(price);
		      myPanel.add(new JLabel("Contact"));
		      myPanel.add(contact);
		      myPanel.add(new JLabel("Address"));
		      myPanel.add(address);
		      myPanel.add(new JLabel("Website"));
		      myPanel.add(website);
		      myPanel.add(new JLabel("Type"));
		      myPanel.add(typeof);
		      myPanel.add(new JLabel("Rating"));
		      myPanel.add(rating);
		      myPanel.add(new JLabel("Review"));
		      myPanel.add(review);
		  
		      myPanel.setPreferredSize(new Dimension(480, 150));
		      myPanel.setBorder(null);
		      
		      
		      // prompting the user to enter restaurant details     
		      int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Creating a new entry for Restaurant", JOptionPane.OK_CANCEL_OPTION);
		      
		      String restName, addr, cuisn, pricetext, url, typ, ratingtext, reviewtext, phno;
		      // if OK button is pressed the details entered are processed.
		      if (result == JOptionPane.OK_OPTION)
		        {
		    	 //getting values inputed by the user into variables
		    	  
		    	   restName = restname.getText();
		    	   addr = address.getText();
			       cuisn = cuisine.getText();
			       pricetext = price.getText();
			       ratingtext = rating.getText() ;
			       typ = typeof.getText();
			       url = website.getText();
			       reviewtext = review.getText();
			       phno = contact.getText();
		    	        
           
            Element root = restdata.getDocumentElement();
            
            Element restaurant = restdata.createElement("Restaurant");
            root.appendChild(restaurant);
            
            Element restDetails = restdata.createElement("RestDetails");
            restaurant.appendChild(restDetails);
            
            Element reviews = restdata.createElement("Reviews");
            restaurant.appendChild(reviews);
            
            Element reviewEl = restdata.createElement("review");
            reviews.appendChild(reviewEl);
            
            
            
            restDetails.setAttribute("Name", restName);
            restDetails.setAttribute("Address", addr);
            restDetails.setAttribute("Cuisine", cuisn);
            restDetails.setAttribute("href", url);
            restDetails.setAttribute("Price", pricetext);
            restDetails.setAttribute("Rating", ratingtext);
            restDetails.setAttribute("typeofrestaurant", typ);
            restDetails.setAttribute("ID", newID);
            restDetails.setAttribute("Contact", phno);
            reviewEl.setTextContent(reviewtext);
            
            
            System.out.println(getXmlString(restdata));
            JOptionPane.showMessageDialog(null,"Restauarant Entry has been addded to XML File ");
            
            //xpath variable
            XPath xPath =  XPathFactory.newInstance().newXPath();
            
           }
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
        /*catch (XPathExpressionException e) {
            e.printStackTrace(); 
        }  */
	}
	
	public static void main(String[] args) {
		
		new InsertRestaurant();
	}
	
	
	 private static String getXmlString(Document document)
     {
      TransformerFactory transFactory = TransformerFactory.newInstance();
      Transformer transformer;
      StringWriter sw = new StringWriter(); 
      File updatexml = new File("C:/MarkUp/Restaurants.xml");
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
