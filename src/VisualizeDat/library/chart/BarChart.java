package VisualizeDat.library.chart;

import VisualizeDat.library.DataTable;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * User: Ruairi
 * Date: 02/04/11
 * Time: 01:39
 */
public class BarChart extends Chart {

    public BarChart(PApplet processor,DataTable table, int xPos, int yPos, int chartWidth, int chartHeight) {
        super(processor,table, xPos, yPos, chartWidth, chartHeight);
    }

    public BarChart(PApplet processor,DataTable table, int xPos, int yPos, int chartWidth, int chartHeight, int[][] colours) {
        super(processor,table, xPos, yPos, chartWidth, chartHeight, colours);
    }


    @Override
    public void draw() {

		float yMax;
		float yMin = 0;
        processor.stroke(0);
        processor.strokeWeight(4);
	   //draw the axis lines.
	   processor.line(xPos-3,yPos+2,xPos+chartWidth,yPos+2);
	   processor.line(xPos-3,yPos+2,xPos-3,yPos-chartWidth);

	   //turn off stroke
	   processor.noStroke();

	   //loop the chart drawing once.
	   for (int c1 = 0; c1 < 1; c1++){
	   //Set the start x point value.
	   int start = xPos;
	   //Set the maximum height.
		yMax = table.getMax() + 10;

		//Count the number of values in the array
		int count = data.length;

		     //Draw the minimum and maximum Y Axis labels.
		     processor.fill (0);
		     processor.textAlign(PConstants.RIGHT, PConstants.CENTER);
		     processor.text((int)yMax-10, xPos-8, (yPos-chartHeight)+10);
		     processor.text((int)yMin, xPos-8, yPos);
		     //Draw each column in the data series.
		     for (int i = 0; i < count; i++) {
		       //Get the column value and set it as the height.
		       float yHeight = table.getFloat(i, ValCol);
		       //Declare the variables to hold column height as scaled to the y axis.
		       float yPercent;
		       float yScaleHeight;
		       //calculate the scale of given the height of the chart.
		       yPercent = yHeight / yMax;
		       //Calculate the scale height of the column given the height of the chart.
		       yScaleHeight = chartHeight * yPercent;

		       //If the column height exceeds the chart height than truncate it to the max value possible.
		       if(yScaleHeight > chartHeight){
		    	   yScaleHeight = chartHeight;
		       }

		       //Determine the width of the column placeholders on the X axis.
		       float columns = chartWidth / count;

		       //Set the width of the columns to 5 pixels less than the column placeholders.
		       float xWidth = columns - 5;

               fillColours(i);
		       //Draw the columns to scale.
		       processor.quad(xPos, yPos, xPos, yPos-yScaleHeight, xPos + xWidth, yPos-yScaleHeight, xPos + xWidth, yPos);

		       //Draw the labels.
		       processor.textAlign(PConstants.CENTER, PConstants.CENTER);
		       processor.fill (0);

		       processor.text(data[i][DatCol], xPos + (xWidth / 2), yPos + 8);

		       //increment the x point at which to draw a column.
		       xPos = xPos + (int)columns;
		       }
		     //Reset the draw point the original X value to prevent infinite redrawing to the right of the chart.
		     xPos = start;
	   }

	}
}
