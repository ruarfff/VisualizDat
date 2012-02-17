package VisualizeDat.library.chart;

import VisualizeDat.library.DataTable;
import processing.core.PApplet;

public class PieChart extends Chart{

    private int diameter;

    public PieChart(PApplet processor,DataTable table,int xPos, int yPos, int diameter) {
        super(processor,table, xPos, yPos, diameter, diameter);
        this.diameter = diameter;
    }

    public PieChart(PApplet processor,DataTable table,int xPos, int yPos, int diameter, int[][] colours) {
        super(processor,table, xPos, yPos, diameter, diameter, colours);
        this.diameter = diameter;
    }

    @Override
    public void draw() {
        processor.noStroke();
        float newAngle = 0;
        float lastAngle = 0;
        int count = data.length;
        float[] angles = new float[count];
        float sum = 0;
        for(int i = 0; i < count; i++)
        {
             sum += table.getFloat(i,ValCol);
        }
        for(int i = 0; i < count; i++)
        {
            newAngle = (table.getFloat(i,ValCol)*100)/sum;
            angles[i] = (newAngle/100)*360;
        }
        for (int i = 0; i < count; i++){
            fillColours(i);
            processor.arc(xPos, yPos, diameter, diameter, lastAngle, lastAngle + processor.radians(angles[i]));
            lastAngle += processor.radians(angles[i]);
        }
    }
}
