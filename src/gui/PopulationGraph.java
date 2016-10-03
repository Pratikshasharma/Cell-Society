package gui;

import java.util.Map;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PopulationGraph {
	private Axis<Number> xAxis = new NumberAxis();
	private Axis<Number> yAxis = new NumberAxis();
	private LineChart<Number, Number> myPopulationChart;
	private final String EMPTY_STATE = "empty";
	private double myXAxisCounter ;


	public void createLineChart( Map<String,Integer> myStatePopulationMap, int numCellsWidth){
		myPopulationChart = new LineChart<Number,Number>(xAxis, yAxis);
		myPopulationChart.setTitle("Population of Different States");
		myPopulationChart.setPrefHeight(400);
		myPopulationChart.setPrefWidth(600);
		xAxis.setTickLabelsVisible(true);
		xAxis.setLabel("Time");
		yAxis.setLabel("Population");
		drawLineGraph(myStatePopulationMap,true);
	}

	public LineChart<Number, Number> getMyStatePopulationChart(){
		return this.myPopulationChart;	
	}

	public void drawLineGraph(Map<String,Integer> myStatePopulationMap, boolean resetLineGraph){
		for(String key:myStatePopulationMap.keySet()){
			if(!key.toString().equals(EMPTY_STATE)){
				if(resetLineGraph){
					myXAxisCounter = 0.5;
					myPopulationChart.getData().remove(0, myPopulationChart.getData().size()-1);
					XYChart.Series<Number,Number> series = new XYChart.Series<>();
					addData(series,myStatePopulationMap.get(key));
					series.setName(key);
					myPopulationChart.getData().add(series);
				}else{
					for (int i = 0; i<myStatePopulationMap.size()-1;i++){
						XYChart.Series<Number,Number> series = myPopulationChart.getData().get(i);
						if(series.getName().equals(key)){
							addData(series,myStatePopulationMap.get(key));
						}
					}
				}
			}
		}
		myXAxisCounter+=1;
	}
	private void addData(XYChart.Series<Number,Number> series, Integer dataPoint){
		series.getData().add(new XYChart.Data<>(myXAxisCounter,dataPoint));
	}
}
