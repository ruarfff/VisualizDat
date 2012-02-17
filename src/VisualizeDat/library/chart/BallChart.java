package VisualizeDat.library.chart;

import VisualizeDat.library.DataTable;
import processing.core.PApplet;

/**
 * User: Ruairi
 * Date: 04/04/11
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class BallChart extends Chart{
    int count = 0;
    float percentages[];
    int x = 0;
    int y = 0;

    public BallChart(PApplet processor, DataTable table, int xPos, int yPos, int chartWidth, int chartHeight) {
        super(processor, table, xPos, yPos, chartWidth, chartHeight);
        setUpChart();
    }
     public BallChart(PApplet processor, DataTable table, int xPos, int yPos, int chartWidth, int chartHeight, int[][] colours) {
        super(processor, table, xPos, yPos, chartWidth, chartHeight, colours);
         setUpChart();
     }

    private void setUpChart()
    {
        count = data.length;
        percentages = new float[count];
        float sum = 0;
        for(int i = 0; i < count; i++)
        {
             sum += table.getFloat(i, ValCol);
        }
        for(int i = 0; i < count; i++)
        {
            float percentage = (table.getFloat(i, ValCol)*100)/sum;
            percentages[i] = (percentage/100)*chartWidth;
        }
    }
    @Override
    public void draw() {

        processor.noStroke();
        x = xPos;
        y = yPos;
        float cushion = chartWidth*0.02f;
        for(int i = 0; i < count; i++)
        {
            fillColours(i);
            //draw balls
            processor.ellipse(x+(cushion/2),y,(percentages[i]-cushion), (percentages[i]-cushion));
            //draw tags
            processor.fill(0);
            processor.text(data[i][0], x,y);
            x+=percentages[i];
        }
    }
}
