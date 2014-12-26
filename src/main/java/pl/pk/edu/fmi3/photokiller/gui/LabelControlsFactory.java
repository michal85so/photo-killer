package pl.pk.edu.fmi3.photokiller.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Factory of label controls
 */
public class LabelControlsFactory extends AbstractControlsFactory{
	
	/**
	 * Constructor
	 * @param name text of label
	 */
	public LabelControlsFactory(String name){
		control = new Label(name);
		((Label)control).setAlignment(Pos.BASELINE_RIGHT);
	}
}
