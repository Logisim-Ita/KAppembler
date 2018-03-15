package Frame;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import I_O.Read;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
//import java.awt.Color;
import java.awt.TextArea;
import java.io.BufferedReader;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Form {
	
	BufferedReader br = null;
	JFrame f = new JFrame("KAppembler");
	Read r=new Read();
	TextArea input=new TextArea(10,60);
	TextArea output=new TextArea(10,60);
	TextArea instr=new TextArea(20,20);
	JButton submit=new JButton("submit");
	JButton export=new JButton("export");
	Listeners listener=new Listeners(input,output);
	ExportListener el=new ExportListener(output);
	BorderLayout b=new BorderLayout();
	GridBagLayout g=new GridBagLayout();
	public void visualizza(String FileName){
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(g);
		JPanel p1=new JPanel();
		JPanel IO=new JPanel();
		IO.setLayout(g);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		input.setText("\tLD A,B");
		output.setEditable(false);
		instr.setEditable(false);
		submit.addActionListener(listener);
		export.addActionListener(el);
		IO.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "I/O Panel"));
		IO.add(input,constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		IO.add(output,constraints);
		p1.add(IO);
		p1.add(submit);
		p1.add(export);
		p1.add(instr);
		instr.setText(r.readfilePass(FileName));
		f.add(p1);
		f.pack();
		f.setVisible(true);
		
	}
	public String GetInput() {
		String s="";
		s=input.getText();
		return s;
	}
	
}
