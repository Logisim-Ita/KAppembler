package Assemblation;

public class codeline {
	public String SemiHumanCode;
	public String Key="";
	public String FirstFactor="";
	public String SecondFactor="";
	public String FirstModifier="";
	public String SecondModifier="";
	public String label="";
	public boolean FirstIsNum;
	public boolean SecondIsNum;
	public int SecondNB;
	public int FirstNB;
	public int SecondN;
	public int FirstN;
	public int Position;
	private String[] atemp;
	private String temp="";
	public String[] RegisterList;
	private int i;
	private int itemp;
	public codeline(String SHC,String[] RL) {
		SemiHumanCode=SHC;
		RegisterList=RL;
		if(SemiHumanCode.contains(";"))
			SemiHumanCode=SemiHumanCode.substring(0,SemiHumanCode.indexOf(";"));
		if(!(SemiHumanCode.startsWith(" ")||SemiHumanCode.startsWith("\t"))) {
			label=SemiHumanCode.substring(0,SemiHumanCode.indexOf(":"));
			SemiHumanCode=SemiHumanCode.substring(SemiHumanCode.indexOf(":")+1);
		}
		while(SemiHumanCode.startsWith(" ") || SemiHumanCode.startsWith("\t")) {
			SemiHumanCode=SemiHumanCode.substring(1);	
		}
		while(SemiHumanCode.endsWith(" ")) {
			SemiHumanCode=SemiHumanCode.substring(0,SemiHumanCode.length()-1);
		}
		if(SemiHumanCode.contains(" ")){
				atemp=SemiHumanCode.split(" ");
				Key=atemp[0];
				temp=atemp[1];
		}else {
			Key=SemiHumanCode;
		}
		if(temp!="" && temp.contains(",")) {
			atemp=temp.split(",");
			FirstFactor=atemp[0];
			SecondFactor=atemp[1];
		}else {
			FirstFactor=temp;
		}
		FirstModifier=GetModifier(FirstFactor,RegisterList);
		SecondModifier=GetModifier(SecondFactor,RegisterList);
		FirstIsNum=IsNum(FirstModifier);
		SecondIsNum=IsNum(SecondModifier);
		if(FirstIsNum) {
			FirstN=Integer.parseInt(FirstModifier);
			FirstNB=GetNumberOfBytes(FirstN);
		}
		if(SecondIsNum) {
			SecondN=Integer.parseInt(SecondModifier);
			SecondNB=GetNumberOfBytes(SecondN);
		}
		//System.out.println(Key+" "+FirstIsNum+" "+SecondIsNum+" "+FirstN+" "+FirstNB+" "+SecondN);
	}
	private String GetModifier(String Factor,String[] Reg) {
		itemp=0;
		for(i=0;i<Reg.length;i++) {
			if(Factor==Reg[i]) {
				itemp++;
			}
		}
		if(itemp==0) {
			for(i=0;i<Reg.length;i++) {
				if(Factor.contains(Reg[i])){
					return Factor.replace(Reg[i],"");
				}
			}
			return Factor;
		}
		return "";
	}
	private Boolean IsNaN(int val) {
		return val!=val;
	}
	private int GetNumberOfBytes(int Num) {
		int Bytes;
		for(Bytes=1;Num>(Math.pow(2, 8*Bytes)-1);Bytes++);
		return Bytes;
	}
	private Boolean IsNum(String Mod) {
		try {
			if(Mod!="")return !IsNaN(Integer.parseInt(Mod));
			else return false;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}
