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
	private int myXAxisCounter =1;


	public void createLineChart( Map<String,Integer> myStatePopulationMap){
		myPopulationChart = new LineChart<Number,Number>(xAxis, yAxis);
		myPopulationChart.setTitle("Population of Different States");
		myPopulationChart.setPrefHeight(300);
		myPopulationChart.setPrefWidth(300);
		xAxis.setTickLabelsVisible(false);
		drawLineGraph(myStatePopulationMap,true);
	}

	public LineChart<Number, Number> getMyStatePopulationChart(){
		return this.myPopulationChart;	
	}

	public void drawLineGraph(Map<String,Integer> myStatePopulationMap, boolean firstTime){
		for(String key:myStatePopulationMap.keySet()){
			if(!key.toString().equals(EMPTY_STATE)){
				if(firstTime){
					XYChart.Series<Number,Number> series = new XYChart.Series<>();
					addData(series,myStatePopulationMap.get(key));
					series.setName(key);
					myPopulationChart.getData().add(series);
				}else{
					for (int i =0; i<myStatePopulationMap.size()-1;i++){
						XYChart.Series<Number,Number> series = myPopulationChart.getData().get(i);
						if(series.getName().equals(key)){
						addData(series,myStatePopulationMap.get(key));
						}
					}
				}
			}
		}
	}

	private void addData(XYChart.Series<Number,Number> series, Integer dataPoint){
		series.getData().add(new XYChart.Data<>(myXAxisCounter,dataPoint));
		myXAxisCounter+=1;
	}
}
