
/**
 *
 * @author Nick Z. Zacharis
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class JSON_Application {
    
    public String CreateJSON()
    {
        JSONObject obj = new JSONObject();

        obj.put("name", "Maria Louka");
        obj.put("university", "West Attica");
        obj.put("age", new Integer(20));
        obj.put("phones", null);
        obj.put("scholarship", new Boolean(true));
        
        JSONObject  addr = new JSONObject();
        addr.put("street", "Petrou Ralli");
        addr.put("no", "250");
        addr.put("postal", "12244");

        obj.put("address", addr);

        JSONArray lessons = new JSONArray();
	lessons.add("Physics");
	lessons.add("Chemistry");
	lessons.add("Computer Programming");
        
        obj.put("lessons", lessons);

        return obj.toJSONString();
    }
    
    public void WriteJSON2File(String fpath, String Content)
    {
        try {

	    FileWriter file = new FileWriter(fpath);
	    file.write(Content);
	    file.flush();
	    file.close();
	} catch (IOException ex) {
    	      System.out.println(ex.getMessage());
	}
    }
    
    public void ReadJSONFile(String fpath) 
    {
        try
        {
        JSONParser parser = new JSONParser();
	Reader reader = new FileReader(fpath);

        JSONObject obj = (JSONObject) parser.parse(reader);
        
        String name = (String) obj.get("name");
	System.out.println("Name = " + name);

        String university = (String) obj.get("university");
	System.out.println("University = " + university);
        
        
        long age = (long) obj.get("age");
	System.out.println("age = " + age);
        

        JSONObject addr = (JSONObject)obj.get("address");
        System.out.println("Address\n========");
        String no = (String) addr.get("no");
        System.out.println("no = " + no);
        String street = (String) addr.get("street");
        System.out.println("Street = " + street);
        String postal = (String) addr.get("postal");
        System.out.println("postal = " + postal);        
        System.out.println("=================");

        JSONArray phones = (JSONArray) obj.get("phones");
        if(phones != null) 
        {
            System.out.println("Phones\n=========");
            
            Iterator<String> it = phones.iterator();
		while (it.hasNext()) {
			System.out.println("Phone = " + it.next());
		}
        }
        
        JSONArray lessons = (JSONArray) obj.get("lessons");
        if(lessons != null)
        {
            System.out.println("Lessons\n=========");
            
            Iterator<String> it = lessons.iterator();
		while (it.hasNext()) {
			System.out.println("Lesson = " + it.next());
		}
        }
        
        reader.close();
        }
        catch(Exception ex) {
            System.out.printf("Error : " + ex.getMessage());
        }
    }
    
    public void ParseJSONString(String text)
    {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(text);
        
            String name = (String) obj.get("name");
	    System.out.println("Name = " + name);
        
        }
        catch(Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        JSON_Application x = new JSON_Application();
        String strJSON = x.CreateJSON();
        x.WriteJSON2File("student.json", strJSON);
        System.out.println(strJSON);
        x.ReadJSONFile("student.json");
        x.ParseJSONString(strJSON);
    }
}
