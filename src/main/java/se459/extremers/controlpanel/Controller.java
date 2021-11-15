package se459.extremers.controlpanel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;


public class Controller implements ActionListener{

    private View GUI;
    private boolean isItCleaning, isPathSet;
    private Model robot;

      

    /**
     * Default constructor. 
     */
    public Controller() throws Exception {
    
        this.GUI = new View();
        this.robot = new Model();
        isItCleaning = false;
        isPathSet = false;

        addActionListeners();
        initializeVaccum();
      
    }
    
  
    

    private void initializeVaccum() throws Exception {
        System.out.println("Initializing... ");
        System.out.println("  - Cleaning? " + isItCleaning);
        System.out.println("  - Path set? " + isPathSet);
     
    }

     private void addActionListeners() {
      
        GUI.getPauseButton().addActionListener(this);
        GUI.getStartButton().addActionListener(this);
        GUI.getShutdownButton().addActionListener(this);
        GUI.getResumeButton().addActionListener(this);
        GUI.getChooseFPButton().addActionListener(this);
       
    }
    
    /**
     * Checks if a button is clicked, and executes the appropriate method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
            
        if ((((JButton) e.getSource()) == GUI.getPauseButton())) {
            try {
                pause();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
   
        if ((((JButton) e.getSource()) == GUI.getStartButton())) {
            try {
                start();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        if ((((JButton) e.getSource()) == GUI.getShutdownButton())) {
            try {
                shutdown();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        if ((((JButton) e.getSource()) == GUI.getResumeButton())) {
            try {
                resume();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        if ((((JButton) e.getSource()) == GUI.getChooseFPButton())) {
            try {
                setFilePath();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    /**
     * Starts the cleaning.
     */
    private void start() {
        System.out.println("START");        
        robot.start();
        isItCleaning = true;
     
    }

    /**
     * Shutdown.
     */
    private void shutdown() {
        System.out.println("SHUTDOWN"); 
        robot.shutdown();
        isItCleaning = false;
    }

    /**
     * Pauses the cleaning.
     */
    private void pause() {
        System.out.println("PAUSE"); 
        robot.pause();
        isItCleaning = false;
    }

     /**
     * Resumes the cleaning.
     */
    private void resume() {
        System.out.println("RESUME");        
        robot.resume();
        isItCleaning = true;
     
    }

    /**
     * Set the Floor Map File Path.
     */
    private void setFilePath() {
        System.out.println("Floor Plan");  
        String path;  
        
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jfc.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "CSV (Comma delimited) (*.csv)";
            }
         
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".csv");
                }
            }
        });

		int returnValue = jfc.showOpenDialog(null);
		

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
			System.out.println(path);
            robot.setFilePath(path);
		}


        
        isPathSet = true;
     
    }
    
  
   
}