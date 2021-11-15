package se459.extremers.jarmoveout;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class FloorPlanExternal{

    int Width, Height;
    CleanSweepNode Reference, RowIterator, ColumnIterator;
    String path;

    public FloorPlanExternal(int width, int height, String Path) throws FileNotFoundException {
        this.Width = width;
        this.Height = height;
        this.path = Path;
        Reference = new CleanSweepNode();
        RowIterator = ColumnIterator = Reference;

        this.SetupFloorPlan();
    }

    public void SetupFloorPlan() throws FileNotFoundException {
        
        this.CreateLinkedList();
        this.PopulateNodesFromCSV();
    }

    // base of code taken from: https://villavu.com/forum/showthread.php?t=95319
    public void CreateLinkedList() {
        for (int I = 0; I < this.Height; ++I) {
            for (int J = 0; J < this.Width; ++J) {
				// if we are on first row
                if (I == 0) {
                    if (J < this.Width - 1) {
                        RowIterator.eastNode = new CleanSweepNode();
                        RowIterator.eastNode.westNode = RowIterator;
                        RowIterator = RowIterator.eastNode;
                    }
                }
                else {  
                    if (J < this.Width - 1) {
                        RowIterator.eastNode = new CleanSweepNode();
                        RowIterator.northNode.southNode = RowIterator;
                        RowIterator.eastNode.westNode = RowIterator;
                        RowIterator.eastNode.northNode = RowIterator.northNode.eastNode;
                        RowIterator = RowIterator.eastNode;
                    }
					else {
						RowIterator.northNode.southNode = RowIterator;
					}
                }
            }
           
            if (I < Height - 1) {
                ColumnIterator.southNode = new CleanSweepNode();
				ColumnIterator.southNode.northNode = ColumnIterator;
				ColumnIterator = ColumnIterator.southNode;
				RowIterator = ColumnIterator;
            }
        }
    }

    private void PopulateNodesFromCSV() throws FileNotFoundException {
        int currCol = 0;
        int currRow = 0;
        
        Scanner scanner = new Scanner(new File(this.path));
        while(scanner.hasNext()){
            String[] tokens = scanner.nextLine().split(",");
            String id = tokens[0];
            //SENSOR ARRAY VALUES
            edgeType n_sensor= edgeType.getEnumValue(tokens[1]); //path info top of the cell
            edgeType s_sensor=  edgeType.getEnumValue(tokens[2]); //path info bottom of the cell
            edgeType e_sensor=  edgeType.getEnumValue(tokens[3]); //path info right of the cell
            edgeType w_sensor=  edgeType.getEnumValue(tokens[4]); //path info left of the cell
            boolean bottom_sensor= Boolean.parseBoolean(tokens[5]);  //there is a stair/decline
            boolean dirt_sensor= Boolean.parseBoolean(tokens[6]); //whether there is dirt
            surfaceType surface_sensor=  surfaceType.valueOf(tokens[7]);  //kind of surface
            boolean charging_station=  Boolean.parseBoolean(tokens[8]); //whether there is a charging station
            int dirt_level=  Integer.parseInt(tokens[9]); //whether there is a charging station

            CleanSweepNode tmp = new CleanSweepNode(Integer.parseInt(id), n_sensor, s_sensor, e_sensor, w_sensor, dirt_sensor, surface_sensor, charging_station, dirt_level);

            this.SetValues(currCol, currRow, tmp);

            //increment current col and row

            // check if at end of current col
            if (currCol == this.Width-1) {
                // check if also at end of row, if so, we are at end of matrix, break
                if (currRow == this.Height-1) {
                    break;
                }
                currCol = 0;
                currRow++;
            }
            else {
                currCol++;
            }
        }
    }

    public CleanSweepNode GetNodeFromXY(int x, int y) {
        RowIterator = Reference;
   
        for (int I = 0; I < y; ++I) {
            RowIterator = RowIterator.southNode;
        }
    
        for (int J = 0; J < x; ++J) {
            RowIterator = RowIterator.eastNode;
        }
    
        return RowIterator;
    }
    
    public CleanSweepNode GetNodeFromNodeAndDirection(CleanSweepNode node, NavigationOptionsEnum direction) {
		RowIterator = Reference;

        

        // loop through linkedlist until we find node we passed in args
		for (int y = 0; y < this.Height; ++y) {
            for (int x = 0; x < this.Width; ++x) {
				CleanSweepNode tmp = GetNodeFromXY(x, y);

                // When node is found return new node in direction
				if(tmp.equals(node)) {
                    for (int i = 0; i < 4; i++) {
                        switch (direction){
                            case EAST:
                                return tmp.eastNode;
                            case SOUTH:
                                return tmp.southNode;
                            case WEST:
                                return tmp.westNode;
                            case NORTH:
                                return tmp.northNode;
                            default:
                                break;
                        }
                    }
				}
			}
		}
		return null;
    }
  

    public void SetValues(int X, int Y, CleanSweepNode copyReference) {
        CleanSweepNode iterator = Reference;
       
        for (int I = 0; I < Y; ++I) {
            iterator = iterator.southNode;
        }
       
        for (int J = 0; J < X; ++J) {
            iterator = iterator.eastNode;
        }
       
		// this is probably a bad way of doing this
		iterator.id = copyReference.id;
        iterator.northEdge = copyReference.northEdge;
		iterator.eastEdge = copyReference.eastEdge;
		iterator.southEdge = copyReference.southEdge;
		iterator.westEdge = copyReference.westEdge;

		iterator.surface = copyReference.surface;
		iterator.dirt = copyReference.dirt;
		iterator.obstacle = copyReference.obstacle;
        iterator.isChargingStation = copyReference.isChargingStation;

	}

    public int GetWidth() {
        return this.Width;
    }
   
    public int GetHeight() {
        return this.Height;
    }
    
}