package dissertation;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class FitnessEvaluationGraphic extends ProblemsGraphic {
	private float[] DrawEAKnapFitnessEvaluation = new float[xCoordNumbers];;
	private float[] DrawStandardKnapFitnessEvaluation = new float[xCoordNumbers];;
	private float[] DrawLambKnapFitnessEvaluation = new float[xCoordNumbers];;
	private String s;
	private String problemName;
	private FitnessFunctions fitnessFunctions;
	private int eachFitnessEvaluation;
	private int oneLambdaScale;
	public FitnessEvaluationGraphic(int NUMBER_EVALUATIONS, int input,float[] standard,float[] lambda,float[] one,FitnessFunctions fitnessFunctions,String problemName,int oneLambdaScale) {
		this.fitnessFunctions = fitnessFunctions;
		this.problemName = problemName;
		this.eachFitnessEvaluation = NUMBER_EVALUATIONS;
		this.oneLambdaScale = oneLambdaScale;
		xCoordNumbers = input / NUMBER_EVALUATIONS;
		DrawStandardKnapFitnessEvaluation = standard;
		DrawLambKnapFitnessEvaluation = lambda;
		DrawEAKnapFitnessEvaluation = one;
		xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordNumbers;
		yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / yCoordNumbers;
		setBackground(Color.white);
		calMaxMinValueFitnessEvaluation(maxYValue, minYValue,xCoordNumbers,xLength,yLength,DrawStandardKnapFitnessEvaluation,DrawLambKnapFitnessEvaluation,DrawEAKnapFitnessEvaluation);
		System.out.println("Max " + maxYValue + " Min " + minYValue);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		Graphics2D g2 = (Graphics2D) g;
//		// draw x-axis numbers
		drawCoordinateElement(eachFitnessEvaluation,g2,2);
		g2.drawString("Fitness Evaluations", (X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2) + 7,
				X_AXIS_Y_COORD + AXIS_STRING_DISTANCE+12);
//		g2.drawString("*10", (X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2) + 20,
//				X_AXIS_Y_COORD + AXIS_STRING_DISTANCE + 11);
		// draw y-axis numbers
		drawYNumber(g2);
		
		// standard GA on Knapsack
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLUE);
		drawLinesFitnessEvaluation(g2, DrawStandardKnapFitnessEvaluation,startPointValue,gapStartEndPointPosition);
		// (1+lambda)EA on Knapsak
		g2.setColor(Color.GREEN);
		drawLinesFitnessEvaluation(g2,  DrawEAKnapFitnessEvaluation,startPointValue,gapStartEndPointPosition);
		// Lambda GA on Knapsack
		g2.setColor(Color.ORANGE);
		drawLinesFitnessEvaluation(g2, DrawLambKnapFitnessEvaluation,startPointValue,gapStartEndPointPosition);
		drawFont(g2,problemName);
		
		g2.setColor(Color.GREEN);
		g2.drawLine(1050, 105, 1075, 105);
		g2.drawString("(1+λ)EA", 1090, 108);
		g2.setColor(Color.BLACK);
		g2.drawString("λ = "+oneLambdaScale, 1050, 130);
	}

	public void drawYNumber(Graphics2D g2){
		float gap = (maxYValue - minYValue) / (yCoordNumbers - 1);
		for (float i = minYValue, k = 1; i <= maxYValue + gap; i = i + gap, k++) {
			s = fitnessFunctions.range(i);
			g2.drawString(s, Y_AXIS_X_COORD - AXIS_STRING_DISTANCE - 14, Y_AXIS_SECOND_Y_COORD - (k * yLength));
			g2.draw(new Line2D.Float(Y_AXIS_X_COORD - AXIS_STRING_DISTANCE + 20, Y_AXIS_SECOND_Y_COORD - (k * yLength),
					Y_AXIS_X_COORD - AXIS_STRING_DISTANCE + 30, Y_AXIS_SECOND_Y_COORD - (k * yLength)));
			if (k == 1) {
				startPointPosition = Y_AXIS_SECOND_Y_COORD;
				startPointValue = Float.valueOf(s);
			}
			if (Float.valueOf(s) >= maxYValue) {
				endPointPosition = Y_AXIS_SECOND_Y_COORD - (k * yLength);
				endPointValue = Float.valueOf(s);
				gapStartEndPointPosition = (startPointPosition - endPointPosition) / (endPointValue - startPointValue);
				break;
			}
		}
	}

}
