package window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.Canvas;
import view.CanvasButtonPanel;

/**
 * 
 * Complete Paint Application containing all the different necessary UI components (Canvas, various Panels, buttons, etc)
 * 
 * @author Michael Shur
 *
 */
public class PaintApplicationWindow extends AbstractApplicationWindow
{
	//the window's default width and height
	private static final int WINDOW_WIDTH=600, WINDOW_HEIGHT=600;
	
	//this window's title
	private static final String TITLE = "Michael's Bad Ass Paint";
	
	/**
	 * Construct a new Paint Application
	 */
	public PaintApplicationWindow()
	{
		super(TITLE, JFrame.EXIT_ON_CLOSE, WINDOW_WIDTH, WINDOW_HEIGHT);
	
		this.setLayout(new BorderLayout());

		//the Canvas you are able to draw things on
		Canvas canvas= new Canvas();
		
		CanvasButtonPanel canvasButtonPanel=new CanvasButtonPanel(canvas);
		
		//adds the Canvas to the Center of the frame
		this.add(canvas, BorderLayout.CENTER);

		//adds the ButtonsPanel to the Left of the frame
		this.add(canvasButtonPanel, BorderLayout.WEST);
		
	}
}