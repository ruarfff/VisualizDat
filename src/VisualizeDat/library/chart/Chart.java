package VisualizeDat.library.chart;

import VisualizeDat.library.DataTable;
import processing.core.PApplet;

public abstract class Chart {

    protected PApplet processor;
    protected DataTable table;
    protected int xPos;
    protected int yPos;
    int chartWidth;
    int chartHeight;
    protected final int DatCol = 0;
    protected final int ValCol = 1;
    protected String[][] data;
    protected static int RGB_Colours[][];
    //Creating a 2D array of different colour values for use in the data visualization
    //Hope to improve on this by allowing users to add their own colours
    private void setColours()
    {
        RGB_Colours = new int[][]{
                {0,192,0},//green
                {0,0,255},//blue
                {255,0,0},//red
                {255,255,0},//yellow
                {107,142,35},//olive drab
                {255,153,51},//orange
                {153,0,51},//purple
                {0,153,102},//green+blue
                {204,153,255},//pink
                {153,255,51},
                {204,255,153},
                {153,102,153},
                {139,69,19},//saddle brown
                {139,134,130},//seashell
                {139,000,000},//dark red
                {169,169,169},//dark grey
                {255,231,186},//tan
                {000,255,255}//cyan
        };
    }
    protected void fillColours(int i)
    {
        int x = i;
        if(x >= RGB_Colours.length)
        {
            x = 0;
        }
        else
        {
            processor.fill( RGB_Colours[x][0],RGB_Colours[x][1],RGB_Colours[x][2]);
        }

    }
    public Chart(PApplet processor, DataTable table, int xPos, int yPos, int chartWidth, int chartHeight)
    {
        this.processor = processor;
        this.table = table;
        data = table.getData();
        this.xPos = xPos;
        this.yPos = yPos;
        this.chartWidth = chartWidth;
        this.chartHeight = chartHeight;
        setColours();
    }


    public Chart(PApplet processor,DataTable table, int xPos, int yPos, int chartWidth, int chartHeight, int[][]colours)
    {
        this.processor = processor;
        this.table = table;
        data = table.getData();
        this.xPos = xPos;
        this.yPos = yPos;
        this.chartWidth = chartWidth;
        this.chartHeight = chartHeight;
        RGB_Colours = colours;
    }

    public abstract void draw();
}
