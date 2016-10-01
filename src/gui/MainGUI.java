package gui;
import button.Reset;
import button.Start;
import button.Step;
import button.Stop;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author pratiksha sharma
 *
 */

public class MainGUI {
	public static final int GRID_WIDTH = 400;
	public static final int GRID_HEIGHT = 400;
	private VBox myVBox;
	private Start myStartSimulationButton;
	private Step myStepSimulationButton;
	private Stop myStopSimulationButton;
	private Reset myResetButton;
	private HBox myHBox;
	private Slider simulationSpeedSlider;
	private String mySimulationName;
	private int numCellsWidth;
	private int numCellsHeight;
	private Grid myGrid;

	public MainGUI() {
		this.myStartSimulationButton = new Start();
		this.myStepSimulationButton = new Step();
		this.myStopSimulationButton = new Stop();
		this.myResetButton = new Reset();
	}

	public Group setScene(Paint[][] myGridColor) {
		Group root = new Group();
		myVBox = new VBox(10);
		myHBox = new HBox(10);
		myVBox.setPadding(new Insets(10));
		myGrid = new Grid(this.numCellsWidth, this.numCellsHeight);
		myGrid.createGrid(myGridColor);

		myVBox.getChildren().addAll(addSimulationTitle(), myGrid.getGrid());
		addButtons();
		addSlider();
		root.getChildren().add(myVBox);
		myVBox.getChildren().add(myHBox);
		return root;
	}

	private Text addSimulationTitle() {
		Text title = new Text(mySimulationName);
		title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 15));
		return title;
	}

	private void addButtons() {
		myHBox.getChildren().addAll(myStopSimulationButton.getButton(), myStartSimulationButton.getButton(),
				myResetButton.getButton(), myStepSimulationButton.getButton());
	}

	private void addSlider() {
		simulationSpeedSlider = new Slider(GUIController.MIN_FRAMES_PER_SECOND, GUIController.MAX_FRAMES_PER_SECOND,
				GUIController.DEFAULT_FRAMES_PER_SECOND);
		simulationSpeedSlider.setMajorTickUnit(10);
		Label speedCaption = new Label("Simulation Speed :");
		Label speedValue = new Label(Double.toString(simulationSpeedSlider.getValue()));

		simulationSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				simulationSpeedSlider.setValue((double) new_val);
				speedValue.textProperty().setValue(String.valueOf((int) simulationSpeedSlider.getValue()));
			}
		});

		myHBox.getChildren().addAll(simulationSpeedSlider, speedCaption, speedValue);
	}

	public Button getResetButton() {
		return this.myResetButton.getButton() ;
	}

	public Button getStopSimulationButton() {
		return this.myStopSimulationButton.getButton();
	}

	public Button getStepSimulationButton() {
		return this.myStepSimulationButton.getButton();
	}

	public Button getStartSimulationButton() {
		return this.myStartSimulationButton.getButton();
	}

	public Slider getSimulationSpeedSlider() {
		return simulationSpeedSlider;
	}

	public void setNumCellsWidth(int numCells) {
		this.numCellsWidth = numCells;
	}

	public void setNumCellsHeight(int numCells) {
		this.numCellsHeight = numCells;
	}

	public void setSimulationName(String simulationName) {
		this.mySimulationName = simulationName;
	}

	public Grid getGrid() {
		return this.myGrid;
	}

	public void updateGridColor(int row, int col, Paint color) {
		myGrid.changeMyGridColor(row, col, color);
	}


	
}
