package se459.extremers.simulator;

public enum PathOptionsEnum {
    UNKNOWN, 
    OPEN,
    OBSTACLE,
    STAIRS;

    public static PathOptionsEnum getEnumValue(String string) {
        PathOptionsEnum result;
        switch (string) {
            case "OPEN":
                result  = PathOptionsEnum.OPEN;
                break;
            
            case "OBSTACLE":
                result  = PathOptionsEnum.OBSTACLE;
                break;
            case "STAIRS":
                result  = PathOptionsEnum.STAIRS;
                break;
        
            default:
             result  = PathOptionsEnum.UNKNOWN;
             break;
        }
        
        return result;
    }
}