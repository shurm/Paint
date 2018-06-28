package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.ChangeBackgroundColorInterface;

/**
 * 
 * Controller which is intended to perform some action on a jComponent using a Color, typically change its background-color
 * 
 * @author Michael Shur
 *
 */
public class ChangeBackgroundColorListener implements ActionListener
{

	private Color color;

	private ChangeBackgroundColorInterface jComponent;
	public ChangeBackgroundColorListener(Color color, ChangeBackgroundColorInterface i)
	{
		this.color = color;
		this.jComponent=i;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		jComponent.setColor(color);
	}

}