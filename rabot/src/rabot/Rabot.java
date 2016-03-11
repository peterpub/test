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
			int[]codes=getCode(c);
			if(codes.length==2){
				System.out.println("shift " + codes[1]);
				r().keyPress(codes[0]);
				r().keyPress(codes[1]);
				r().keyRelease(codes[1]);
				r().keyRelease(codes[0]);				
			}else{
				System.out.println(codes[0]);
				
				r().keyPress(codes[0]);
				r().keyRelease(codes[0]);
			}		
		}
	}
	public static int[] getCode(char character) {
        switch (character) {
        case 'a': return new int[]{KeyEvent.VK_A};
        case 'b': return new int[]{KeyEvent.VK_B};
        case 'c': return new int[]{KeyEvent.VK_C};
        case 'd': return new int[]{KeyEvent.VK_D};
        case 'e': return new int[]{KeyEvent.VK_E};
        case 'f': return new int[]{KeyEvent.VK_F};
        case 'g': return new int[]{KeyEvent.VK_G};
        case 'h': return new int[]{KeyEvent.VK_H};
        case 'i': return new int[]{KeyEvent.VK_I};
        case 'j': return new int[]{KeyEvent.VK_J};
        case 'k': return new int[]{KeyEvent.VK_K};
        case 'l': return new int[]{KeyEvent.VK_L};
        case 'm': return new int[]{KeyEvent.VK_M};
        case 'n': return new int[]{KeyEvent.VK_N};
        case 'o': return new int[]{KeyEvent.VK_O};
        case 'p': return new int[]{KeyEvent.VK_P};
        case 'q': return new int[]{KeyEvent.VK_Q};
        case 'r': return new int[]{KeyEvent.VK_R};
        case 's': return new int[]{KeyEvent.VK_S};
        case 't': return new int[]{KeyEvent.VK_T};
        case 'u': return new int[]{KeyEvent.VK_U};
        case 'v': return new int[]{KeyEvent.VK_V};
        case 'w': return new int[]{KeyEvent.VK_W};
        case 'x': return new int[]{KeyEvent.VK_X};
        case 'y': return new int[]{KeyEvent.VK_Y};
        case 'z': return new int[]{KeyEvent.VK_Z};
        case 'A': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_A};
        case 'B': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_B};
        case 'C': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_C};
        case 'D': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_D};
        case 'E': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_E};
        case 'F': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_F};
        case 'G': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_G};
        case 'H': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_H};
        case 'I': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_I};
        case 'J': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_J};
        case 'K': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_K};
        case 'L': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_L};
        case 'M': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_M};
        case 'N': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_N};
        case 'O': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_O};
        case 'P': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_P};
        case 'Q': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_Q};
        case 'R': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_R};
        case 'S': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_S};
        case 'T': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_T};
        case 'U': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_U};
        case 'V': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_V};
        case 'W': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_W};
        case 'X': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_X};
        case 'Y': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_Y};
        case 'Z': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_Z};
        case '`': return new int[]{KeyEvent.VK_BACK_QUOTE};
        case '0': return new int[]{KeyEvent.VK_0};
        case '1': return new int[]{KeyEvent.VK_1};
        case '2': return new int[]{KeyEvent.VK_2};
        case '3': return new int[]{KeyEvent.VK_3};
        case '4': return new int[]{KeyEvent.VK_4};
        case '5': return new int[]{KeyEvent.VK_5};
        case '6': return new int[]{KeyEvent.VK_6};
        case '7': return new int[]{KeyEvent.VK_7};
        case '8': return new int[]{KeyEvent.VK_8};
        case '9': return new int[]{KeyEvent.VK_9};
        case '-': return new int[]{KeyEvent.VK_MINUS};
        case '=': return new int[]{KeyEvent.VK_EQUALS};
        case '~': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE};
        case '!': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_1};
        case '@': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_2};
        case '#': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_3};
        case '$': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_4};
        case '%': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_5};
        case '^': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_6};
        case '&': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_7};
        case '*': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_8};
        case '(': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_9};
        case ')': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_0};
        case '_': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS};
        case '+': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS};
        case '\t':return new int[]{KeyEvent.VK_TAB};
        case '\n':return new int[]{KeyEvent.VK_ENTER};
        case '[': return new int[]{KeyEvent.VK_OPEN_BRACKET};
        case ']': return new int[]{KeyEvent.VK_CLOSE_BRACKET};
        case '\\':return new int[]{KeyEvent.VK_BACK_SLASH};
        case '{': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET};
        case '}': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET};
        case '|': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH};
        case ';': return new int[]{KeyEvent.VK_SEMICOLON};
        case ':': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON};
        case '\'':return new int[]{KeyEvent.VK_QUOTE};
        case '"': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE};
        case ',': return new int[]{KeyEvent.VK_COMMA};
        case '<': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA};
        case '.': return new int[]{KeyEvent.VK_PERIOD};
        case '>': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD};
        case '/': return new int[]{KeyEvent.VK_SLASH};
        case '?': return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH};
        case ' ': return new int[]{KeyEvent.VK_SPACE};
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

}
