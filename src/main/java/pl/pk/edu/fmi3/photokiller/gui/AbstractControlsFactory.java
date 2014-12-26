package pl.pk.edu.fmi3.photokiller.gui;

import javafx.scene.control.Control;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Abstract class of all controls factories
 */
public abstract class AbstractControlsFactory {
	protected Control control = null;
	
	/**
	 * Method returns controls
	 * @return controls
	 */
	public Control getControl(){
		return control;
	}
}
