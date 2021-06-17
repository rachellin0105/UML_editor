package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import SHAPE.Group;
import SHAPE.Shape;

import javax.swing.JMenuBar;  

public class ManuBar extends JMenuBar {
	/*
	 *  Java - singleton pattern 
	 *   only one Object will be generate
	 */
	public static ManuBar uniqueInstance ;
	
	private Canvas canvas = Canvas.getInstance();
	/*
	 * two menu
	 */
	private JMenu file;
	private JMenuItem exit, clear;
	
	private JMenu edit;
	private JMenuItem group, ungroup, rename;
	
	private ManuBar(){
		System.out.println("ManuBar Panel constructor");
		file = new JMenu("File");
		edit = new JMenu("Edit");
		initFileMenu();
		initEditMenu();
		add(file);
		add(edit);
		
	}
	
	private void initFileMenu() {
		exit = new JMenuItem("Exit");
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
		clear = new JMenuItem("Clear");
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.removeAll();
				canvas.objs.clear();
				canvas.repaint();
				
			}
		});
		
		file.add(exit);
		file.add(clear);
		
		
	}
	
	private void initEditMenu() {
		rename = new JMenuItem("Rename");
		
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame smallWindow = new JFrame();
				if (canvas.getSelectedObjs().length != 0) {
					Shape selectedObj = canvas.getSelectedObjs()[0];
					String name = JOptionPane.showInputDialog(smallWindow,
		                        "Enter the new name", selectedObj.getName());
					if (name != null && !name.toString().isEmpty())
						selectedObj.setName(name.toString());
				}

				
				canvas.repaint();
			}
			
		});
		
		group = new JMenuItem("Group");
		
		group.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape[] selectedObjs = canvas.getSelectedObjs();
				// canvas.remove(selectedObjs);
				if (selectedObjs.length > 1) {
					for(Shape shape : selectedObjs) {
						if (shape.getChildren()!= null) {
							canvas.objs.remove(shape);
							canvas.remove(shape);
							selectedObjs = canvas.getSelectedObjs();
						}
					}
					Group g = new Group(selectedObjs);
					canvas.objs.add(g);
					canvas.add(g);
				}
				
				canvas.resetDepth();
				canvas.repaint();
				
			}
		});
		
		ungroup = new JMenuItem("Ungroup");
		
		ungroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape[] selectedObjs = canvas.getSelectedObjs();
				if (canvas.getSelectedObjs().length != 0) {
					for (Shape shape : selectedObjs) {
						if ( shape instanceof Group) {
							canvas.objs.remove(shape);
							canvas.remove(shape);
						}
					}
				}

				//canvas.showObjs();
				canvas.repaint();
				
			}
		});
		
		edit.add(rename);
		edit.add(group);
		edit.add(ungroup);
	}
	public static synchronized ManuBar getInstance(){
		if (uniqueInstance == null) {
			uniqueInstance = new ManuBar();
		}
		return uniqueInstance;
		
	}

}
