import java.awt.BorderLayout;

import javax.swing.JFrame;

import UI.Buttons;
import UI.Canvas;
import UI.ManuBar;

public class UML extends JFrame{
	

	private Canvas canvas = Canvas.getInstance();
	private Buttons buttens = Buttons.getInstance();
	private ManuBar manuBar = ManuBar.getInstance();
	public UML() {
		// set JFrame
		super("UML_editor");
		System.out.println("start generate UML_editor window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		
		// add canvas buttons manuBar in JFrame
		add(canvas,BorderLayout.CENTER);
		add(buttens,BorderLayout.WEST);
		add(manuBar,BorderLayout.NORTH);
		
		// show editor in the middle of screen
		setLocationRelativeTo(null);

	}
	


}
