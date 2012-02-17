import VisualizeDat.*;

VisualizeDat vd;

void setup()
{
  size(800,600);
  background(255);
  smooth();
  vd = new VisualizeDat(this);
  vd.setData("oil,33,gold,44,silver,25,steel,18,copper,19");
  vd.makeLineChart(100,300,400,400);
}

void draw()
{
  vd.drawLineChart();
}
