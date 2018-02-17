package Assemblation;


public class instructions {
	public String MachineCode;
	public String SemiHumanCode;
	public String Key="";
	public String FirstFactor="";
	public String SecondFactor="";
	public String FirstModifier="";
	public String SecondModifier="";
	public Boolean FirstIsNum= false;
	public Boolean SecondIsNum= false;
	public int SecondNB;
	public int FirstNB;
	private String[] atemp;
	private String temp="";
	public String[] RegisterList;
	private int i;
	private int itemp;
	public instructions(String MC,String SHC,String[] RL) {
		MachineCode=MC;
		SemiHumanCode=SHC;
		RegisterList=RL;
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
		if(FirstModifier.contains("n")) {
			FirstNB=GetNumber(FirstModifier);
			FirstIsNum=true;
		}
		if(SecondModifier.contains("n")) {
			SecondNB=GetNumber(SecondModifier);
			SecondIsNum=true;
		}
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
	private int GetNumber(String mod) {
			mod=mod.replace("n","");
		return Integer.parseInt(mod);
	}
}
