package GUIPackage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author pratiksha sharma
 *
 */

public class CreateGrid {
	public static final int GRID_WIDTH = 400;
	public static final int GRID_HEIGHT = 400;
	private Rectangle rectangle; 
	private VBox myVBox;
	private ButtonCreater startSimulationButton;
	private ButtonCreater stepSimulationButton;
	private ButtonCreater stopSimulationButton;
	private ButtonCreater resetButton;
	private HBox myHBox;
	private Slider simulationSpeedSlider;
	private String mySimulationName;
	private int numCellsWidth;
	private int numCellsHeight;


	private GridPane myGrid;
	public CreateGrid(){
		startSimulationButton = new ButtonCreater("Start",null);
		stepSimulationButton = new ButtonCreater("Step",null);
		resetButton = new ButtonCreater("Reset",null);
		stopSimulationButton = new ButtonCreater("Stop",null);
		myGrid = new GridPane();
	}

	public Group createCellsList(Paint[][] myGridColor){

		myGrid.setGridLinesVisible(true);
		myGrid.setAlignment(Pos.CENTER);
		Group root = new Group();

		for ( int i = 0; i<=myGridColor[0].length-1;i++ ){
			for (int j=0;j<=myGridColor.length-1;j++){
				rectangle = new Rectangle(GRID_WIDTH/numCellsWidth,GRID_HEIGHT/numCellsHeight);
				rectangle.setFill(myGridColor[i][j]);
				myGrid.add(rectangle, i, j);
			}
		}		
		createVBox();
		myVBox.getChildren().add(myGrid);
		myVBox.getChildren().add(myHBox);
		root.getChildren().add(myVBox);
		return root;
	}

	public void createVBox(){
		myHBox = new HBox(30);
		myVBox = new VBox(50);
		myVBox.setPadding(new Insets(10));    
		Text title = new Text(mySimulationName);
		title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
		myVBox.getChildren().add(title);
		changeButtonSettings();


		myHBox.getChildren().addAll(stopSimulationButton.getButton(),startSimulationButton.getButton(),resetButton.getButton(),stepSimulationButton.getButton());
		addSlider();
	}

	private void changeButtonSettings(){
		stopSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.7*GUIController.SCENE_WIDTH,14);
		startSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.75*GUIController.SCENE_WIDTH,14);
		stepSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.8*GUIController.SCENE_WIDTH,14);
		resetButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.85*GUIController.SCENE_WIDTH,14);
	}	

	public void updateColor(int row, int col, Paint color){
		for (Node node : myGrid.getChildren()) {
			if (checkNodeIDNull(node)){
				if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
					((Rectangle)node).setFill(color);
				}
			}
		}
	}

	private boolean checkNodeIDNull(Node tempNode){
		return (GridPane.getColumnIndex(tempNode)!=null || GridPane.getRowIndex(tempNode)!= null);	
	}

	private void addSlider(){ 
		simulationSpeedSlider = new Slider(GUIController.MIN_FRAMES_PER_SECOND,GUIController.MAX_FRAMES_PER_SECOND,GUIController.DEFAULT_FRAMES_PER_SECOND);
		simulationSpeedSlider.setMajorTickUnit(10);
		Label speedCaption = new Label("Simulation Speed :");
		Label speedValue = new Label(Double.toString(simulationSpeedSlider.getValue()));

		simulationSpeedSlider.valueProperty().addListener(new ChangeListener(){
			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				speedValue.textProperty().setValue(
						String.valueOf((int) simulationSpeedSlider.getValue()));} });

		myHBox.getChildren().addAll(simulationSpeedSlider,speedCaption,speedValue);


	}	
	public ButtonCreater getResetButton() {
		return resetButton;
	}
	public ButtonCreater getStopSimulationButton() {
		return stopSimulationButton;
	}
	public ButtonCreater getStepSimulationButton() {
		return stepSimulationButton;
	}
	public ButtonCreater getStartSimulationButton() {
		return startSimulationButton;
	}

	public Slider getSimulationSpeedSlider() {
		return simulationSpeedSlider;
	}

	public void setNumCellsWidth(int numCells){
		this.numCellsWidth = numCells;
	}

	public void setNumCellsHeight(int numCells){
		this.numCellsHeight=numCells;
	}
	public void setSimulationName(String simulationName){
		this.mySimulationName = simulationName;
	}
}






