package se459.extremers.cleanSweepFloorPlan;
import floorplan.*;

public class Tuple {
    public final CleanSweepNode node;
    public final NavigationOptionsEnum dir;
    public Tuple(CleanSweepNode node, NavigationOptionsEnum dir) {
        this.node = node;
        this.dir = dir;
    }

    @Override
    public boolean equals(Object tmp) {
        return (this.node.getId() == ((Tuple)tmp).node.getId() && this.dir == ((Tuple)tmp).dir);
    }
    
}
