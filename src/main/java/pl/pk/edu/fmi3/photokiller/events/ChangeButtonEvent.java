package pl.pk.edu.fmi3.photokiller.events;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import pl.pk.edu.fmi3.photokiller.gui.GuiCreatorInterface;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Class which is invoke when the change button is pressed.
 */
public class ChangeButtonEvent implements EventHandler<ActionEvent>{
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * Enum with type of pressed button
	 */
	public static enum ChangeName{
		Source("source path"),
		Search("search path");
		
		String actionName = "";
		
		/**
		 * Constructor
		 * @param actionName name of pressed button
		 */
		ChangeName(String actionName){
			this.actionName = actionName;
		}
		
		/**
		 * Method return name of pressed button
		 * @return button's name
		 */
		public String getActionName(){
			return actionName;
		}
	}
	
	ChangeName chanName;
	GuiCreatorInterface guiC;
	AbstractChangeControl absChaCon;
	
	/**
	 * Constructor
	 * @param nameChangeControl type of pressed button
	 * @param guiC object of GuiCreator
	 */
	public ChangeButtonEvent(ChangeName nameChangeControl, GuiCreatorInterface guiC){
		chanName = nameChangeControl;
		this.guiC = guiC;
	}

	/**
	 * Method invokes when the button is pressed
	 * event action sended from window
	 */
	public void handle(ActionEvent event) {
		File newPath;
		if (ChangeName.Search == chanName){
			absChaCon = new SearchChangeControl();
			newPath = absChaCon.selectDirectory();
			if (newPath != null)
				guiC.setSearchFile(newPath);
		}
		else {
			absChaCon = new SourceChangeControl();
			newPath = absChaCon.selectDirectory();
			if (newPath != null)
				guiC.setSourceFile(newPath);
		}
	}

	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * Abstract class, that is call window with directory choose
	 */
	public abstract class AbstractChangeControl{
		protected String controlName;
		
		/**
		 * Method invoke window to choose directory
		 * @return
		 */
		public File selectDirectory(){
			DirectoryChooser dirChoos = new DirectoryChooser();
			dirChoos.setTitle("select your " + controlName);
			return dirChoos.showDialog(null);
		}
	}
	
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * Implementation of astractChangeControl on change button for source path. 
	 */
	public class SourceChangeControl extends AbstractChangeControl{
		/**
		 * Constructor
		 */
		public SourceChangeControl(){
			controlName = ChangeName.Source.getActionName();
		}
	}
	
	/**
	 * 
	 * @author Michał Policht - michal85so@gmail.com
	 * Implementation of astractChangeControl on change button for search path.
	 */
	public class SearchChangeControl extends AbstractChangeControl{
		/**
		 * Constructor
		 */
		public SearchChangeControl(){
			controlName = ChangeName.Search.getActionName();
		}
	}

}
