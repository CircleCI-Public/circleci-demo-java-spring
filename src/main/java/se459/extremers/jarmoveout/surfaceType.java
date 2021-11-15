package se459.extremers.jarmoveout;

public enum surfaceType {
    BARE_FLOOR(1),
    LOW_PILE_CARPET(2),
    HIGH_PILE_CARPET(3);

    private int c_units;

    public int getUnits(){
        return this.c_units;
    }

    private surfaceType(int u){
        this.c_units = u;
    }
}
