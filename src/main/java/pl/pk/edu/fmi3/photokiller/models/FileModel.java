package pl.pk.edu.fmi3.photokiller.models;
/**
 * 
 * @author Michał Policht - michal85so@gmail.com
 * Model for file
 */
public class FileModel {
	private Boolean fileSelection;
	private String fileName;
	private String filePath;
	public FileModel(boolean fileSelection, String fileName, String filePath){
		this.fileSelection = fileSelection;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public Boolean getFileSelection() {
		return fileSelection;
	}
	public void setFileSelection(Boolean fileSelection) {
		this.fileSelection = fileSelection;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
