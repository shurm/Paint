package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import model.Canvas;

/**
 * The complete Panel of Buttons the user can click to affect the canvas and drawings
 * 
 * @author Michael Shur
 *
 */
@SuppressWarnings("serial")
public class CanvasButtonPanel extends JPanel
{
	public CanvasButtonPanel(Canvas canvas) 
	{
		this.setLayout(new BorderLayout());
	
		RadioButtonPanel upperPortion = new RadioButtonPanel(canvas);
		
		this.add(upperPortion, BorderLayout.NORTH);
	
		LowerButtonPanel lowerPortion = new LowerButtonPanel(canvas);
		
		this.add(lowerPortion,BorderLayout.CENTER);
	}
}
