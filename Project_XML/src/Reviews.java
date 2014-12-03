import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;   
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
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

public class Reviews {
	
public static void main(String[] args) {
		
		new Reviews();
		
           	}

public Reviews() {
	
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
        String name;
        String reviewString;
        String items2[] = new String[100];
        
        
       // System.out.println("----------------------------");
        
    	for (int temp = 0; temp < nList.getLength(); temp++) {
     
    		 nNode = nList.item(temp);
     
    		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
     
    		//if (nNode.getNodeType() == Node.ELEMENT_NODE) {
     
    		 Element eElement = (Element) nNode;
 			
 			name = eElement.getAttribute("Name");
 			//Id = eElement.getAttribute("ID");
 			
 			
 			 String pt1 = "<html><body Name:'";
              String pt2 =
                  "" +
                  "<p>Name: " + name +
                  
                  "<p><br>";
                  
              items[temp] = name;
              
    			
}
    	 UIManager.put("OptionPane.background",new ColorUIResource(127, 127, 255));
    	 UIManager.put("Panel.background",new ColorUIResource(127, 127, 255));
    	             
    	             
    	             
         
    	 JList list = new JList(items);
         JPanel panel = new JPanel();
         panel.add(list);
         
         
         JFrame frame = new JFrame("Input Dialog Example 3");
         
         String response = (String) JOptionPane.showInputDialog(frame, "Please Select the Restaurant from the list you want to see the reviews for",
         		"Update Restaurant", JOptionPane.QUESTION_MESSAGE,null,items,items[0]);
         panel.setPreferredSize(new Dimension(480, 150));
 	    panel.setBorder(null);
 	   for (int temp3 = 0; temp3 < nList.getLength(); temp3++) {
           
    		 nNode = nList.item(temp3);
    		Element eElement = (Element) nNode;
    		
    		if(eElement.getAttribute("Name").equals(response))
    		{
    			
    			    Node pNode = nNode.getParentNode();
				    NodeList rNodeList = pNode.getChildNodes();
				    for(int temp = 0; temp < nList.getLength(); temp++)
			
				    {
				    	System.out.println(rNodeList.item(temp).getNodeName());
				    	if(rNodeList.item(temp).getNodeName().equals("Reviews"))
				    	{
				    	
						    NodeList rNodeList2 = rNodeList.item(temp).getChildNodes();
						    for(int temp2 = 0; temp2 < rNodeList2.getLength(); temp2++)
						    {
						    	if(rNodeList.item(temp2).getNodeName().equals("review"))
						    	{
					    		String review = rNodeList.item(temp).getTextContent();
					    		System.out.println(review);
					    		 String pt1 = "<html><body Reviews:'";
					    		 String pt2 =
					                     "" +
					                     "<p>" + review +
					                     
	                                      "<p><br></html>";
					                     
					                 items2[temp] = pt1 + pt2; 
					                 break;
						       }
				    		
				        	}
    			
    		          }
    		
	                }
 	   }
 	   
    		
    		JList list2 = new JList(items2);
            final JScrollPane scroll = new JScrollPane(list2);  
            
            JPanel gui = new JPanel(new BorderLayout(3,3));
            final JPanel panel2 = new JPanel();
            scroll.setPreferredSize(new Dimension(480,250));
            gui.add(scroll, BorderLayout.CENTER);
            ActionListener listener = new ActionListener() {
                            int counter = 0;
                            public void actionPerformed(ActionEvent ae) {
                                panel2.add(new JLabel("Label " + ++counter));
                                panel2.revalidate();
                                int height = (int)panel2.getPreferredSize().getHeight();
                                scroll.getVerticalScrollBar().setValue(height);
                            }
                        };
                       
             JOptionPane.showMessageDialog(null, gui);
                    
                   
         
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
    
	
	
}
}
