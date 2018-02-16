package Assemblation;

public class codeline {
	public String SemiHumanCode;
	public String Key="";
	public String FirstFactor="";
	public String SecondFactor="";
	public String FirstModifier="";
	public String SecondModifier="";
	public int SecondNB;
	public int FirstNB;
	private String[] atemp;
	private String temp="";
	public String[] RegisterList;
	private int i;
	private int itemp;
	public codeline(String SHC,String[] RL) {
		SemiHumanCode=SHC;
		RegisterList=RL;
		if(SemiHumanCode.contains(" ")){
			if(!SemiHumanCode.endsWith(" ")) {
				atemp=SemiHumanCode.split(" ");
				Key=atemp[0];
				temp=atemp[1];
			}else {
				atemp=SemiHumanCode.split(" ");
				Key=atemp[0];
			}
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
		if(FirstModifier.contains("n"))FirstNB=GetNumber(FirstModifier);
		if(SecondModifier.contains("n"))SecondNB=GetNumber(SecondModifier);
		System.out.println(Key+" "+FirstModifier+" "+SecondModifier+" "+FirstNB+" "+SecondNB);
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
