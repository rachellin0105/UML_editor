package UI;

import javax.swing.JButton;

import Mode.Mode;

public class ModeButten extends JButton{
	private Mode mode;
	public ModeButten(Mode mode,String name) {
		super(name);
		this.mode = mode;
	}
	
	public Mode getMode() {
		return mode;
	}
	
}
