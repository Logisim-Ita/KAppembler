package Frame;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class ExportListener implements ActionListener{
	private TextArea output;
	public ExportListener(TextArea o) {
		this.output=o;
	}
	public void actionPerformed(ActionEvent evt) {
		saveFile("v2.0 raw\r\n"+output.getText());
	}
	public void saveFile(String text){
		// parent component of the dialog
		JFrame parentFrame= new JFrame("Savefile");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    try {
				writeFile(fileToSave,text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void writeFile(File dest,String text) throws IOException{
		    FileWriter fileWriter = new FileWriter(dest);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    //printWriter.print("Some String");
		    printWriter.printf(text);
		    printWriter.close();
		
	}
}

