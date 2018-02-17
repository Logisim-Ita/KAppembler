package Frame;

import javax.swing.JPanel;

import I_O.Read;

import java.awt.Component;
//import java.awt.Color;
import java.awt.TextArea;
import java.io.BufferedReader;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Form {
	BufferedReader br = null;
	JFrame f = new JFrame("Frame");
	Read r=new Read();
	TextArea input=new TextArea();
	TextArea output=new TextArea();
	TextArea instr=new TextArea();
	JButton submit=new JButton("submit");
	Listeners listener=new Listeners(input,output);
	
	public void visualizza(String FileName){
		f.setSize(700, 700);
		JPanel p1=new JPanel();
		input.setSize(500, 500);
		input.setText("HALT \r\nJP");
		output.setSize(400, 500);
		instr.setSize(400, 500);
		output.setEditable(false);
		instr.setEditable(false);
		submit.addActionListener(listener);
		p1.add(input);
		p1.add(output);
		p1.add(instr);
		p1.add(submit);
		instr.setText(r.readfilePass(FileName));
		f.add(p1);
		f.setVisible(true);
		
	}
	public String GetInput() {
		String s="";
		s=input.getText();
		return s;
	}
	
}
