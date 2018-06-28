package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ClearButtonListener;
import controller.UndoButtonListener;
import model.Canvas;

/**
 * Panel which contains the clear and undo buttons
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class DeleteDrawingButtonsPanel extends JPanel 
{
	public DeleteDrawingButtonsPanel(Canvas canvas) 
	{
		this.setLayout(new GridLayout(1,2));
		
		JButton clear = new JButton("clear");
		
		clear.addActionListener(new ClearButtonListener(canvas));
		this.add(clear);
		
		JButton undo = new JButton("undo");
		undo.addActionListener(new UndoButtonListener(canvas));
		this.add(undo);
	}
}
