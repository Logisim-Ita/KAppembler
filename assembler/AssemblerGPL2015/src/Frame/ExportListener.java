package Frame;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class ExportListener implements ActionListener{
	private TextArea output;
	public ExportListener(TextArea o) {
		this.output=o;
	}
	public void actionPerformed(ActionEvent evt) {
		saveFile();
	}
	public void saveFile(){
		// parent component of the dialog
		JFrame parentFrame= new JFrame("Savefile");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}
}

