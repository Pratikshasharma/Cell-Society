package GUIPackage;
import CellPackage.Cell;
import CellPackage.State;
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

public class CreateGrid {
	State myCellState;
	private int CELL_WIDTH = 20;
	private int CELL_HEIGHT = 20;
	private Rectangle rectangle; 
	private VBox myVBox;
	private ButtonCreater startSimulationButton;
	private ButtonCreater stepSimulationButton;
	private ButtonCreater stopSimulationButton;
	private ButtonCreater resetButton;
	private HBox myHBox;
	private Slider simulationSpeedSlider;
	

	private GridPane myGrid;

	public CreateGrid(){
		startSimulationButton = new ButtonCreater("Start",null);
		stepSimulationButton = new ButtonCreater("Step",null);
		resetButton = new ButtonCreater("Reset",null);
		stopSimulationButton = new ButtonCreater("Stop",event -> GUIController.exitGame());
		myHBox = new HBox(30);
		myVBox = new VBox(50);
		myGrid = new GridPane();
	}
	public Group createCellsList(){
		State myCellState0 = new State("unhappy", Paint.valueOf("RED"));
		myGrid.setGridLinesVisible(true);
		myGrid.setAlignment(Pos.CENTER);
		Group root = new Group();
		for ( int i = 0; i<=20;i++ ){
			for (int j=0;j<=20;j++){
				rectangle = new Rectangle(CELL_WIDTH,CELL_HEIGHT);
				//rectangle.setStroke(Paint.valueOf("BLACK"));
				Cell myCell = new Cell(rectangle,myCellState0);
				rectangle.setFill(myCell.getCellCurrentState().getStateColor());
				//myCell.getShape().setId(Integer.toString(i));
				myGrid.add(myCell.getShape(), i, j);  
			}
		}

		createVBox();
		myVBox.getChildren().add(myGrid);
		myVBox.getChildren().add(myHBox);
		root.getChildren().add(myVBox);
		//updateCells(2,5);
		return root;
	}

	public void createVBox(){
		myVBox.setPadding(new Insets(10));    
		Text title = new Text("SIMULATION PROFILE NAME");
		title.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
		myVBox.getChildren().add(title);
		changeButtonSettings();

		// Add buttons the HBox
		myHBox.getChildren().add(stopSimulationButton.getButton());
		myHBox.getChildren().add(startSimulationButton.getButton());
		myHBox.getChildren().add(resetButton.getButton());
		myHBox.getChildren().add(stepSimulationButton.getButton());	
		
		addSlider();
	}

	private void changeButtonSettings(){
		stopSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.7*GUIController.SCENE_WIDTH,14);
		startSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.75*GUIController.SCENE_WIDTH,14);
		stepSimulationButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.8*GUIController.SCENE_WIDTH,14);
		resetButton.setButtonSettings(0.8*GUIController.SCENE_HEIGHT,0.85*GUIController.SCENE_WIDTH,14);
	}	

	public Group updateCells(){
		updateColor(2,5);
		System.out.println("EHYY " );
		
		return null;
		//return root;
	}

	private void updateColor(int row, int col){
			for (Node node : myGrid.getChildren()) {
				if (checkNodeIDNull(node)){
					if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
						System.out.println("PRESENT " + myGrid.getChildren().contains(node));
						((Rectangle)node).setFill(Paint.valueOf("BLACK"));
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
	    
	    myHBox.getChildren().add(simulationSpeedSlider);
	    myHBox.getChildren().add(speedCaption);
	    myHBox.getChildren().add(speedValue);
	    	
	}

}






