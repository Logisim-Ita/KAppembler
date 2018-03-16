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
	private int cas;
	public ExportListener(TextArea o,int cas) {
		this.output=o;
		this.cas=cas;
	}
	public void actionPerformed(ActionEvent evt) {
		switch(cas) {
		case 0:saveFile("v2.0 raw\r\n"+output.getText(),".b18");
		case 1:saveFile(output.getText(),".asm");
		}
	}
	public void saveFile(String text,String est){
		// parent component of the dialog
		JFrame parentFrame= new JFrame("Savefile");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		    try {
				writeFile(fileToSave,text,est);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void writeFile(File dest,String text,String est) throws IOException{
		    FileWriter fileWriter = new FileWriter(dest+est);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    //printWriter.print("Some String");
		    printWriter.printf(text);
		    printWriter.close();
		
	}
}

