/**
 * VisualizeDat is a small light library for visualising data sets from different formats.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		Ruairi O'Brien
 * @modified	##date##
 * @version		0.1
 */

package VisualizeDat.library;

import VisualizeDat.library.chart.BallChart;
import VisualizeDat.library.chart.BarChart;
import VisualizeDat.library.chart.LineChart;
import VisualizeDat.library.chart.PieChart;
import processing.core.PApplet;

import java.util.Random;

/**
 *  
 */
public class VisualizeDat {

	// myParent is a reference to the parent sketch
	private static PApplet myParent;

	Random rand = new Random();

    DataTable table;

    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;
    private BallChart ballChart;

    private gSettings settings;

	public final static String VERSION = "##version##";

	public VisualizeDat(PApplet theParent) {
		myParent = theParent;
        table = new DataTable();
        settings = new gSettings();
	}


	/**
	 * This method accepts data as a supported file or as a string. If a string
	 * of data is being passed it must have data separated by commas in csv
	 * fashion. The first half of the string should be headings and the second
	 * half values OR all values. File types accepted are ".CSV", ".TXT" in csv
	 * format i.e. data separated by commas. Spaces are ignored, ".XML".
	 * The file should contain one row of headings and one row of
	 * values, or just one row of values.
	 * 
	 * @param data
	 *            Enter a file name ending with .TXT, .CSV, .XML...or...a
	 *            structured string of data.
	 */
	public void setData(String data) {
		            table = new DataTable(data, myParent);
	}

    public String[][] getData()
    {
        return table.getData();
    }

    public void makePieChart(int xPos, int yPos, int diameter)
    {
         pieChart = new PieChart(myParent, table, xPos,yPos,diameter);
    }

    public void makePieChart(int xPos, int yPos, int diameter, int[][] colours)
    {
         pieChart = new PieChart(myParent, table, xPos,yPos,diameter, colours);
    }


    public void makeBarChart(int xPos, int yPos, int chartWidth, int chartHeight)
    {
        barChart = new BarChart(myParent, table, xPos, yPos, chartWidth, chartHeight);
    }

     public void makeBarChart(int xPos, int yPos, int chartWidth, int chartHeight, int[][] colours)
    {
        barChart = new BarChart(myParent, table, xPos, yPos, chartWidth, chartHeight, colours);
    }

    public void makeLineChart(int xPos, int yPos, int chartWidth, int chartHeight)
    {
        lineChart = new LineChart(myParent, table, xPos,yPos,chartWidth, chartHeight);
    }

    public void makeLineChart(int xPos, int yPos, int chartWidth, int chartHeight, int[][] colours)
    {
        lineChart = new LineChart(myParent, table, xPos,yPos,chartWidth, chartHeight, colours);
    }

    public void makeBallChart(int xPos, int yPos, int chartWidth, int chartHeight)
    {
        ballChart = new BallChart(myParent, table, xPos, yPos, chartWidth, chartHeight);
    }

    public void makeBallChart(int xPos, int yPos, int chartWidth, int chartHeight, int[][] colours)
    {
        ballChart = new BallChart(myParent, table, xPos, yPos, chartWidth, chartHeight, colours);
    }

    public void drawPieChart()
    {
        settings.pushSettings();
        pieChart.draw();
        settings.popSettings();
    }

    public void drawBarChart()
    {
        settings.pushSettings();
        barChart.draw();
        settings.popSettings();
    }

    public void drawLineChart()
    {
        settings.pushSettings();
        lineChart.draw();
        settings.popSettings();
    }

    public void drawBallChart()
    {
        settings.pushSettings();
        ballChart.draw();
        settings.popSettings();
    }

	/**
	 * return the version of the library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}


	public void dispose() {

	}

         private class gSettings
        {
          public boolean smooth;

          public int rectMode, ellipseMode;

          public int textAlign;
          public float textSize;

          public boolean tint;
          public int tintColor;
          public boolean fill;
          public int fillColor;
          public boolean stroke;
          public int strokeColor;
          public float strokeWeight;

          public gSettings () {
            pushSettings();
          }

          public void pushSettings()
          {
            smooth = myParent.g.smooth;

            rectMode = myParent.g.rectMode;
            ellipseMode = myParent.g.ellipseMode;

            textAlign = myParent.g.textAlign;
            textSize = myParent.g.textSize;

            tint = myParent.g.tint;
            fill = myParent.g.fill;
            stroke = myParent.g.stroke;
            tintColor = myParent.g.tintColor;
            fillColor = myParent.g.fillColor;
            strokeColor = myParent.g.strokeColor;
            strokeWeight = myParent.g.strokeWeight;
          }

          public void popSettings()
          {
            if(smooth) myParent.smooth();
            else myParent.noSmooth();

            myParent.rectMode(rectMode);
            myParent.ellipseMode(ellipseMode);

            myParent.textAlign(textAlign);
            myParent.textSize(textSize);

            if(tint) myParent.tint(tintColor);
            else myParent.noTint();

            if(fill) myParent.fill(fillColor);
            else myParent.noFill();

            if(stroke) myParent.stroke(strokeColor);
            else myParent.noStroke();

            myParent.strokeWeight(strokeWeight);
          }
        }
}

