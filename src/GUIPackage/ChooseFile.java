package GUIPackage;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * @author pratikshasharma
 *
 */
public class ChooseFile{
	private FileChooser fileChooser = new FileChooser();
	private Stage myStage;

	public File chooseFile(){
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("XML files (*.XML)", "*.XML");		
		
		String userDirectoryString = System.getProperty("user.dir") + File.separator;
		File userDirectory = new File(userDirectoryString) ;
		fileChooser.setInitialDirectory(userDirectory);
		fileChooser.getExtensionFilters().add(extentionFilter);
		File chosenFile = fileChooser.showOpenDialog(myStage);
		if(chosenFile == null) {
			GUIController.exitGame();
		}
		return chosenFile;			
	}

	public FileChooser getFileChooser() {
		return this.fileChooser;	
	}	
}


