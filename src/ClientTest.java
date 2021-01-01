import org.json.simple.JSONObject;

public class ClientTest extends Thread {
	static DataBase d=new DataBase();
	@SuppressWarnings("unchecked")
	public void run()
	{
		
		JSONObject obj=new JSONObject();
		obj.put("city","Bangalore");
		obj.put("age",32);
		System.out.println(d.create("Ragnar", obj));
		System.out.println(d.create("Bjorn", obj, 1));
		System.out.println(d.delete("Hello this is longest key over thirty two(32) characters to test"));
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(d.delete("Jai"));
		test();
		
	}
	
	 @SuppressWarnings("unchecked")
	public static void test()
	 {
		 JSONObject obj=new JSONObject();
			obj.put("city","pondy");
			obj.put("age",26);
			
		 System.out.println(d.read("Bjorn"));
			System.out.println(d.create("Bjorn", obj));
	 }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		JSONObject value=new JSONObject();
		value.put("city", "Hyderabad");
		value.put("age",21);
		System.out.println(d.create("Ragnar", value));
		System.out.println(d.read("Jai"));
		ClientTest ct=new ClientTest();
		ct.start();
		System.out.println(d.create("Jai", value));
		System.out.println(d.read("Jai"));
		System.out.println(d.delete("Rest"));
		
	}

}
