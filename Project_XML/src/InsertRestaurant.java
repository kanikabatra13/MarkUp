import java.awt.Graphics;   

import javax.swing.JOptionPane;

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

public class InsertRestaurant {
	
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
            Element root = restdata.getDocumentElement();
            Element restaurant = restdata.createElement("Restaurantdetails");
            root.appendChild(restaurant);
            
            restaurant.setAttribute("name", "Test Restauarant");
            
            
          //xpath variable
            XPath xPath =  XPathFactory.newInstance().newXPath();
            
            
            
            
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
