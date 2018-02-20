package Main;



import java.net.URISyntaxException;
import Frame.Form;
import I_O.Read;

public class main {
	static public void main(String[] args) {
		String s="";
		try {
			 s = main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		s=s.substring(0,s.lastIndexOf("/"));
		String FileName=s+"\\instruction.txt";
		Form obj=new Form();
		Read r=new Read();
		r.readfile(FileName);
		obj.visualizza(FileName);
	}
}