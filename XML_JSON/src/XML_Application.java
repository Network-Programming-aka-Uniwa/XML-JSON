/**
 *
 * @author Nick Z. Zacharis
 */
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;        

public class XML_Application {

    public void ReadXMLFile(String fpath)
    {
      try {
         File inputFile = new File(fpath);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("food");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Type : " + 
           eElement.getAttribute("type"));
               System.out.println("Name : " + 
           eElement.getElementsByTagName("name").item(0).getTextContent());
               System.out.println("Price : " + 
           eElement.getElementsByTagName("price").item(0).getTextContent());
               System.out.println("Description : " + 
           eElement.getElementsByTagName("description").item(0).getTextContent());
         System.out.println("Calories : " + 
           eElement.getElementsByTagName("calories").item(0).getTextContent());
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
    
    public void CreateXML() 
    {
       try {
  	  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	// root element
	Document doc = docBuilder.newDocument();
	Element rootElement = doc.createElement("company");
	doc.appendChild(rootElement);

	// employee element
	Element employee = doc.createElement("employee");
	rootElement.appendChild(employee);

	// set attribute to employee element
	Attr attr = doc.createAttribute("id");
	attr.setValue("1");
	employee.setAttributeNode(attr);

	// name elements
	Element name = doc.createElement("name");
	name.appendChild(doc.createTextNode("Mary"));
	employee.appendChild(name);

	// surname element
	Element surname = doc.createElement("surname");
	surname.appendChild(doc.createTextNode("Louka"));
	employee.appendChild(surname);

	// position element
	Element position = doc.createElement("position");
	position.appendChild(doc.createTextNode("manager"));
	employee.appendChild(position);

	// salary element
	Element salary = doc.createElement("salary");
	salary.appendChild(doc.createTextNode("100000"));
	employee.appendChild(salary);

	// write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File("company.xml"));
	// StreamResult result = new StreamResult(System.out);

	transformer.transform(source, result);

	System.out.println("File saved!");

	} 
       catch (Exception ex) {
           System.out.println("Error : " + ex.getMessage());
	} 
    }
    
    public static void main(String[] args) {
       XML_Application x = new XML_Application();
       x.ReadXMLFile("breakfast.xml");
       x.CreateXML();
    }
}
