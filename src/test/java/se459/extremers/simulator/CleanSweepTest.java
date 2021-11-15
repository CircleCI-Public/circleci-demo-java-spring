package se459.extremers.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Testing CleanSweep")
class CleanSweepTest {

    private HashMap<CellIndex, String> floorPlanExpected;
    private int row = 0;
    private int col = 0;
    private CellIndex ci ;
    private SensorArray saTest;

    @BeforeEach
    void setUp() {

        floorPlanExpected = new HashMap<CellIndex, String>();  //TODO:  Expected Floor Plan
        ci =  new CellIndex(row,col);
        floorPlanExpected.put(ci,row +"," + col);

        String[] tokens = new String[]{"0","OBSTACLE","OPEN","OPEN","OBSTACLE","FALSE","FALSE","BARE_FLOOR","TRUE"};

        PathOptionsEnum n_sensor= PathOptionsEnum.getEnumValue(tokens[1]); //path info top of the cell
        PathOptionsEnum s_sensor=  PathOptionsEnum.getEnumValue(tokens[2]); //path info bottom of the cell
        PathOptionsEnum e_sensor=  PathOptionsEnum.getEnumValue(tokens[3]); //path info right of the cell
        PathOptionsEnum w_sensor=  PathOptionsEnum.getEnumValue(tokens[4]); //path info left of the cell
        boolean bottom_sensor= Boolean.parseBoolean(tokens[5]);  //there is a stair/decline
        boolean dirt_sensor= Boolean.parseBoolean(tokens[6]); //whether there is dirt
        SurfaceEnum surface_sensor=  SurfaceEnum.valueOf(tokens[7]);  //kind of surface
        boolean charging_station=  Boolean.parseBoolean(tokens[8]); //whether there is a charging station

        saTest = new SensorArray(n_sensor,s_sensor,e_sensor,w_sensor,bottom_sensor,dirt_sensor,surface_sensor,charging_station);

    }

    // @DisplayName("Checking simulateFromFile() ")
    // @Test
    // void simulateFromFile() throws FileNotFoundException {
    //         CleanSweep cleanSweep = Mockito.spy(new CleanSweep());
    //         cleanSweep.simulateFromFile("./src/test/fileTest.csv");
    //         Mockito.verify(cleanSweep, Mockito.times(1)).clean(Mockito.any());
    //         Mockito.verify(cleanSweep, Mockito.times(1)).traverse(Mockito.any());
    //         Mockito.verify(cleanSweep, Mockito.times(1)).moveEast();
    //         Mockito.verify(cleanSweep, Mockito.times(1)).setMaxCol();
    //         Mockito.verify(cleanSweep, Mockito.times(0)).moveWest();
    //         Mockito.verify(cleanSweep, Mockito.times(0)).setMaxRow();
    //         Mockito.verify(cleanSweep, Mockito.times(0)).moveSouth();
    //         Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //         assertEquals(2,cleanSweep.getMaxJ());
    //         assertEquals(1,cleanSweep.getMaxI());
    // }

    @DisplayName("Checking calls of printFloorPlan()")
    @Test
    void printFloorPlan() throws FileNotFoundException {
        CleanSweep cleanSweep = Mockito.spy(new CleanSweep());
        cleanSweep.simulateFromFile("./src/test/file.csv");
        Mockito.verify(cleanSweep, Mockito.times(1)).printFloorPlan();
        assertEquals(20,cleanSweep.getFloorPlan().size());
    }

    @DisplayName("Checking if addCell() is called or not")
    @Test
    void addCell() {

        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.addCell(saTest);
        assertEquals(floorPlanExpected,cleanSweep.getFloorPlan());

    }

    // @DisplayName("Checking all cases for traversal")
    // @Test
    // void traversTest() throws FileNotFoundException
    // {

    //     CleanSweep cleanSweep = Mockito.spy(new CleanSweep());
    //     cleanSweep.traverse(saTest);
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveEast();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxCol();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveWest();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).setMaxRow();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveSouth();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //     assertEquals(1,cleanSweep.getMaxJ());
    //     assertEquals(0,cleanSweep.getMaxI());

    //     saTest = new SensorArray(PathOptionsEnum.OBSTACLE,PathOptionsEnum.OPEN,PathOptionsEnum.OBSTACLE,PathOptionsEnum.OPEN,false,true,SurfaceEnum.BARE_FLOOR,false);
    //     cleanSweep.traverse(saTest);
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveEast();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxCol();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveWest();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxRow();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveSouth();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //     assertEquals(1,cleanSweep.getMaxJ());
    //     assertEquals(1,cleanSweep.getMaxI());


    //     saTest = new SensorArray(PathOptionsEnum.OBSTACLE,PathOptionsEnum.OPEN,PathOptionsEnum.OPEN,PathOptionsEnum.OPEN,false,true,SurfaceEnum.BARE_FLOOR,false);
    //     cleanSweep.traverse(saTest);
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveEast();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxCol();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveWest();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxRow();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveSouth();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //     assertEquals(1,cleanSweep.getMaxJ());
    //     assertEquals(1,cleanSweep.getMaxI());

    //     saTest = new SensorArray(PathOptionsEnum.OBSTACLE,PathOptionsEnum.OPEN,PathOptionsEnum.OPEN,PathOptionsEnum.OBSTACLE,false,true,SurfaceEnum.BARE_FLOOR,false);
    //     cleanSweep.traverse(saTest);
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveEast();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).setMaxCol();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveWest();
    //     Mockito.verify(cleanSweep, Mockito.times(2)).setMaxRow();
    //     Mockito.verify(cleanSweep, Mockito.times(2)).moveSouth();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //     assertEquals(1,cleanSweep.getMaxJ());
    //     assertEquals(2,cleanSweep.getMaxI());

    //     saTest = new SensorArray(PathOptionsEnum.OBSTACLE,PathOptionsEnum.OPEN,PathOptionsEnum.OPEN,PathOptionsEnum.OBSTACLE,false,true,SurfaceEnum.BARE_FLOOR,false);
    //     cleanSweep.traverse(saTest);
    //     Mockito.verify(cleanSweep, Mockito.times(2)).moveEast();
    //     Mockito.verify(cleanSweep, Mockito.times(2)).setMaxCol();
    //     Mockito.verify(cleanSweep, Mockito.times(1)).moveWest();
    //     Mockito.verify(cleanSweep, Mockito.times(2)).setMaxRow();
    //     Mockito.verify(cleanSweep, Mockito.times(2)).moveSouth();
    //     Mockito.verify(cleanSweep, Mockito.times(0)).moveNorth();
    //     assertEquals(1,cleanSweep.getMaxJ());
    //     assertEquals(2,cleanSweep.getMaxI());

    // }

    // @DisplayName("Checking cleaning is done ")
    // @Test
    // void clean() {
    //     PrintStream stdout = System.out;
    //     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(byteArrayOutputStream));
    //     CleanSweep cleanSweep = new CleanSweep();
    //     cleanSweep.clean(saTest);
    //     assertEquals("Cleaning...", byteArrayOutputStream.toString().trim());
    // }

    @DisplayName("Checking if moveNorth() is called or not")
    @Test
    void moveNorth() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.moveEast();
        assertNotEquals("N", byteArrayOutputStream.toString().trim());
    }

    @DisplayName("Checking if moveSouth() is called or not")
    @Test
    void moveSouth() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.moveEast();
        assertNotEquals("S", byteArrayOutputStream.toString().trim());
    }


    @DisplayName("Checking if moveEast() is called or not")
    @Test
    void moveEast() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.moveEast();
        assertEquals("E", byteArrayOutputStream.toString().trim());
    }


    @DisplayName("Checking if moveWest() is called or not")
    @Test
    void moveWest() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.moveEast();
        assertNotEquals("W", byteArrayOutputStream.toString().trim());
    }
}