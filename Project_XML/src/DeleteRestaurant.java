
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

public class DeleteRestaurant {

	public static void main(String[] args) {
		new DeleteRestaurant();
	}
	
	public DeleteRestaurant()
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
            Node pNode;
            
            String message = "Please Enter the ID of the Restaurant you wish to Delete"; 
            String RestID = JOptionPane.showInputDialog(message);
            int flag = 0;
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
       		 nNode = nList.item(temp);
       		Element eElement = (Element) nNode;
       		if(eElement.getAttribute("ID").equals(RestID))
       		{
       			pNode = nNode.getParentNode();
       			pNode.getParentNode().removeChild(pNode);
       			flag = 1;
       			break;
       		}
       		 
            }
            
            if(flag == 1){
            	 System.out.println(getXmlString(restdata));
            	JOptionPane.showMessageDialog(null,"Restaurant has been Deleted from the xml");
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
        /*catch (XPathExpressionException e) {
            e.printStackTrace(); 
        }  */
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

