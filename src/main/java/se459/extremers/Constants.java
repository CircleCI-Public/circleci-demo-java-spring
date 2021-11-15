package se459.extremers;



import se459.extremers.cleanSweepFloorPlan.ModeOptions;
import floorplan.NavigationOptionsEnum;
import floorplan.Point;

public class Constants {
    public static final float MAX_POWER_CHARGE = 250f;
    public static final int MAX_DIRT_CAPACITY = 50;
    public static final int CELL_REACHAABILITY = 2;  
    public static final NavigationOptionsEnum DEF_DIRECTION = NavigationOptionsEnum.EAST;
    public static final Point DEF_INITIAL_POSITION = new Point(0,0);
    public static final ModeOptions DEF_MODE = ModeOptions.CLEANING;    
}
