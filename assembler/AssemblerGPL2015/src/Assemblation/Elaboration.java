package Assemblation;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import Frame.Form;
import I_O.OutError;
import I_O.Read;
import Main.main;

public class Elaboration {
	Form obj = new Form();
	Read r = new Read();
	OutError er = new OutError();
	instructions in;
	public ArrayList<instructions> inst = new ArrayList<instructions>();
	String[] RegList;
	String[] ModList;

	public void setInstructions() {
		String s = "";
		try {
			s = main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			er.printError("instruction file is missing");
			e.printStackTrace();
		}
		s = s.substring(0, s.lastIndexOf("/"));
		String FileName = s + File.separator +"instruction.txt";
		String instructionSet = r.readfilePass(FileName);
		String[] atemp = instructionSet.split("___");
		RegList = r.linedivision(atemp[0]);
		ModList = r.linedivision(atemp[1]);
		String[] linesSet = r.linedivision(atemp[2]);
		for (int i = 1; i < linesSet.length; i++) {
			inst.add(new instructions(linesSet[i].substring(0, linesSet[i].indexOf(" ")),
					linesSet[i].substring(linesSet[i].indexOf(" ") + 1), RegList, ModList));
		}
	}

	/**/
	public String traduction(String input) {
		er.printError("_______________________________");
		ArrayList<codeline> code = new ArrayList<codeline>();
		String temp = "";
		String trad = "";
		int tempFactorPos = 0;
		int WordsCounter = 0;
		int tempNB = 0;
		String[] atemp = input.split("\n");
		for (int i = 0; i < atemp.length; i++) {
			code.add(new codeline(atemp[i], RegList, ModList));
		}
		for (int i = 0; i < code.size(); i++) {
			temp = trad;
			code.get(i).Position = WordsCounter;
			if(code.get(i).isORG) WordsCounter+=code.get(i).ORG;
			/* RAM portion occupied */
			else {
			for (int c = 0; c < inst.size(); c++) {
				if (code.get(i).Key.equals(inst.get(c).Key)
						&& code.get(i).ContainedMod.equals(inst.get(c).ContainedMod)) {
					if (code.get(i).FirstIsNum) {
						if (code.get(i).FirstNB <= inst.get(c).FirstNB) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									WordsCounter += 1 + code.get(i).FirstNB + code.get(i).SecondNB;
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN)+DeHex(1,code.get(l).Position);
											WordsCounter += 2 + code.get(i).FirstNB;
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											WordsCounter += 1 + code.get(i).GetNumberOfBytes(code.get(l).Cost.getn())
													+ code.get(i).FirstNB;
										}
									}
									// if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
									// number","Attention please");
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).FirstNB,code.get(i).FirstN);

									WordsCounter += 1 + code.get(i).FirstNB;
								}
							}
						}
					} else {
						if (inst.get(c).FirstIsNum) {
							Boolean isLabel = false;
							for (int l = 0; l < code.size(); l++) {
								if (code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel = true;
									tempFactorPos = code.get(l).Position;
									tempNB = 1;
								} else if (code.get(i).FirstFactor.equals(code.get(l).Cost.gets()) && code.get(i)
										.GetNumberOfBytes(code.get(l).Cost.getn()) <= inst.get(c).FirstNB) {
									isLabel = true;
									tempFactorPos = code.get(l).Cost.getn();
									tempNB = code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
								}
							}
							if (isLabel) {
								if (code.get(i).SecondIsNum) {
									if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
										// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos)+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
										WordsCounter += 1 + tempNB + code.get(i).SecondNB;
									}
								} else {
									if (inst.get(c).SecondIsNum) {
										// Boolean isLabell=false;
										for (int l = 0; l < code.size(); l++) {
											if (code.get(i).SecondFactor.equals(code.get(l).label)) {
												// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos)+DeHex(1,code.get(l).Position);

												WordsCounter += 2 + tempNB;
												// isLabell=true;
											} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
													&& code.get(i).GetNumberOfBytes(
															code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
												WordsCounter += 1 + tempNB
														+ code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
											}
										}
										// if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
										// number","Attention please");
									} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
										// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,tempFactorPos);

										WordsCounter += 1 + tempNB;
									}
								}
							}
						} else if (code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									// trad+=inst.get(c).MachineCode+"\n"+DeHex(code.get(i).SecondNB,code.get(i).SecondN);
									WordsCounter += 1 + code.get(i).SecondNB;
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											// trad+=inst.get(c).MachineCode+"\n"+DeHex(1,code.get(l).Position);
											WordsCounter += 2;
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											WordsCounter += 1 + code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
										}
									}
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									// trad+=inst.get(c).MachineCode+"\n";
									WordsCounter++;
								}
							}
						}
					}
				}
			}
			}
		}
		for (int i = 0; i < code.size(); i++) {
			temp = trad;
			if(code.get(i).isORG )
			for(int k=0;k<(code.get(i).ORG-code.get(i).Position-1);k++) {
				trad+="00\n";
			}else {
			for (int c = 0; c < inst.size(); c++) {
				if (code.get(i).Key.equals(inst.get(c).Key)
						&& code.get(i).ContainedMod.equals(inst.get(c).ContainedMod)) {
					if (code.get(i).FirstIsNum) {
						if (code.get(i).FirstNB <= inst.get(c).FirstNB) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
											+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
													+ DeHex(1, code.get(l).Position);
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(i).FirstNB, code.get(i).FirstN)
													+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
															code.get(l).Cost.getn());
										}
									}
									// if(!isLabel)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
									// number","Attention please");
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).FirstNB, code.get(i).FirstN);
								}
							}
						}
					} else {
						if (inst.get(c).FirstIsNum) {
							Boolean isLabel = false;
							for (int l = 0; l < code.size(); l++) {
								if (code.get(i).FirstFactor.equals(code.get(l).label)) {
									isLabel = true;
									tempFactorPos = code.get(l).Position;
									tempNB = 1;
								} else if (code.get(i).FirstFactor.equals(code.get(l).Cost.gets()) && code.get(i)
										.GetNumberOfBytes(code.get(l).Cost.getn()) <= inst.get(c).FirstNB) {
									isLabel = true;
									tempFactorPos = code.get(l).Cost.getn();
									tempNB = code.get(l).GetNumberOfBytes(code.get(l).Cost.getn());
								}
							}
							if (isLabel) {
								if (code.get(i).SecondIsNum) {
									if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
										trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
												+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
									}
								} else {
									if (inst.get(c).SecondIsNum) {
										// Boolean isLabell=false;
										for (int l = 0; l < code.size(); l++) {
											if (code.get(i).SecondFactor.equals(code.get(l).label)) {
												trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
														+ DeHex(1, code.get(l).Position);
												// isLabell=true;
											} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
													&& code.get(i).GetNumberOfBytes(
															code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
												trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos)
														+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
																code.get(l).Cost.getn());
											}
										}
										// if(!isLabell)infoBox("You digited "+code.get(i).SecondFactor+" instead of a
										// number","Attention please");
									} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
										trad += inst.get(c).MachineCode + "\n" + DeHex(tempNB, tempFactorPos);
									}
								}
							}
						} else if (code.get(i).FirstFactor.equals(inst.get(c).FirstFactor)) {
							if (code.get(i).SecondIsNum) {
								if (code.get(i).SecondNB <= inst.get(c).SecondNB) {
									trad += inst.get(c).MachineCode + "\n"
											+ DeHex(code.get(i).SecondNB, code.get(i).SecondN);
								}
							} else {
								if (inst.get(c).SecondIsNum) {
									// Boolean isLabel=false;
									for (int l = 0; l < code.size(); l++) {
										if (code.get(i).SecondFactor.equals(code.get(l).label)) {
											trad += inst.get(c).MachineCode + "\n" + DeHex(1, code.get(l).Position);
											// isLabel=true;
										} else if (code.get(i).SecondFactor.equals(code.get(l).Cost.gets())
												&& code.get(i).GetNumberOfBytes(
														code.get(l).Cost.getn()) <= inst.get(c).SecondNB) {
											trad += inst.get(c).MachineCode + "\n"
													+ DeHex(code.get(l).GetNumberOfBytes(code.get(l).Cost.getn()),
															code.get(l).Cost.getn());
										}
									}
								} else if (code.get(i).SecondFactor.equals(inst.get(c).SecondFactor)) {
									trad += inst.get(c).MachineCode + "\n";
								}
							}
						}
					}
				}
			}
			if (temp.equals(trad) && !code.get(i).Key.equals("")) {
				er.printError("No instuction found for line " + (i + 1) + " please check your code");
			}
		}
		}
		er.printError("compilation finished");
		return trad;
	}

	private String DeHex(int NB, int num) {
		String ByS = Integer.toHexString(num);
		String res = "";
		while (ByS.length() / 2 <= NB) {
			ByS = "0" + ByS;
		}
		for (int i = 0; i < NB; i++) {
			res += ByS.substring(ByS.length() - 2) + "\n";
			;
			ByS = ByS.substring(0, ByS.length() - 2);
		}
		res = res.toUpperCase();
		return res;
	}

}
