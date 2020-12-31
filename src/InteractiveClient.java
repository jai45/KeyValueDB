import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;
public class InteractiveClient {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		int flag=0,choice=0,age;
		DataBase db=null;
		String key,city;
		JSONObject value;
		while(true)
		{
			if(flag==0)
			{
				System.out.println("Enter the location to create database file else enter[N] ");
				String path=s.next();
				flag=1;
				if(path.equals("N"))
					db=new DataBase();
				else
					db=new DataBase(path);
				choice=1;
			}
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter Name");
				key=s.next();
				System.out.println("Enter age");
				age=s.nextInt();
				System.out.println("Enter city");
				city =s.next();
				value=new JSONObject();
				value.put("city", city);
				value.put("age", age);
				System.out.println("Enter the timeout of data if not enter 0 ");
				int timeOut=s.nextInt();
				if(timeOut==0)
					db.create(key, value);
				else
					db.create(key, value, timeOut);
				break;
			case 2:
				System.out.println("Enter Name");
				key=s.next();
				value=db.read(key);
				if(!(value==null))
				{
					System.out.println("Age of "+key+" is"+value.get("age"));
					System.out.println("City of "+key+" is"+value.get("city"));
				}
				else
				{
					System.out.println("Name not Exists in DataBase");
				}
				break;
			case 3:
				System.out.println("Enter Name");
				key=s.next();
				db.delete(key);
				break;
			case 4:
				System.out.println("Thank you... Process Terminated");
				break;
			default:
				System.out.println("Enter only 1 or 2 or 3");
				break;
				
			}
			if(choice==4)
				break;
			System.out.println("Enter 1-create,2-read,3-delete,4-Exit");
			choice=s.nextInt();
		}
			
		s.close();

	}

}
