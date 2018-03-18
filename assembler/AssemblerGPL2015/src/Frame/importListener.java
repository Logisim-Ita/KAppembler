package Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import I_O.Read;

public class importListener implements ActionListener{
	private TextArea input;
	protected importListener(TextArea i) {
		this.input=i;
	}
	public void importFile(){
		// parent component of the dialog
		JFrame parentFrame= new JFrame("Import asm file");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Importing");   
		fileChooser.setFileFilter(new asmfilefilter());
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToImport = fileChooser.getSelectedFile();
			importFile(fileToImport);
		}
	}
	public void importFile(File from) {
		Read r=new Read();
		input.setText(r.readfilePass(from.getAbsolutePath()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		importFile();
	}
}
