package se459.extremers.simulator;

import java.io.FileNotFoundException;

public class SimulatorMain {

    public static void main(String args[]) throws FileNotFoundException {

       // CleanSweep.simulateFromFile("./src/test/file.csv");
        CleanSweep cleanSweep = new CleanSweep();
        cleanSweep.simulateFromFile("./src/test/file.csv");



    }


}
