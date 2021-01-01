import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class testDB {

	DataBase db=new DataBase();
	@SuppressWarnings("unchecked")
	@Test
	void test() {
		JSONObject value=new JSONObject();
		value.put("age", 21);
		value.put("city", "Hyderabad");
		assertEquals("jai is successfully stored in the database",db.create("jai", value));
	}
	@Test
	void test1() {
		assertEquals("Hello this is longest key over thirty two(32) characters to test is not Valid",db.delete("Hello this is longest key over thirty two(32) characters to test"));
	}
	@SuppressWarnings("unchecked")
	@Test
	void test2() {
		JSONObject obj=new JSONObject();
		obj.put("city","Bangalore");
		obj.put("age",32);
		assertEquals("Bjorn is successfully stored in the database",db.create("Bjorn", obj));
	}
	@SuppressWarnings("unchecked")
	@Test
	void test3() {
		JSONObject obj=new JSONObject();
		obj.put("city","Goa");
		obj.put("age",32);
		db.create("ragnar", obj,1);
		assertEquals(obj,db.read("ragnar"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void test4() { 
		JSONObject obj=new JSONObject();
		obj.put("city","Delhi");
		obj.put("age",32);
		db.create("Krish", obj,1);
		assertEquals(null,db.read("discovery"));
	}
	@SuppressWarnings("unchecked")
	@Test
	void test5() {
		JSONObject obj=new JSONObject();
		obj.put("city","Mumbai");
		obj.put("age",32);
		db.create("Ram", obj);
		assertEquals("Ram is deleted",db.delete("Ram"));
	}
	@SuppressWarnings("unchecked")
	@Test
	void test6()
	{
		JSONObject obj=new JSONObject();
		obj.put("city","pondy");
		obj.put("age",26);
		db.create("selena", obj);
		assertEquals("Ram doesn't exists",db.delete("Ram"));
	}
	
}
