package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import Assemblation.Elaboration;

public class Listeners implements ActionListener {
	private JTextArea input;
	private JTextArea output;

	public Listeners(JTextArea input2, JTextArea output2) {
		this.input = input2;
		this.output = output2;
	}

	public void actionPerformed(ActionEvent evt) {
		Elaboration el = new Elaboration();
		el.setInstructions();
		output.setText(el.traduction(input.getText()));
	}

}
