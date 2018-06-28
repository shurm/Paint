package window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ColorArrowButtonListener;
import controller.CreateNewColorListener;
import view.ColorButtonsPanel;

/**
 * The Window the user utilizes in order in create his/her own Color
 * 
 * @author Michael Shur
 */
public class CustomColorWindow extends AbstractApplicationWindow
{
	/*
	 * CONSTANTS
	 */
	//this window's width and height
	private static final int WINDOW_WIDTH=380, WINDOW_HEIGHT=300;
	
	//this window's title
	private static final String TITLE = "Create your own Color";
	
	private static final String COLOR_BUTTON_TITLE = "Add This Color";
	
	//the names of the different color components
	private static final String [] COLORINPUTS={"RED","GREEN","BLUE","ALPHA"};

	//how much a color component gets altered from a single press of one of the arrow keys
	private static final int DELTA = 1;
	
	private static final int SLEEP_TIME_IN_MILLISECONDS = 40;
	
	
	
	/**
	 * The different ArrowButton types 
	 * 
	 * @author Michael Shur
	 *
	 */
	public enum ArrowButtonType 
	{
		UP("^",1),DOWN("v",-1);
		
		private final String stringValue;
		
		private final int d;
		private ArrowButtonType(String stringValue, int i)
		{
			this.stringValue=stringValue;
			this.d=i;
		}
		public int getD()
		{
			return d;
		}
		
		public String toString()
		{
			return stringValue;
		}
	}
	
	
	//defaults color component values which will later be altered by the user
	private int [] colorComponents = {170, 50, 20, 255};

	//which color component values the user is altering/last altered
	private int colorComponentIndex;
	
	//the type of button that the user is currently pushing/last pushed i.e. (UP or DOWN)
	private ArrowButtonType currentArrowButtonType;

	private Color currentColor;

	private JLabel [] colorComponentLabels;

	private JPanel currentColorDisplay;
	
	public CustomColorWindow(ColorButtonsPanel cbp)
	{
		super(TITLE, JFrame.DISPOSE_ON_CLOSE, WINDOW_WIDTH, WINDOW_HEIGHT,false);
		
		currentColorDisplay= new JPanel();
		currentColorDisplay.setLayout(new BorderLayout());

		colorComponentLabels=new JLabel[colorComponents.length];

		JPanel arrowGroups = createArrowGroupsPanel();
		
		JPanel buttonsAtBottom= new JPanel();
		buttonsAtBottom.setLayout(new GridLayout(2,1));

		JButton addColorButton= new JButton(COLOR_BUTTON_TITLE); 
		addColorButton.addActionListener(new CreateNewColorListener(this, cbp));

		buttonsAtBottom.add(addColorButton);

		arrowGroups.add(buttonsAtBottom);

		currentColorDisplay.add(arrowGroups,BorderLayout.SOUTH);
	
		//sets the background color using the default color components
		currentColor = createColor();
		currentColorDisplay.setBackground(currentColor);

		this.add(currentColorDisplay);
	}

	/*
	 * creates a Panel containing a label and arrow buttons for each Color Component
	 */
	private JPanel createArrowGroupsPanel()
	{
		JPanel content = new JPanel();
		
		for(int colorComponentIndex=0;colorComponentIndex<colorComponents.length;colorComponentIndex++)
		{
			content.add(createArrowGroupWithLabel(colorComponentIndex));
		}

		return content;
	}

	//creates a panel which contain a label and and arrow buttons
	private JPanel createArrowGroupWithLabel(int colorComponentIndex)
	{
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,1));
		
		//adds the label
		colorComponentLabels[colorComponentIndex]=new JLabel(COLORINPUTS[colorComponentIndex]+": "+colorComponents[colorComponentIndex]);
		content.add(colorComponentLabels[colorComponentIndex]);
		
		//adds the arrow buttons
		ArrowButtonType[] buttonTypes = ArrowButtonType.values();
		JPanel arrowButtons = new JPanel();
		arrowButtons.setLayout(new GridLayout(buttonTypes.length,1));

		for(int d=0;d<buttonTypes.length;d++)
		{
			JButton arrowButton = new JButton(buttonTypes[d].toString());
			arrowButton.addMouseListener(new ColorArrowButtonListener(this,colorComponentIndex,buttonTypes[d]));
			arrowButtons.add(arrowButton);
		}
		
		content.add(arrowButtons);
		
		return content;
	}
	
	/**
	 * @return a new Color which represents the current values of this object's color components, namely, ("RED","GREEN","BLUE","ALPHA") 
	 */
	public Color createColor() 
	{
		return new Color(colorComponents[0], colorComponents[1], colorComponents[2], colorComponents[3]);
	}

	
	private void changeBackGround() 
	{
		int delta = DELTA;
		
		delta*=currentArrowButtonType.getD();

		//potentially changes one of the color components that makes up the back
		colorComponents[colorComponentIndex] = rangeCheckAndAlter(colorComponents[colorComponentIndex],delta);
		colorComponentLabels[colorComponentIndex].setText(COLORINPUTS[colorComponentIndex]+": "+colorComponents[colorComponentIndex]);
		
		currentColor = createColor();
		currentColorDisplay.setBackground(currentColor);
		
		//slows this Thread so the change in Color is visible
		sleep();
	}

	/*
	 * slows this Thread so the change in Color is visible
	 */
	private void sleep()
	{
		try {
			Thread.sleep(SLEEP_TIME_IN_MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int rangeCheckAndAlter(int i, int delta)
	{
		if(i+delta<0 || i+delta>255)
			return i;
		return i+delta;
	}
	
	
	public void setColorComponentIndex(int colorComponentIndex) 
	{
		this.colorComponentIndex = colorComponentIndex;
	}

	public void setCurrentArrowButtonType(ArrowButtonType currentArrowButtonType) 
	{
		this.currentArrowButtonType = currentArrowButtonType;
	}

	
	/**
	 * Thread which is used to change the background color of the currentColorDisplay
	 */
	public class ChangeColorFrameComponentsThread extends Thread
	{
		private final AtomicBoolean runThread = new AtomicBoolean(true);
	
		public void run()
		{
			while(runThread.get())
			{
				changeBackGround();
			}
		}
		
		/**
		 * Makes this Thread die (poor thread)
		 */
		public void endThread()
		{
			runThread.set(false);
		}
	}
}
