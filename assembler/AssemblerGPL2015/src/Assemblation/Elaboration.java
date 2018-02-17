package Assemblation;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Frame.Form;
import I_O.Read;
public class Elaboration {
	Form obj=new Form();
	Read r=new Read();
	instructions in;
	public ArrayList<instructions> inst= new ArrayList<instructions>();
	String[] RegList;
	public void setInstructions() {
		//String instructionSet=r.readfilePass("Z:\\KAppembler-master\\KAppembler\\assembler\\instruction.txt");
		String instructionSet=r.readfilePass("C:\\Users\\Samuele Capani\\Desktop\\Assembler\\KAppembler\\assembler\\instruction.txt");
		String[] atemp=instructionSet.split("___");
		RegList= r.linedivision(atemp[0]);
		String[] linesSet=r.linedivision(atemp[1]);
		for(int i=1;i<linesSet.length;i++) {
			atemp=linesSet[i].split("§");
			inst.add(new instructions(atemp[0],atemp[1],RegList));
		}
	}
	public String traduction(String input) {
		ArrayList<codeline> code= new ArrayList<codeline>();
		String temp="";
		String trad="";
		String[] atemp=input.split("\r\n");
		for(int i=0;i<atemp.length;i++) {
			code.add(new codeline(atemp[i],RegList));
		}
		for(int i=0;i<code.size();i++){
			temp=trad;
			for(int c=0;c<inst.size();c++) {
				if(code.get(i).Key.equals(inst.get(c).Key)) {
					if(code.get(i).FirstIsNum) {
						if(code.get(i).FirstNB<=inst.get(c).FirstNB) {
							if(code.get(i).SecondIsNum) {
								if(code.get(i).SecondNB<=inst.get(c).SecondNB){
									trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
								}
							}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
								if(inst.get(c).SecondIsNum){
									infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
								}else
									trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN);
							}
						}
					}else if(code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)){
						if(inst.get(c).FirstIsNum){
							infoBox("You digited "+code.get(i).FirstFactor+" instead of a number","Attention please");
						}else if(code.get(i).SecondIsNum) {
							if(code.get(i).SecondNB<=inst.get(c).SecondNB){
								trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
							}
						}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
							if(inst.get(c).SecondIsNum){
								infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
							}else 
								trad+=inst.get(c).MachineCode+"\r\n";
						}
					}
					//trad+=inst.get(c).MachineCode+"\r\n";
				}
			}
			if(temp.equals(trad)) {
				infoBox("No instuction found for line "+(i+1)+" please check your code","Attention please");
			}
		}
		return trad;
	}
	private String DeByte(int NB,int num){
		String ByS=Integer.toBinaryString(num);
		String res="";
		for(int i=0;i<NB;i++) {
			if(ByS.length()-8>=0) {
				res+=ByS.substring(ByS.length()-8)+"\r\n";
				ByS=ByS.substring(0,ByS.length()-8);
			}
			else {
				for(int c=0;c<(8-ByS.length());c++) {
					res+=0;
				}
				res+=ByS.substring(0)+"\r\n";
			}
		}
		return res;
	}
	private String DeHex(int NB,int num){
		String ByS=Integer.toHexString(num);
		String res="";
		for(int i=0;i<NB;i++) {
			if(ByS.length()-2>=0) {
				res+=ByS.substring(ByS.length()-2)+"\r\n";
				ByS=ByS.substring(0,ByS.length()-2);
			}
			else {
				for(int c=0;c<(2-ByS.length());c++) {
					res+=0;
				}
				res+=ByS.substring(0)+"\r\n";
			}
		}
		return res;
	}
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
	