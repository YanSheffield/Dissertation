package dissertation;

import java.awt.*;
import java.awt.geom.Line2D;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class GenerationProblemGraphic extends ProblemsGraphic {

	private float[] DrawaverageBestStandardGAKnapsack = new float[xCoordNumbers];
	private float[] DrawaveragelambdaKapsackArray = new float[xCoordNumbers];
	private float[] DrawaverageOneEAKnapsackArray = new float[xCoordNumbers];
	private FitnessFunctions fitnessFunctions;
	private String problemName;
	
	public GenerationProblemGraphic(int input,float[] standard,float[] lambda,float[] one,FitnessFunctions fitnessFunctions,String problemName) {
		this.fitnessFunctions = fitnessFunctions;
		this.problemName = problemName;
		xCoordNumbers = input;
		DrawaverageBestStandardGAKnapsack = standard;
		DrawaveragelambdaKapsackArray = lambda;
		DrawaverageOneEAKnapsackArray = one;
		xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordNumbers;
		yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / yCoordNumbers;
		setBackground(Color.white);
		calMaxMinValue(maxYValue, minYValue,xCoordNumbers,xLength,yLength,DrawaverageBestStandardGAKnapsack,DrawaveragelambdaKapsackArray,DrawaverageOneEAKnapsackArray);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		Graphics2D g2 = (Graphics2D) g;

		drawCoordinateElement(1,g2,1);
		g2.drawString("Generations", (X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2) + 20,
				X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
		// draw y-axis numbers
		drawYNumber(g2,fitnessFunctions);
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLUE);
		drawLinesFitnessEvaluation(g2, DrawaverageBestStandardGAKnapsack,startPointValue,gapStartEndPointPosition);
		g2.setStroke(new BasicStroke(1));

		g2.setColor(Color.ORANGE);
		drawLinesFitnessEvaluation(g2, DrawaveragelambdaKapsackArray,startPointValue,gapStartEndPointPosition);

		g2.setColor(Color.GREEN);
		drawLinesFitnessEvaluation(g2, DrawaverageOneEAKnapsackArray,startPointValue,gapStartEndPointPosition);
		
		drawFont(g2,problemName);
		
		g2.setColor(Color.GREEN);
		g2.drawLine(1050, 105, 1075, 105);
		g2.drawString("(1+1)EA", 1090, 108);
	}
	
	public void drawYNumber(Graphics2D g2,FitnessFunctions fitnessFunctions){
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
				// System.out.println("gap "+gapStartEndPointPosition);
				break;
			}
		}
	}
}
