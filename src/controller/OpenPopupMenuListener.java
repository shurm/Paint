package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

/**
 * 
 * i.e when the button variable is clicked, the popupMenu variable should appear
 * 
 * @author Michael Shur
 *
 */
public class OpenPopupMenuListener extends MouseAdapter
{

	private JPopupMenu popupMenu;
	private JButton button;
	
	
	public OpenPopupMenuListener(JPopupMenu popupMenu, JButton button) 
	{
		this.popupMenu = popupMenu;
		this.button = button;
	}

	public void mouseClicked(MouseEvent e) {
		popupMenu.show(button,e.getX(),e.getY());
	}
}
