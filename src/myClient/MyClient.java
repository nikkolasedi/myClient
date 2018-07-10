package myClient;
import myInterface.MyInterface;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

public class MyClient {
	

  
public static void main (String[] args) {
		
		SmallExample smallExample = new SmallExample("bolt://localhost:7687","nikkolasedi","cobacoba123");
		smallExample.addPerson("nikko");
		smallExample.printPeople("N");
		
		JSONParser parser = new JSONParser();

        try {
        	Registry myReg = LocateRegistry.getRegistry("127.0.0.1",1022);
			MyInterface c = (MyInterface) myReg.lookup("myRMI");
        	JSONArray obj = (JSONArray)parser.parse(new FileReader("C:\\Users\\Nikkolas Edi P\\git\\repository\\MyClient\\src\\myClient\\testfile.json"));

            for (Object o : obj)
            {
              JSONObject person = (JSONObject) o;

              String name = (String) person.get("name");
              System.out.println(name+"="+c.length(name));

              String city = (String) person.get("city");
              System.out.println(city+"="+c.length(city));

              String job = (String) person.get("job");
              System.out.println(job+"="+c.length(job));

              JSONArray cars = (JSONArray) person.get("cars");

              for (Object d : cars)
              {
                System.out.println((String)d+"="+c.length((String)d));
              }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(NotBoundException e) {
        	e.printStackTrace();
        }
		
		

	}
	}



