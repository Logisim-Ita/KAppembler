package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import Main.main;

public class ExportListener implements ActionListener {
	private JTextArea output;
	private int cas;
	private Preferences prefs = Preferences.userNodeForPackage(Frame.ExportListener.class);
	//-------------------test-----------------
	private void setPathPref(String mod) {
	// Preference key name
	final String PREF_NAME = "exportdir";

	// Set the value of the preference
	String newValue = mod;
	prefs.put(PREF_NAME,newValue);
	
	// Get the value of the preference;
	// default value is returned if the preference does not exist
	String defaultValue = main.path;
	String propertyValue = prefs.get(PREF_NAME, defaultValue);
	}
	//---------------------------------------
	public ExportListener(JTextArea output2, int cas) {
		this.output = output2;
		this.cas = cas;
	}

	public void actionPerformed(ActionEvent evt) {
		switch (cas) {
		case 0:
			saveFile("v2.0 raw\r" + output.getText(), ".b18");
			break;
		case 1:
			saveFile(output.getText(), ".asm");
			break;
		default:
		}
	}

	public void saveFile(String text, String est) {
		// parent component of the dialog
		JFrame parentFrame = new JFrame("Savefile");
		JFileChooser fileChooser = new JFileChooser(prefs.get("exportdir", main.path));
		fileChooser.setDialogTitle("Saving");
		if (est.equals(".asm")) {
			fileChooser.setFileFilter(new asmfilefilter());
		} else if (est.equals(".b18")) {
			fileChooser.setFileFilter(new b18filefilter());
		}
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String path=fileToSave.getParent();
			
			//System.out.println("Save as file: " + path);
			setPathPref(path);
			try {
				writeFile(fileToSave, text, est);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void writeFile(File dest, String text, String est) throws IOException {
		FileWriter fileWriter = new FileWriter(dest + est);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.printf(text);
		printWriter.close();

	}
}
