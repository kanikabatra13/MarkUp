import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;   

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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


public class ViewRestaurants {
	
	public static void main(String[] args) {
		
		new ViewRestaurants();
		
	}
	
		
public ViewRestaurants(){
			
		
		 
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
            
            //NodeList nodeListPub = HW3.getElementsByTagName("published"); 
           // NodeList nodeListArt;
            Element element;
            
          //xpath variable
            XPath xPath =  XPathFactory.newInstance().newXPath();
            String items[] = new String[100];
            
            
            
            String name, address, type, cuisine, price, rating, website;
            String reviewString;
            
            
            System.out.println("----------------------------");
            
        	for (int temp = 0; temp < nList.getLength(); temp++) {
         
        		 nNode = nList.item(temp);
         
        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
         
        		//if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         
        			Element eElement = (Element) nNode;
        			
        			name = eElement.getAttribute("Name");
        			address = eElement.getAttribute("Address");
        			cuisine = eElement.getAttribute("Cuisine");
        			price = eElement.getAttribute("Price");
        			rating = eElement.getAttribute("Rating");
        			website = eElement.getAttribute("href");
        			
        			 String pt1 = "<html><body Name:'";
                     String pt2 =
                         "" +
                         "<p>Name: " + name +
                         "<p>Address: "+ address +
                         "<p>Cusine: " + cuisine +
                         "<p><br>";
                         
                     
        			//items[temp] = "Name:"+name+ "\n Address:"+address+"\n Cuisine:"+cuisine;
        			items[temp] =  pt1 + pt2;
                    
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
            
            JOptionPane.showMessageDialog(null, panel);
            panel.setPreferredSize(new Dimension(480, 150));
		    panel.setBorder(null);
		   
            
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
}
