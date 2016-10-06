package gui;

import java.util.Map;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/** Purpose: Create a LineChart in the Scene for Different States 
 * @author pratiksha sharma
 *
 */

public class PopulationGraph {
    private Axis<Number> xAxis = new NumberAxis();
    private Axis<Number> yAxis = new NumberAxis();
    private LineChart<Number, Number> myPopulationChart;
    private final String EMPTY_STATE = "empty";
    private double myXAxisCounter;

    /**
     *  Creates LineChart Graph, does basic Settings
     * @param myStatePopulationMap
     */
    public void drawGraph(Map<String, Integer> myStatePopulationMap) {
	myPopulationChart = new LineChart<>(xAxis, yAxis);
	setGraphSettings();
	addPointsOnGraph(myStatePopulationMap, true);
    }
    
/**
 * Draws Line Chart with the values from the Map, Resets the Map when needed to 
 * @param myStatePopulationMap
 * @param resetLineGraph
 */
    public void addPointsOnGraph(Map<String, Integer> myStatePopulationMap, boolean resetLineGraph) {
	for (String key : myStatePopulationMap.keySet()) {
	    if (!key.toString().equals(EMPTY_STATE)) {
		if (resetLineGraph) {
		    myXAxisCounter = 0.5;
		    myPopulationChart.getData().remove(0, myPopulationChart.getData().size() - 1);
		    XYChart.Series<Number, Number> series = new XYChart.Series<>();
		    addData(series, myStatePopulationMap.get(key));
		    series.setName(key);
		    myPopulationChart.getData().add(series);
		} else {
		    for (int i = 0; i < myStatePopulationMap.size() - 1; i++) {
			XYChart.Series<Number, Number> series = myPopulationChart.getData().get(i);
			if (series.getName().equals(key)) {
			    addData(series, myStatePopulationMap.get(key));
			}
		    }
		}
	    }
	}
	myXAxisCounter += 1;
    }

    private void addData(XYChart.Series<Number, Number> series, Integer dataPoint) {
	series.getData().add(new XYChart.Data<>(myXAxisCounter, dataPoint));
    }
    
    /**
     * 
     * @return LineChart For the Scene
     */
    public LineChart<Number, Number> getMyStatePopulationChart() {
        return myPopulationChart;
    }
    
    private void setGraphSettings(){
        myPopulationChart.setTitle("Population of Different States");
        myPopulationChart.setStyle("-fx-border-style: solid; -fx-border-width: 4px; ");
        myPopulationChart.setPrefHeight(400);
        myPopulationChart.setPrefWidth(600);
        xAxis.setTickLabelsVisible(true);
        xAxis.setLabel("Time");
        yAxis.setLabel("Population");
    }
}
