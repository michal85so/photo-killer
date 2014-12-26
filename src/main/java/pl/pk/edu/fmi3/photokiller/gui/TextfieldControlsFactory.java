package pl.pk.edu.fmi3.photokiller.gui;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Factory of textfield controls
 */
public class TextfieldControlsFactory extends AbstractControlsFactory{
	/**
	 * Constructor
	 */
	public TextfieldControlsFactory(){
		control = new TextField();
		((TextField)control).setAlignment(Pos.BASELINE_LEFT);
		((TextField)control).setPrefColumnCount(40);
	}
	
	/**
	 * Constructor
	 * @param editable is editable
	 */
	public TextfieldControlsFactory(Boolean editable){
		this();
		((TextField)control).setEditable(editable);
	}
}
