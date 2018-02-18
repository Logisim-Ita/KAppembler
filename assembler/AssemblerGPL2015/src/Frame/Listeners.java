package Frame;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Assemblation.Elaboration;

public class Listeners implements ActionListener{
	private TextArea input;
	private TextArea output;
	public Listeners(TextArea i,TextArea o) {
		this.input=i;
		this.output=o;
	}
	public void actionPerformed(ActionEvent evt) {
		Elaboration el=new Elaboration();
		el.setInstructions();
        output.setText(el.traduction(input.getText()));
	}
}
