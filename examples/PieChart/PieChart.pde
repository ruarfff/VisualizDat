import VisualizeDat.*;

VisualizeDat vd;

void setup()
{
  size(800,600);
  background(255);
  smooth();
  vd = new VisualizeDat(this);
  vd.setData("oil,33,gold,40,silver,25,steel,18,copper,19");
  vd.makePieChart(400,200,400);
  vd.makeBallChart(100,500,600,200);
}
void draw()
{
  vd.drawPieChart();
  vd.drawBallChart();
}
