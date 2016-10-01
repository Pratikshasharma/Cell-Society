package gui;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author pratikshasharma
 *
 */
public class ChooseFile {
	private FileChooser fileChooser = new FileChooser();
	private Stage myStage;
	private final static String CHOOSE_FILE_ERROR = "No File Chosen";

	public File chooseFile() {
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("XML files (*.XML)", "*.XML");

		String userDirectoryString = System.getProperty("user.dir") + File.separator;
		File userDirectory = new File(userDirectoryString);
		fileChooser.setInitialDirectory(userDirectory);
		fileChooser.getExtensionFilters().add(extentionFilter);
		File chosenFile = fileChooser.showOpenDialog(myStage);
		if (chosenFile == null) {
			//throw new IllegalArgumentException(CHOOSE_FILE_ERROR); 
		}
		return chosenFile;
	}

	public FileChooser getFileChooser() {
		return this.fileChooser;
	}
}
