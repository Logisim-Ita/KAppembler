package Assemblation;

import java.util.ArrayList;

import I_O.OutError;

public class codeline {
	public String SemiHumanCode;
	public String Key = "";
	public String FirstFactor = "";
	public String SecondFactor = "";
	public String FirstModifier = "";
	public String SecondModifier = "";
	public String label = "";
	public boolean FirstIsNum;
	public boolean SecondIsNum;
	public boolean HexError;
	public int SecondNB;
	public int FirstNB;
	public int SecondN;
	public int FirstN;
	public int Position;
	private String[] atemp;
	private String temp = "";
	public String[] RegisterList;
	public String[] ModifierList;
	public ArrayList<String> ContainedMod;
	public Costant Cost=new Costant(0,"01923824");
	private int i;
	private int itemp;
	private OutError er=new OutError();
	public codeline(String SHC, String[] RL,String[] ML) {
		SemiHumanCode = SHC;
		RegisterList = RL;
		ModifierList=ML;
		ContainedMod=new ArrayList<String>();
		for(i=0;i<ML.length;i++) {
			if(SemiHumanCode.contains(ML[i])) 
				ContainedMod.add(ML[i]);
		}
		
		if (SemiHumanCode.contains(";"))
			SemiHumanCode = SemiHumanCode.substring(0, SemiHumanCode.indexOf(";"));
		if(SemiHumanCode.contains("Cost")) {
			SemiHumanCode = SemiHumanCode.substring(SemiHumanCode.indexOf("Cost")+5);
			atemp=SemiHumanCode.split("=");
			SemiHumanCode="";
			if(IsNum(atemp[1],ModifierList)) {
				if(atemp[1].startsWith("0x")) {
					atemp[1]=atemp[1].replace("0x","");
					itemp=HexToDecimal(atemp[1]);
				}else
					itemp = Integer.parseInt(atemp[1]);
				Cost=new Costant(itemp,atemp[0]);
			}
		}
		if (!SemiHumanCode.equals("")) {
			
			if (!(SemiHumanCode.startsWith(" ") || SemiHumanCode.startsWith("\t"))) {
				if(SemiHumanCode.contains(":")){
					label = SemiHumanCode.substring(0, SemiHumanCode.indexOf(":"));
					SemiHumanCode = SemiHumanCode.substring(SemiHumanCode.indexOf(":") + 1);
				}else {
					er.printError("unvalid label in label position", "Label error");
				}
			}
			SemiHumanCode=StringCleaning(SemiHumanCode);
			if (SemiHumanCode.contains(" ")) {
				Key = SemiHumanCode.substring(0,SemiHumanCode.indexOf(" "));
				temp = SemiHumanCode.substring(SemiHumanCode.indexOf(" "));
			} else {
				Key = SemiHumanCode;
			}
			if (temp != "" && temp.contains(",")) {
				atemp = temp.split(",");
				FirstFactor = atemp[0];
				SecondFactor = atemp[1];
			} else {
				FirstFactor = temp;
			}
			FirstFactor=StringCleaning(FirstFactor);
			SecondFactor=StringCleaning(SecondFactor);
			FirstModifier = GetModifier(FirstFactor, RegisterList);
			SecondModifier = GetModifier(SecondFactor, RegisterList);
			FirstIsNum = IsNum(FirstModifier,ModifierList);
			SecondIsNum = IsNum(SecondModifier,ModifierList);
			if (FirstIsNum) {
				for(int i=0;i<ML.length;i++){
					if(FirstModifier.contains(ML[i])){
						FirstModifier=FirstModifier.replace(ML[i],"");
					}
				}
				if(FirstModifier.startsWith("0x")) {
					FirstModifier=FirstModifier.replace("0x","");
					FirstN=HexToDecimal(FirstModifier);
				}else
					FirstN = Integer.parseInt(FirstModifier);
				FirstNB = GetNumberOfBytes(FirstN);
			}
			if (SecondIsNum) {
				for(int i=0;i<ML.length;i++){
					if(SecondModifier.contains(ML[i])){
						SecondModifier=SecondModifier.replace(ML[i],"");
					}
				}
				if(SecondModifier.startsWith("0x")) {
					SecondModifier=SecondModifier.replace("0x","");
					SecondN=HexToDecimal(SecondModifier);
				}else
					SecondN = Integer.parseInt(SecondModifier);
				SecondNB = GetNumberOfBytes(SecondN);
			}
			
		}
		if(HexError) {
			er.printError("unvalid Hex number insered, please check your code", "Hex error");
		}
		// System.out.println(Key+" "+FirstIsNum+" "+SecondIsNum+" "+FirstN+"
		// "+FirstNB+" "+SecondN);
	}

	private String GetModifier(String Factor, String[] Reg) {
		if(Factor.contains("0x"))return Factor;
		itemp = 0;
		for (i = 0; i < Reg.length; i++) {
			if (Factor == Reg[i]) {
				itemp++;
			}
		}
		if (itemp == 0) {
			for (i = 0; i < Reg.length; i++) {
				if (Factor.contains(Reg[i])) {
					return Factor.replace(Reg[i], "");
				}
			}
			return Factor;
		}
		return "";
	}

	private Boolean IsNaN(int val) {
		return val != val;
	}

	public int GetNumberOfBytes(int Num) {
		int Bytes;
		for (Bytes = 1; Num > (Math.pow(2, 8 * Bytes) - 1); Bytes++)
			;
		return Bytes;
	}
	private int HexToDecimal(String s) {
		int n=0;
		int e=0;
		while(s.length()!=0) {
			n+=Dec(s.substring(s.length()-1))*Math.pow(16, e);
			e++;
			s=s.substring(0,s.length()-1);
		}
		return n;
	}
	private int Dec(String s) {
		s=s.toUpperCase();
		switch(s) {
			case "A":{return 10;}
			case "B":{return 11;}
			case "C":{return 12;}
			case "D":{return 13;}
			case "E":{return 14;}
			case "F":{return 15;}
			default: 
			try {
				if (s != "")
					return (Integer.parseInt(s));
			} catch (NumberFormatException e) {
				HexError=true;
			}
		}
		return 0;
	}
	private Boolean IsNum(String Mod,String[] ML) {
		for(int i=0;i<ML.length;i++){
			if(Mod.contains(ML[i])){
				Mod=Mod.replace(ML[i],"");
			}
		}
		if(Mod.startsWith("0x")) {
			Mod=Mod.replace("0x","");
			Mod=Integer.toString(HexToDecimal(Mod));
		}
		
		try {
			if (Mod != "")
				return !IsNaN(Integer.parseInt(Mod));
			else
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private String StringCleaning(String toClean) {
		while (toClean.startsWith(" ") || toClean.startsWith("\t")) {
			toClean = toClean.substring(1);
		}
		while (toClean.endsWith(" ") || toClean.endsWith("\t")) {
			toClean = toClean.substring(0, toClean.length() - 1);
		}
		return toClean;
	}
}
