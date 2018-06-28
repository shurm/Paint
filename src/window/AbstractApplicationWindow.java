package window;

import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Abstract Window class 
 * 
 * @author Michael Shur
 *
 */
public abstract class AbstractApplicationWindow 
{
	private JFrame window = new JFrame();
	

	public AbstractApplicationWindow (String title, int operation, int windowWidth, int windowHeight)
	{
		this(title, operation, windowWidth, windowHeight, true);
	}
	
	public AbstractApplicationWindow (String title, int operation, int windowWidth, int windowHeight, boolean resizable)
	{
		//sets the title of the window
		window.setTitle(title);
		
		//sets the size of the window
		window.setSize(windowWidth, windowHeight);

		//makes window appear in the center of the screen
		window.setDefaultCloseOperation(operation);
				
		//makes window appear in the center of the screen
		window.setLocationRelativeTo(null);
		
		//determine whether or not this window's size can be changed by the user
		window.setResizable(resizable);
	}
	
	/**
	 * Appends the specified component to the end of this container.
	 * 
	 * @param comp the component to be added
	 */
	public void add(JComponent comp)
	{
		window.add(comp);
	}
	
	public void add(JComponent comp, Object constraints)
	{
		window.add(comp, constraints);
	}
	
	/**
	 * Sets the LayoutManager. 
	 * 
	 * @param layout the LayoutManager
	 */
	public void setLayout(LayoutManager layout)
	{
		window.setLayout(layout);
	}
	
	/**
	 * makes the window visible
	 */
	public void show()
	{
		window.setVisible(true);
	}
	
	/**
	 * closes this window and releases all of the native screen resources used by this Window, its subcomponents, and all of its owned children. 
	 * That is, the resources for these Components will be destroyed, any memory they consume will be returned to the OS, 
	 * and they will be marked as undisplayable. 
	 */
	public void dispose() 
	{
		window.dispose();
	}
}
