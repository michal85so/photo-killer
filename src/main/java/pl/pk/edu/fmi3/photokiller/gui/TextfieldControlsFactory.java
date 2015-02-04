package pl.pk.edu.fmi3.photokiller.gui;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Factory of textfield controls
 */
public class TextfieldControlsFactory {
	/**
	 * Constructor
	 */
	public TextfieldControlsFactory(TextField control){
		control = new TextField();
		control.setAlignment(Pos.BASELINE_LEFT);
		control.setPrefColumnCount(40);
	}
	
	/**
	 * Constructor
	 * @param editable is editable
	 */
	public TextfieldControlsFactory(TextField control, Boolean editable){
		this(control);
		control.setEditable(editable);
	}
	
	public static TextField createTextField(){
		TextField control = new TextField();
		control.setAlignment(Pos.BASELINE_LEFT);
		control.setPrefColumnCount(40);
		return control;
	}
	
	public static TextField createTextField(Boolean editable){
		TextField control = createTextField();
		control.setEditable(editable);
		return control;
	}
}
