package se459.extremers.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class CleanSweep {
    private static int row = 0; //initial or current row
    private static int col = 0; //initial or current col
    private static int maxI= 0; //max number of rows
    private static int maxJ= 0; //max number of cols
    private  HashMap<CellIndex, String> floorPlan = new HashMap<CellIndex, String>();  //TODO:  Replace String for BuiltMapNode or SensorArray

    static float batteryCharge = 250f;
    static int dirtCapacity = 50;

    private static NavigationOptionsEnum direction = NavigationOptionsEnum.EAST;
    private static SensorArray sa;


    public  void main(String args[]) throws FileNotFoundException {

        simulateFromFile("./src/test/file.csv");
    }

    
    public  void simulateFromFile(String filepath) throws FileNotFoundException {

        SensorArray sa = new SensorArray();

        Scanner scanner = new Scanner(new File(filepath));
        
        while(scanner.hasNext()){
            String[] tokens = scanner.nextLine().split(",");
            String id = tokens[0];
            //SENSOR ARRAY VALUES
            PathOptionsEnum n_sensor= PathOptionsEnum.getEnumValue(tokens[1]); //path info top of the cell
            PathOptionsEnum s_sensor=  PathOptionsEnum.getEnumValue(tokens[2]); //path info bottom of the cell
            PathOptionsEnum e_sensor=  PathOptionsEnum.getEnumValue(tokens[3]); //path info right of the cell
            PathOptionsEnum w_sensor=  PathOptionsEnum.getEnumValue(tokens[4]); //path info left of the cell
            boolean bottom_sensor= Boolean.parseBoolean(tokens[5]);  //there is a stair/decline
            boolean dirt_sensor= Boolean.parseBoolean(tokens[6]); //whether there is dirt
            SurfaceEnum surface_sensor=  SurfaceEnum.valueOf(tokens[7]);  //kind of surface
            boolean charging_station=  Boolean.parseBoolean(tokens[8]); //whether there is a charging station

         
            // Because the sensor array is unaware of the floor type it is moving to, the battery reduction for traversal has to be done here
            if (sa.surface_sensor != null) {
                // Manage power for traversal: remove average of last surface and current surface
                RemoveCharge((sa.surface_sensor.getUnits() + surface_sensor.getUnits())/2f);
                System.out.println("Capacity after traversal = " + GetCharge());
                System.out.println( );
            }

            System.out.print(id + "- ");
            System.out.print(n_sensor.toString() + " | ");
            System.out.print(s_sensor.toString() + " | ");
            System.out.print(e_sensor.toString() + " | ");
            System.out.print(w_sensor.toString() + " | ");
            System.out.print(bottom_sensor + " | ");
            System.out.print(dirt_sensor + " | ");
            System.out.print(surface_sensor.toString() + " | ");
            System.out.println(charging_station); 


            sa = new SensorArray(n_sensor,s_sensor,e_sensor,w_sensor,bottom_sensor,dirt_sensor,surface_sensor,charging_station);
            addCell(sa);
            clean(sa);
            traverse(sa);
            System.out.println( );

            
         }
         scanner.close();
         maxI++;
         maxJ++;
         System.out.println( "--------------------------------------");
         System.out.println( floorPlan );
         System.out.println( "--------------------------------------");
         printFloorPlan();


    }


    public  void printFloorPlan() {
        System.out.println("MAX ROW: " + maxI);
        System.out.println("MAX COL: " + maxJ);
        CellIndex key = null;
        String value = null;
        for (int i = 0; i < maxI; i++) {
            for (int j = 0; j < maxJ; j++) {
                key = new CellIndex(i,j);
                value = (String)floorPlan.get(key);
                System.out.print(value);
                System.out.print(" | ");
            }
            System.out.println("");
        }
    }


    public HashMap<CellIndex, String> getFloorPlan() {
        return floorPlan;
    }

    public  void addCell(SensorArray sa) {

        //String key = row +"," + col;
        CellIndex ci = new CellIndex(row, col);
        floorPlan.put(ci, row +"," + col);

         //TODO:  Replace String for BuiltMapNode
    }

  
    public static void traverse(SensorArray sa) {
        System.out.println("Traversing...");

        /* 
         This loop checks if the direction we last moved in is still open. If it is, continute in that direction.
         If it's not open, change direction to the next in the order (E, S, W, N) and check there. Continue until open 
         direction is found
        */
        for (int i = 0; i < 2; i++) {

            boolean foundDirection = false;

            // check if direction from previous traversal is open again
            switch (direction) {
                case EAST:
                    if (sa.e_sensor.equals(PathOptionsEnum.OPEN)) {
                        foundDirection = true;
                    }
                    break;
                case SOUTH:
                    if (sa.s_sensor.equals(PathOptionsEnum.OPEN)) {
                        foundDirection = true;
                    }
                    break;
                case WEST:
                    if (sa.w_sensor.equals(PathOptionsEnum.OPEN)) {
                        foundDirection = true;
                    }
                    break;
                case NORTH:
                    if (sa.n_sensor.equals(PathOptionsEnum.OPEN)) {
                        foundDirection = true;
                    }
                    break;
            
                default:
                    break;
            }
            if (foundDirection) {
                break;
            }

            // if direction from previous traversal is blocked, change direction to next in order (E, S, W, N)
            switch (direction) {
                case EAST:
                    direction = NavigationOptionsEnum.SOUTH;
                    break;
                case SOUTH:
                    direction = NavigationOptionsEnum.WEST;
                    break;
                case WEST:
                    direction = NavigationOptionsEnum.NORTH;
                    break;
                case NORTH:
                    direction = NavigationOptionsEnum.EAST;
                    break;
                default:
                    break;
            }

        }

        switch (direction) {
            case EAST:
                moveEast();
                break;
            case SOUTH:
                moveSouth();
                break;
            case WEST:
                moveWest();
                break;
            case NORTH:
                moveNorth();
                break;
            default:
                break;
        }
    }

   /*  private  boolean visited(Integer i, Integer j) {
        
        return floorPlan.containsKey((i * maxColumns) + j);
    } */

    public  void clean(SensorArray sa) {
        
        System.out.println("Capacity before clean = " + GetCharge());
        System.out.println("Cleaning...");

        // remove battery charge depending on current floor type
        RemoveCharge(sa.surface_sensor.getUnits());

        System.out.println("Capacity after clean = " + GetCharge());
    }

    public static void moveNorth() {
        System.out.println("N");
        //CleanSweep.
                row--;
    }
    public static void moveSouth() {
        System.out.println("S");
        //CleanSweep.
                row++;
        setMaxRow();
    }

    public static void setMaxRow() {
        if (row > maxI) maxI = row;
    }

    public static void moveEast() {
        System.out.println("E");
        //CleanSweep.
                col++;
        setMaxCol();
        
    }
    public static void setMaxCol() {
        if (col > maxJ) maxJ = col;
    }

    public static void moveWest(){
        System.out.println("W");
        //CleanSweep.
                col--;
    }

    public static int getMaxI() {
        return maxI;
    }

    public static  int getMaxJ() {
        return maxJ;
    }
    
    private static float GetCharge() {
        return batteryCharge;
    }

    private static void RemoveCharge(float reductionAmount) {
        batteryCharge -= reductionAmount;
    }

    private static void RemoveCapacity(int reductionAmount) {
        dirtCapacity -= reductionAmount;
    }

}
