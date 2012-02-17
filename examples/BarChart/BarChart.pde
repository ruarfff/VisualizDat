import VisualizeDat.*;

VisualizeDat vd;

void setup()
{
   size(800, 600);
   background(255);
   smooth();
   vd = new VisualizeDat(this);
   //Visualize a har coded string		
   //vd.setData("oil,33,gold,44,silver,25,steel,18,copper,19");
   //Or import a file
   //vd.setData("Commodities.csv");
   //vd.setData("Commodities.txt");
   vd.setData("Commodities.xml");
   
   vd.makeBarChart(100, 500, 400, 200);
}

void draw()
{
  vd.drawBarChart();
}
