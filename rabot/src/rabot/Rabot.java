package rabot;
import java.util.*;

import com.google.gson.*;

import java.awt.*;
import java.awt.event.*;

public class Rabot {
	public static void main(String[]z){
		Rabot.request("");
	}
	public static String request(String json){
		String str =
		"["
		+"  {"
		+"     \"tasks\":"
		+"     ["
		+"        {\"xy\":[300,300]},"
		+"        {\"click\":0},"
		+"        {\"type\":\"`1234567890-=qwertyuiop[]\\asdfghjkl;zxcvbnm,./\"},"
		+"        {\"xy\":[500,500]},"
		+"        {\"click\":0},"
		+"        {\"type\":\"~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?,./\"}"
		+"     ]"
		+"  }"
		+"]";
		System.out.println(str);
		JsonParser jP = new JsonParser();
		JsonArray queue = jP.parse(str).getAsJsonArray();
		for(JsonElement je:queue){
			JsonObject queueItem = je.getAsJsonObject();
			JsonArray tasks = queueItem.get("tasks").getAsJsonArray();
			for(JsonElement task:tasks){
				JsonObject left = task.getAsJsonObject();
				if(left.get("xy")!=null){
					JsonArray coordinates = left.get("xy").getAsJsonArray();
					util.xy(
							coordinates.get(0).getAsInt(),
							coordinates.get(1).getAsInt()
					);
				}else
				if(left.get("click")!=null){
					util.click();

					//util.vk();
				}else
				if(left.get("type")!=null){
					String s = left.get("type").getAsString();
					util.type(s);
				}						
			}
		}
		return "";
	}
}
class util{
	private static Robot _robot;
	private static Robot r(){
		if(_robot==null)
		try {
			_robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _robot;
	}
	public static void xy(int x,int y){
		r().delay(wait(200,3000));
		r().mouseMove(x,y);
	}
	private static int wait(int preset,int randomLength){
		double random = new Random().nextDouble();
		return preset + (int)((((double)randomLength)*random));
	}
	public static void click(){
		r().mousePress(InputEvent.BUTTON1_MASK);
		r().delay(wait(200,200));
		r().mouseRelease(InputEvent.BUTTON1_MASK);
		r().delay(wait(200,100));
	}
	public static void type(String s){
		doType(s,false);
	}
	
	public static void cutNPaste(String s){
		doType(s,true);
	}	
	private static void doType(String s,boolean cutNpaste) {
		char[] chars = s.toCharArray();
		for (char c : chars) {
			char sister = sisterKey(c);
			if(sister!='1'){
				r().keyPress(c);
				r().keyRelease(c);
			}
			else{
				r().keyPress(20);
				r().keyRelease(20);
				
				r().keyPress(sister);
				r().keyRelease(sister);
				
				r().keyPress(20);
				r().keyRelease(20);
			}
			
			
		}
	}
//	public static void vk(){
//		Map<Integer,String> keyTextToCode = new HashMap<Integer,String>(256);
//		java.lang.reflect.Field[] fields = KeyEvent.class.getDeclaredFields();
//		for (java.lang.reflect.Field field : fields) {
//		    String name = field.getName();
//		    if (name.startsWith("VK_")) {
//		        try {
//					keyTextToCode.put(field.getInt(null),name.substring("VK_".length()).toUpperCase());
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//		}
//		Set keys = keyTextToCode.keySet();
//		for(Object key:keys){
//			//int 
//			System.out.println(key+" : "+keyTextToCode.get(key));
//			try {
//				Object o = KeyEvent.class.getField("VK_"+keyTextToCode.get(key) );
//				System.out.println(o);
//			} catch (NoSuchFieldException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//System.out.println("res:"+o);
//		}
//		//return "";
//		
//		
//	}
	private static char sisterKey(char charCode){
		if(charCode=='~') return '`';
		if(charCode=='!') return '1';
		if(charCode=='@') return '2';
		if(charCode=='#') return '3';
		if(charCode=='$') return '4';
		if(charCode=='%') return '5';
		if(charCode=='^') return '6';
		if(charCode=='&') return '7';
		if(charCode=='*') return '8';
		if(charCode=='(') return '9';
		if(charCode==')') return '0';
		if(charCode=='_') return '-';
		if(charCode=='+') return '=';
		if(charCode=='Q') return 'q';
		if(charCode=='W') return 'w';
		if(charCode=='E') return 'e';
		if(charCode=='R') return 'r';
		if(charCode=='T') return 't';
		if(charCode=='Y') return 'y';
		if(charCode=='U') return 'u';
		if(charCode=='I') return 'i';
		if(charCode=='O') return 'o';
		if(charCode=='P') return 'p';
		if(charCode=='{') return '[';
		if(charCode=='}') return ']';
		if(charCode=='A') return 'a';
		if(charCode=='S') return 's';
		if(charCode=='D') return 'd';
		if(charCode=='F') return 'f';
		if(charCode=='G') return 'g';
		if(charCode=='H') return 'h';
		if(charCode=='J') return 'j';
		if(charCode=='K') return 'k';
		if(charCode=='L') return 'l';
		if(charCode==':') return ';';
		if(charCode=='"') return '\'';
		if(charCode=='Z') return 'z';
		if(charCode=='X') return 'x';
		if(charCode=='C') return 'c';
		if(charCode=='V') return 'v';
		if(charCode=='B') return 'b';
		if(charCode=='N') return 'n';
		if(charCode=='M') return 'm';
		if(charCode=='<') return ',';
		if(charCode=='>') return '.';
		if(charCode=='?') return '/';
		return '1';
	}
}
