package Main;

import Assemblation.Elaboration;
import Frame.Form;
import I_O.Read;

public class main {
	static public void main(String[] args) {
		//String FileName="Z:\\KAppembler-master\\KAppembler\\assembler\\instruction.txt";
		String FileName="C:\\Users\\Samuele Capani\\Desktop\\Assembler\\KAppembler\\assembler\\instruction.txt";
		Form obj=new Form();
		Read r=new Read();
		r.readfile(FileName);
		obj.visualizza(FileName);
	}
}