package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import I_O.Read;
import Main.main;

public class importListener implements ActionListener {
	private JTextArea input;
	private Preferences prefs = Preferences.userNodeForPackage(Frame.ExportListener.class);;
	protected importListener(JTextArea input2) {
		this.input = input2;
	}
	private void setPathPref(String mod) {
		// Preference key name
		final String PREF_NAME = "importdir";

		// Set the value of the preference
		String newValue = mod;
		prefs.put(PREF_NAME,newValue);
		
		// Get the value of the preference;
		// default value is returned if the preference does not exist
		String defaultValue = main.path;
		String propertyValue = prefs.get(PREF_NAME, defaultValue);
		}
	public void importFile() {
		// parent component of the dialog
		JFrame parentFrame = new JFrame("Import asm file");
		JFileChooser fileChooser = new JFileChooser(prefs.get("importdir", main.path));
		fileChooser.setDialogTitle("Importing");
		fileChooser.setFileFilter(new asmfilefilter());
		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToImport = fileChooser.getSelectedFile();
			String path=fileToImport.getParent();
			setPathPref(path);
			importFile(fileToImport);
		}
	}

	public void importFile(File from) {
		Read r = new Read();
		input.setText(r.readfilePass(from.getAbsolutePath()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		importFile();
	}
}
