import java.io.Serializable;

import org.json.simple.JSONObject;
@SuppressWarnings("serial")
public class Data implements Serializable{
private JSONObject Value;
private int timeOut; 
private long storedtime;
public JSONObject getValue() {
	return Value;
}
public void setValue(JSONObject value) {
	Value = value;
}
public int getTimeOut() {
	return timeOut;
}
public void setTimeOut(int timeOut) {
	this.timeOut = timeOut;
}
public long getStoredtime() {
	return storedtime;
}
public void setStoredtime(long storedtime) {
	this.storedtime = storedtime;
}


}
