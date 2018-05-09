package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.io.BufferedReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import I_O.Read;
import Main.main;

public class Form {

	BufferedReader br = null;
	JFrame f = new JFrame("KAppembler");
	Read r = new Read();
	JTextArea input = new JTextArea(10, 60);
	JTextArea output = new JTextArea(10, 60);
	JTextArea instr = new JTextArea(20, 20);
	public JTextArea errors = new JTextArea(6, 106);
	JScrollPane instrScroll = new JScrollPane(instr);
	JScrollPane inputScroll = new JScrollPane(input);
	JScrollPane outputScroll = new JScrollPane(output);
	JScrollPane errorScroll = new JScrollPane(errors);
	JButton submit = new JButton("Assembla");
	JButton export = new JButton("Esporta per ROM Logisim");
	JButton save = new JButton("Salva Assembly");
	JButton imp = new JButton("Importa Assembly");
	Listeners listener = new Listeners(input, output,errors);
	ExportListener el = new ExportListener(output, 0);
	ExportListener sl = new ExportListener(input, 1);
	importListener il = new importListener(input);
	BorderLayout b = new BorderLayout();
	GridBagLayout g = new GridBagLayout();
	private final Color CBackground = new Color(40, 40, 40);
	private final Color CText = new Color(255, 127, 56);
	public void visualizza(String FileName) {
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(g);
		f.setResizable(false);
		output.setFocusable(false);
		JPanel p1 = new JPanel();
		JPanel IO = new JPanel();
		JPanel Buttons = new JPanel();
		JPanel Ins = new JPanel();
		JPanel Err = new JPanel();
		f.getContentPane().setBackground(CBackground);
		Err.setBackground(CBackground);
		p1.setBackground(CBackground);
		IO.setBackground(CBackground);
		Buttons.setBackground(CBackground);
		Ins.setBackground(CBackground);
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
		inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		errorScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		input.setText("\tLD A,B");
		input.setBackground(CBackground.darker());
		output.setBackground(CBackground.darker());
		instr.setBackground(CBackground.darker());
		errors.setBackground(CBackground.darker());
		input.setForeground(CText);
		input.setCaretColor(CText);
		output.setForeground(CText);
		instr.setForeground(CText);
		errors.setForeground(Color.RED);
		errors.setEditable(false);
		output.setEditable(false);
		instr.setEditable(false);
		submit.addActionListener(listener);
		export.addActionListener(el);
		save.addActionListener(sl);
		imp.addActionListener(il);
		Border bIO = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "I/O Panel", 2, 0,
				new Font(null), CText);
		Border bER = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ERRORS", 2, 0,
				new Font(null), CText);
		Border bIN = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "INSTRUCTIONS", 2, 0,
				new Font(null), CText);
		IO.setBorder(bIO);
		Ins.setBorder(bIN);
		Err.setBorder(bER);
		IO.add(inputScroll, constraints);
		constraints.gridy = 10;
		IO.add(outputScroll, constraints);
		p1.add(IO);
		submit.setForeground(CText);
		submit.setBackground(CBackground.darker());
		submit.setContentAreaFilled(false);
		submit.setOpaque(true);
		export.setForeground(CText);
		export.setBackground(CBackground.darker());
		export.setContentAreaFilled(false);
		export.setOpaque(true);
		save.setForeground(CText);
		save.setBackground(CBackground.darker());
		save.setContentAreaFilled(false);
		save.setOpaque(true);
		imp.setForeground(CText);
		imp.setBackground(CBackground.darker());
		imp.setContentAreaFilled(false);
		imp.setOpaque(true);
		submit.setSize(new Dimension(2000,2));
		Buttons.add(submit, Bcon);
		Bcon.gridy = 1;
		Buttons.add(export, Bcon);
		Bcon.gridy = 2;
		Buttons.add(save, Bcon);
		Bcon.gridy = 3;
		Buttons.add(imp, Bcon);
		p1.add(Buttons);
		Icon.gridy = 1;
		Ins.add(instrScroll,Icon);
		p1.add(Ins);
		instr.setText(r.readfilePass(FileName)
				.substring(r.readfilePass(FileName).indexOf("___", r.readfilePass(FileName).indexOf("___") + 1) + 3));
		Err.add(errorScroll);
		f.add(Err, Icon);
		f.add(p1);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public String GetInput() {
		String s = "";
		s = input.getText();
		return s;
	}
}
