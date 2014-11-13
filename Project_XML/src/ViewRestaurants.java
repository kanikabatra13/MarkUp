import java.awt.Color;
import java.awt.Graphics;   

import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;




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
		 
        try {
        	
        	// Taking the xml file as input.
            FileInputStream file = new FileInputStream(new File("C:/Users/cguliani/Desktop/MarkUp/Restaurants.xml"));
                 
            //setting up DOM elements for XML file
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
             
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
             
            Document restdata = builder.parse(file);
            
            restdata.getDocumentElement().normalize();
            
            //NodeList Variables to get the Element names.
            
            NodeList nList = restdata.getElementsByTagName("Restaurant");
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
        			
        			name = eElement.getAttribute("name");
        			address = eElement.getAttribute("address");
        			cuisine = eElement.getAttribute("Cuisine");
        			price = eElement.getAttribute("price");
        			rating = eElement.getAttribute("rating");
        			website = eElement.getAttribute("href");
        			
        			items[temp] = "Name:"+name+"\n Address:"+address+"\n Cuisine:"+cuisine;
                    
        	}
        	


        	 UIManager UI = new UIManager();
        	             UI.put("OptionPane.background", Color.BLUE);
        	             UI.put("OptionPane.messagebackground", Color.BLUE);
        	             UI.put("Panel.background", Color.BLUE);
        	
        	JList list = new JList(items);
            JPanel panel = new JPanel();
            panel.add(list);
            JOptionPane.showMessageDialog(null, panel);
        
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
}
