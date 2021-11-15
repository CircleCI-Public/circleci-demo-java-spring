package se459.extremers.controlpanel;

public class Model {
    
  
    public Model() {
        System.out.println("Model created - here goes the Vacuum");
    }
    
    
    

    public void start() {
        System.out.println("MODEL - Start");
    }
    
    public void shutdown() {
        System.out.println("MODEL - Shutdown");
      
    }
    public void resume() {
        System.out.println("MODEL - Resume");
    }
    
    public void pause() {
        System.out.println("MODEL - Pause");
    }

    public void setFilePath(String path) {
        System.out.println("MODEL - Path File: ");
        System.out.println(">>: " + path);
    }




    public void setFloorPlan() {
    }
    
       
}