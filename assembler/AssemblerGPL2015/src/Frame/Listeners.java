package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import Assemblation.Elaboration;
import Main.main;

public class Listeners implements ActionListener {
	private JTextArea input;
	private JTextArea output;
	private JTextArea err;

	public Listeners(JTextArea input2, JTextArea output2,JTextArea e) {
		this.input = input2;
		this.output = output2;
		this.err=e;
	}

	public void actionPerformed(ActionEvent evt) {
		Elaboration el = new Elaboration();
		el.setInstructions();
		output.setText(el.traduction(input.getText()));
		err.setText(main.Serrors);
	}

}
