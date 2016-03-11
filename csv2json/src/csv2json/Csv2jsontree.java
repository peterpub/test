package csv2json;
import java.io.*;
import java.util.*;

import org.json.JSONException;
import org.json.JSONStringer;
public class Csv2jsontree {
	static Node root;
	public static void main(String[]z){
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\testdata\\input.csv"));
			List<String[]> data = new ArrayList<String[]>();
			for(String tmp; ( tmp=br.readLine() )!=null ; ){
				data.add(tmp.split(","));
			}
			Node[] pnt = new Node[data.get(0).length];
			for(int c = 0 ; c<pnt.length;c++){
				if(c==0)
					pnt[0] = root = new Node(data.get(1)[0],data.get(0)[0]);
				else{
					pnt[c] = new Node(data.get(1)[c],data.get(0)[c]);
					pnt[c-1].children.add(pnt[c]);
				}
			}
			for(int r = 2 ; r < data.size(); r++){
				String[] row = data.get(r);
				forc:for(int c = 0; c < row.length; c++){
					if(!row[c].equals(pnt[c].name+"")){
						for(int cc=c;cc<row.length;cc++){
							pnt[cc]=new Node(row[cc],data.get(0)[cc]);
							pnt[cc-1].children.add(pnt[cc]);
						}
						break forc;
					}
				}
			}
			System.out.println(util.toJson(root));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
class Node{
	public Node(String name,String type){
		this.name = name;
		this.type = type;
	}
	String name;
	String type;
	List<Node> children = new ArrayList<Node>();
}
class util{
	public static String toJson(Node n){
		JSONStringer js = new JSONStringer();
		_toJson(n,js);
		return js.toString();
	}
	public static void _toJson(Node n,JSONStringer js){
		try {
			js.object();
			js.key("name").value(n.name);
			js.key("type").value(n.type);
			if(n.children.size()>0){
				js.key("children");
				js.array();
				for(Node nI:n.children){
					_toJson(nI,js);
				}
				js.endArray();
			}
			js.endObject();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}