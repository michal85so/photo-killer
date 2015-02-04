package pl.pk.edu.fmi3.photokiller.gui;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pl.pk.edu.fmi3.photokiller.events.ChangeButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.CompareButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.DeleteButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.SearchButtonEvent;
import pl.pk.edu.fmi3.photokiller.events.ChangeButtonEvent.ChangeName;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Class of main frame of application
 */
public class GUICreator implements GuiCreatorInterface{
	private BorderPane mainPane = null;
	private final String URI_TO_DELETE_ICON = "Resource/file_document_paper_red_g10010.png";
	private final String URI_TO_SEARCH_ICON = "Resource/Search2.png";
	private final String URI_TO_EXIT_ICON = "Resource/file_document_paper_red_g12932.png";
	private final String URI_TO_COMPARE_ICON = "Resource/WorkGroup.png";
	private File sourcePath;
	private File searchPath;
	
	private TableView<FileModelForTableView> tabFiles;
	private TextField tfSourcePath;
	private TextField tfSearchPath;
	
	/**
	 * Contstructor
	 */
	public GUICreator(){
		this.mainPane = new BorderPane();
		createMainWindow();
	}
	
	/**
	 * Method add controls to main frame
	 */
	private void createMainWindow(){
		addControlsToNorthSide();
		addControlsToSouthSide();
		addControlsInside();
	}
	
	/**
	 * Method adds controls in the top part of frame
	 */
	private void addControlsToNorthSide(){
		FlowPane northPane = new FlowPane();
		AbstractControlsFactory buttonSearch = new ButtonControlsFactory("Search",URI_TO_SEARCH_ICON);
		((Button)buttonSearch.getControl()).setOnAction(new SearchButtonEvent());
		AbstractControlsFactory buttonCompare = new ButtonControlsFactory("Compare",URI_TO_COMPARE_ICON);
		((Button)buttonCompare.getControl()).setOnAction(new CompareButtonEvent());
		AbstractControlsFactory buttonExit = new ButtonControlsFactory("Exit",URI_TO_EXIT_ICON);
		((Button)buttonExit.getControl()).setOnAction(event -> System.exit(0));
		AbstractControlsFactory buttonDelete = new ButtonControlsFactory("Delete",URI_TO_DELETE_ICON);
		((Button)buttonDelete.getControl()).setOnAction(new DeleteButtonEvent());
		northPane.getChildren().addAll(buttonExit.getControl(),buttonSearch.getControl(),buttonCompare.getControl(),buttonDelete.getControl());
		mainPane.setTop(northPane);
	}
	
	/**
	 * Method adds controls in the bottom part of frame
	 */
	private void addControlsToSouthSide(){
		GridPane southPane = new GridPane();
		
		AbstractControlsFactory labForSource = new LabelControlsFactory("Source path:");
		tfSourcePath = TextfieldControlsFactory.createTextField(false);
		AbstractControlsFactory butForChangeSource = new ButtonControlsFactory("Change");
		AbstractControlsFactory labForSearch = new LabelControlsFactory("Search path:");
		tfSearchPath = TextfieldControlsFactory.createTextField(false);
		AbstractControlsFactory butForChangeSearch = new ButtonControlsFactory("Change");
		
		GridPane.setConstraints(labForSource.getControl(), 1, 1);
		GridPane.setConstraints(tfSourcePath, 2, 1);
		GridPane.setConstraints(butForChangeSource.getControl(), 3, 1);
		GridPane.setConstraints(labForSearch.getControl(), 1, 2);
		GridPane.setConstraints(tfSearchPath, 2, 2);
		GridPane.setConstraints(butForChangeSearch.getControl(), 3, 2);
		
		((Button)butForChangeSearch.getControl()).setOnAction(new ChangeButtonEvent(ChangeName.Search, this));
		((Button)butForChangeSource.getControl()).setOnAction(new ChangeButtonEvent(ChangeName.Source, this));
		
		ArrayList<Node> controls = new ArrayList<>();
		controls.add(labForSource.getControl());
		controls.add(tfSourcePath);
		controls.add(labForSearch.getControl());
		controls.add(tfSearchPath);
		controls.add(butForChangeSource.getControl());
		controls.add(butForChangeSearch.getControl());
		
		southPane.getChildren().addAll(controls);
		mainPane.setBottom(southPane);
	}
	
	/**
	 * Method adds controls in the center of main frame
	 */
	private void addControlsInside(){
		tabFiles = new TableView<FileModelForTableView>();
		new TableViewControlsFactory(tabFiles);
		mainPane.setCenter(tabFiles);
	}
	
	/**
	 * Method returns main panel
	 * @return main panel
	 */
	public Pane getMainPane(){
		return mainPane;
	}
	
	/**
	 * Method sets search path in control
	 */
	public void setSearchFile(File searchFile){
		searchPath = searchFile;
		tfSearchPath.setText(searchPath.getPath());
	}
	
	/**
	 * Method sets source path in control
	 */
	public void setSourceFile(File sourceFile){
		sourcePath = sourceFile;
		tfSourcePath.setText(sourcePath.getPath());
		
		//TODO to test only
		for (FileModelForTableView item : tabFiles.getItems())
			if (item.getFileSelection())
				System.out.println(item.getFileName());
	}
}
