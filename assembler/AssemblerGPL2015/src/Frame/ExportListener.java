package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ExportListener implements ActionListener {
	private JTextArea output;
	private int cas;

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
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Saving");
		if (est.equals(".asm")) {
			fileChooser.setFileFilter(new asmfilefilter());
		} else if (est.equals(".b18")) {
			fileChooser.setFileFilter(new b18filefilter());
		}
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
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
