package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import model.Canvas;
import window.SaveImageWindow;

/**
 * Opens up a file chooser window and then opens a SaveImageWindow if the user selects a valid folder
 * 
 * @author Michael Shur
 *
 */
public class SaveImageListener implements ActionListener
{
	//Window that is able to save picture in the paint 
	//canvas to the directory of your choice
	private final JFileChooser selectFolderWindow = new JFileChooser();
	
	private Canvas canvas;
	
	public SaveImageListener(Canvas canvas)
	{
		this.canvas = canvas;
		
		selectFolderWindow.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		selectFolderWindow.setApproveButtonText("Select");
		selectFolderWindow.setDialogTitle("Select Folder");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{

		if(selectFolderWindow.isShowing()==false)
		{
			//Open the selectFolderWindow at the center of the screen
			int a=selectFolderWindow.showOpenDialog(null);

			//if you selected a proper directory
			if(a==JFileChooser.APPROVE_OPTION)
			{
				new SaveImageWindow(canvas, selectFolderWindow.getSelectedFile().toString()).show();
			}
		}
	}
}
