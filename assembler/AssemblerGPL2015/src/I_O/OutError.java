package I_O;

import javax.swing.JOptionPane;

import Frame.Form;
import Main.main;

public class OutError {
	public void printError(String errMessage) {
		main.Serrors+=errMessage+"\n";
	}
}
