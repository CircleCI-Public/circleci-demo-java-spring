package se459.extremers.jarmoveout;

import java.io.FileNotFoundException;

//import se459.extremers.cleanSweepFloorPlan.FloorPlanExternal;

public class CleanSweepSimulator {
    FloorPlanExternal extFloorPlanCopy;

    public  CleanSweepSimulator(FloorPlanExternal extFloorPlan){
        this.extFloorPlanCopy = extFloorPlan;
    }
    public  CleanSweepSimulator(int x, int y, String path) throws FileNotFoundException{
        this.extFloorPlanCopy = new FloorPlanExternal(x, y, path);
    }

    public FloorPlanExternal getExternalFloorPlan() {
        return this.extFloorPlanCopy;
    }
    
}
