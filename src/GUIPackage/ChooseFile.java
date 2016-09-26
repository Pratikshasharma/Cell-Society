package GUIPackage;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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


