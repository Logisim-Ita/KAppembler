package Assemblation;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Frame.Form;
import I_O.Read;
import Main.main;
public class Elaboration {
	Form obj=new Form();
	Read r=new Read();
	instructions in;
	public ArrayList<instructions> inst= new ArrayList<instructions>();
	String[] RegList;
	String[] ModList;
	
	public void setInstructions() {
		String s="";
		try {
			 s = main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s=s.substring(0,s.lastIndexOf("/"));
		String FileName=s+"\\instruction.txt";
		String instructionSet=r.readfilePass(FileName);
		String[] atemp=instructionSet.split("___");
		RegList= r.linedivision(atemp[0]);
		ModList= r.linedivision(atemp[1]);
		String[] linesSet=r.linedivision(atemp[2]);
		for(int i=1;i<linesSet.length;i++) {
			//atemp=linesSet[i].split("§");
			inst.add(new instructions(linesSet[i].substring(0, linesSet[i].indexOf(" ")),linesSet[i].substring(linesSet[i].indexOf(" ")+1),RegList,ModList));
		}
	}
	public String traduction(String input) {
		ArrayList<codeline> code= new ArrayList<codeline>();
		String temp="";
		String trad="";
		int tempFactorPos = 0;
		int WordsCounter=0;
		String[] atemp=input.split("\r\n");
		for(int i=0;i<atemp.length;i++) {
			code.add(new codeline(atemp[i],RegList,ModList));
		}
		for(int i=0;i<code.size();i++){
			temp=trad;
			code.get(i).Position=WordsCounter;
			for(int c=0;c<inst.size();c++) {
				if(code.get(i).Key.equals(inst.get(c).Key)) {
					if(code.get(i).FirstIsNum) {
						if(code.get(i).FirstNB<=inst.get(c).FirstNB) {
							if(code.get(i).SecondIsNum) {
								if(code.get(i).SecondNB<=inst.get(c).SecondNB){
									//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									
									WordsCounter+=1+code.get(i).FirstNB+code.get(i).SecondNB;
								}
							}else {
								if(inst.get(c).SecondIsNum){
									//Boolean isLabel=false;
									for(int l=0;l<code.size();l++){
										if(code.get(i).SecondFactor.equals(code.get(l).label)) {
											//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(1,code.get(l).Position);
											
											WordsCounter+=2+code.get(i).FirstNB;
											//isLabel=true;
										}
									}
									//if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
								}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
									//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN);
									
									WordsCounter+=1+code.get(i).FirstNB;
								}
							}
						}
					}else {
						if(inst.get(c).FirstIsNum){
							Boolean isLabel=false;
							for(int l=0;l<code.size();l++){
								if(code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel=true;
									tempFactorPos=code.get(l).Position;
								}
							}
							if(isLabel) {
								if(code.get(i).SecondIsNum) {
									if(code.get(i).SecondNB<=inst.get(c).SecondNB){
										//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
										
										WordsCounter+=2+code.get(i).SecondNB;
									}
								}else {
									if(inst.get(c).SecondIsNum){
										//Boolean isLabell=false;
										for(int l=0;l<code.size();l++){
											if(code.get(i).SecondFactor.equals(code.get(l).label)) {
												//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos)+DeHex(1,code.get(l).Position);
												
												WordsCounter+=3;
												//isLabell=true;
											}
										}
										//if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
									}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
										//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos);
										
										WordsCounter+=2;
									}
								}
							}
						}else if(code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if(code.get(i).SecondIsNum) {
								if(code.get(i).SecondNB<=inst.get(c).SecondNB){
									//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									
									WordsCounter+=1+code.get(i).SecondNB;
								}
							}else {
								if(inst.get(c).SecondIsNum){
									Boolean isLabel=false;
									for(int l=0;l<code.size();l++){
										if(code.get(i).SecondFactor.equals(code.get(l).label)) {
											//trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,code.get(l).Position);
											
											WordsCounter+=2;
											isLabel=true;
										}
									}
								}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
									//trad+=inst.get(c).MachineCode+"\r\n";
									WordsCounter++;
								}
							}
						}
					}
				}
			}
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
							}else {
								if(inst.get(c).SecondIsNum){
									//Boolean isLabel=false;
									for(int l=0;l<code.size();l++){
										if(code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(1,code.get(l).Position);
											//isLabel=true;
										}
									}
									//if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
								}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
									trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN);
								}
							}
						}
					}else {
						if(inst.get(c).FirstIsNum){
							Boolean isLabel=false;
							for(int l=0;l<code.size();l++){
								if(code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel=true;
									tempFactorPos=code.get(l).Position;
								}
							}
							if(isLabel) {
								if(code.get(i).SecondIsNum) {
									if(code.get(i).SecondNB<=inst.get(c).SecondNB){
										trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									}
								}else {
									if(inst.get(c).SecondIsNum){
										//Boolean isLabell=false;
										for(int l=0;l<code.size();l++){
											if(code.get(i).SecondFactor.equals(code.get(l).label)) {
												trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos)+DeHex(1,code.get(l).Position);
												//isLabell=true;
											}
										}
										//if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a number","Attention please");
									}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
										trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,tempFactorPos);
									}
								}
							}
						}else if(code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if(code.get(i).SecondIsNum) {
								if(code.get(i).SecondNB<=inst.get(c).SecondNB){
									trad+=inst.get(c).MachineCode+"\r\n"+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
								}
							}else {
								if(inst.get(c).SecondIsNum){
									Boolean isLabel=false;
									for(int l=0;l<code.size();l++){
										if(code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad+=inst.get(c).MachineCode+"\r\n"+DeHex(1,code.get(l).Position);
											isLabel=true;
										}
									}
								}else if(code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)){
									trad+=inst.get(c).MachineCode+"\r\n";
								}
							}
						}
					}
				}
			}
			if(temp.equals(trad) && !code.get(i).Key.equals("")) {
				infoBox("No instuction found for line "+(i+1)+" please check your code","Attention please");
			}
		}
		return trad;
	}
	private String DeByte(int NB,int num){
		//TODO
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
		//TODO
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
	