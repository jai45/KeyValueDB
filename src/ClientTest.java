import org.json.simple.JSONObject;

public class ClientTest extends Thread {
	static DataBase d=new DataBase();
	@SuppressWarnings("unchecked")
	public void run()
	{
		
		JSONObject obj=new JSONObject();
		obj.put("city","Bangalore");
		obj.put("age",32);
		d.delete("Jai");
		d.create("Bjorn", obj, 1);
		d.delete("Hello this is longest key over thirty two(32) characters to test");
		d.create("Bjorn", obj);
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		JSONObject value=new JSONObject();
		value.put("city", "Hyderabad");
		value.put("age",21);
		ClientTest ct=new ClientTest();
		ct.start();
		d.create("Jai", value);
		d.create("Ragnar", value);
		d.delete("Bjorn");
		
	}

}
