package dissertation;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class ProblemsGraphic extends JPanel {
	protected float maxYValue;
	protected float minYValue;
	protected int xCoordNumbers;
	protected int ONE_GAP_DISTANCE = 26;
	protected float CORRENT = 0.95f;
	protected int yCoordNumbers = 21;
	protected String s;
	protected  int tempPoint = 0;
	protected float startPointPosition;
	protected float startPointValue;
	protected float endPointPosition;
	protected float endPointValue;
	protected float gapStartEndPointPosition;
	protected int xLength;
	protected int yLength;
	
//	protected static final int NUMBER_EVALUATIONS = 40;

	protected static final int X_AXIS_FIRST_X_COORD = 50;
	protected static final int X_AXIS_SECOND_X_COORD = 1080;
	protected static final int X_AXIS_Y_COORD = 600;

	// y-axis coord constants
	protected static final int Y_AXIS_FIRST_Y_COORD = 50;
	protected static final int Y_AXIS_SECOND_Y_COORD = 600;
	protected static final int Y_AXIS_X_COORD = 50;

	// arrows of axis are represented with "hipotenuse" of
	// triangle
	// now we are define length of cathetas of that triangle
	protected static final int FIRST_LENGHT = 10;
	protected static final int SECOND_LENGHT = 5;

	// size of start coordinate lenght
	protected static final int ORIGIN_COORDINATE_LENGHT = 9;

	// distance of coordinate strings from axis
	protected static final int AXIS_STRING_DISTANCE = 20;
	private int eachEvaluation;

	public void calMaxMinValue(float max, float min, int xCoordNumbers, int xlength, int ylength, float[] standard,
			float[] lambda, float[] one) {
		this.xCoordNumbers = xCoordNumbers;
		this.xLength = xlength;
		this.yLength = ylength;
		Float[] temp = { standard[0], standard[xCoordNumbers - 1], lambda[0], lambda[xCoordNumbers - 1], one[0],
				one[xCoordNumbers - 1] };
		max = (float) Collections.max(Arrays.asList(temp));
		min = (float) Collections.min(Arrays.asList(temp));
		maxYValue = max;
		minYValue = min;
		System.out.println("max " + maxYValue + "Min " + minYValue);
	}

	// calculate the max and min value
	public void calMaxMinValueFitnessEvaluation(float max, float min, int xCoordNumbers, int xlength, int ylength,
			float[] standard, float[] lambda, float[] one) {
		this.xCoordNumbers = xCoordNumbers;
		this.xLength = xlength;
		this.yLength = ylength;
		Float[] temp = { one[0], one[xCoordNumbers], standard[0], standard[xCoordNumbers], lambda[0],
				lambda[xCoordNumbers] };
		max = (float) Collections.max(Arrays.asList(temp));
		min = (float) Collections.min(Arrays.asList(temp));
		maxYValue = max;
		minYValue = min;
	}

	public void drawCoordinateElement(int each,Graphics2D g2, int r) {
		eachEvaluation = each;
		// improve the quality of image
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2));
		// x-axis
		g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
		// y-axis
		g2.drawLine(Y_AXIS_X_COORD, 20, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

		// y-axis arrow
		g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, 20 + FIRST_LENGHT, Y_AXIS_X_COORD, 20);
		g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, 20 + FIRST_LENGHT, Y_AXIS_X_COORD, 20);

		// draw origin Point
		g2.fillOval(X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
				Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2), ORIGIN_COORDINATE_LENGHT,
				ORIGIN_COORDINATE_LENGHT);

		// draw text "X" and draw text "Y"
		g2.drawString("Fitness value", (Y_AXIS_X_COORD - AXIS_STRING_DISTANCE) + 10, 10 + AXIS_STRING_DISTANCE / 2);

		// draw x-axis numbers
		drawXAxisNum(g2, xCoordNumbers, r);
		g2.drawString(Integer.toString(0), Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, Y_AXIS_SECOND_Y_COORD);
	}

	public void drawXAxisNum(Graphics2D g2, int xCoordNumber, int r) {
		if (xCoordNumber >= 1 && xCoordNumber <= 25) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 1);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 1);
			}
			
		}
		if (xCoordNumber > 25 && xCoordNumber <= 50) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 2);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 2);
			}
			
		}
		if (xCoordNumber > 50 && xCoordNumber <= 100) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 3);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 3);
			}
			
		}
		if (xCoordNumber > 100 && xCoordNumber <= 150) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 5);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 5);
			}
			
		}
		if (xCoordNumber > 150 && xCoordNumber <= 250) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 10);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 10);
			}
			
		}
		if (xCoordNumber > 250 && xCoordNumber <= 350) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 15);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 15);
			}
			
		}
		if (xCoordNumber > 350 && xCoordNumber <= 500) {
			if (r == 1) {
				callDrawXNumber(g2, xCoordNumber, 20);
			} else {
				callDrawXNumberFitnessEvaluation(g2, xCoordNumber, 20);
			}
			
		}
	}

	public void callDrawXNumber(Graphics2D g2, int xCoordNumber, int u) {
		for (int i = 0; i <= xCoordNumber; i = i + u) {// 每隔20画一个，每次的差值20*2，2是差值
			if (xCoordNumber - i >= u) {
				g2.drawString(Integer.toString(i), X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
						X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
				g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT,
						X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
			} else {
				g2.drawString(Integer.toString(xCoordNumber),
						X_AXIS_FIRST_X_COORD + (i * xLength) - 3 + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
				g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength) + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD - SECOND_LENGHT,
						X_AXIS_FIRST_X_COORD + (i * xLength) + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD + SECOND_LENGHT);
			}
		}
	}

	public void callDrawXNumberFitnessEvaluation(Graphics2D g2, int xCoordNumber, int u) {
		for (int i = 0; i <= xCoordNumber; i = i + u) {// 每隔20画一个，每次的差值20*2，2是差值
			if (xCoordNumber - i >= u) {
				g2.drawString(Integer.toString((i * eachEvaluation)), X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
						X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
				g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD - SECOND_LENGHT,
						X_AXIS_FIRST_X_COORD + (i * xLength), X_AXIS_Y_COORD + SECOND_LENGHT);
			} else {
				g2.drawString(Integer.toString((xCoordNumber * eachEvaluation)),
						X_AXIS_FIRST_X_COORD + (i * xLength) - 3 + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
				g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength) + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD - SECOND_LENGHT,
						X_AXIS_FIRST_X_COORD + (i * xLength) + (xLength * (xCoordNumber % u)),
						X_AXIS_Y_COORD + SECOND_LENGHT);
			}
		}
	}
	public void drawLinesFitnessEvaluation(Graphics2D g2, float[] DrawGeneralLines, float startPointValue,
			float gapStartEndPointPosition) {
		for (int j = 0; j < xCoordNumbers; j++) {	
			this.startPointValue = startPointValue;
			this.gapStartEndPointPosition = gapStartEndPointPosition;
			if (DrawGeneralLines[j] == minYValue) {
				g2.draw(new Line2D.Float(X_AXIS_FIRST_X_COORD + xLength * j, 574, X_AXIS_FIRST_X_COORD + xLength * (j), 574));
				tempPoint = tempPoint + 1;
			}
			if (tempPoint == 0) {
				g2.draw(new Line2D.Float(X_AXIS_FIRST_X_COORD + xLength * j,
						Y_AXIS_SECOND_Y_COORD
								- ((DrawGeneralLines[j] - startPointValue) * gapStartEndPointPosition),
						X_AXIS_FIRST_X_COORD + xLength * (j + 1),
						Y_AXIS_SECOND_Y_COORD - ((DrawGeneralLines[j + 1] - startPointValue)
								* gapStartEndPointPosition)));
			} else {
				g2.draw(new Line2D.Float(X_AXIS_FIRST_X_COORD + xLength * j,
						-ONE_GAP_DISTANCE + Y_AXIS_SECOND_Y_COORD
								- ((DrawGeneralLines[j] - startPointValue) * gapStartEndPointPosition*CORRENT),
						X_AXIS_FIRST_X_COORD + xLength * (j + 1),
						-ONE_GAP_DISTANCE + Y_AXIS_SECOND_Y_COORD - ((DrawGeneralLines[j + 1] - startPointValue)
								* gapStartEndPointPosition*CORRENT)));
			}
		}
	}
	public void drawFont(Graphics2D g2,String title) {
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2.setColor(Color.black);
		g2.drawString(title, 500, 25);
		g2.setStroke(new BasicStroke(5));
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g2.setColor(Color.BLUE);
		g2.drawLine(1050, 25, 1075, 25);
		g2.drawString("Standad GA", 1090, 28);
		g2.setColor(Color.ORANGE);
		g2.drawLine(1050, 65, 1075, 65);
		g2.drawString("(1+λ)λ GA", 1090, 68);
//		g2.setColor(Color.GREEN);
//		g2.drawLine(1050, 105, 1075, 105);
//		g2.drawString("(1+λ)EA", 1090, 108);
	}
}
