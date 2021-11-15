package se459.extremers.jarmoveout;

public enum NavigationOptionsEnum {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static NavigationOptionsEnum RotateDirection(NavigationOptionsEnum direction) {
         // if direction from previous traversal is blocked, change direction to next in order (E, S, W, N)
         switch (direction) {
            case EAST:
                return  NavigationOptionsEnum.SOUTH;
            case SOUTH:
                return  NavigationOptionsEnum.WEST;
            case WEST:
                return  NavigationOptionsEnum.NORTH;
            case NORTH:
                return  NavigationOptionsEnum.EAST;
            default:
                return null;
        }
    }
}
