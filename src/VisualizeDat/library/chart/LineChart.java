package VisualizeDat.library.chart;

import VisualizeDat.library.DataTable;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * User: Ruairi
 * Date: 02/04/11
 * Time: 01:40
 */
public class LineChart extends Chart{

    public LineChart(PApplet processor,DataTable table,int xPos, int yPos, int chartHeight, int chartWidth) {
        super(processor,table, xPos, yPos, chartWidth, chartHeight);
    }

    public LineChart(PApplet processor,DataTable table,int xPos, int yPos, int chartHeight, int chartWidth, int[][] colours) {
        super(processor,table, xPos, yPos, chartWidth, chartHeight, colours);
    }

    @Override
    public void draw() {
        float pointSeparation = chartWidth / data.length;
        float nextXPoint = 0;
		float lastXPoint = xPos;
		float lastYPoint = yPos;
        float yMax;
		float yMin = 0;
        yMax = table.getMax() + 10;
        int count = data.length;
        processor.stroke(0);
        processor.strokeWeight(4);
	    //draw the axis lines.
	    processor.line(xPos-3,yPos+2,xPos+chartWidth,yPos+2);
	    processor.line(xPos-3,yPos+2,xPos-3,yPos-chartWidth);

        //Draw the minimum and maximum Y Axis labels.
        processor.fill (0);
        processor.textAlign(PConstants.RIGHT, PConstants.CENTER);
        processor.text((int)yMax-10, xPos-8, (yPos-chartHeight)+10);
        processor.text((int)yMin, xPos-8, yPos);

	   for(int i = 0; i < count; i++)
	   {
	       float yheight = table.getFloat(i,ValCol);
	       float yPercent;
	       float yScaleHeight;
	       float ymax = table.getMax() + 10;
	       //calculate the scale of given the height of the chart.
	       yPercent = yheight / ymax;
	       //Calculate the scale height of the point given the height of the chart.
	       yScaleHeight = chartHeight * yPercent;

	       //If the point height exceeds the chart height than truncate it to the max value possible.
	       if(yScaleHeight > chartHeight){
	    	   yScaleHeight = chartHeight;
	       }
           //Determine the width of the column placeholders on the X axis.
           float columns = chartWidth / count;
           //Set the width of the columns to 5 pixels less than the column placeholders.
		   float xWidth = columns - 5;
           processor.stroke(RGB_Colours[i][0], RGB_Colours[i][1], RGB_Colours[i][2]);
		   processor.line(lastXPoint, lastYPoint, lastXPoint+pointSeparation, yPos-yScaleHeight);
		   processor.text(data[i][DatCol], lastXPoint+pointSeparation*0.5f, yPos + 8);
           lastYPoint = yPos-yScaleHeight;
		   lastXPoint += pointSeparation;

           //Draw the labels.
           processor.textAlign(PConstants.CENTER, PConstants.CENTER);
           processor.fill (0);

	   }
    }
}
