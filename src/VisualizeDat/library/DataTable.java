package VisualizeDat.library;

import processing.core.PApplet;
import processing.xml.XMLElement;

/**
 * User: Ruairi
 * Date: 01/04/11
 * Time: 23:10.
 */
public class DataTable {

    private static PApplet _processor;
    String[][] _data;
    int rowCount = 0;

      public DataTable() {
        _data = new String[10][10];
      }

      public DataTable(String data, PApplet processor) {
          _processor = processor;

     try {
			if (data.endsWith(".txt") || data.endsWith(".csv")) {
                processDataFile(data);
			} else if (data.endsWith(".xml")) {
				loadXML(data);
			} else{
                processStringData(data);
			}
		} catch (Exception e) {
           _processor.println("Error processing data: "+data);
		}
	}

    private void processDataFile(String filename)
    {
        String[] lines = _processor.loadStrings(filename);
        String rawData = "";
        if(lines.length > 1){
            for(int i = 0; i < lines.length; i++)
            {
                rawData += lines[i];
            }
        }
        else
        {
            rawData = lines[0];
        }
        processStringData(rawData);
    }

    private void processStringData(String data)
    {
        String[] rows = PApplet.split(data,',');
         _data = new String[rows.length/2][2];
        int rowIndex = 0;
        for(int i = 0; i < _data.length; i++)
        {
            if (_processor.trim(rows[rowIndex]).length() == 0) {
            continue; // skip empty rows
          }
          _data[i][0] = rows[rowIndex];
            rowIndex++;
            if(_processor.trim(rows[rowIndex]).length() == 0) {
            //No value provided so set to zero by default
                rows[rowIndex] = "0";
          }
          _data[i][1] = rows[rowIndex];
            rowIndex++;
        }
    }

	private void loadXML(String filename) {
		XMLElement xml = new XMLElement(_processor, filename);
		int cols = xml.getChildCount();
        _data = new String[cols][2];
		for (int i = 0; i < cols; i++) {
			XMLElement item = xml.getChild(i);
			_data[i][0] = item.getAttribute("name");
			_data[i][1] = item.getAttribute("value");

		}

	}

     public int getRowCount() {
        return rowCount;
      }


      // find a row by its name, returns -1 if no row found
     public int getRowIndex(String name) {
        for (int i = 0; i < rowCount; i++) {
          if (_data[i][0].equals(name)) {
            return i;
          }
        }
        _processor.println("No row named '" + name + "' was found");
        return -1;
      }

    public String[][] getData()
    {
        return _data;
    }

      public String getRowName(int row) {
        return getString(row, 0);
      }


      public String getString(int rowIndex, int column) {
        return _data[rowIndex][column];
      }


      public String getString(String rowName, int column) {
        return getString(getRowIndex(rowName), column);
      }


      public int getInt(String rowName, int column) {
        return _processor.parseInt(getString(rowName, column));
      }


      public int getInt(int rowIndex, int column) {
        return _processor.parseInt(getString(rowIndex, column));
      }


      public float getFloat(String rowName, int column) {
        return _processor.parseFloat(getString(rowName, column));
      }


      public float getFloat(int rowIndex, int column) {
        return _processor.parseFloat(getString(rowIndex, column));
      }


      public void setRowName(int row, String what) {
        _data[row][0] = what;
      }


      public void setString(int rowIndex, int column, String what) {
        _data[rowIndex][column] = what;
      }

      public void setString(String rowName, int column, String what) {
        int rowIndex = getRowIndex(rowName);
        _data[rowIndex][column] = what;
      }


      public void setInt(int rowIndex, int column, int what) {
        _data[rowIndex][column] = _processor.str(what);
      }


      public void setInt(String rowName, int column, int what) {
        int rowIndex = getRowIndex(rowName);
        _data[rowIndex][column] = _processor.str(what);
      }


      public void setFloat(int rowIndex, int column, float what) {
        _data[rowIndex][column] = _processor.str(what);
      }


      public void setFloat(String rowName, int column, float what) {
        int rowIndex = getRowIndex(rowName);
        _data[rowIndex][column] = _processor.str(what);
      }


    public float getMax()
    {
    	int n = _data.length;
    	String[][] holder = _data;
        for (int pass=1; pass < n; pass++) {  // count how many times
            for (int i=0; i < n-pass; i++) {
                if (getFloat(i,1) > getFloat(i+1,1)) {
                    // exchange elements
                    String[] temp = holder[i];  holder[i] = holder[i+1];  holder[i+1] = temp;
                }
            }
        }
        return  _processor.parseFloat(holder[_data.length-1][1]);
    }

}
