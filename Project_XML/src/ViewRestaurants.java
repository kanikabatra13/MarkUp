import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;   
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
          
            FileInputStream file = new FileInputStream(new File("C:/MarkUp/Restaurants.xml"));
                 
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
            String name, contact, address, type, cuisine, price, rating, website;
            String reviewString;
            
            
           // System.out.println("----------------------------");
            
        	for (int temp = 0; temp < nList.getLength(); temp++) {
         
        		 nNode = nList.item(temp);
         
        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
         
        		//if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         
        			Element eElement = (Element) nNode;
        			
        			name = eElement.getAttribute("Name");
        			address = eElement.getAttribute("Address");
        			contact = eElement.getAttribute("Contact");
        			cuisine = eElement.getAttribute("Cuisine");
        			price = eElement.getAttribute("Price");
        			rating = eElement.getAttribute("Rating");
        			website = eElement.getAttribute("href");
        			
        			 String pt1 = "<html><body Name:'";
                     String pt2 =
                         "" +
                         "<p>Name:    " + name +
                         "<p>Contact: " + contact +
                         "<p>Address: "+ address +
                         "<p>Cusine:  " + cuisine +
                         "<p>Price:   " + price +
                         "<p>Rating:  " + rating +
                         "<p>Website: " + website +
                         "<p><br></html>";
                      
        			items[temp] =  pt1 + pt2;
                    
        	}
        	
        	 UIManager.put("OptionPane.background",new ColorUIResource(127, 127, 255));
        	 UIManager.put("Panel.background",new ColorUIResource(127, 127, 255));
        	             
        	             
        	             
             
        	JList list = new JList(items);
            final JScrollPane scroll = new JScrollPane(list);  
            
            JPanel gui = new JPanel(new BorderLayout(3,3));
            final JPanel panel = new JPanel();
            scroll.setPreferredSize(new Dimension(480,250));
            gui.add(scroll, BorderLayout.CENTER);
            ActionListener listener = new ActionListener() {
                            int counter = 0;
                            public void actionPerformed(ActionEvent ae) {
                                panel.add(new JLabel("Label " + ++counter));
                                panel.revalidate();
                                int height = (int)panel.getPreferredSize().getHeight();
                                scroll.getVerticalScrollBar().setValue(height);
                            }
                        };
                       
             JOptionPane.showMessageDialog(null, gui);
                 
           
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
