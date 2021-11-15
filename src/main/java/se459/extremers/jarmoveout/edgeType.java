package se459.extremers.jarmoveout;

public enum edgeType {
	UNKNOWN, 
    OPEN,
    OBSTACLE;

    public static edgeType getEnumValue(String string) {
        edgeType result;
        switch (string) {
            case "OPEN":
                result  = edgeType.OPEN;
                break;
            
            case "OBSTACLE":
                result  = edgeType.OBSTACLE;
                break;
            case "STAIRS":
                result  = edgeType.OBSTACLE;
                break;
            default:
             result  = edgeType.UNKNOWN;
             break;
        }
        
        return result;
    }
}
