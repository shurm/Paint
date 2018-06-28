package util;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class which is "in charge" of the LookAndFeel the UI design uses
 * 
 * @author Michael Shur
 *
 */
public class MyUIManager 
{
	private static LookAndFeel previousLF ;

	//all predefined UI types which are contained inside the jar files this project uses
	public enum UITypes 
	{
		
		Metal("javax.swing.plaf.metal.MetalLookAndFeel"), Nimbus("javax.swing.plaf.nimbus.NimbusLookAndFeel"), 
		Motif("com.sun.java.swing.plaf.motif.MotifLookAndFeel"), Windows("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"),
		WindowsClassic("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

		private final String className;

		private UITypes(String className)
		{
			this.className = className;
		}
		public String getClassName() {
			return className;
		}
	}

	/**
	 * Changes the current LookAndFeel to the specified lookAndFeelType
	 * 
	 * @param lookAndFeelType the desired LookAndFeel
	 */
	public static void changeLookAndFeel(UITypes lookAndFeelType)
	{
		previousLF = UIManager.getLookAndFeel();

		try {
			UIManager.setLookAndFeel(lookAndFeelType.getClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Changes the current LookAndFeel to the lookAndFeelType that was previously used
	 * 
	 */
	public static void revertLookAndFeel()
	{
		try {
			UIManager.setLookAndFeel(previousLF);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
