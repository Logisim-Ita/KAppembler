package Main;

import Assemblation.Elaboration;
import Frame.Form;
import I_O.Read;

public class main {
	static public void main(String[] args) {
		Form obj=new Form();
		Read r=new Read();
		r.readfile();
		obj.visualizza();
	}
}