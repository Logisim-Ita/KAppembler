package I_O;

import javax.swing.JOptionPane;

public class OutError {
	public void printError(String errMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, errMessage, "ErrorBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
	}
}

