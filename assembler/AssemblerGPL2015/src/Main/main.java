package Main;

import java.awt.Color;
import java.net.URISyntaxException;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import Frame.Form;
import I_O.Read;

public class main {
	public static String Serrors="";
	public static String path;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIDefaults uid = UIManager.getDefaults();
			uid.put("ScrollBar.highlight", new ColorUIResource(Color.ORANGE));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 path = "";
		try {
			path = main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		path = path.substring(0, path.lastIndexOf("/"));
		String FileName = path + "/instruction.txt";
		System.out.println(FileName);
		Form obj = new Form();
		Read r = new Read();
		r.readfile(FileName);
		obj.visualizza(FileName);
	}
}

