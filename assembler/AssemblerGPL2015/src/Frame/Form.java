package Frame;

import javax.swing.JPanel;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import I_O.Read;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
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
	JButton save=new JButton("Save Assembly");
	Listeners listener=new Listeners(input,output);
	Label in=new Label("instructions:");
	ExportListener el=new ExportListener(output,0);
	ExportListener sl=new ExportListener(input,1);
	BorderLayout b=new BorderLayout();
	GridBagLayout g=new GridBagLayout();
	
	public void visualizza(String FileName){
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(g);
		f.setResizable(false);
		JPanel p1=new JPanel();
		JPanel IO=new JPanel();
		JPanel Buttons=new JPanel();
		JPanel Ins=new JPanel();
		IO.setLayout(g);
		Buttons.setLayout(g);
		Ins.setLayout(g);
		GridBagConstraints constraints = new GridBagConstraints();
		GridBagConstraints Bcon = new GridBagConstraints();
		GridBagConstraints Icon = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		Bcon.anchor = GridBagConstraints.NORTH;
		Bcon.insets = new Insets(10, 10, 10, 10);
		Bcon.gridx = 0;
		Bcon.gridy = 0;
		Icon.anchor = GridBagConstraints.NORTH;
		Icon.insets = new Insets(10, 10, 10, 10);
		Icon.gridx = 0;
		Icon.gridy = 0;
		input.setText("\tLD A,B");
		output.setEditable(false);
		instr.setEditable(false);
		submit.addActionListener(listener);
		export.addActionListener(el);
		save.addActionListener(sl);
		IO.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "I/O Panel"));
		IO.add(input,constraints);
		constraints.gridy = 10;
		IO.add(output,constraints);
		p1.add(IO);
		Buttons.add(submit,Bcon);
		Bcon.gridy = 1;
		Buttons.add(export,Bcon);
		Bcon.gridy = 2;
		Buttons.add(save,Bcon);
		p1.add(Buttons);
		Ins.add(in,Icon);
		Icon.gridy=1;
		Ins.add(instr,Icon);
		p1.add(Ins);
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
