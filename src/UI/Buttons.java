package UI;

import javax.swing.JPanel;

import Mode.SelectMode;
import Mode.UseCaseMode;

import Mode.ClassMode;

import Mode.LineMode;
import Mode.Mode;
import Mode.ModeFactory;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;

public class Buttons extends JPanel{
	
	private Canvas canvas = Canvas.getInstance();
	/*
	 *  Java - singleton pattern 
	 *   only one Object will be generate
	 */
	private static Buttons uniqueInstance ;
	/*
	 * JButton vector to store button
	 */
	private static Vector<JButton> buttonVec;
	/*
	 * set Mode by click button
	 */
	private static JButton selectedBtn;
	
	
	private Buttons() {	
		
		buttonVec = new Vector<JButton>();
		
		initModes();

		int NumOfMode = ModeList.values().length;
		setLayout(new GridLayout(NumOfMode,0));
		setSize(64,64);  
		setVisible(true);  
	}
	

	public static synchronized Buttons getInstance(){
		if (uniqueInstance == null) {
			uniqueInstance = new Buttons();
		}
		return uniqueInstance;
		
	}
	
	private void initModes() {
		ModeFactory mf = new ModeFactory();
		for (ModeList m : ModeList.values()) {
			final ModeButten btn = new ModeButten(mf.createMode(m.name()),m.name());
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){  
					if( selectedBtn != null ) { // has selected 
						//set last time selected button back to original color;
						selectedBtn.setBackground(null);
						selectedBtn.setForeground(null);
					}				
					btn.setBackground(Color.BLACK);
					btn.setForeground(Color.white);
					canvas.setMode(btn.getMode());
					selectedBtn = btn;
				}  
			});  
			this.add(btn);
			buttonVec.add(btn);
		}
		
	}
	
}
