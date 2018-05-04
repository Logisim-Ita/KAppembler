package I_O;

import javax.swing.JOptionPane;

import Frame.Form;
import Main.main;

public class OutError {
	public void printError(String errMessage) {
		//JOptionPane.showMessageDialog(null, errMessage, "ErrorBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
		main.Serrors+="\n"+errMessage;
	}
}
