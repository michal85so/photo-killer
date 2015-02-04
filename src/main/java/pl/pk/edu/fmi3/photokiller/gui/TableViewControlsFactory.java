package pl.pk.edu.fmi3.photokiller.gui;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pl.pk.edu.fmi3.photokiller.models.FileModel;
import pl.pk.edu.fmi3.photokiller.models.FileModelForTableView;

/**
 * 
 * @author Michał Policht - michal85so@gmail.com Factory of tableview controls
 */
public class TableViewControlsFactory {

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public TableViewControlsFactory(final TableView<FileModelForTableView> control){
		TableColumn<FileModelForTableView, Boolean> selectionColumn = new TableColumn<FileModelForTableView, Boolean>();
		selectionColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, Boolean>("fileSelection"));
		selectionColumn.setCellFactory(new Callback<TableColumn<FileModelForTableView, Boolean>, TableCell<FileModelForTableView, Boolean>>() {
			public TableCell<FileModelForTableView, Boolean> call(TableColumn<FileModelForTableView, Boolean> p) {
				final TableCell<FileModelForTableView, Boolean> cell = new TableCell<FileModelForTableView, Boolean>() {
					@Override
					public void updateItem(final Boolean item, boolean empty) {
						if (item == null)
							return;
						super.updateItem(item, empty);
						if (!isEmpty()) {
							final FileModelForTableView employee = getTableView().getItems().get(getIndex());
							CheckBox checkBox = new CheckBox();
							checkBox.selectedProperty().bindBidirectional(employee.getFileSelectionProperty());
							setGraphic(checkBox);
						}
					}
				};
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		selectionColumn.setEditable(true);

		TableColumn<FileModelForTableView, String> fileNameColumn = new TableColumn<FileModelForTableView, String>("File name");
		fileNameColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("fileName"));
		fileNameColumn.setPrefWidth(300);

		TableColumn<FileModelForTableView, String> filePathColumn = new TableColumn<FileModelForTableView, String>("File path");
		filePathColumn.setCellValueFactory(new PropertyValueFactory<FileModelForTableView, String>("filePath"));
		filePathColumn.setPrefWidth(550);
		
		
		
		control.getColumns().addAll(selectionColumn,fileNameColumn,filePathColumn);
		control.setEditable(true);
		
		//TODO to test only
		ArrayList<FileModel> al = new ArrayList<FileModel>();
		FileModel fm = new FileModel(false,"aaaa", "bbbb");
		al.add(fm);
		addItemsToTable(control, al);
		
	}
	
	public class CheckboxCell extends TableCell<FileModelForTableView, Boolean> {
		CheckBox checkbox;

		@Override
		protected void updateItem(Boolean arg0, boolean arg1) {
		    super.updateItem(arg0, arg1);
		        paintCell();
		}

		private void paintCell() {
		    if (checkbox == null) {
		        checkbox = new CheckBox();
		        checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {

							@Override
							public void changed(ObservableValue<? extends Boolean> observable,
									Boolean oldValue, Boolean newValue) {
								setItem(newValue);
								((FileModelForTableView)getTableView().getItems().get(getTableRow().getIndex())).setFileSelection(newValue);
							}
		        	
						});
		    }
		    checkbox.setSelected(getValue());
		    setText(null);
		    setGraphic(checkbox);
		}

		private Boolean getValue() {
		    return getItem() == null ? false : getItem();
		}
		}

	/**
	 * Method adds items to tableview
	 * 
	 * @param items
	 *          arraylist of items
	 */
	public synchronized static void addItemsToTable(
			TableView<FileModelForTableView> control, ArrayList<FileModel> items) {
		ObservableList<FileModelForTableView> tableList = FXCollections
				.observableArrayList();
		for (FileModel fm : items)
			tableList.add(new FileModelForTableView(fm.getFileSelection(), fm.getFileName(), fm
					.getFilePath()));
		control.setItems(tableList);
	}

	/**
	 * Method returns selected items
	 * 
	 * @return arraylist with selected items
	 */
	public synchronized static ArrayList<FileModel> getSelectedItemsAsArrayList(
			TableView<FileModelForTableView> control) {
		ArrayList<FileModel> selectedItems = new ArrayList<FileModel>();
		if (control != null) {
			ObservableList<FileModelForTableView> items = control.getItems();
			for (FileModelForTableView fm : items) {
				if (fm.getFileSelection())
					selectedItems.add(new FileModel(fm.getFileSelection(), fm.getFileName(), fm.getFilePath()));
			}
		}
		return selectedItems;
	}
}
