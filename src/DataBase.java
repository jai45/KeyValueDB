import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;
import org.json.simple.JSONObject;

public class DataBase {
String path;
File file=null;
ConcurrentHashMap<String,Data> KVdata=new ConcurrentHashMap<String,Data>();  // To store the Key value pair
FileInputStream fis;
ObjectInputStream ois;
FileOutputStream fos;
ObjectOutputStream os;


	DataBase(String Path)    // Constructor to initialize the file
	{
		try
		{
		this.path=Path+System.currentTimeMillis()+"dataStorage.txt";
			createFile(path);
		}
		catch(Exception e)
		{
			
		}
	}
	DataBase()               //  this constructor is called if the path is not Specified
	{
		this("");
		
	}
	
	
	
	public void createFile(String path) throws IOException     // Creation of file
	{
		file=new File(path);
		file.createNewFile();
		
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void readFile()   // Reading the file 
	{
		
		try {
		 fis=new FileInputStream(path);                                 
		 ois = new ObjectInputStream(fis);                              
		 KVdata=(ConcurrentHashMap<String, Data>) ois.readObject();     // Storing the data into Hashmap from the file
		ois.close();fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public synchronized void writeFile()
	{
	
		try
		{
			fos=new FileOutputStream(path);
			os=new ObjectOutputStream(fos);
			os.writeObject(KVdata);                              // Writing the data to the file
			os.close();fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	
	/*
	  
	Method to check if the key is already exists in the database
	
	*/
	public boolean isKeyAvailable(String key) throws IOException   
	{
		if(file.length()==0)               // If the file is empty directly returns false
			return false;
		boolean isavailable=false;
		try {
		readFile();                       // invoking readFile() method to read and store the data to HashMap
		if(KVdata.containsKey(key))
		{
			Data data=KVdata.get(key);
			long currtime=System.currentTimeMillis();
			long storedtime=data.getStoredtime(); 
			int timeout=data.getTimeOut();
			if(timeout>0)                     // Checking if key is expired or not
			{
				if(currtime<=storedtime+timeout)
				{
					isavailable=true;
				}
				else
				{
					isavailable=false;
					KVdata.remove(key);  // If the key is Expired it must be removed from the database
					writeFile();
				}
			}
			else
			{
				isavailable=true;
			}
		}
		else
		{
			isavailable=false;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return isavailable;
	}
	
	
	
	
	public boolean isKeyValid(String key)                  // method to validate the key 
	{
		if(key.length()>32)                               // Key must be less than 32 characters
			return false;
		return true;
	}
	
	
	
	
	
	public boolean isValueValid(JSONObject value)        // Method to validate JSON Object 
	{
		int size=value.toJSONString().getBytes().length/1024;   // json object should be less than 16KB
		if(size>16)
		{
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	public void create(String key,JSONObject value,int timeOut)       // method to store key and value into the database 
	{
		if(!isKeyValid(key))                                           // invoking key validation method
		{
			System.out.println("Key is not Valid");
			return;
		}
		if(!isValueValid(value))                                      // invoking json object validation method
		{
			System.out.println("-----------MEMORY ERROR------------");
			System.out.println("Json Object memory Exceeded 16kb ");
			return;
		}
		long size=value.toJSONString().getBytes().length/(1024*1024) + file.length()/(1024*1024);
		if(size>1024)
		{
			System.out.println("-----------MEMORY LIMIT REACHED------------");     // checking the size of database it should not exceed 1GB
			return;
		}
		try
		{
		if(!isKeyAvailable(key))
		{
			Data data=new Data();
			data.setStoredtime(System.currentTimeMillis());
			data.setValue(value);
			data.setTimeOut(timeOut);
			KVdata.put(key, data);
			writeFile();
			System.out.println(key+" is successfully stored in the database");
		}
		else
		{
			System.out.println("Key Already Exists");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void create(String key,JSONObject value)           // method to store key and value into the database without any expiry timelimit of data
	{
		try
		{
			create(key,value,0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public JSONObject read(String key)            // method to read the data from database
	{
		if(!isKeyValid(key))
		{
			System.out.println("Key is not Valid");
			return null;
		}
		try
		{
			if(isKeyAvailable(key))
			{
				return KVdata.get(key).getValue();
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public void delete(String key)                              // method to delete the data from database
	{
		if(!isKeyValid(key))
		{
			System.out.println("Key is not Valid");
			return;
		}
		try
		{
			if(isKeyAvailable(key))
			{
				
				KVdata.remove(key);
				writeFile();
				System.out.println(key +" is deleted");
			}
			else
			{
				System.out.println("Key doesn't exists");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}